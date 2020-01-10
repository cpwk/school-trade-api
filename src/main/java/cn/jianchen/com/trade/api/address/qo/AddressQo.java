package cn.jianchen.com.trade.api.address.qo;


import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectSort;
import cn.jianchen.com.trade.common.reposiotry.support.QueryField;
import cn.jianchen.com.trade.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 **/

public class AddressQo extends DataQueryObjectSort {

    private String sortPropertyName = "def";
    private boolean sortAscending = true;

    @QueryField(type = QueryType.EQUAL, name = "userId")
    private Integer userId;

    @Override
    public String getSortPropertyName() {
        return sortPropertyName;
    }

    @Override
    public void setSortPropertyName(String sortPropertyName) {
        this.sortPropertyName = sortPropertyName;
    }

    @Override
    public boolean isSortAscending() {
        return sortAscending;
    }

    @Override
    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
