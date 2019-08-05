package org.com.tianzmp.service.impl;

import org.com.tianzmp.dao.TianChannelDao;
import org.com.tianzmp.service.TianChannelService;
import org.com.tianzmp.vo.TianChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tianChannelService")
public class TianChannelServiceImpl implements TianChannelService {

    @Autowired
    private TianChannelDao tianChannelDao;

    @Override
    public List<TianChannelVO> selectAll() {
        return tianChannelDao.selectAll();
    }
}
