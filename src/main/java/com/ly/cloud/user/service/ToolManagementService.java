package com.ly.cloud.user.service;

import com.ly.cloud.user.entity.ToolManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识平台工具管理表#ToolManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-22
 */
public interface ToolManagementService extends IService<ToolManagement> {

	Page<Map<String, Object>> selectAllPage(Page<Map<String, Object>> page);

    List<ToolManagement> selectAll();

    List<ToolManagement> selectByKeywords(String keywords);
}
