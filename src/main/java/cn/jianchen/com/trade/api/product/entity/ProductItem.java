package cn.jianchen.com.trade.api.product.entity;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class ProductItem {

    private List<String> imgs;
    private List<ItemParam> params;
    private Integer stock;
    private Integer price;
    private Integer sno;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public List<ItemParam> getParams() {
        return params;
    }

    public void setParams(List<ItemParam> params) {
        this.params = params;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}