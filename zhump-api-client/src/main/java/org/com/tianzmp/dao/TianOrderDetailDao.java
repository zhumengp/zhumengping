package org.com.tianzmp.dao;

import org.com.tianzmp.dto.TianOrderDetailDTO;
import org.com.tianzmp.vo.TianOrderDetailVO;

import java.util.List;

public interface TianOrderDetailDao {

    /**订单明细入库*/
    Integer insert(TianOrderDetailDTO tianOrderDetailDTO);
    /**查询所有*/
    List<TianOrderDetailVO> selectAll(TianOrderDetailDTO tianOrderDetailDTO);
}
