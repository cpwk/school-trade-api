package cn.jianchen.com.trade.common.task;


import cn.jianchen.com.trade.common.context.SessionThreadLocal;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * <p>
 * 类名称:ApiTask
 **/
public abstract class ApiTask implements Runnable {

    @Override
    public final void run() {
        SessionThreadLocal.getInstance().set(null);
        try {
            doApiWork();
        } catch (Throwable t) {
            System.err.println(t);
        }
    }

    protected abstract void doApiWork() throws Exception;

}
