package cn.jianchen.com.trade.api.merchant.entity;

public enum MerchantState {
    NORMAL(1), BLOCKED(2), IN_ARREARS(3);

    private byte value;

    private MerchantState(int value) {
        this.value = (byte) value;
    }

    public static MerchantState find(byte value) {
        for (MerchantState item : MerchantState.values()) {
            if (item.value == value) {
                return item;
            }
        }
        return null;
    }

    public byte value() {
        return value;
    }
}
