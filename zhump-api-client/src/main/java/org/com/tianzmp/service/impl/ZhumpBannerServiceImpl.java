package org.com.tianzmp.service.impl;

import org.com.tianzmp.dao.ZhumpBannerDao;
import org.com.tianzmp.service.ZhumpBannerService;
import org.com.tianzmp.vo.ZhumpBannerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tianBannerService")
public class ZhumpBannerServiceImpl implements ZhumpBannerService {

    @Autowired
    private ZhumpBannerDao tianBannerDao;

    @Override
    public List<ZhumpBannerVO> selectAll() {
        return tianBannerDao.selectAll();
    }
}
