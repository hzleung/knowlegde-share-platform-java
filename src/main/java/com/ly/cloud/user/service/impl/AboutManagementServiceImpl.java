package com.ly.cloud.user.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ly.cloud.user.entity.ProduceType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.AboutManagement;
import com.ly.cloud.user.mapper.AboutManagementMapper;
import com.ly.cloud.user.service.AboutManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台关于模块表#AboutManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class AboutManagementServiceImpl extends ServiceImpl<AboutManagementMapper, AboutManagement> implements AboutManagementService {

    private final static Log logger = LogFactory.getLog(AboutManagementServiceImpl.class);

	@Autowired
	private AboutManagementMapper aboutManagementMapper;

	@Override
	public Page<AboutManagement> selectPage(Page<AboutManagement> page, AboutManagement dto) {
		page.setRecords(aboutManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public List<AboutManagement> selectAll() {
		return aboutManagementMapper.query();
	}

	@Override
	public AboutManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(AboutManagement entity) {
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(AboutManagement entity) {
		return super.updateById(entity);
	}
}
