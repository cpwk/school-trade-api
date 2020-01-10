package cn.jianchen.com.trade.api.coupon.service;


import cn.jianchen.com.trade.api.coupon.model.UserCoupon;
import cn.jianchen.com.trade.api.coupon.qo.UserCouponQo;
import cn.jianchen.com.trade.common.exception.ServiceException;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface IUserCouponService {

    void save(UserCoupon userCoupon) throws ServiceException;

    void remove(Integer id) throws Exception;

    void used(Integer id) throws Exception;

    UserCoupon coupon(Integer id);

    List<UserCoupon> userCoupons(UserCouponQo qo);

    void checkCoupon() throws Exception;

}
