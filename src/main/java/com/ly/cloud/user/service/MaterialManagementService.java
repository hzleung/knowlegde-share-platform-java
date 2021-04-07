package com.ly.cloud.user.service;

import com.ly.cloud.user.dto.MaterialDTO;
import com.ly.cloud.user.entity.MaterialManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;

/**
 * <p>
 * 知识平台素材管理表#MaterialManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-01-07
 */
public interface MaterialManagementService extends IService<MaterialManagement> {

	Page<MaterialDTO> selectPage(Page<MaterialDTO> page, String type, String logoType, String materialStatus, String userId);

	MaterialDTO selectDetailById(Serializable id);

	Page<MaterialDTO> selectPageBykeywords(Page<MaterialDTO> page, String keywords, String materialType, String logoType);
}
