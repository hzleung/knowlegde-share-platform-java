package com.ly.cloud.user.service;

import com.ly.cloud.user.entity.MaterialTypeManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 知识平台素材类型管理表#MaterialTypeManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-01-07
 */
public interface MaterialTypeManagementService extends IService<MaterialTypeManagement> {

	Page<MaterialTypeManagement> selectPage(Page<MaterialTypeManagement> page, MaterialTypeManagement dto);
	
}
