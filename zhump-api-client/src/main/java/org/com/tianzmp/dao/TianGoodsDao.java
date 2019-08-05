package org.com.tianzmp.dao;

import java.util.List;
import java.util.Map;

import org.com.tianzmp.dto.TianGoodsDTO;
import org.com.tianzmp.vo.TianGoodsVO;
import org.springframework.data.repository.query.Param;

public interface TianGoodsDao {
	//查询一条数据
	TianGoodsVO findById(Long id);
	/**新增一条数据*/
	Integer insert(TianGoodsDTO tianGoodsDTO);
	/**分页查询数据*/
	List<TianGoodsVO> pageInfoDTO(TianGoodsDTO tianGoodsDTO);
	/**查询商品count数*/
	Integer count(TianGoodsDTO tianGoodsDTO);
	/**更新商品数据*/
	Integer update(TianGoodsDTO tianGoodsDTO);
	/**增加库存*/
	Integer addGoodsNum(Map<String,Object> map);
	/**减库存*/
	Integer reduceGoodsNum(Map<String,Object> map);



	

}
