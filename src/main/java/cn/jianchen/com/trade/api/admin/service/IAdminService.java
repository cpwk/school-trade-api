package cn.jianchen.com.trade.api.admin.service;

import cn.jianchen.com.trade.api.admin.model.Admin;
import cn.jianchen.com.trade.api.admin.model.AdminSession;
import cn.jianchen.com.trade.api.admin.model.Role;
import cn.jianchen.com.trade.api.admin.qo.AdminSessionQo;
import cn.jianchen.com.trade.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public interface IAdminService {

    // role

    void save_role(Role role);

    void remove_role(int id);

    Role role(int id);

    List<Role> roles(boolean init);

    // admin

    List<Admin> admins();

    void save_admin(Admin admin) throws ServiceException;

    void admin_status(int id, Byte status) throws ServiceException;

    Admin admin(int id, boolean init);

    void remove_admin(int id) throws ServiceException;

    Page<AdminSession> adminSessions(AdminSessionQo qo) throws Exception;

    AdminSession adminSession(String token);

    void update_password(String password, String oldPassword) throws ServiceException;

    // signin

    Map signin(Admin admin, String ip) throws Exception;

    Map profile() throws Exception;

}
