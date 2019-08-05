package org.com.tianzmp.vo;

import org.com.tianzmp.entity.ZhumpCart;
/**
* @Title: ZhumpCartVO.java
* @Package dell
* @Description:
* @author zhump  
* @date 2019/7/31 14:58
* @version V1.0  
*/
public class ZhumpCartVO extends ZhumpCart {
	
	/**
	 * 商品
	 */
	private ZhumpGoodsVO tianGoodsVO;

	public ZhumpGoodsVO getTianGoodsVO() {
		return tianGoodsVO;
	}

	public void setTianGoodsVO(ZhumpGoodsVO tianGoodsVO) {
		this.tianGoodsVO = tianGoodsVO;
	}
	
	

}
