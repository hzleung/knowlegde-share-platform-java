package com.ly.cloud.user.service;

import com.ly.cloud.user.entity.Department;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 知识平台部门类别表#Department# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-22
 */
public interface DepartmentService extends IService<Department> {

	Page<Department> selectPage(Page<Department> page, Department dto);
	
}
