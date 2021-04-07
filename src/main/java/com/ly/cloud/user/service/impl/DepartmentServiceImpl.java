package com.ly.cloud.user.service.impl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.Department;
import com.ly.cloud.user.mapper.DepartmentMapper;
import com.ly.cloud.user.service.DepartmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台部门类别表#Department# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-22
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    private final static Log logger = LogFactory.getLog(DepartmentServiceImpl.class);

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public Page<Department> selectPage(Page<Department> page, Department dto) {
		page.setRecords(departmentMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public Department selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(Department entity) {
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(Department entity) {
		return super.updateById(entity);
	}
}
