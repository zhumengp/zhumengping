package org.com.tianzmp.vo;

import org.com.tianzmp.entity.TianOrderDetail;

public class TianOrderDetailVO extends TianOrderDetail {

    private TianGoodsVO tianGoodsVO;

    public TianGoodsVO getTianGoodsVO() {
        return tianGoodsVO;
    }

    public void setTianGoodsVO(TianGoodsVO tianGoodsVO) {
        this.tianGoodsVO = tianGoodsVO;
    }
}
