package com.ly.cloud.user.mapper;

import java.util.List;

import com.ly.cloud.user.dto.FollowUpDTO;
import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.ProjectFollowUpManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台项目跟踪管理表#projectFollowUpManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-02-02
 */
public interface ProjectFollowUpManagementMapper extends BaseMapper<ProjectFollowUpManagement> {

	List<ProjectFollowUpManagement> selectPage(@Param("page")Page<ProjectFollowUpManagement> page, @Param("dto")ProjectFollowUpManagement dto);

    List<FollowUpDTO> selectAllPage(@Param("page")Page<FollowUpDTO> page, @Param("type") String type);

    List<FollowUpDTO> queryByKeywords(@Param("page")Page<FollowUpDTO> page, @Param("projectName")String projectName, @Param("department")String department, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("status") String status);
}
