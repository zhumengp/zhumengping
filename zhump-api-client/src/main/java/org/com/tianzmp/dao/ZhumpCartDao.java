package org.com.tianzmp.dao;

import java.util.List;
import java.util.Map;

import org.com.tianzmp.dto.ZhumpCartDTO;
import org.com.tianzmp.vo.ZhumpCartVO;

public interface ZhumpCartDao {
	/**插入*/
	Integer insert(ZhumpCartDTO tianCartDTO);
	/**分页*/
	List<ZhumpCartVO> pageInfoDTO(ZhumpCartDTO tianCartDTO);
	/**修改*/
	Integer update(ZhumpCartDTO tianCartDTO);
	/**查询所有*/
	List<ZhumpCartVO> selectAll(ZhumpCartDTO tianCartDTO);
	/**分页数量*/
	Integer count(ZhumpCartDTO tianCartDTO);
	/**查询用户的购物*/
	ZhumpCartVO findById(Map<String,Object> map);
}
