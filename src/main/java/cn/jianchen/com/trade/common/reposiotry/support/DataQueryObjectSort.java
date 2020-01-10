package cn.jianchen.com.trade.common.reposiotry.support;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class DataQueryObjectSort implements DataQueryObject {

    protected String sortPropertyName = "id";
    protected boolean sortAscending = false;


    public String getSortPropertyName() {
        return sortPropertyName;
    }

    public void setSortPropertyName(String sortPropertyName) {
        this.sortPropertyName = sortPropertyName;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }
}
