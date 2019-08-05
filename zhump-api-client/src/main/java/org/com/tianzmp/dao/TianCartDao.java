package org.com.tianzmp.dao;

import java.util.List;
import java.util.Map;

import org.com.tianzmp.dto.TianCartDTO;
import org.com.tianzmp.vo.TianCartVO;

public interface TianCartDao {
	/**插入*/
	Integer insert(TianCartDTO tianCartDTO);
	/**分页*/
	List<TianCartVO> pageInfoDTO(TianCartDTO tianCartDTO);
	/**修改*/
	Integer update(TianCartDTO tianCartDTO);
	/**查询所有*/
	List<TianCartVO> selectAll(TianCartDTO tianCartDTO);
	/**分页数量*/
	Integer count(TianCartDTO tianCartDTO);
	/**查询用户的购物*/
	TianCartVO findById(Map<String,Object> map);
}
