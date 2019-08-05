package org.com.tianzmp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.com.tianzmp.cache.RedisUtils;
import org.com.tianzmp.common.Constant;
import org.com.tianzmp.dao.TianCategoryDao;
import org.com.tianzmp.dto.TianCategoryDTO;
import org.com.tianzmp.service.TianCategoryService;
import org.com.tianzmp.util.JsonMapper;
import org.com.tianzmp.vo.TianCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;


@Service("tianCategoryService")
public class TianCategoryServiceImpl implements TianCategoryService{
	
	@Autowired
	private TianCategoryDao tianCategoryDao;
	
	@Autowired
	RedisUtils redisUtils;
	

	@Override
	public boolean save(Long pId,String name,Integer status){
		TianCategoryDTO tianCategoryDTO = new TianCategoryDTO();
		tianCategoryDTO.setName(name);
		tianCategoryDTO.setStatus(status);
		tianCategoryDTO.setpId(pId);
		Integer resut = tianCategoryDao.insert(tianCategoryDTO);
		return resut > 0 ? true : false;
	}

	@Override
	public List<TianCategoryVO> selectAll(TianCategoryDTO tianCategoryDTO) {
		List<TianCategoryVO> list = tianCategoryDao.selectAll(tianCategoryDTO);
		return list;
	}

	@Override
	public List<TianCategoryVO> findByPid() {
		TianCategoryDTO tianCategoryDTO = new TianCategoryDTO();
		
		String redis_List = redisUtils.getRedis_List(Constant.redisEnumKey.CATEGORY.getKey());
		List<TianCategoryVO> list = new ArrayList<TianCategoryVO>();
		if(StringUtils.isBlank(redis_List)) {
			tianCategoryDTO.setpId(0L);
			list = tianCategoryDao.selectAll(tianCategoryDTO);
			for(TianCategoryVO tianCategoryVO : list) {
				tianCategoryDTO.setpId(tianCategoryVO.getId());
				List<TianCategoryVO> childer_list = tianCategoryDao.selectAll(tianCategoryDTO);
				tianCategoryVO.setChilder(childer_list);
			}
		}else {
			list = JsonMapper.getInstance().fromJson(redis_List, JsonMapper.getInstance().createCollectionType(List.class, TianCategoryVO.class));
		}
		return list;
	}

	

	
}
