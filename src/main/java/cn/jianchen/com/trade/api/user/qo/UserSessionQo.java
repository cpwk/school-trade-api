package cn.jianchen.com.trade.api.user.qo;


import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectPage;
import cn.jianchen.com.trade.common.reposiotry.support.QueryField;
import cn.jianchen.com.trade.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class UserSessionQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "userId")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
