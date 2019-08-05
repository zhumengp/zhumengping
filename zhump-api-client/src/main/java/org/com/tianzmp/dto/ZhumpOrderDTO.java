package org.com.tianzmp.dto;

import org.com.tianzmp.base.ZhumpBaseDTO;

import java.math.BigDecimal;

public class ZhumpOrderDTO extends ZhumpBaseDTO {

    /**主键*/
    private Long id;
    /**名称*/
    private Long userId;
    /**购物车id*/
    private Integer status;
    /**支付状态 1:未支付2：已支付3：已退款*/
    private Integer payStatus;
    /**价格*/
    private BigDecimal price;
    /**支付类型  0：未知 1：微信 2：支付宝。*/
    private Integer payType;
    /**订单id*/
    private Long orderId;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
