package cn.jianchen.com.trade.common.code.controller;


import cn.jianchen.com.trade.common.authority.AdminType;
import cn.jianchen.com.trade.common.authority.RequiredPermission;
import cn.jianchen.com.trade.common.code.service.SmsService;
import cn.jianchen.com.trade.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * <p>
 * 类名称:SmsController
 * 类描述:短信验证码接口
 **/

@Controller
@RequestMapping("/support/sms")
public class SmsController extends BaseController {
    @Autowired
    private SmsService smsService;

    @RequiredPermission(adminType = AdminType.NONE)
    @RequestMapping("/phone_vcode")
    public ModelAndView phone_vcode(String key, String mobile) throws Exception {
        smsService.sendVcode(key, mobile);
        return feedback("success");
    }
}
