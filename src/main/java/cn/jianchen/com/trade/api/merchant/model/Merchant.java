package cn.jianchen.com.trade.api.merchant.model;


import cn.jianchen.com.trade.api.merchant.converter.MerchantSettingConverter;
import cn.jianchen.com.trade.api.product.model.Product;
import cn.jianchen.com.trade.common.converter.StringArrayConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String logo;
    @Column(name = "product_category_sequences")
    @Convert(converter = StringArrayConverter.class)
    private List<String> productCategorySequences;
    @Column(name = "valid_thru")
    private Long validThru;
    @Column
    private Byte state;
    @Column(updatable = false, name = "created_at")
    private Long createdAt;
    @Column
    @Convert(converter = MerchantSettingConverter.class)
    private MerchantSetting setting;
    @Transient
    private String duration;

    @Transient
    private List<MerchantAdmin> admins;

    @Transient
    private List<Product> compProducts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public List<String> getProductCategorySequences() {
        return productCategorySequences;
    }

    public void setProductCategorySequences(List<String> productCategorySequences) {
        this.productCategorySequences = productCategorySequences;
    }

    public MerchantSetting getSetting() {
        return setting;
    }

    public void setSetting(MerchantSetting setting) {
        this.setting = setting;
    }

    public Long getValidThru() {
        return validThru;
    }

    public void setValidThru(Long validThru) {
        this.validThru = validThru;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<MerchantAdmin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<MerchantAdmin> admins) {
        this.admins = admins;
    }

    public List<Product> getCompProducts() {
        return compProducts;
    }

    public void setCompProducts(List<Product> compProducts) {
        this.compProducts = compProducts;
    }
}
