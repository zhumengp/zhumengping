package org.com.tianzmp.service;

import org.com.tianzmp.dto.ZhumpAddressDTO;
import org.com.tianzmp.vo.ZhumpAddressVO;

import java.util.List;

public interface ZhumpAddressService {
	
	
	// 查询一条数据
	ZhumpAddressVO findById(Long id);
	/** 插入一条数据 */
	boolean insert(ZhumpAddressDTO tianAddressDTO)throws Exception;
	/**查询用户地址*/
	List<ZhumpAddressVO> findByUserAddress(ZhumpAddressDTO tianAddressDTO);
	/**更新地址*/
	boolean update(ZhumpAddressDTO tianAddressDTO) throws Exception;
}
