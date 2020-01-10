package cn.jianchen.com.trade.api.coupon.service;


import cn.jianchen.com.trade.api.coupon.model.Coupon;
import cn.jianchen.com.trade.api.coupon.qo.CouponQo;
import cn.jianchen.com.trade.common.exception.ServiceException;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface ICouponService {

    void save(Coupon coupon) throws ServiceException;

    void remove(Integer id) throws Exception;

    Coupon coupon(Integer id);

    List<Coupon> coupons(CouponQo qo);

}
