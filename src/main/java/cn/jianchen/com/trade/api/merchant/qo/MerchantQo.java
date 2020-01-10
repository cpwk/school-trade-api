package cn.jianchen.com.trade.api.merchant.qo;


import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectPage;
import cn.jianchen.com.trade.common.reposiotry.support.QueryField;
import cn.jianchen.com.trade.common.reposiotry.support.QueryType;

import static cn.jianchen.com.trade.common.entity.Constants.ZERO_BYTE;

public class MerchantQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "state")
    private Byte state;

    @QueryField(type = QueryType.FULL_LIKE, name = "name")
    private String name;

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state == ZERO_BYTE ? null : state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
