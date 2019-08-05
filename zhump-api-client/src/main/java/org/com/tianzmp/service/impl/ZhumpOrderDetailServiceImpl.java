package org.com.tianzmp.service.impl;

import org.com.tianzmp.dao.ZhumpOrderDetailDao;
import org.com.tianzmp.dto.ZhumpOrderDetailDTO;
import org.com.tianzmp.service.ZhumpOrderDetailService;
import org.com.tianzmp.vo.ZhumpOrderDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单详情列表
 */
@Service("tianOrderDetailService")
public class ZhumpOrderDetailServiceImpl implements ZhumpOrderDetailService {

    @Autowired
    private ZhumpOrderDetailDao tianOrderDetailDao;

    /**
     * 查询用户订单列表
     * @param userId
     * @return
     */
    @Override
    public List<ZhumpOrderDetailVO> selectAll(Long userId) {
        ZhumpOrderDetailDTO tianOrderDetailDTO = new ZhumpOrderDetailDTO();
        tianOrderDetailDTO.setUserId(userId);
        List<ZhumpOrderDetailVO> tianOrderDetailVOS = tianOrderDetailDao.selectAll(tianOrderDetailDTO);
        return tianOrderDetailVOS;
    }

    /**
     * 用户单个订单列表
     * @param orderId
     * @return
     */
    @Override
    public List<ZhumpOrderDetailVO> findByOrderId(Long orderId) {
        ZhumpOrderDetailDTO tianOrderDetailDTO = new ZhumpOrderDetailDTO();
        tianOrderDetailDTO.setOrderId(orderId);
        List<ZhumpOrderDetailVO> tianOrderDetailVOS = tianOrderDetailDao.selectAll(tianOrderDetailDTO);
        return tianOrderDetailVOS;
    }
}
