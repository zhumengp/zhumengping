package org.com.tianzmp.dao;

import java.util.List;

import org.com.tianzmp.dto.ZhumpCategoryDTO;
import org.com.tianzmp.vo.ZhumpCategoryVO;

public interface ZhumpCategoryDao {
	
	/**查询*/
	ZhumpCategoryVO findById(Long id);
	/**新增*/
	Integer insert(ZhumpCategoryDTO tianCategoryDTO);
	/**修改*/
	Integer update(ZhumpCategoryDTO tianCategoryDTO);
	/**查询所有分类*/
	List<ZhumpCategoryVO> selectAll(ZhumpCategoryDTO tianCategoryDTO);

}
