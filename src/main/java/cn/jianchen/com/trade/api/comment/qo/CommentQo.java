package cn.jianchen.com.trade.api.comment.qo;


import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectPage;
import cn.jianchen.com.trade.common.reposiotry.support.QueryField;
import cn.jianchen.com.trade.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class CommentQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "userId")
    private Integer userId;

    @QueryField(type = QueryType.EQUAL, name = "productId")
    private Integer productId;

    @QueryField(type = QueryType.EQUAL, name = "ordersId")
    private Integer ordersId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }
}
