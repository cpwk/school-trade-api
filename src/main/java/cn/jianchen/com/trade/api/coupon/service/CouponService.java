package cn.jianchen.com.trade.api.coupon.service;


import cn.jianchen.com.trade.api.coupon.model.Coupon;
import cn.jianchen.com.trade.api.coupon.qo.CouponQo;
import cn.jianchen.com.trade.api.coupon.repository.CouponRepository;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.jianchen.com.trade.common.exception.ErrorCode.ERR_DATA_NOT_FOUND;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/


@Service
public class CouponService implements ICouponService {

    @Autowired
    private CouponRepository couponRepository;

    public Coupon getById(Integer couponId) throws ServiceException {

        Coupon coupon = couponRepository.findById(couponId).orElse(null);

        if (coupon == null) {
            throw new ServiceException(ERR_DATA_NOT_FOUND);
        }

        return coupon;
    }

    @Override
    public void save(Coupon coupon) throws ServiceException {
        couponRepository.save(coupon);
    }

    @Override
    public void remove(Integer id) throws Exception {
        couponRepository.deleteById(id);
    }

    @Override
    public Coupon coupon(Integer id) {
        return getById(id);
    }

    @Override
    public List<Coupon> coupons(CouponQo qo) {
        return couponRepository.findAll(qo);
    }
}