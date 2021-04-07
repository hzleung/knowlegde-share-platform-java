package com.ly.cloud.user.service;

import com.ly.cloud.user.entity.LabelManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 知识平台标签管理表#LabelManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
public interface LabelManagementService extends IService<LabelManagement> {

	Page<LabelManagement> selectPage(Page<LabelManagement> page, LabelManagement dto);
	
}
