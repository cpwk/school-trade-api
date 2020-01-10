package cn.jianchen.com.trade.api.merchant.controller;

import cn.jianchen.com.trade.api.merchant.qo.MerchantUserQo;
import cn.jianchen.com.trade.api.merchant.service.IMerchantUserService;
import cn.jianchen.com.trade.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/usr/merchant")
public class UserMerchantController extends BaseController {

    @Autowired
    private IMerchantUserService merchantUserService;

    @RequestMapping(value = "/merchants")
    public ModelAndView merchants(String merchantUserQo) {
        return feedback(merchantUserService.merchants(parseModel(merchantUserQo, new MerchantUserQo())));
    }
}
