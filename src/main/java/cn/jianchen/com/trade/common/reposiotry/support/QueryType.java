package cn.jianchen.com.trade.common.reposiotry.support;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public enum QueryType {

    EQUAL(false),
    BEWTEEN(false),
    LESS_THAN(false),
    LESS_THAN_EQUAL(false),
    GREATEROR_THAN(false),
    GREATEROR_THAN_EQUAL(false),
    NOT_EQUAL(false),
    IS_NULL(true),
    IS_NOT_NULL(true),
    RIGHT_LIKE(false),
    LEFT_LIKE(false),
    FULL_LIKE(false),
    DEFAULT_LIKE(false),
    NOT_LIKE(false),
    IN(false);

    //	是否可以为空
    private boolean isCanBeNull;

    private QueryType(boolean isCanBeNull) {
        this.isCanBeNull = isCanBeNull;
    }

    public boolean isCanBeNull() {
        return this.isCanBeNull;
    }
}
