package cn.jianchen.com.trade.api.merchant.authority;


import cn.jianchen.com.trade.api.merchant.model.Merchant;
import cn.jianchen.com.trade.api.merchant.model.MerchantAdmin;
import cn.jianchen.com.trade.api.merchant.model.MerchantAdminSession;
import cn.jianchen.com.trade.common.context.SessionWrap;

public class MerchantAdminSessionWrapper implements SessionWrap {

    private Merchant merchant;
    private MerchantAdmin merchantAdmin;
    private MerchantAdminSession merchantAdminSession;

    public MerchantAdminSessionWrapper() {
    }

    public MerchantAdminSessionWrapper(Merchant merchant, MerchantAdmin merchantAdmin, MerchantAdminSession merchantAdminSession) {
        this.merchant = merchant;
        this.merchantAdmin = merchantAdmin;
        this.merchantAdminSession = merchantAdminSession;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public MerchantAdminSession getMerchantAdminSession() {
        return merchantAdminSession;
    }

    public void setMerchantAdminSession(MerchantAdminSession merchantAdminSession) {
        this.merchantAdminSession = merchantAdminSession;
    }

    public MerchantAdmin getMerchantAdmin() {
        return merchantAdmin;
    }

    public void setMerchantAdmin(MerchantAdmin merchantAdmin) {
        this.merchantAdmin = merchantAdmin;
    }
}
