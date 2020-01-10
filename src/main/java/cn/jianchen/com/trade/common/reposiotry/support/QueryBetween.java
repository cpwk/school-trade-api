package cn.jianchen.com.trade.common.reposiotry.support;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class QueryBetween<T extends Comparable<?>> {

    public T before;
    public T after;

    public T getBefore() {
        return before;
    }

    public void setBefore(T before) {
        this.before = before;
    }

    public T getAfter() {
        return after;
    }

    public void setAfter(T after) {
        this.after = after;
    }

}
