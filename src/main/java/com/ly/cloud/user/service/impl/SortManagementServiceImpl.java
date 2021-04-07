package com.ly.cloud.user.service.impl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.SortManagement;
import com.ly.cloud.user.mapper.SortManagementMapper;
import com.ly.cloud.user.service.SortManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台分类管理表#SortManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class SortManagementServiceImpl extends ServiceImpl<SortManagementMapper, SortManagement> implements SortManagementService {

    private final static Log logger = LogFactory.getLog(SortManagementServiceImpl.class);

	@Autowired
	private SortManagementMapper sortManagementMapper;

	@Override
	public Page<SortManagement> selectPage(Page<SortManagement> page, SortManagement dto) {
		page.setRecords(sortManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public SortManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(SortManagement entity) {
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(SortManagement entity) {
		return super.updateById(entity);
	}
}
