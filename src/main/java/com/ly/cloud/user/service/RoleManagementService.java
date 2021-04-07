package com.ly.cloud.user.service;

import com.ly.cloud.user.entity.RoleManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 知识平台角色管理表#RoleManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-21
 */
public interface RoleManagementService extends IService<RoleManagement> {

	Page<RoleManagement> selectPage(Page<RoleManagement> page, RoleManagement dto);

	List<RoleManagement> selectAllRole();
}
