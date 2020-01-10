package cn.jianchen.com.trade.common.entity;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class KeyValue {

    private Integer key;
    private String val;

    public KeyValue() {
    }

    public KeyValue(Integer key, String val) {
        this.key = key;
        this.val = val;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
