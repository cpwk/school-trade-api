package cn.jianchen.com.trade.api.merchant.qo;


import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectSort;
import cn.jianchen.com.trade.common.reposiotry.support.QueryField;
import cn.jianchen.com.trade.common.reposiotry.support.QueryType;

public class MerchantAdminQo extends DataQueryObjectSort {

    @QueryField(type = QueryType.EQUAL, name = "merchantId")
    private Integer merchantId;

    public MerchantAdminQo() {
    }

    public MerchantAdminQo(Integer merchantId) {
        this.setMerchantId(merchantId);
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }
}

