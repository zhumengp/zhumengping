package org.com.tianzmp.dao;

import java.util.List;
import java.util.Map;

import org.com.tianzmp.dto.ZhumpGoodsDTO;
import org.com.tianzmp.vo.ZhumpGoodsVO;

public interface ZhumpGoodsDao {
	//查询一条数据
	ZhumpGoodsVO findById(Long id);
	/**新增一条数据*/
	Integer insert(ZhumpGoodsDTO tianGoodsDTO);
	/**分页查询数据*/
	List<ZhumpGoodsVO> pageInfoDTO(ZhumpGoodsDTO tianGoodsDTO);
	/**查询商品count数*/
	Integer count(ZhumpGoodsDTO tianGoodsDTO);
	/**更新商品数据*/
	Integer update(ZhumpGoodsDTO tianGoodsDTO);
	/**增加库存*/
	Integer addGoodsNum(Map<String,Object> map);
	/**减库存*/
	Integer reduceGoodsNum(Map<String,Object> map);



	

}
