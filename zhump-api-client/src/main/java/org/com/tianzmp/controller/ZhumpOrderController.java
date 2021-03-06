package org.com.tianzmp.controller;

import org.apache.commons.collections.CollectionUtils;
import org.com.tianzmp.common.result.ZhumpResultBase;
import org.com.tianzmp.common.result.ZhumpResultStatus;
import org.com.tianzmp.dto.ZhumpOrderDTO;
import org.com.tianzmp.entity.response.ResponseOrderDetail;
import org.com.tianzmp.entity.response.ResponseOrderDetail.ResponseOrderDetail_Item;
import org.com.tianzmp.exception.BusinessException;
import org.com.tianzmp.service.ZhumpCartService;
import org.com.tianzmp.service.ZhumpOrderDetailService;
import org.com.tianzmp.service.ZhumpOrderService;
import org.com.tianzmp.vo.ZhumpCartVO;
import org.com.tianzmp.vo.ZhumpOrderDetailVO;
import org.com.tianzmp.vo.ZhumpOrderVO;
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
public class ZhumpOrderController {

    private final Logger log = LoggerFactory.getLogger(ZhumpOrderController.class);

    @Autowired
    private ZhumpOrderService tianOrderService;

    @Autowired
    private ZhumpOrderDetailService tianOrderDetailService;

    @Autowired
    private ZhumpCartService tianCartService;

    /**
     * 创建订单
     */
    @RequestMapping(value="/create",method =  RequestMethod.POST)
    @ResponseBody
    public Object create(Long userId,@RequestParam("cartIds")List<Long> cartIds) {
        try {
            boolean result = tianOrderService.createOrder(userId,cartIds);
            if (!result){
                return new ZhumpResultBase(ZhumpResultStatus.FALI,null);
            }
            return new ZhumpResultBase(ZhumpResultStatus.SUCCESS,null);
        } catch (Exception e) {
            log.error("系统异常",e);
            if (e instanceof BusinessException){
                return new ZhumpResultBase(ZhumpResultStatus.FALI,e.getMessage());
            }
            return new ZhumpResultBase(ZhumpResultStatus.ERROR, null);
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
            return new ZhumpResultBase(ZhumpResultStatus.PARMSERROR,null);
        }
      try {
          ZhumpOrderVO tianOrderVO = tianOrderService.findById(id);
          if (tianOrderVO == null || tianOrderVO.getUserId().longValue() != userId.intValue()){
              log.error("【订单详情】：发现查询订单的用户信息不匹配");
              return new ZhumpResultBase(ZhumpResultStatus.FALI,"用户id不匹配");
          }
          ResponseOrderDetail responseOrderDetail = new ResponseOrderDetail();
          if (tianOrderVO != null) {
              BeanUtils.copyProperties(tianOrderVO,responseOrderDetail);
              List<ZhumpOrderDetailVO> tianOrderDetailVOS = tianOrderDetailService.findByOrderId(tianOrderVO.getOrderId());
              List<ResponseOrderDetail_Item> list = new ArrayList<ResponseOrderDetail_Item>();
              for (ZhumpOrderDetailVO tianOrderDetailVO : tianOrderDetailVOS){
                  ResponseOrderDetail_Item responseOrderDetail_item = new ResponseOrderDetail_Item();
                  responseOrderDetail_item.setName(tianOrderDetailVO.getTianGoodsVO().getName());
                  responseOrderDetail_item.setPicture(tianOrderDetailVO.getTianGoodsVO().getPicture());
                  responseOrderDetail_item.setPrice(tianOrderDetailVO.getTianGoodsVO().getPrice());
                  responseOrderDetail_item.setGoodsId(tianOrderDetailVO.getGoodsId());
                  responseOrderDetail_item.setTotalPrice(tianOrderDetailVO.getTotalPrice());
                  ZhumpCartVO byGoodsId = tianCartService.findByGoodsId(tianOrderDetailVO.getGoodsId(),userId);
                  responseOrderDetail_item.setBuyGoodsNum(byGoodsId.getGoodsNum());
                  list.add(responseOrderDetail_item);
              }
              responseOrderDetail.setOrderDetail(list);
              return new ZhumpResultBase(ZhumpResultStatus.SUCCESS,responseOrderDetail);
          }else{
              return new ZhumpResultBase(ZhumpResultStatus.FALI,null);
          }
      }catch (Exception e){
          log.error("系统异常",e);
          return new ZhumpResultBase(ZhumpResultStatus.ERROR,null);
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
            return new ZhumpResultBase(ZhumpResultStatus.PARMSERROR,null);
        }
        try {
            ZhumpOrderDTO tianOrderDTO = new ZhumpOrderDTO();
            tianOrderDTO.setUserId(userId);
            List<ZhumpOrderVO> zhumpOrderVOS = tianOrderService.selectAll(tianOrderDTO);
            List<ResponseOrderDetail> list = new ArrayList<ResponseOrderDetail>();
            if (CollectionUtils.isNotEmpty(zhumpOrderVOS)){
                for (ZhumpOrderVO zhumpOrderVO : zhumpOrderVOS){
                    ResponseOrderDetail responseOrderDetail = new ResponseOrderDetail();
                    BeanUtils.copyProperties(zhumpOrderVO,responseOrderDetail);
                    List<ZhumpOrderDetailVO> orderList = tianOrderDetailService.findByOrderId(zhumpOrderVO.getOrderId());
                    List<ResponseOrderDetail_Item> responseOrderDetail_items = new ArrayList<>();
                    for (ZhumpOrderDetailVO zhumpOrderDetailVO : orderList){
                        ResponseOrderDetail_Item responseOrderDetail_item = new ResponseOrderDetail_Item();
                        responseOrderDetail_item.setName(zhumpOrderDetailVO.getTianGoodsVO().getName());
                        responseOrderDetail_item.setPicture(zhumpOrderDetailVO.getTianGoodsVO().getPicture());
                        responseOrderDetail_item.setPrice(zhumpOrderDetailVO.getTianGoodsVO().getPrice());
                        responseOrderDetail_item.setGoodsId(zhumpOrderDetailVO.getGoodsId());
                        responseOrderDetail_item.setTotalPrice(zhumpOrderDetailVO.getTotalPrice());
                        ZhumpCartVO byGoodsId = tianCartService.findByGoodsId(zhumpOrderDetailVO.getGoodsId(),userId);
                        responseOrderDetail_item.setBuyGoodsNum(byGoodsId.getGoodsNum());
                        responseOrderDetail_items.add(responseOrderDetail_item);
                        responseOrderDetail.setOrderDetail(responseOrderDetail_items);
                    }
                    list.add(responseOrderDetail);
                }
                return new ZhumpResultBase(ZhumpResultStatus.SUCCESS,list);
            }
            return new ZhumpResultBase(ZhumpResultStatus.FALI,null);
        }catch (Exception e){
            log.error("系统异常",e);
            return new ZhumpResultBase(ZhumpResultStatus.ERROR,null);
        }
    }

    /**
     * 取消订单
     */
    @RequestMapping(value = "/cancelOrder",method = RequestMethod.POST)
    @ResponseBody
    public Object cancelOrder(Long id){
        try {
            boolean falg = tianOrderService.cancelOrder(id);
            if (!falg){
                return new ZhumpResultBase(ZhumpResultStatus.FALI,null);
            }
            return new ZhumpResultBase(ZhumpResultStatus.SUCCESS,null);
        }catch (Exception e){
            log.error("系统异常",e);
            return new ZhumpResultBase(ZhumpResultStatus.ERROR,null);
        }
    }

    /**
     * 取消订单
     */
    @RequestMapping(value = "/deleteOrder",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteOrder(Long id){
        try {
            boolean falg = tianOrderService.deleteOrder(id);
            if (!falg){
                return new ZhumpResultBase(ZhumpResultStatus.FALI,null);
            }
            return new ZhumpResultBase(ZhumpResultStatus.SUCCESS,null);
        }catch (Exception e){
            log.error("系统异常",e);
            return new ZhumpResultBase(ZhumpResultStatus.ERROR,null);
        }
    }
}
