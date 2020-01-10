package cn.jianchen.com.trade.api.merchant.qo;


import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectPage;
import cn.jianchen.com.trade.common.reposiotry.support.QueryField;
import cn.jianchen.com.trade.common.reposiotry.support.QueryType;

public class MerchantAdminSessionQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "adminId")
    private Integer adminId;

    public MerchantAdminSessionQo() {
    }

    public MerchantAdminSessionQo(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

}
