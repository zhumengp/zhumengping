package org.com.tianzmp.dao;

import java.util.List;

import org.com.tianzmp.dto.TianUserDTO;
import org.com.tianzmp.vo.TianUserVO;

public interface TianUserDao {
	
	
	//新增一条数据
	Integer insert(TianUserDTO tianUserDTO);
	//查询所有数据
	List<TianUserVO> getAll();
	//查询一条数据
	TianUserVO findById(Long id);

}
