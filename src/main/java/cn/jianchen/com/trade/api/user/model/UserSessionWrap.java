package cn.jianchen.com.trade.api.user.model;


import cn.jianchen.com.trade.common.context.SessionWrap;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-09 13:58
 **/

public class UserSessionWrap implements SessionWrap {

    private User user;
    private UserSession userSession;

    public UserSessionWrap(User user, UserSession userSession) {
        this.user = user;
        this.userSession = userSession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}