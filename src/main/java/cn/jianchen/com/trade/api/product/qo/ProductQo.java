package cn.jianchen.com.trade.api.product.qo;


import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectPage;
import cn.jianchen.com.trade.common.reposiotry.support.QueryField;
import cn.jianchen.com.trade.common.reposiotry.support.QueryType;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class ProductQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.FULL_LIKE, name = "title")
    private String title;

    private Integer firstSortId;

    @QueryField(type = QueryType.IN, name = "sortId")
    private List<Integer> sortIds;

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Byte status;

    @QueryField(type = QueryType.EQUAL, name = "state")
    private Byte state;

    @QueryField(type = QueryType.EQUAL, name = "userId")
    private Integer userId;

    private List<String> codes;

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

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public List<Integer> getSortIds() {
        return sortIds;
    }

    public void setSortIds(List<Integer> sortIds) {
        this.sortIds = sortIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFirstSortId() {
        return firstSortId;
    }

    public void setFirstSortId(Integer firstSortId) {
        this.firstSortId = firstSortId;
    }
}