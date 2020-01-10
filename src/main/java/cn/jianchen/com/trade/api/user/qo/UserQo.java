package cn.jianchen.com.trade.api.user.qo;


import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectPage;
import cn.jianchen.com.trade.common.reposiotry.support.QueryField;
import cn.jianchen.com.trade.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-17 14:16
 **/

public class UserQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.FULL_LIKE, name = "nick")
    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
