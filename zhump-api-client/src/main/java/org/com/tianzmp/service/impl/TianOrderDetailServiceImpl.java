package org.com.tianzmp.service.impl;

import org.com.tianzmp.dao.TianOrderDetailDao;
import org.com.tianzmp.dto.TianOrderDetailDTO;
import org.com.tianzmp.entity.TianOrderDetail;
import org.com.tianzmp.service.TianOrderDetailService;
import org.com.tianzmp.vo.TianOrderDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单详情列表
 */
@Service("tianOrderDetailService")
public class TianOrderDetailServiceImpl implements TianOrderDetailService {

    @Autowired
    private TianOrderDetailDao tianOrderDetailDao;

    /**
     * 查询用户订单列表
     * @param userId
     * @return
     */
    @Override
    public List<TianOrderDetailVO> selectAll(Long userId) {
        TianOrderDetailDTO tianOrderDetailDTO = new TianOrderDetailDTO();
        tianOrderDetailDTO.setUserId(userId);
        List<TianOrderDetailVO> tianOrderDetailVOS = tianOrderDetailDao.selectAll(tianOrderDetailDTO);
        return tianOrderDetailVOS;
    }

    /**
     * 用户单个订单列表
     * @param orderId
     * @return
     */
    @Override
    public List<TianOrderDetailVO> findByOrderId(Long orderId) {
        TianOrderDetailDTO tianOrderDetailDTO = new TianOrderDetailDTO();
        tianOrderDetailDTO.setOrderId(orderId);
        List<TianOrderDetailVO> tianOrderDetailVOS = tianOrderDetailDao.selectAll(tianOrderDetailDTO);
        return tianOrderDetailVOS;
    }
}
