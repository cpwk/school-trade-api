package cn.jianchen.com.trade.api.sort.qo;


import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectSort;
import cn.jianchen.com.trade.common.reposiotry.support.QueryField;
import cn.jianchen.com.trade.common.reposiotry.support.QueryType;

import static cn.jianchen.com.trade.common.entity.Constants.ZERO_BYTE;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class SortQo extends DataQueryObjectSort {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Byte status = 1;

    @QueryField(type = QueryType.FULL_LIKE, name = "name")
    private String name;

    private String sortPropertyName = "priority";

    public SortQo() {
    }

    public SortQo(Byte status) {
        this.setStatus(status);
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status == ZERO_BYTE ? null : status;
    }

    @Override
    public String getSortPropertyName() {
        return sortPropertyName;
    }

    @Override
    public void setSortPropertyName(String sortPropertyName) {
        this.sortPropertyName = sortPropertyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
