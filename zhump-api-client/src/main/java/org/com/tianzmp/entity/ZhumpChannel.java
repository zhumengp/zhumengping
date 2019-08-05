package org.com.tianzmp.entity;

import java.sql.Timestamp;

/**
 * 频道
 * @author zhump
 */
public class ZhumpChannel {

    /**主键*/
    private Long id ;
    /**名称*/
    private String name ;
    /**状态 0：不显示，1：显示*/
    private Integer status ;
    /**更新时间*/
    private Timestamp updateTime;
    /**创建时间*/
    private Timestamp createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ZhumpChannel{" +
                "id=" + id +
                ", name=" + name +
                ", status=" + status +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}

