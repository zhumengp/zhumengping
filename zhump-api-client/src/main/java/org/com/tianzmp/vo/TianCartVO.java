package org.com.tianzmp.vo;

import org.com.tianzmp.entity.TianCart;
/**
* @Title: TianCartVO.java
* @Package dell
* @Description:
* @author zhump  
* @date 2019/7/31 14:58
* @version V1.0  
*/
public class TianCartVO extends TianCart{
	
	/**
	 * 商品
	 */
	private TianGoodsVO tianGoodsVO;

	public TianGoodsVO getTianGoodsVO() {
		return tianGoodsVO;
	}

	public void setTianGoodsVO(TianGoodsVO tianGoodsVO) {
		this.tianGoodsVO = tianGoodsVO;
	}
	
	

}
