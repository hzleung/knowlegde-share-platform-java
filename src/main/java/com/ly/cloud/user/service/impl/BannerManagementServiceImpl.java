package com.ly.cloud.user.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.BannerManagement;
import com.ly.cloud.user.mapper.BannerManagementMapper;
import com.ly.cloud.user.service.BannerManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台banner表#BannerManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-12
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class BannerManagementServiceImpl extends ServiceImpl<BannerManagementMapper, BannerManagement> implements BannerManagementService {

    private final static Log logger = LogFactory.getLog(BannerManagementServiceImpl.class);

	@Autowired
	private BannerManagementMapper bannerManagementMapper;

	@Override
	public Page<BannerManagement> selectPage(Page<BannerManagement> page, BannerManagement dto) {
		page.setRecords(bannerManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public BannerManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(BannerManagement entity) {
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(BannerManagement entity) {
		return super.updateById(entity);
	}

	@Override
	public List<BannerManagement> selectByKeywords(String keywords) {
		return bannerManagementMapper.queryByKeywords(keywords);
	}

	@Override
	public List<BannerManagement> selectAllBanner(String bannerStatus) {
		if(bannerStatus != null) {
			Number status = BigDecimal.valueOf(Long.parseLong(bannerStatus));
			return bannerManagementMapper.queryAllByBannerStatus(status);
		}
		return bannerManagementMapper.queryAll();
	}
}
