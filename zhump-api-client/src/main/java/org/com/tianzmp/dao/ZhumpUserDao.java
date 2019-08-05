package org.com.tianzmp.dao;

import java.util.List;

import org.com.tianzmp.dto.ZhumpUserDTO;
import org.com.tianzmp.vo.ZhumpUserVO;

public interface ZhumpUserDao {
	
	
	//新增一条数据
	Integer insert(ZhumpUserDTO tianUserDTO);
	//查询所有数据
	List<ZhumpUserVO> getAll();
	//查询一条数据
	ZhumpUserVO findById(Long id);

}
