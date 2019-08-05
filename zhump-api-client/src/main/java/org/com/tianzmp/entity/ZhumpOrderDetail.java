package org.com.tianzmp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 订单商品明细表
 */
public class ZhumpOrderDetail implements Serializable {

    /**主键*/
    private Long id;
    /**订单id*/
    private Long orderId;
    /**商品id*/
    private Long goodsId;
    /**商品总金额*/
    private BigDecimal totalPrice;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

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

    @Override
    public String toString() {
        return "ZhumpOrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                ", totalPrice=" + totalPrice +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
