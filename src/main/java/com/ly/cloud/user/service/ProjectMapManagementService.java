package com.ly.cloud.user.service;

import com.ly.cloud.user.dto.ProjectDTO;
import com.ly.cloud.user.entity.ArticleManagement;
import com.ly.cloud.user.entity.ProjectMapManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识平台项目地图管理表#ProjectMapManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-22
 */
public interface ProjectMapManagementService extends IService<ProjectMapManagement> {

	Page<ProjectMapManagement> selectPage(Page<ProjectMapManagement> page, String userId);

    List<ProjectMapManagement> selectByKeywords(String keywords, String  type, String area, String userId);

    ProjectDTO queryById(Serializable id);

    List<ProjectDTO> selectAll(String userId, String projectStatus);

    Page<ProjectMapManagement> searchPageByProduceType(Page<ProjectMapManagement> page, String produceTypeName);

    Page<ProjectMapManagement> selectByAdmin(Page<ProjectMapManagement> page, String projectName, String version, String area, String projectType);
}
