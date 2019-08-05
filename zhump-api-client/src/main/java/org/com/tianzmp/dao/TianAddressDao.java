package org.com.tianzmp.dao;

import java.util.List;

import org.com.tianzmp.dto.TianAddressDTO;
import org.com.tianzmp.vo.TianAddressVO;
/**
* @Title: TianAddressDao.java
* @Package dell
* @Description:
* @author zhump  
* @date 2019/7/31 16:25
* @version V1.0  
*/
public interface TianAddressDao {
	
	
	/** 查询一条数据*/
	TianAddressVO findById(Long id);
	/**新增数据*/
	Integer insert(TianAddressDTO tianAddressDTO);
	/**查询用户地址*/
	List<TianAddressVO> findByUserAddress(TianAddressDTO tianAddressDTO);
	/**更新地址*/
	Integer update(TianAddressDTO tianAddressDTO);
}
