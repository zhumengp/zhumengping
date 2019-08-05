package org.com.tianzmp.vo;

import org.com.tianzmp.entity.ZhumpOrderDetail;

public class ZhumpOrderDetailVO extends ZhumpOrderDetail {

    private ZhumpGoodsVO tianGoodsVO;

    public ZhumpGoodsVO getTianGoodsVO() {
        return tianGoodsVO;
    }

    public void setTianGoodsVO(ZhumpGoodsVO tianGoodsVO) {
        this.tianGoodsVO = tianGoodsVO;
    }
}
