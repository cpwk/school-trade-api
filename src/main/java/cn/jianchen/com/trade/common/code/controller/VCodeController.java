package cn.jianchen.com.trade.common.code.controller;


import cn.jianchen.com.trade.common.authority.AdminType;
import cn.jianchen.com.trade.common.authority.RequiredPermission;
import cn.jianchen.com.trade.common.code.model.VCode;
import cn.jianchen.com.trade.common.code.service.VCodeServiceImp;
import cn.jianchen.com.trade.common.controller.BaseController;
import cn.jianchen.com.trade.common.util.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * <p>
 * 类名称:VCodeController
 * 类描述:图片验证码接口
 **/

@Controller
@RequestMapping("/support/vcode")
public class VCodeController extends BaseController {

    @Autowired
    private VCodeServiceImp vCodeService;

    @RequiredPermission(adminType = AdminType.NONE)
    @RequestMapping(value = "/vcode")
    public void vcode(HttpServletRequest request, HttpServletResponse response, Long key) {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
        String randomString = randomValidateCode.getRandcode(request, response, key);//输出验证码图片方法
        vCodeService.saveVCode(new VCode(key, randomString));
    }
}
