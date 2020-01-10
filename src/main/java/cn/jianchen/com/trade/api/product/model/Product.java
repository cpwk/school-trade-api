package cn.jianchen.com.trade.api.product.model;


import cn.jianchen.com.trade.api.product.entity.ProductItem;
import cn.jianchen.com.trade.api.product.productItemarrayconverter.ProductItemArrayConverter;

import javax.persistence.*;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "sort_id")
    private Integer sortId;

    @Column(name = "priority")
    private Integer priority = 1;

    @Column(name = "status")
    private Byte status = 2;

    private Byte state = 1;

    //@Column(name = "user_id")
    private Integer userId;

    @Column(name = "content")
    private String content;

    @Convert(converter = ProductItemArrayConverter.class)
    @Column(name = "product_items")
    private List<ProductItem> productItems;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}