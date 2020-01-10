package cn.jianchen.com.trade.api.coupon.model;


import cn.jianchen.com.trade.api.coupon.entity.CouponRule;
import cn.jianchen.com.trade.api.coupon.rulesconverter.RulesConverter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Entity
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "code")
    private String code;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "status")
    private Byte status;

    @Column(name = "img")
    private String img;

    @Convert(converter = RulesConverter.class)
    @Column(name = "rule")
    private CouponRule rule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public CouponRule getRule() {
        return rule;
    }

    public void setRule(CouponRule rule) {
        this.rule = rule;
    }
}
