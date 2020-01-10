package cn.jianchen.com.trade.api.merchant.qo;

public class MerchantWrapOption {

    private boolean withAdmin = false;

    private MerchantWrapOption() {

    }

    public static final MerchantWrapOption getDefaultInstance() {
        return new MerchantWrapOption();
    }


    public static final MerchantWrapOption getOmsDetailInstance() {
        return getDefaultInstance().setWithAdmin(true);

    }

    public boolean isWithAdmin() {
        return withAdmin;
    }

    public MerchantWrapOption setWithAdmin(boolean withAdmin) {
        this.withAdmin = withAdmin;
        return this;
    }
}
