package cn.jianchen.com.trade.api.orders.qo;


import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectPage;
import cn.jianchen.com.trade.common.reposiotry.support.QueryField;
import cn.jianchen.com.trade.common.reposiotry.support.QueryType;

import static cn.jianchen.com.trade.common.entity.Constants.ZERO_BYTE;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class OrdersQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Byte status;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status == ZERO_BYTE ? null : status;
    }

}
