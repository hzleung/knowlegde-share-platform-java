package com.ly.cloud.user.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.RoleManagement;
import com.ly.cloud.user.mapper.RoleManagementMapper;
import com.ly.cloud.user.service.RoleManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台角色管理表#RoleManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-21
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class RoleManagementServiceImpl extends ServiceImpl<RoleManagementMapper, RoleManagement> implements RoleManagementService {

    private final static Log logger = LogFactory.getLog(RoleManagementServiceImpl.class);

	@Autowired
	private RoleManagementMapper roleManagementMapper;

	@Override
	public Page<RoleManagement> selectPage(Page<RoleManagement> page, RoleManagement dto) {
		page.setRecords(roleManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public List<RoleManagement> selectAllRole() {
		return roleManagementMapper.queryAllRole();
	}

	@Override
	public RoleManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(RoleManagement entity) {
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(RoleManagement entity) {
		return super.updateById(entity);
	}
}
