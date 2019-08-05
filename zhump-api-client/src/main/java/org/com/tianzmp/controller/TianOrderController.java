package org.com.tianzmp.controller;

import org.apache.commons.collections.CollectionUtils;
import org.com.tianzmp.common.result.Result;
import org.com.tianzmp.common.result.ResultStatus;
import org.com.tianzmp.dto.TianOrderDTO;
import org.com.tianzmp.entity.response.ResponseOrderDetail;
import org.com.tianzmp.entity.response.ResponseOrderDetail.ResponseOrderDetail_Item;
import org.com.tianzmp.exception.BusinessException;
import org.com.tianzmp.service.TianCartService;
import org.com.tianzmp.service.TianOrderDetailService;
import org.com.tianzmp.service.TianOrderService;
import org.com.tianzmp.vo.TianCartVO;
import org.com.tianzmp.vo.TianOrderDetailVO;
import org.com.tianzmp.vo.TianOrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * 订单处理类
 */
@Controller
@RequestMapping(value="order")
public class TianOrderController {

    private final Logger log = LoggerFactory.getLogger(TianOrderController.class);

    @Autowired
    private TianOrderService tianOrderService;

    @Autowired
    private TianOrderDetailService tianOrderDetailService;

    @Autowired
    private TianCartService tianCartService;

    /**
     * 创建订单
     */
    @RequestMapping(value="/create",method =  RequestMethod.POST)
    @ResponseBody
    public Object create(Long userId,@RequestParam("cartIds")List<Long> cartIds) {
        try {
            boolean result = tianOrderService.createOrder(userId,cartIds);
            if (!result){
                return new Result(ResultStatus.FALI,null);
            }
            return new Result(ResultStatus.SUCCESS,null);
        } catch (Exception e) {
            log.error("系统异常",e);
            if (e instanceof BusinessException){
                return new Result(ResultStatus.FALI,e.getMessage());
            }
            return new Result(ResultStatus.ERROR, null);
        }
    }

    /**
     * 查询单个订单
     * @param userId 用户id
     * @param id 订单id
     * @return
     */
    @RequestMapping(value="detail",method=RequestMethod.GET)
    @ResponseBody
    public Object detail(Long userId,Long id){
        if (userId == null || id == null){
            return new Result(ResultStatus.PARMSERROR,null);
        }
      try {
          TianOrderVO tianOrderVO = tianOrderService.findById(id);
          if (tianOrderVO.getUserId().longValue() != userId.intValue()){
              return new Result(ResultStatus.FALI,"用户id不匹配");
          }
          ResponseOrderDetail responseOrderDetail = new ResponseOrderDetail();
          if (tianOrderVO != null) {
              BeanUtils.copyProperties(tianOrderVO,responseOrderDetail);
              List<TianOrderDetailVO> tianOrderDetailVOS = tianOrderDetailService.findByOrderId(tianOrderVO.getOrderId());
              List<ResponseOrderDetail_Item> list = new ArrayList<ResponseOrderDetail_Item>();
              for (TianOrderDetailVO tianOrderDetailVO : tianOrderDetailVOS){
                  ResponseOrderDetail_Item responseOrderDetail_item = new ResponseOrderDetail_Item();
                  responseOrderDetail_item.setName(tianOrderDetailVO.getTianGoodsVO().getName());
                  responseOrderDetail_item.setPicture(tianOrderDetailVO.getTianGoodsVO().getPicture());
                  responseOrderDetail_item.setPrice(tianOrderDetailVO.getTianGoodsVO().getPrice());
                  responseOrderDetail_item.setGoodsId(tianOrderDetailVO.getGoodsId());
                  responseOrderDetail_item.setTotalPrice(tianOrderDetailVO.getTotalPrice());
                  TianCartVO byGoodsId = tianCartService.findByGoodsId(tianOrderDetailVO.getGoodsId());
                  responseOrderDetail_item.setBuyGoodsNum(byGoodsId.getGoodsNum());
                  list.add(responseOrderDetail_item);
              }
              responseOrderDetail.setOrderDetail(list);
              return new Result(ResultStatus.SUCCESS,responseOrderDetail);
          }else{
              return new Result(ResultStatus.FALI,null);
          }
      }catch (Exception e){
          log.error("系统异常",e);
          return new Result(ResultStatus.ERROR,null);
      }
    }

    /**
     * 查询用户的订单列表
     * @param userId 用户
     * @return
     */
    @RequestMapping(value="list",method=RequestMethod.GET)
    @ResponseBody
    public Object list(Long userId){
        if (userId == null){
            return new Result(ResultStatus.PARMSERROR,null);
        }
        try {
            TianOrderDTO tianOrderDTO = new TianOrderDTO();
            tianOrderDTO.setUserId(userId);
            List<TianOrderVO> tianOrderVOS = tianOrderService.selectAll(tianOrderDTO);
            List<ResponseOrderDetail> list = new ArrayList<ResponseOrderDetail>();
            if (CollectionUtils.isNotEmpty(tianOrderVOS)){
                for (TianOrderVO tianOrderVO : tianOrderVOS){
                    ResponseOrderDetail responseOrderDetail = new ResponseOrderDetail();
                    BeanUtils.copyProperties(tianOrderVO,responseOrderDetail);
                    List<TianOrderDetailVO> orderList = tianOrderDetailService.findByOrderId(tianOrderVO.getOrderId());
                    List<ResponseOrderDetail_Item> responseOrderDetail_items = new ArrayList<>();
                    for (TianOrderDetailVO tianOrderDetailVO : orderList){
                        ResponseOrderDetail_Item responseOrderDetail_item = new ResponseOrderDetail_Item();
                        responseOrderDetail_item.setName(tianOrderDetailVO.getTianGoodsVO().getName());
                        responseOrderDetail_item.setPicture(tianOrderDetailVO.getTianGoodsVO().getPicture());
                        responseOrderDetail_item.setPrice(tianOrderDetailVO.getTianGoodsVO().getPrice());
                        responseOrderDetail_item.setGoodsId(tianOrderDetailVO.getGoodsId());
                        responseOrderDetail_item.setTotalPrice(tianOrderDetailVO.getTotalPrice());
                        TianCartVO byGoodsId = tianCartService.findByGoodsId(tianOrderDetailVO.getGoodsId());
                        responseOrderDetail_item.setBuyGoodsNum(byGoodsId.getGoodsNum());
                        responseOrderDetail_items.add(responseOrderDetail_item);
                        responseOrderDetail.setOrderDetail(responseOrderDetail_items);
                    }
                    list.add(responseOrderDetail);
                }
                return new Result(ResultStatus.SUCCESS,list);
            }
            return new Result(ResultStatus.FALI,null);
        }catch (Exception e){
            log.error("系统异常",e);
            return new Result(ResultStatus.ERROR,null);
        }
    }

}
