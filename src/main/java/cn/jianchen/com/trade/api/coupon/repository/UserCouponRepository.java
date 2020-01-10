package cn.jianchen.com.trade.api.coupon.repository;


import cn.jianchen.com.trade.api.coupon.model.UserCoupon;
import cn.jianchen.com.trade.common.reposiotry.BaseRepository;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface UserCouponRepository extends BaseRepository<UserCoupon, Integer> {

    UserCoupon findByCouponId(Integer couponId);

    List<UserCoupon> findByStatusAndExpirAtBefore(Byte status, Long expiredAt);

}