package cn.jianchen.com.trade.api.vip.entity;

import java.math.BigDecimal;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class PriceRule {

    private String duration;
    private BigDecimal price;
    private Integer sno;

    public PriceRule() {
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }
}
