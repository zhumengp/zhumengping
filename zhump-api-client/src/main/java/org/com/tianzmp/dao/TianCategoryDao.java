package org.com.tianzmp.dao;

import java.util.List;

import org.com.tianzmp.dto.TianCategoryDTO;
import org.com.tianzmp.vo.TianCategoryVO;

public interface TianCategoryDao {
	
	/**查询*/
	TianCategoryVO findById(Long id);
	/**新增*/
	Integer insert(TianCategoryDTO tianCategoryDTO);
	/**修改*/
	Integer update(TianCategoryDTO tianCategoryDTO);
	/**查询所有分类*/
	List<TianCategoryVO> selectAll(TianCategoryDTO tianCategoryDTO);

}
