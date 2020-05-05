package cn.jianchen.com.trade.api.coupon.service;


import cn.jianchen.com.trade.api.coupon.model.UserCoupon;
import cn.jianchen.com.trade.api.coupon.qo.UserCouponQo;
import cn.jianchen.com.trade.api.coupon.repository.UserCouponRepository;
import cn.jianchen.com.trade.api.user.model.User;
import cn.jianchen.com.trade.api.user.service.IUserService;
import cn.jianchen.com.trade.common.context.Contexts;
import cn.jianchen.com.trade.common.exception.ArgumentServiceException;
import cn.jianchen.com.trade.common.exception.ServiceException;
import cn.jianchen.com.trade.common.mail.MailHelper;
import cn.jianchen.com.trade.common.mail.MailService;
import cn.jianchen.com.trade.common.task.ApiTask;
import cn.jianchen.com.trade.common.task.TaskService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.jianchen.com.trade.common.entity.Constants.*;
import static cn.jianchen.com.trade.common.exception.ErrorCode.ERR_DATA_NOT_FOUND;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Service
public class UserCouponService implements IUserCouponService {
    @Autowired
    private UserCouponRepository userCouponRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private IUserService userService;
    @Autowired
    private MailService mailService;

    public UserCoupon getById(Integer userCouponId) throws ServiceException {

        UserCoupon userCoupon = userCouponRepository.findById(userCouponId).orElse(null);

        if (userCoupon == null) {
            throw new ServiceException(ERR_DATA_NOT_FOUND);
        }

        return userCoupon;
    }

    @Override
    public void save(UserCoupon userCoupon) throws ServiceException {

        UserCoupon exist = userCouponRepository.findByCouponId(userCoupon.getCouponId());

        if (exist != null) {
            throw new ArgumentServiceException("已经领取过此优惠券");
        }

        userCoupon.setUserId(Contexts.requestUserId());

        userCoupon.setGetAt(System.currentTimeMillis());

        userCoupon.setExpirAt((couponService.getById(userCoupon.getCouponId()).getDuration()) * 86400000 + System.currentTimeMillis());

        userCouponRepository.save(userCoupon);
    }

    @Override
    public void remove(Integer id) throws Exception {

        UserCoupon userCoupon = getById(id);

        userCoupon.setStatus(EXPIRED);

        userCouponRepository.save(userCoupon);
    }

    @Override
    public void used(Integer id) throws Exception {

        UserCoupon userCoupon = getById(id);

        userCoupon.setStatus(USER_ED);

        userCouponRepository.save(userCoupon);
    }

    @Override
    public UserCoupon coupon(Integer id) {
        return getById(id);
    }

    @Override
    public List<UserCoupon> userCoupons(UserCouponQo qo) {
        return userCouponRepository.findAll(qo);
    }

    @Override
    public void checkCoupon() throws Exception {
        long expiredAt = System.currentTimeMillis() + DateUtils.MILLIS_PER_DAY;

        List<UserCoupon> couponUsers = userCouponRepository.findByStatusAndExpirAtBefore(WAIT_USER, expiredAt);

        Map<Integer, List<UserCoupon>> map = new HashMap<>(couponUsers.size());

        for (UserCoupon couponUser : couponUsers) {

            Integer userId = couponUser.getUserId();

            if (map.containsKey(userId)) {
                List<UserCoupon> list = map.get(userId);
                list.add(couponUser);
                map.put(userId, list);
            } else {
                List<UserCoupon> list = new ArrayList<>(1);
                list.add(couponUser);
                map.put(userId, list);
            }

        }
        List<Integer> ids = new ArrayList<>(map.keySet().size());
        for (Integer userId : map.keySet()) {
            ids.add(userId);
        }
        List<User> users = userService.findByIdIn(ids);

        for (User user : users) {
            List<UserCoupon> list = map.get(user.getId());
            taskService.addTask(new CouponEmail(user, list.size()));
        }
    }

    private class CouponEmail extends ApiTask {
        private User user;
        private int count;

        public CouponEmail(User user, int count) {
            super();
            this.user = user;
            this.count = count;
        }

        @Override
        protected void doApiWork() {
            MailHelper.MailInfo mail = new MailHelper.MailInfo();
            mail.setToAddress(user.getEmail());
            mail.setSubject("剑陈商城");
            mail.setContent("尊敬的用户：您有 " + count + " 张优惠券即将过期，请尽快使用!");
            mailService.send(mail);
        }
    }


}