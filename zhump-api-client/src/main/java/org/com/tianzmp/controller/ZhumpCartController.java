package org.com.tianzmp.controller;


import java.util.List;

import javax.annotation.Resource;

import org.com.tianzmp.common.result.ZhumpResultBase;
import org.com.tianzmp.common.result.ZhumpResultStatus;
import org.com.tianzmp.dto.ZhumpCartDTO;
import org.com.tianzmp.service.ZhumpCartService;
import org.com.tianzmp.vo.ZhumpCartVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 购物车
 * @author zhump
 *
 */
@Controller
@RequestMapping(value="cart")
public class ZhumpCartController {

	private final Logger log = LoggerFactory.getLogger(ZhumpCartController.class);

	@Resource(name="tianCartService")
	private ZhumpCartService tianCartService;

	/**
	 * 商品加入购物车
	 * @param goodsId
	 * @param goodsNum
	 * @return
	 */
	@RequestMapping(value="/insert")
	@ResponseBody
	public Object insert(Long user_id,Long goodsId,Integer goodsNum) {
		if (user_id == null || goodsId == null || goodsNum == null){
			return new ZhumpResultBase(ZhumpResultStatus.PARMSERROR,null);
		}
		try {
			boolean addCart = tianCartService.save(user_id,goodsId, goodsNum);
			if(addCart) {
				return new ZhumpResultBase(ZhumpResultStatus.SUCCESS, null);
			}else {
				return new ZhumpResultBase(ZhumpResultStatus.FALI, null);
			}
		} catch (Exception e) {
			log.error("系统异常",e);
			return new ZhumpResultBase(ZhumpResultStatus.ERROR, null);
		}
	}
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list(Long user_id,Integer page,Integer rows) {
		try {
			ZhumpCartDTO tianCartDTO = new ZhumpCartDTO();
			if(page == null || user_id == null) {
				return new ZhumpResultBase(ZhumpResultStatus.PARMSERROR, null);
			}
			tianCartDTO.setUserId(user_id);
			tianCartDTO.setPage(page);
			if(rows != null) {
				tianCartDTO.setRows(rows);
			}
			List<ZhumpCartVO> list = tianCartService.pageInfoDTO(tianCartDTO);
			return new ZhumpResultBase(ZhumpResultStatus.SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常",e);
			return new ZhumpResultBase(ZhumpResultStatus.ERROR, null);
		}
	}
}
