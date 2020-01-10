package cn.jianchen.com.trade.api.merchant.qo;


import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectPage;
import cn.jianchen.com.trade.common.reposiotry.support.QueryField;
import cn.jianchen.com.trade.common.reposiotry.support.QueryType;

public class MerchantUserQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "state")
    private Byte state = 1;

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
