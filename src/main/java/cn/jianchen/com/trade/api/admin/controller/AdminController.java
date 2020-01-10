package cn.jianchen.com.trade.api.admin.controller;


import cn.jianchen.com.trade.api.admin.authority.AdminPermission;
import cn.jianchen.com.trade.api.admin.authority.AdminPermissionVO;
import cn.jianchen.com.trade.api.admin.model.Admin;
import cn.jianchen.com.trade.api.admin.model.Role;
import cn.jianchen.com.trade.api.admin.qo.AdminSessionQo;
import cn.jianchen.com.trade.api.admin.service.IAdminService;
import cn.jianchen.com.trade.common.authority.AdminType;
import cn.jianchen.com.trade.common.authority.RequiredPermission;
import cn.jianchen.com.trade.common.controller.BaseController;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Controller
@RequestMapping("/adm/admin")
public class AdminController extends BaseController {

    @Autowired
    private IAdminService adminService;

    @RequestMapping(value = "/save_role")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ROLE_EDIT)
    public ModelAndView save(String role) throws Exception {
        adminService.save_role(parseModel(role, new Role()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove_role")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ROLE_EDIT)
    public ModelAndView remove_role(Integer id) throws ServiceException {
        adminService.remove_role(id);
        return feedback(null);
    }

    @RequestMapping(value = "/role")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ROLE_EDIT)
    public ModelAndView role(Integer id) throws ServiceException {
        return feedback(adminService.role(id));
    }

    @RequestMapping(value = "/permissions")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ROLE_EDIT)
    public ModelAndView permissions() throws ServiceException {
        return feedback(AdminPermissionVO.initPermissions());
    }

    @RequestMapping(value = "/roles")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ROLE_EDIT)
    public ModelAndView roles() throws ServiceException {
        return feedback(adminService.roles(true));
    }

    @RequestMapping(value = "/adminSessions")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ADMIN_LIST)
    public ModelAndView adminSessions(String adminSessionQo) throws Exception {
        return feedback(adminService.adminSessions(parseModel(adminSessionQo, new AdminSessionQo())));
    }

    @RequestMapping(value = "/remove_admin")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ADMIN_EDIT)
    public ModelAndView admin_remove(Integer id) throws Exception {
        adminService.remove_admin(id);
        return feedback(null);
    }

    @RequestMapping(value = "/save_admin")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ADMIN_EDIT)
    public ModelAndView save_admin(String admin) throws Exception {
        adminService.save_admin(parseModel(admin, new Admin()));
        return feedback(null);
    }

    @RequestMapping(value = "/admin")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ADMIN_EDIT)
    public ModelAndView admin(Integer id) throws ServiceException {
        return feedback(adminService.admin(id, false));
    }

    @RequestMapping(value = "/admin_status")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ADMIN_EDIT)
    public ModelAndView admin_status(Integer id, Byte status) throws ServiceException {
        adminService.admin_status(id, status);
        return feedback(null);
    }

    @RequestMapping(value = "/admins")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ADMIN_LIST)
    public ModelAndView admins() throws ServiceException {
        return feedback(adminService.admins());
    }


    @RequestMapping(value = "/update_password")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView update_password(String password, String oldPassword) throws Exception {
        adminService.update_password(password, oldPassword);
        return feedback(null);
    }

    @RequestMapping(value = "/signin")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView signin(String admin) throws Exception {
        return feedback(adminService.signin(parseModel(admin, new Admin()), getRemoteAddress()));
    }

    @RequestMapping(value = "/profile")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView profile() throws Exception {
        return feedback(adminService.profile());
    }

}