package cn.jianchen.com.trade.api.admin.authority;


import cn.jianchen.com.trade.common.context.Context;
import cn.jianchen.com.trade.common.context.Contexts;
import cn.jianchen.com.trade.common.context.SessionWrap;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class AdminContext {

    public static AdminSessionWrap getSessionWrap() {
        Context context = Contexts.get();
        if (context == null) {
            return null;
        }
        SessionWrap session = context.getSession();
        if (session == null) {
            return null;
        }
        if (!(session instanceof AdminSessionWrap)) {
            return null;
        }
        return (AdminSessionWrap) session;
    }
}