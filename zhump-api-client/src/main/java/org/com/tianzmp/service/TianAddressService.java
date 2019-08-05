package org.com.tianzmp.service;

import org.com.tianzmp.dto.TianAddressDTO;
import org.com.tianzmp.vo.TianAddressVO;

import java.util.List;

public interface TianAddressService {
	
	
	// 查询一条数据
	TianAddressVO findById(Long id);
	/** 插入一条数据 */
	boolean insert(TianAddressDTO tianAddressDTO)throws Exception;
	/**查询用户地址*/
	List<TianAddressVO> findByUserAddress(TianAddressDTO tianAddressDTO);
	/**更新地址*/
	boolean update(TianAddressDTO tianAddressDTO) throws Exception;
}
