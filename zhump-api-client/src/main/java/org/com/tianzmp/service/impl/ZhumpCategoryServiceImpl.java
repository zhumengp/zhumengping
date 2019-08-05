package org.com.tianzmp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.com.tianzmp.cache.RedisUtils;
import org.com.tianzmp.common.Constant;
import org.com.tianzmp.dao.ZhumpCategoryDao;
import org.com.tianzmp.dto.ZhumpCategoryDTO;
import org.com.tianzmp.service.ZhumpCategoryService;
import org.com.tianzmp.util.JsonMapper;
import org.com.tianzmp.vo.ZhumpCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;


@Service("tianCategoryService")
public class ZhumpCategoryServiceImpl implements ZhumpCategoryService {
	
	@Autowired
	private ZhumpCategoryDao tianCategoryDao;
	
	@Autowired
	RedisUtils redisUtils;
	

	@Override
	public boolean save(Long pId,String name,Integer status){
		ZhumpCategoryDTO zhumpCategoryDTO = new ZhumpCategoryDTO();
		zhumpCategoryDTO.setName(name);
		zhumpCategoryDTO.setStatus(status);
		zhumpCategoryDTO.setpId(pId);
		Integer resut = tianCategoryDao.insert(zhumpCategoryDTO);
		return resut > 0 ? true : false;
	}

	@Override
	public List<ZhumpCategoryVO> selectAll(ZhumpCategoryDTO zhumpCategoryDTO) {
		List<ZhumpCategoryVO> list = tianCategoryDao.selectAll(zhumpCategoryDTO);
		return list;
	}

	@Override
	public List<ZhumpCategoryVO> findByPid() {
		ZhumpCategoryDTO zhumpCategoryDTO = new ZhumpCategoryDTO();
		
		String redis_List = redisUtils.getRedis_List(Constant.redisEnumKey.CATEGORY.getKey());
		List<ZhumpCategoryVO> list = new ArrayList<ZhumpCategoryVO>();
		if(StringUtils.isBlank(redis_List)) {
			zhumpCategoryDTO.setpId(0L);
			list = tianCategoryDao.selectAll(zhumpCategoryDTO);
			for(ZhumpCategoryVO tianCategoryVO : list) {
				zhumpCategoryDTO.setpId(tianCategoryVO.getId());
				List<ZhumpCategoryVO> childer_list = tianCategoryDao.selectAll(zhumpCategoryDTO);
				tianCategoryVO.setChilder(childer_list);
			}
		}else {
			list = JsonMapper.getInstance().fromJson(redis_List, JsonMapper.getInstance().createCollectionType(List.class, ZhumpCategoryVO.class));
		}
		return list;
	}

	

	
}
