package org.com.tianzmp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 订单表
 * @author zhump
 */
public class TianOrder {

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

    @Override
    public String toString() {
        return "TianOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", payStatus=" + payStatus +
                ", price=" + price +
                ", payType=" + payType +
                ", orderId=" + orderId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
