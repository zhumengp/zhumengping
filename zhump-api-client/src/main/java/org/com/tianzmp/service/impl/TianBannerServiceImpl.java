package org.com.tianzmp.service.impl;

import org.com.tianzmp.dao.TianBannerDao;
import org.com.tianzmp.service.TianBannerService;
import org.com.tianzmp.vo.TianBannerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tianBannerService")
public class TianBannerServiceImpl implements TianBannerService {

    @Autowired
    private TianBannerDao tianBannerDao;

    @Override
    public List<TianBannerVO> selectAll() {
        return tianBannerDao.selectAll();
    }
}
