package org.com.tianzmp.dto;

import org.com.tianzmp.base.ZhumpBaseDTO;

public class ZhumpBannerDTO extends ZhumpBaseDTO {

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

    @Override
    public String toString() {
        return "ZhumpBannerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", status=" + status +
                ", linkUrl=" + linkUrl +
                ", sort=" + sort +
                '}';
    }
}
