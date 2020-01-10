package cn.jianchen.com.trade.common.context;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class SessionThreadLocal extends ThreadLocal<Context> {

    private static final SessionThreadLocal INSTANCE = new SessionThreadLocal();

    private SessionThreadLocal() {
    }

    public static SessionThreadLocal getInstance() {
        return INSTANCE;
    }

}
