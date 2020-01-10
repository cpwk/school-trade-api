package cn.jianchen.com.trade.api.coupon.controller;


import cn.jianchen.com.trade.api.admin.authority.AdminPermission;
import cn.jianchen.com.trade.api.coupon.model.Coupon;
import cn.jianchen.com.trade.api.coupon.qo.CouponQo;
import cn.jianchen.com.trade.api.coupon.service.CouponService;
import cn.jianchen.com.trade.common.authority.AdminType;
import cn.jianchen.com.trade.common.authority.RequiredPermission;
import cn.jianchen.com.trade.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Controller
@RequestMapping("/adm/coupon")
public class CouponController extends BaseController {

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COUPON_EDIT)
    public ModelAndView save(String coupon) throws Exception {
        couponService.save(parseModel(coupon, new Coupon()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COUPON_EDIT)
    public ModelAndView remove(Integer id) throws Exception {
        couponService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/coupon")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COUPON_EDIT)
    public ModelAndView coupon(Integer id) throws Exception {
        return feedback(couponService.coupon(id));
    }

    @RequestMapping(value = "/coupons")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COUPON_EDIT)
    public ModelAndView coupons(String couponQo) throws Exception {
        return feedback(couponService.coupons(parseModel(couponQo, new CouponQo())));
    }

}
