package org.com.tianzmp.dao;

import org.com.tianzmp.vo.ZhumpChannelVO;

import java.util.List;

/**
 * 频道管理
 */
public interface ZhumpChannelDao {

    /**查询所有频道*/
    List<ZhumpChannelVO> selectAll();
}
