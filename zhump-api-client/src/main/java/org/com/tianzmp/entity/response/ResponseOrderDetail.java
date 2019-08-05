package org.com.tianzmp.entity.response;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 订单明细返回实体类
 */
public class ResponseOrderDetail {


    /**主键*/
    private Long id;
    /**名称*/
    private Long userId;
    /**商品id*/
    private Long status;
    /**支付状态 1:未支付2：已支付3：已退款*/
    private Long payStatus;
    /**价格*/
    private BigDecimal price;
    /**支付类型  0：未知 1：微信 2：支付宝。*/
    private Long payType;
    /**订单id*/
    private Long orderId;
    /**创建时间*/
    private Timestamp createTime;
    /**更新时间*/
    private Timestamp updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Long payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getPayType() {
        return payType;
    }

    public void setPayType(Long payType) {
        this.payType = payType;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public List<ResponseOrderDetail_Item> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<ResponseOrderDetail_Item> orderDetail) {
        this.orderDetail = orderDetail;
    }

    private List<ResponseOrderDetail_Item> orderDetail;


    public static class  ResponseOrderDetail_Item{
        /**商品id*/
        private Long goodsId;
        /**商品总金额*/
        private BigDecimal totalPrice;
        /**商品名称*/
        private String name;
        /**商品单价*/
        private BigDecimal price;
        /**图片*/
        private String picture;
        /**购买商品数量*/
        private Integer buyGoodsNum;

        public Long getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Long goodsId) {
            this.goodsId = goodsId;
        }

        public BigDecimal getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public Integer getBuyGoodsNum() {
            return buyGoodsNum;
        }

        public void setBuyGoodsNum(Integer buyGoodsNum) {
            this.buyGoodsNum = buyGoodsNum;
        }
    }


}
