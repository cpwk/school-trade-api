package cn.jianchen.com.trade.common.authority;


import cn.jianchen.com.trade.api.admin.authority.AdminSessionWrap;
import cn.jianchen.com.trade.api.admin.model.Admin;
import cn.jianchen.com.trade.api.admin.model.AdminSession;
import cn.jianchen.com.trade.api.admin.service.IAdminService;
import cn.jianchen.com.trade.api.user.model.User;
import cn.jianchen.com.trade.api.user.model.UserSession;
import cn.jianchen.com.trade.api.user.model.UserSessionWrap;
import cn.jianchen.com.trade.api.user.service.IUserService;
import cn.jianchen.com.trade.common.context.SessionWrap;
import cn.jianchen.com.trade.common.exception.ServiceException;
import cn.jianchen.com.trade.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static cn.jianchen.com.trade.common.exception.ErrorCode.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class SessionUtil {

    public static Map<String, SessionWrap> map = new HashMap<>();
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IUserService userService;

    public static SessionWrap getSession(String token) {
        return map.get(token);
    }

    public static boolean tokenTimeout(String token) {
        if (map.get(token) == null) {
            return true;
        } else {
            SessionWrap wrap = map.get(token);
            if (wrap == null) {
                return true;
            }
            if (wrap instanceof AdminSessionWrap) {
                AdminSession session = ((AdminSessionWrap) wrap).getAdminSession();
                return session == null || session.getExpireAt() <= (new Date().getTime());
            } else {
                return true;
            }
        }

    }

    public static void putSession(String token, SessionWrap sess) {
        if (map == null || map.isEmpty()) {
            map = new HashMap<>();
        }
        map.put(token, sess);
    }

    public static void removeSession(String token) {
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (token.equals(key)) {
                iterator.remove();
                map.remove(key);
            }
        }
    }

    public SessionWrap adminPermissionCheck(Enum type, String token, String permission) throws ServiceException {

        if (tokenTimeout(token)) {
            if (type == AdminType.ADMIN) {
                AdminSession session = adminService.adminSession(token);

                if (session != null && session.getExpireAt() > (new Date().getTime())) {
                    Admin admin = adminService.admin(session.getAdminId(), true);
                    if (admin != null && admin.getStatus() == 1) {
                        AdminSessionWrap wrap = new AdminSessionWrap(admin, session);
                        putSession(token, wrap);
                        return wrap;
                    } else {
                        throw new ServiceException(ERR_ACCOUNT_NOT_EXIST);
                    }
                } else {
                    throw new ServiceException(ERR_SESSION_EXPIRES);
                }
            } else if (type == AdminType.USER) {
                UserSession session = userService.findByToken(token);

                if (session != null && session.getExpireAt() > (new Date().getTime())) {
                    User user = userService.user(session.getUserId(), true);
                    if (user != null) {
                        UserSessionWrap wrap = new UserSessionWrap(user, session);
                        putSession(token, wrap);
                        return wrap;
                    } else {
                        throw new ServiceException(ERR_PERMISSION_DENIED);
                    }
                } else {
                    throw new ServiceException(ERR_SESSION_EXPIRES);
                }
            }
        }

        {
            boolean pass = false;

            SessionWrap wrap = getSession(token);
            if (wrap == null) {
                throw new ServiceException(ERR_SESSION_EXPIRES);
            }

            if (StringUtils.isEmpty(permission) || permission.equals("NONE")) {
                pass = true;
            } else {
                List<String> ps = new ArrayList<>();
                if (wrap instanceof AdminSessionWrap) {
                    Admin admin = ((AdminSessionWrap) wrap).getAdmin();
                    ps = admin.getRole().getPermissions();
                }
                pass = ps.contains(permission);
            }
            if (pass) {
                return wrap;
            } else {
                throw new ServiceException(ERR_PERMISSION_DENIED);
            }
        }
    }
}