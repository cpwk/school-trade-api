package cn.jianchen.com.trade.api.vip.model;


import cn.jianchen.com.trade.api.vip.converter.PriceRuleConverter;
import cn.jianchen.com.trade.api.vip.entity.PriceRule;

import javax.persistence.*;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Entity
@Table(name = "vip")
public class Vip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "intro")
    private String intro;

    @Column(name = "price_rule")
    @Convert(converter = PriceRuleConverter.class)
    private List<PriceRule> priceRule;

    @Column(name = "status")
    private Byte status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public List<PriceRule> getPriceRule() {
        return priceRule;
    }

    public void setPriceRule(List<PriceRule> priceRule) {
        this.priceRule = priceRule;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

}
