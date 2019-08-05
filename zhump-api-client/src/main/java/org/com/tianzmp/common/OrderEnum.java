package org.com.tianzmp.common;

public enum OrderEnum {

    /************订单状态***************/
    SUBMIT_ORDER(1,"提交订单"),
    SUCCESS_ORDER(2,"完成订单"),
    AUTOMATIC_CANCEL_ORDER(3,"自动取消订单"),
    MANUAL_CANCEL_ORDER(3,"手动取消订单"),
    SETTLEMENT_ORDER(4,"结算订单"),

    /************支付状态***************/
    PAY_DEFAULT_ORDER(1,"未支付"),
    PAY_SUCCESS_ORDER(2,"完成支付"),
    PAY_REFUND_ORDER(3,"已退款")
    ;

    private Integer status;

    private String description;

    OrderEnum(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
