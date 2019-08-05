package org.com.tianzmp.service.impl;

import org.com.tianzmp.common.OrderEnum;
import org.com.tianzmp.dao.ZhumpOrderDao;
import org.com.tianzmp.dao.ZhumpOrderDetailDao;
import org.com.tianzmp.dto.ZhumpOrderDTO;
import org.com.tianzmp.dto.ZhumpOrderDetailDTO;
import org.com.tianzmp.exception.BusinessException;
import org.com.tianzmp.service.ZhumpCartService;
import org.com.tianzmp.service.ZhumpGoodsService;
import org.com.tianzmp.service.ZhumpOrderService;
import org.com.tianzmp.util.IdUtils;
import org.com.tianzmp.vo.ZhumpCartVO;
import org.com.tianzmp.vo.ZhumpGoodsVO;
import org.com.tianzmp.vo.ZhumpOrderVO;
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
public class ZhumpOrderServiceImpl implements ZhumpOrderService {

    private final Logger log = LoggerFactory.getLogger(ZhumpOrderServiceImpl.class);

    @Autowired
    private ZhumpOrderDao tianOrderDao;

    @Autowired
    private ZhumpOrderDetailDao tianOrderDetailDao;

    @Autowired
    private ZhumpCartService tianCartService;

    @Autowired
    private ZhumpGoodsService tianGoodsService;

    @Override
    public List<ZhumpOrderVO> selectAll(ZhumpOrderDTO tianOrderDTO) {
        List<ZhumpOrderVO> tianOrderVOS = tianOrderDao.selectAll(tianOrderDTO);
        return tianOrderVOS;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public  boolean createOrder(Long userId,List<Long> cartIds) throws Exception{
        ZhumpOrderDTO tianOrderDTO = new ZhumpOrderDTO();
        BigDecimal bigDecimal = new BigDecimal(0);
        for (Long cartId : cartIds){
            ZhumpCartVO tianCartVO = tianCartService.findById(cartId);
            if (tianCartVO == null){
                log.error("【订单处理】：购物车没有此商品");
                throw new BusinessException("没有添加此商品");
            }
            ZhumpGoodsVO tianGoodsVO = tianGoodsService.findById(tianCartVO.getGoodsId());
            if (tianGoodsVO == null || tianGoodsVO.getInventory().intValue() == 0 || tianGoodsVO.getInventory() == null){
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
            ZhumpOrderDetailDTO zhumpOrderDetailDTO = new ZhumpOrderDetailDTO();
            ZhumpCartVO zhumpCartVO = tianCartService.findById(cartId);
            ZhumpGoodsVO tianGoodsVO = tianGoodsService.findById(zhumpCartVO.getGoodsId());
            BigDecimal total_price = new BigDecimal(zhumpCartVO.getGoodsNum()).multiply(tianGoodsVO.getPrice());
            zhumpOrderDetailDTO.setGoodsId(zhumpCartVO.getGoodsId());
            zhumpOrderDetailDTO.setOrderId(id);
            zhumpOrderDetailDTO.setTotalPrice(total_price);
            Integer insert = tianOrderDetailDao.insert(zhumpOrderDetailDTO);
            if (insert < 0){
                log.error("【订单处理】：订单明细表入库失败");
                throw new BusinessException("订单明细表入库失败");
            }
        }
        log.info("【订单处理】：创建订单成功,订单id为："+id);
        return true;
    }

    @Override
    public ZhumpOrderVO findById(Long id) {
        return tianOrderDao.findById(id);
    }

    /**用户成功取消订单*/
    @Override
    public boolean cancelOrder(Long id) {
        ZhumpOrderDTO zhumpOrderDTO = new ZhumpOrderDTO();
        zhumpOrderDTO.setId(id);
        zhumpOrderDTO.setStatus(OrderEnum.MANUAL_CANCEL_ORDER.getStatus());
        Integer result = tianOrderDao.update(zhumpOrderDTO);
        if (result < 0){
            log.error("【订单处理】:取消订单失败");
            return false;
        }
        log.info("【订单处理】:用户成功取消订单,订单主键为："+id);
        return true;
    }

    @Override
    public boolean deleteOrder(Long id) {
        Integer delete = tianOrderDao.delete(id);
        if (delete < 0){
            log.error("【订单处理】:删除订单失败");
            return false;
        }
        log.info("【订单处理】:用户成功删除订单,订单主键为："+id);
        return true;
    }
}
