package org.com.tianzmp.dto;

import org.com.tianzmp.base.ZhumpBaseDTO;

import java.math.BigDecimal;

public class ZhumpOrderDetailDTO extends ZhumpBaseDTO {

    /**主键*/
    private Long id;
    /**订单id*/
    private Long orderId;
    /**商品id*/
    private Long goodsId;
    /**商品总金额*/
    private BigDecimal totalPrice;
    /**用户id*/
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ZhumpOrderDetailDTO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
