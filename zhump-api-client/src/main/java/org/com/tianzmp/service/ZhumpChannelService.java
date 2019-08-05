package org.com.tianzmp.service;

import org.com.tianzmp.vo.ZhumpChannelVO;

import java.util.List;

public interface ZhumpChannelService {

    /**查询所有频道*/
    List<ZhumpChannelVO> selectAll();
}
