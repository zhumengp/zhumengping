package org.com.tianzmp.dao;

import java.util.List;
import java.util.Map;

import org.com.tianzmp.dto.ZhumpCartDTO;
import org.com.tianzmp.vo.ZhumpCartVO;

public interface ZhumpCartDao {
	/**插入*/
	Integer insert(ZhumpCartDTO zhumpCartDTO);
	/**分页*/
	List<ZhumpCartVO> pageInfoDTO(ZhumpCartDTO zhumpCartDTO);
	/**修改*/
	Integer update(ZhumpCartDTO tianCartDTO);
	/**根据商品id去查购物车信息*/
	ZhumpCartVO findByGoodsId(Map<String,Object> map);
	/**分页数量*/
	Integer count(ZhumpCartDTO tianCartDTO);
	/**查询用户的购物*/
	ZhumpCartVO findById(Map<String,Object> map);
	/**新增购物车商品数量*/
	Integer updateCartGoodsNum(ZhumpCartDTO zhumpCartDTO);
}
