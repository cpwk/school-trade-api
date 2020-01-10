package cn.jianchen.com.trade.api.home;


import cn.jianchen.com.trade.api.banner.qo.BannerQo;
import cn.jianchen.com.trade.api.banner.service.IBannerService;
import cn.jianchen.com.trade.api.comment.model.Comment;
import cn.jianchen.com.trade.api.comment.service.CommentService;
import cn.jianchen.com.trade.api.coupon.qo.CouponQo;
import cn.jianchen.com.trade.api.coupon.service.ICouponService;
import cn.jianchen.com.trade.api.product.qo.ProductQo;
import cn.jianchen.com.trade.api.product.service.IProductService;
import cn.jianchen.com.trade.api.sort.service.ISortService;
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
@RequestMapping("/usr/home")
public class HomeController extends BaseController {

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICouponService couponService;

    @Autowired
    private ISortService sortService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/banners")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView banners(String bannerQo) throws Exception {
        return feedback(bannerService.banners(parseModel(bannerQo, new BannerQo()), false));
    }

    @RequestMapping(value = "/products_home")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView products_home(String productQo) throws Exception {
        return feedback(productService.products_home(parseModel(productQo, new ProductQo())));
    }

    @RequestMapping(value = "/products_by_type")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView products_by_type(String productQo) throws Exception {
        return feedback(productService.products_by_type(parseModel(productQo, new ProductQo())));
    }

    @RequestMapping(value = "/coupons")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView coupons(String couponQo) throws Exception {
        return feedback(couponService.coupons(parseModel(couponQo, new CouponQo())));
    }

    @RequestMapping(value = "/sorts")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView sorts() {
        return feedback(sortService.sorts(true));
    }

    @RequiredPermission(adminType = AdminType.USER)
    @RequestMapping(value = "/saveComment")
    public ModelAndView save(String comment) throws Exception {
        commentService.save(parseModel(comment, new Comment()));
        return feedback(null);
    }

    @RequestMapping(value = "/removeComment")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView remove(Integer id) throws Exception {
        commentService.remove(id);
        return feedback(null);
    }

}