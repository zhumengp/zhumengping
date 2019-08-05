package org.com.tianzmp.controller;


import java.util.List;

import javax.annotation.Resource;

import org.com.tianzmp.common.result.Result;
import org.com.tianzmp.common.result.ResultStatus;
import org.com.tianzmp.dto.TianCartDTO;
import org.com.tianzmp.service.TianCartService;
import org.com.tianzmp.vo.TianCartVO;
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
public class TianCartController {

	private final Logger log = LoggerFactory.getLogger(TianCartController.class);

	@Resource(name="tianCartService")
	private TianCartService tianCartService;

	/**
	 * 商品加入购物车
	 * @param goodsId
	 * @param goodsNum
	 * @return
	 */
	@RequestMapping(value="/insert")
	@ResponseBody
	public Object insert(Long user_id,Long goodsId,Integer goodsNum) {
		try {
			boolean addCart = tianCartService.save(user_id,goodsId, goodsNum);
			if(addCart) {
				return new Result(ResultStatus.SUCCESS, null);
			}else {
				return new Result(ResultStatus.FALI, null);
			}
		} catch (Exception e) {
			log.error("系统异常",e);
			return new Result(ResultStatus.ERROR, null);
		}
	}
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list(Long user_id,Integer page,Integer rows) {
		try {
			TianCartDTO tianCartDTO = new TianCartDTO();
			if(page == null || user_id == null) {
				return new Result(ResultStatus.PARMSERROR, null);
			}
			tianCartDTO.setUserId(user_id);
			tianCartDTO.setPage(page);
			if(rows != null) {
				tianCartDTO.setRows(rows);
			}
			List<TianCartVO> list = tianCartService.pageInfoDTO(tianCartDTO);
			return new Result(ResultStatus.SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常",e);
			return new Result(ResultStatus.ERROR, null);
		}
	}
}
