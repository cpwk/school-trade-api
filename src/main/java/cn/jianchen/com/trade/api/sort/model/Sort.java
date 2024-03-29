package cn.jianchen.com.trade.api.sort.model;

import javax.persistence.*;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Entity
@Table(name = "sort")
public class Sort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String sequence;

    @Column
    private String name;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column
    private Integer priority;

    @Column
    private Byte status;

    @Transient
    private List<Sort> children;

    public Sort() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public List<Sort> getChildren() {
        return children;
    }

    public void setChildren(List<Sort> children) {
        this.children = children;
    }
}