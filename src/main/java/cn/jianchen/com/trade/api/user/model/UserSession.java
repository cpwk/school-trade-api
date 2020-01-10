package cn.jianchen.com.trade.api.user.model;

import javax.persistence.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-09 10:30
 **/

@Entity
@Table(name = "user_session")
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column
    private String token;
    @Column(name = "signin_at")
    private Long signInAt;
    @Column(name = "expire_at")
    private Long expireAt;
    @Column
    private String ip;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getSignInAt() {
        return signInAt;
    }

    public void setSignInAt(Long signInAt) {
        this.signInAt = signInAt;
    }

    public Long getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Long expireAt) {
        this.expireAt = expireAt;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
