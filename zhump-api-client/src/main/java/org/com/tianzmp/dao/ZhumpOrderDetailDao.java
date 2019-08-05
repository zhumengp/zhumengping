package org.com.tianzmp.dao;

import org.com.tianzmp.dto.ZhumpOrderDetailDTO;
import org.com.tianzmp.vo.ZhumpOrderDetailVO;

import java.util.List;

public interface ZhumpOrderDetailDao {

    /**订单明细入库*/
    Integer insert(ZhumpOrderDetailDTO tianOrderDetailDTO);
    /**查询所有*/
    List<ZhumpOrderDetailVO> selectAll(ZhumpOrderDetailDTO tianOrderDetailDTO);
}
