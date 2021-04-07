package com.ly.cloud.user.service.impl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.LabelManagement;
import com.ly.cloud.user.mapper.LabelManagementMapper;
import com.ly.cloud.user.service.LabelManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台标签管理表#LabelManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class LabelManagementServiceImpl extends ServiceImpl<LabelManagementMapper, LabelManagement> implements LabelManagementService {

    private final static Log logger = LogFactory.getLog(LabelManagementServiceImpl.class);

	@Autowired
	private LabelManagementMapper labelManagementMapper;

	@Override
	public Page<LabelManagement> selectPage(Page<LabelManagement> page, LabelManagement dto) {
		page.setRecords(labelManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public LabelManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(LabelManagement entity) {
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(LabelManagement entity) {
		return super.updateById(entity);
	}
}
