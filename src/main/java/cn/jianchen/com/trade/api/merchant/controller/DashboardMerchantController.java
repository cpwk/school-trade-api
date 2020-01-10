package cn.jianchen.com.trade.api.merchant.controller;

import cn.jianchen.com.trade.api.merchant.model.MerchantAdmin;
import cn.jianchen.com.trade.api.merchant.qo.MerchantAdminQo;
import cn.jianchen.com.trade.api.merchant.qo.MerchantAdminSessionQo;
import cn.jianchen.com.trade.api.merchant.service.IMerchantAdminService;
import cn.jianchen.com.trade.common.authority.AdminType;
import cn.jianchen.com.trade.common.authority.RequiredPermission;
import cn.jianchen.com.trade.common.controller.BaseController;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/dashboard/merchant")
public class DashboardMerchantController extends BaseController {

    @Autowired
    private IMerchantAdminService merchantAdminService;

    @RequiredPermission(adminType = AdminType.NONE)
    @RequestMapping(value = "/admins")
    public ModelAndView admins() throws ServiceException {
        return feedback(merchantAdminService.admins(new MerchantAdminQo(), false));
    }

    @RequestMapping(value = "/admin")
    @RequiredPermission(adminType = AdminType.NONE)

    public ModelAndView admin(Integer id) throws ServiceException {
        return feedback(merchantAdminService.admin(id, false));
    }

    @RequestMapping(value = "/adminSessions")
    @RequiredPermission(adminType = AdminType.NONE)

    public ModelAndView adminSessions(String merchantAdminSessionQo) {
        return feedback(merchantAdminService.adminSessions(parseModel(merchantAdminSessionQo, new MerchantAdminSessionQo())));
    }

    @RequestMapping(value = "/remove_admin")
    @RequiredPermission(adminType = AdminType.NONE)

    public ModelAndView removeAdmin(Integer id) {
        merchantAdminService.removeAdmin(id);
        return feedback(null);
    }

    @RequestMapping(value = "/save_admin")
    @RequiredPermission(adminType = AdminType.NONE)

    public ModelAndView saveAdmin(String admin) {
        merchantAdminService.saveAdmin(parseModel(admin, new MerchantAdmin()), false);
        return feedback(null);
    }

    @RequestMapping(value = "/admin_status")
    @RequiredPermission(adminType = AdminType.NONE)

    public ModelAndView adminStatus(Integer id, Byte status) throws ServiceException {
        merchantAdminService.adminStatus(id, status);
        return feedback(null);
    }

    @RequestMapping(value = "/update_my_password")
    @RequiredPermission(adminType = AdminType.NONE)

    public ModelAndView updateMyPassword(String password, String oldPassword) {
        merchantAdminService.updateMyPassword(password, oldPassword);
        return feedback(null);
    }

    @RequestMapping(value = "/update_password")
    @RequiredPermission(adminType = AdminType.NONE)

    public ModelAndView updatePassword(Integer id, String oldPassword) {
        merchantAdminService.updatePassword(id, oldPassword);
        return feedback(null);
    }

    @RequestMapping(value = "/signin")
    public ModelAndView signin(String admin) {
        return feedback(merchantAdminService.signin(parseModel(admin, new MerchantAdmin()), getRemoteAddress()));
    }

    @RequestMapping(value = "/profile")
    public ModelAndView profile() {
        return feedback(merchantAdminService.profile());
    }


}
