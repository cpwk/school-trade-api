package cn.jianchen.com.trade.common.file.controller;


import cn.jianchen.com.trade.api.admin.authority.AdminPermission;
import cn.jianchen.com.trade.common.authority.AdminType;
import cn.jianchen.com.trade.common.authority.RequiredPermission;
import cn.jianchen.com.trade.common.controller.BaseController;
import cn.jianchen.com.trade.common.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Controller
@RequestMapping("/adm/file")
public class AdmFileController extends BaseController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload_token")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.NONE)
    public ModelAndView upload_token(String fileName, int fileSize) throws Exception {
        return feedback(fileService.uploadToken("f", fileName, fileSize, true));
    }

    @RequestMapping(value = "usr/upload_token")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView usrupload_token(String fileName, int fileSize) throws Exception {
        return feedback(fileService.uploadToken("f", fileName, fileSize, true));
    }

}
