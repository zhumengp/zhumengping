package org.com.tianzmp.service.impl;

import org.com.tianzmp.dao.ZhumpChannelDao;
import org.com.tianzmp.service.ZhumpChannelService;
import org.com.tianzmp.vo.ZhumpChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tianChannelService")
public class ZhumpChannelServiceImpl implements ZhumpChannelService {

    @Autowired
    private ZhumpChannelDao tianChannelDao;

    @Override
    public List<ZhumpChannelVO> selectAll() {
        return tianChannelDao.selectAll();
    }
}
