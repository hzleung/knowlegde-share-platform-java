package com.ly.cloud.user.service;

import com.ly.cloud.user.entity.AboutManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 知识平台关于模块表#AboutManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
public interface AboutManagementService extends IService<AboutManagement> {

	Page<AboutManagement> selectPage(Page<AboutManagement> page, AboutManagement dto);

    List<AboutManagement> selectAll();
}
