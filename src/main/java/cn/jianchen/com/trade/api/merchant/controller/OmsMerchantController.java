package cn.jianchen.com.trade.api.merchant.controller;

import cn.jianchen.com.trade.api.merchant.model.Merchant;
import cn.jianchen.com.trade.api.merchant.model.MerchantAdmin;
import cn.jianchen.com.trade.api.merchant.model.MerchantSetting;
import cn.jianchen.com.trade.api.merchant.qo.MerchantQo;
import cn.jianchen.com.trade.api.merchant.qo.MerchantWrapOption;
import cn.jianchen.com.trade.api.merchant.service.IMerchantAdminService;
import cn.jianchen.com.trade.api.merchant.service.IMerchantService;
import cn.jianchen.com.trade.common.authority.AdminType;
import cn.jianchen.com.trade.common.authority.RequiredPermission;
import cn.jianchen.com.trade.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/oms/merchant")
public class OmsMerchantController extends BaseController {

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private IMerchantAdminService merchantAdminService;

    @RequestMapping(value = "/create")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView save(String merchant, String merchantAdmin) {
        merchantService.create(parseModel(merchant, new Merchant()), parseModel(merchantAdmin, new MerchantAdmin()));
        return feedback();
    }

    @RequestMapping(value = "/update")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView update(String merchant) {
        merchantService.update(parseModel(merchant, new Merchant()));
        return feedback();
    }

    @RequestMapping(value = "/items")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView items(String merchantQo) {
        return feedback(merchantService.items(parseModel(merchantQo, new MerchantQo()), MerchantWrapOption.getDefaultInstance()));
    }

    @RequestMapping(value = "/item")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView item(Integer id) {
        return feedback(merchantService.merchant(id, true));
    }

    @RequestMapping(value = "/merchant_status")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView customerStatus(Integer id, Byte state) {
        merchantService.merchantState(id, state);
        return feedback(null);
    }

    @RequestMapping(value = "/renew")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView renew(Integer id, String duration) {
        merchantService.renew(id, duration);
        return feedback(null);
    }

    @RequestMapping(value = "/update_password")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView updatePassword(Integer id, String password) {
        merchantAdminService.updatePassword(id, password);
        return feedback(null);
    }

    @RequestMapping(value = "/update_setting")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView updateSetting(Integer merchantId, String setting) {
        merchantService.updateSetting(merchantId, parseModel(setting, new MerchantSetting()));
        return feedback();
    }

    @RequestMapping(value = "/create_admin")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView createAdmin(String admin) {
        merchantAdminService.saveAdmin(parseModel(admin, new MerchantAdmin()), true);
        return feedback(null);
    }

    @RequestMapping(value = "/search")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView search(String merchantQo) {
        return feedback(merchantService.items(parseModel(merchantQo, new MerchantQo()), MerchantWrapOption.getDefaultInstance()));
    }

}
