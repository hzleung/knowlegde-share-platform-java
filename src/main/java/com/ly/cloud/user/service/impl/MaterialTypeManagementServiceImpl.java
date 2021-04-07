package com.ly.cloud.user.service.impl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.MaterialTypeManagement;
import com.ly.cloud.user.mapper.MaterialTypeManagementMapper;
import com.ly.cloud.user.service.MaterialTypeManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台素材类型管理表#MaterialTypeManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-01-07
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class MaterialTypeManagementServiceImpl extends ServiceImpl<MaterialTypeManagementMapper, MaterialTypeManagement> implements MaterialTypeManagementService {

    private final static Log logger = LogFactory.getLog(MaterialTypeManagementServiceImpl.class);

	@Autowired
	private MaterialTypeManagementMapper materialTypeManagementMapper;

	@Override
	public Page<MaterialTypeManagement> selectPage(Page<MaterialTypeManagement> page, MaterialTypeManagement dto) {
		page.setRecords(materialTypeManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public MaterialTypeManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(MaterialTypeManagement entity) {
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(MaterialTypeManagement entity) {
		return super.updateById(entity);
	}
}
