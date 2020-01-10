package cn.jianchen.com.trade.api.comment.model;


import cn.jianchen.com.trade.api.comment.custcase.CommentArrayConverter;
import cn.jianchen.com.trade.api.comment.entity.CommentItems;
import cn.jianchen.com.trade.api.orders.model.Orders;
import cn.jianchen.com.trade.api.user.model.User;

import javax.persistence.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "create_at")
    private Long createAt;

    @Column(name = "content")
    @Convert(converter = CommentArrayConverter.class)
    private CommentItems content;

    @Column(name = "orders_id")
    private Integer ordersId;

    @Transient
    private Orders orders;

    @Transient
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public CommentItems getContent() {
        return content;
    }

    public void setContent(CommentItems content) {
        this.content = content;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
