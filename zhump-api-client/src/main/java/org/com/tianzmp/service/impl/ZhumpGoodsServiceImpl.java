package org.com.tianzmp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.com.tianzmp.base.PageInfo;
import org.com.tianzmp.cache.RedisUtils;
import org.com.tianzmp.common.Constant;
import org.com.tianzmp.dao.ZhumpGoodsDao;
import org.com.tianzmp.dto.ZhumpGoodsDTO;
import org.com.tianzmp.service.ZhumpGoodsService;
import org.com.tianzmp.util.IdUtils;
import org.com.tianzmp.util.JsonMapper;
import org.com.tianzmp.vo.ZhumpGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import org.springframework.transaction.annotation.Transactional;


@Service("tianGoodsService")
public class ZhumpGoodsServiceImpl implements ZhumpGoodsService {
	
	@Autowired
	private ZhumpGoodsDao tianGoodsDao;
	
	@Autowired
	RedisUtils redisUtils;


	@Override
	public ZhumpGoodsVO findById(Long id){
		String  result = redisUtils.getRedis("goods_"+id);
		if(StringUtil.isEmpty(result)) {
			ZhumpGoodsVO tianGoodsVO = tianGoodsDao.findById(id);
			redisUtils.setRedis(Constant.redisEnumKey.GOODS.getKey()+id, JsonMapper.getInstance().toJson(tianGoodsVO));
			return tianGoodsVO; 
		}
		ZhumpGoodsVO tianGoodsVO = JsonMapper.getInstance().fromJson(result, ZhumpGoodsVO.class);
		return tianGoodsVO; 
	}


	@Override
	public PageInfo<ZhumpGoodsVO> pageInfoDTO(ZhumpGoodsDTO zhumpGoodsDTO)throws Exception {
		Integer count = tianGoodsDao.count(zhumpGoodsDTO);
		List<ZhumpGoodsVO> list = new ArrayList<ZhumpGoodsVO>();
		//缓存拿数据
		String redis_List = redisUtils.hGetRedis(Constant.redisEnumKey.GOODS_LIST.getKey(), zhumpGoodsDTO.getPage().toString());
		if(StringUtil.isEmpty(redis_List) && count > 0) {
			list = tianGoodsDao.pageInfoDTO(zhumpGoodsDTO);
			redisUtils.hSetRedis(Constant.redisEnumKey.GOODS_LIST.getKey(), zhumpGoodsDTO.getPage().toString(), list);
		}else {
			list = JsonMapper.getInstance().fromJson(redis_List, JsonMapper.getInstance().createCollectionType(List.class, ZhumpGoodsVO.class));
		}
		PageInfo<ZhumpGoodsVO> pg = new PageInfo<ZhumpGoodsVO>();
		pg.setCurrentPage(zhumpGoodsDTO.getPage());
		pg.setData(list);
		pg.setTotalCount(count);
		pg.setPageSize(zhumpGoodsDTO.getRows());
		return pg;
	}


	@Override
	public Boolean insert(ZhumpGoodsDTO zhumpGoodsDTO) throws Exception {
		zhumpGoodsDTO.setId(IdUtils.getInstance().nextId());
		Integer result = tianGoodsDao.insert(zhumpGoodsDTO);
		if(result > 0) {
			ZhumpGoodsVO findById = tianGoodsDao.findById(zhumpGoodsDTO.getId());
			//缓存单个商品
			redisUtils.setRedis(Constant.redisEnumKey.GOODS.getKey()+zhumpGoodsDTO.getId(), JsonMapper.getInstance().toJson(findById));
			//删除所有商品
			redisUtils.delRedis(Constant.redisEnumKey.GOODS_LIST.getKey());
		}
		return result > 0 ? true : false;
	}


	@Override
	public boolean edit(String name, Integer num, String picture, BigDecimal price, Long categoryId) throws Exception {
		ZhumpGoodsDTO zhumpGoodsDTO = new ZhumpGoodsDTO();
		zhumpGoodsDTO.setName(name);
		zhumpGoodsDTO.setPrice(price);
		zhumpGoodsDTO.setPicture(picture);
		zhumpGoodsDTO.setInventory(num);
		zhumpGoodsDTO.setCategoryId(categoryId);
		Integer result = tianGoodsDao.update(zhumpGoodsDTO);
		return result > 0 ? true : false;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean addGoodsNum(Integer num, Long goodsId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("goodsNum",num);
		map.put("id",goodsId);
		Integer result = tianGoodsDao.addGoodsNum(map);
		return result > 0 ? true : false;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean reduceGoodsNum(Integer num, Long goodsId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("goodsNum",num);
		map.put("id",goodsId);
		Integer result = tianGoodsDao.reduceGoodsNum(map);
		return result > 0 ? true : false;
	}

}
