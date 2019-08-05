package org.com.tianzmp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 轮播图
 * @author zhump
 */
public class TianBanner implements Serializable {

    /**主键*/
    private Long id;
    /**名字*/
    private String name;
    /**图片*/
    private String picUrl;
    /**1：显示2：不显示*/
    private Integer status;
    /**跳转连接*/
    private Long linkUrl;
    /**排序*/
    private Long sort;
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(Long linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
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
        return "TianBanner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", status=" + status +
                ", linkUrl=" + linkUrl +
                ", sort=" + sort +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
