package cn.jianchen.com.trade.api.vip.qo;


import cn.jianchen.com.trade.common.reposiotry.support.DataQueryObjectSort;
import cn.jianchen.com.trade.common.reposiotry.support.QueryField;
import cn.jianchen.com.trade.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

public class VipQo extends DataQueryObjectSort {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Byte status;

    @QueryField(type = QueryType.DEFAULT_LIKE, name = "name")
    private String name;

    @QueryField(type = QueryType.EQUAL, name = "grade")
    private Integer grade;

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


    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
