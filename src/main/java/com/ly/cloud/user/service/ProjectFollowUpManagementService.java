package com.ly.cloud.user.service;

import com.ly.cloud.user.dto.FollowUpDTO;
import com.ly.cloud.user.entity.ProjectFollowUpManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 知识平台项目跟踪管理表#projectFollowUpManagement# 服务接口
 * </p>
 *0
 * @author lianghaizhong
 * @since 2021-02-02
 */
public interface ProjectFollowUpManagementService extends IService<ProjectFollowUpManagement> {

	Page<ProjectFollowUpManagement> selectPage(Page<ProjectFollowUpManagement> page, ProjectFollowUpManagement dto);

    Page<FollowUpDTO> selectAllPage(Page<FollowUpDTO> page, String type);

    Page<FollowUpDTO> selectBykeywords(Page<FollowUpDTO> followUpDTOPage, String projectName, String department, String time, String status);
}
