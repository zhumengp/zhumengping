package org.com.tianzmp.service;

import java.math.BigDecimal;

import org.com.tianzmp.base.PageInfo;
import org.com.tianzmp.dto.ZhumpGoodsDTO;
import org.com.tianzmp.vo.ZhumpGoodsVO;

public interface ZhumpGoodsService {
	
	/**查询一条数据*/
	ZhumpGoodsVO findById(Long id);
	/**插入一条数据*/
	Boolean insert(ZhumpGoodsDTO tianGoodsDTO)throws Exception;
	/**分页查询数据*/
	PageInfo<ZhumpGoodsVO> pageInfoDTO(ZhumpGoodsDTO tianGoodsDTO)throws Exception;
	/**编辑商品*/
	boolean edit(String name,Integer num,String picture,BigDecimal price,Long categoryId)throws Exception;
	/**增加库存*/
	boolean addGoodsNum(Integer num, Long goodsId);
	/**减库存*/
	boolean reduceGoodsNum(Integer num,Long goodsId);

}
