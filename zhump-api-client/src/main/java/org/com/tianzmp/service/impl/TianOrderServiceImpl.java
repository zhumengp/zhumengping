package org.com.tianzmp.service.impl;

import org.com.tianzmp.common.OrderEnum;
import org.com.tianzmp.dao.TianOrderDao;
import org.com.tianzmp.dao.TianOrderDetailDao;
import org.com.tianzmp.dto.TianOrderDTO;
import org.com.tianzmp.dto.TianOrderDetailDTO;
import org.com.tianzmp.exception.BusinessException;
import org.com.tianzmp.service.TianCartService;
import org.com.tianzmp.service.TianGoodsService;
import org.com.tianzmp.service.TianOrderService;
import org.com.tianzmp.util.IdUtils;
import org.com.tianzmp.vo.TianCartVO;
import org.com.tianzmp.vo.TianGoodsVO;
import org.com.tianzmp.vo.TianOrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单处理服务层
 */
@Service("tianOrderService")
public class TianOrderServiceImpl implements TianOrderService {

    private final Logger log = LoggerFactory.getLogger(TianOrderServiceImpl.class);

    @Autowired
    private TianOrderDao tianOrderDao;

    @Autowired
    private TianOrderDetailDao tianOrderDetailDao;

    @Autowired
    private TianCartService tianCartService;

    @Autowired
    private TianGoodsService tianGoodsService;

    @Override
    public List<TianOrderVO> selectAll(TianOrderDTO tianOrderDTO) {
        List<TianOrderVO> tianOrderVOS = tianOrderDao.selectAll(tianOrderDTO);
        return tianOrderVOS;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public  boolean createOrder(Long userId,List<Long> cartIds) throws Exception{
        TianOrderDTO tianOrderDTO = new TianOrderDTO();
        BigDecimal bigDecimal = new BigDecimal(0);
        for (Long cartId : cartIds){
            TianCartVO tianCartVO = tianCartService.findById(cartId);
            if (tianCartVO == null){
                log.error("【订单处理】：购物车没有此商品");
                throw new BusinessException("没有添加此商品");
            }
            TianGoodsVO tianGoodsVO = tianGoodsService.findById(tianCartVO.getGoodsId());
            if (tianGoodsVO == null){
                log.error("【订单处理】：商品库存没有此商品");
                throw new BusinessException("商品库没有此商品");
            }
            if (tianGoodsVO.getInventory().intValue() == 0 || tianGoodsVO.getInventory() == null){
                log.error("【订单处理】：该商品库存已被抢光");
                throw new BusinessException("该商品库存为0");
            }
            bigDecimal = bigDecimal.add(new BigDecimal(tianCartVO.getGoodsNum()).multiply(tianGoodsVO.getPrice()));
        }
        long id = IdUtils.getInstance().nextId();
        tianOrderDTO.setUserId(userId);
        tianOrderDTO.setOrderId(id);
        tianOrderDTO.setStatus(OrderEnum.SUBMIT_ORDER.getStatus());
        tianOrderDTO.setPayStatus(OrderEnum.PAY_DEFAULT_ORDER.getStatus());
        tianOrderDTO.setPrice(bigDecimal);
        Integer createResult = tianOrderDao.insert(tianOrderDTO);
        if (createResult < 0){
            log.error("【订单处理】：订单主表入库失败");
            throw new BusinessException("订单入库失败");
        }
        //订单明细入库
        for (Long cartId : cartIds){
            TianOrderDetailDTO tianOrderDetailDTO = new TianOrderDetailDTO();
            TianCartVO tianCartVO = tianCartService.findById(cartId);
            TianGoodsVO tianGoodsVO = tianGoodsService.findById(tianCartVO.getGoodsId());
            BigDecimal total_price = new BigDecimal(tianCartVO.getGoodsNum()).multiply(tianGoodsVO.getPrice());
            tianOrderDetailDTO.setGoodsId(tianCartVO.getGoodsId());
            tianOrderDetailDTO.setOrderId(id);
            tianOrderDetailDTO.setTotalPrice(total_price);
            Integer insert = tianOrderDetailDao.insert(tianOrderDetailDTO);
            if (insert < 0){
                log.error("【订单处理】：订单明细表入库失败");
                throw new BusinessException("订单明细表入库失败");
            }
        }
        log.info("【订单处理】：创建订单成功,订单id为："+id);
        return true;
    }

    @Override
    public TianOrderVO findById(Long id) {
        return tianOrderDao.findById(id);
    }

    @Override
    public Integer update(TianOrderDTO tianOrderDTO) {
        return null;
    }
}
