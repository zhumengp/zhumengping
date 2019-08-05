package org.com.tianzmp.service;

import java.util.List;

import org.com.tianzmp.dto.TianCategoryDTO;
import org.com.tianzmp.vo.TianCategoryVO;

public interface TianCategoryService {
	
	
	/**根据作品id去加入购物车*/
	boolean save(Long pId,String name,Integer status);
	/**
	 * 购物车列表
	 */
	List<TianCategoryVO> selectAll(TianCategoryDTO tianCategoryDTO);
	
	/**根据父类id去查询子类id**/
	List<TianCategoryVO> findByPid();
}
