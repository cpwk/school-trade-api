package cn.jianchen.com.trade.api.banner.controller;


import cn.jianchen.com.trade.api.admin.authority.AdminPermission;
import cn.jianchen.com.trade.api.banner.model.Banner;
import cn.jianchen.com.trade.api.banner.qo.BannerQo;
import cn.jianchen.com.trade.api.banner.service.IBannerService;
import cn.jianchen.com.trade.common.authority.AdminType;
import cn.jianchen.com.trade.common.authority.RequiredPermission;
import cn.jianchen.com.trade.common.controller.BaseController;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Controller
@RequestMapping("/adm/banner")
public class AdmBannerController extends BaseController {

    @Autowired
    private IBannerService bannerService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView save(String banner) throws Exception {
        bannerService.save(parseModel(banner, new Banner()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView remove(Integer id) throws Exception {
        bannerService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/banner")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView banner(Integer id) throws Exception {
        return feedback(bannerService.banner(id));
    }

    @RequestMapping(value = "/banners")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView banners(String bannerQo) throws Exception {
        return feedback(bannerService.banners(parseModel(bannerQo, new BannerQo()), true));
    }

    @RequestMapping(value = "/outsome")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView outSome(String ids) throws Exception {

        bannerService.outSome(JSON.parseArray(ids, Integer.class));
        return feedback();
    }

    @RequestMapping(value = "/putsome")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.BANNER_EDIT)
    public ModelAndView putSome(String ids) throws Exception {
        bannerService.putSome(JSON.parseArray(ids, Integer.class));
        return feedback();
    }
}