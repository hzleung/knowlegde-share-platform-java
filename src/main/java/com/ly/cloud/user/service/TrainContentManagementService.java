package com.ly.cloud.user.service;

import com.ly.cloud.user.entity.TrainContentManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 知识平台培训内容管理表#TrainContentManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-29
 */
public interface TrainContentManagementService extends IService<TrainContentManagement> {

	Page<TrainContentManagement> selectPage(Page<TrainContentManagement> page, TrainContentManagement dto);
	
}
