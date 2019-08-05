package org.com.tianzmp.dao;

import java.util.List;

import org.com.tianzmp.dto.ZhumpAddressDTO;
import org.com.tianzmp.vo.ZhumpAddressVO;

/**
* @Title: ZhumpAddressDao.java
* @Package dell
* @Description:
* @author zhump  
* @date 2019/7/31 16:25
* @version V1.0  
*/
public interface ZhumpAddressDao {
	
	
	/** 查询一条数据*/
	ZhumpAddressVO findById(Long id);
	/**新增数据*/
	Integer insert(ZhumpAddressDTO tianAddressDTO);
	/**查询用户地址*/
	List<ZhumpAddressVO> findByUserAddress(ZhumpAddressDTO tianAddressDTO);
	/**更新地址*/
	Integer update(ZhumpAddressDTO tianAddressDTO);
}
