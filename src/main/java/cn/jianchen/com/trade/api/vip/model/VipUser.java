package cn.jianchen.com.trade.api.vip.model;


import cn.jianchen.com.trade.api.user.model.User;

import javax.persistence.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Entity
@Table(name = "vip_user")
public class VipUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sno")
    private Integer sno;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "vip_id")
    private Integer vipId;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "expire_at")
    private Long expireAt;

    @Column(name = "remark")
    private String remark;

    @Transient
    private String duration;

    @Transient
    private Vip vip;

    @Transient
    private User user;

    @Transient
    private Integer grade;

    @Transient
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Long expireAt) {
        this.expireAt = expireAt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
