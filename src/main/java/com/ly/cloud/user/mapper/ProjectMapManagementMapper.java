package com.ly.cloud.user.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ly.cloud.user.dto.ArticleDTO;
import com.ly.cloud.user.dto.ProjectDTO;
import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.ProjectMapManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台项目地图管理表#ProjectMapManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-22
 */
public interface ProjectMapManagementMapper extends BaseMapper<ProjectMapManagement> {

	List<ProjectMapManagement> selectPage(@Param("page")Page<ProjectMapManagement> page, @Param("userId")String userId);

    List<ProjectMapManagement> queryByKeywords(@Param("keywords")String keywords, @Param("type")String type, @Param("area")String area, @Param("userId")String userId);

    ProjectDTO queryById(@Param("projectId") Serializable projectId);

    List<ProjectMapManagement> queryTopTen();

    List<ProjectDTO> queryAll(@Param("userId")String userId, @Param("projectStatus")String projectStatus);

    String queryProduceSumByType(@Param("produceType")String produceType);

    List<ProjectMapManagement> queryPageByProduceType(@Param("page")Page<ProjectMapManagement> page, @Param("produceTypeName")String produceTypeName);

    List<ProjectMapManagement> queryByAdmin(@Param("page")Page<ProjectMapManagement> page,@Param("projectName")String projectName, @Param("version")String version, @Param("area")String area, @Param("projectType") String projectType);

//    List<ProjectMapManagement> queryPage(@Param("page")Page<ProjectMapManagement> page);
}
