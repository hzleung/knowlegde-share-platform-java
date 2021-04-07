package com.ly.cloud.user.service;

import com.ly.cloud.user.entity.SortManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 知识平台分类管理表#SortManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
public interface SortManagementService extends IService<SortManagement> {

	Page<SortManagement> selectPage(Page<SortManagement> page, SortManagement dto);
	
}
