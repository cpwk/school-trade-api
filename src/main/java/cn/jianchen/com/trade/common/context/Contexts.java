package cn.jianchen.com.trade.common.context;


import cn.jianchen.com.trade.api.admin.authority.AdminSessionWrap;
import cn.jianchen.com.trade.api.user.model.UserSessionWrap;
import cn.jianchen.com.trade.common.exception.ServiceException;

import static cn.jianchen.com.trade.common.exception.ErrorCode.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class Contexts {

    public static void set(Context context) {
        SessionThreadLocal.getInstance().set(context);
    }

    public static Context get() {
        return SessionThreadLocal.getInstance().get();
    }

    public static SessionWrap getSession() {
        return get().getSession();
    }

    public static Integer requestAdminId() throws ServiceException {
        Context context = get();
        if (context == null) {
            throw new ServiceException(ERR_SESSION_EXPIRES);
        }
        Integer adminId = sessionAdminId();
        if (adminId == null) {
            throw new ServiceException(ERR_NEED_ADMIN_ID);
        }
        return adminId;
    }

    public static Integer sessionAdminId() throws ServiceException {
        Context context = get();
        if (context == null) {
            return null;
        }
        SessionWrap wrap = context.getSession();
        Integer adminId = null;

        if (wrap instanceof AdminSessionWrap) {
            adminId = ((AdminSessionWrap) wrap).getAdmin().getId();
        }
        return adminId;
    }

    public static Integer sessionUserId() throws ServiceException {
        Context context = get();
        if (context == null) {
            return null;
        }
        SessionWrap wrap = context.getSession();
        Integer id = null;
        if (wrap instanceof UserSessionWrap) {
            id = ((UserSessionWrap) wrap).getUser().getId();
        }
        return id;
    }

    public static Integer requestUserId() throws ServiceException {
        Context context = get();
        if (context == null) {
            throw new ServiceException(ERR_SESSION_EXPIRES);
        }
        Integer id = sessionUserId();
        if (id == null) {
            throw new ServiceException(ERR_NEED_USER_ID);
        }
        return id;
    }

    protected static SessionWrap getWrapper() {
        Context context = get();
        if (context == null) {
            return null;
        }
        return context.getSession();
    }
}