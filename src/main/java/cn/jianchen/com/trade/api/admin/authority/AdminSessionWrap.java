package cn.jianchen.com.trade.api.admin.authority;


import cn.jianchen.com.trade.api.admin.model.Admin;
import cn.jianchen.com.trade.api.admin.model.AdminSession;
import cn.jianchen.com.trade.common.context.SessionWrap;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class AdminSessionWrap implements SessionWrap {

    private Admin admin;
    @JSONField(serialize = false)
    private AdminSession adminSession;

    public AdminSessionWrap(Admin admin, AdminSession adminSession) {
        this.admin = admin;
        this.adminSession = adminSession;
    }

    public AdminSession getAdminSession() {
        return adminSession;
    }

    public void setAdminSession(AdminSession adminSession) {
        this.adminSession = adminSession;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

}
