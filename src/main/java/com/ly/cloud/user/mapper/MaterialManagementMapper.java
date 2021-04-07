package com.ly.cloud.user.mapper;

import java.io.Serializable;
import java.util.List;

import com.ly.cloud.user.dto.MaterialDTO;
import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.MaterialManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台素材管理表#MaterialManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-01-07
 */
public interface MaterialManagementMapper extends BaseMapper<MaterialManagement> {

	List<MaterialDTO> selectPage(@Param("page")Page<MaterialDTO> page, @Param("type")String type, @Param("materialStatus")String materialStatus);

    List<MaterialManagement> selectMaterialByType(@Param("type")String type);

    MaterialDTO selectDetailById(@Param("id")Serializable id);

    List<MaterialDTO> selectLogoPage(@Param("page")Page<MaterialDTO> page, @Param("logoType")String logoType);

    List<MaterialDTO> queryPageBykeywords(@Param("page")Page<MaterialDTO> page, @Param("keywords")String keywords, @Param("materialType")String materialType, @Param("logoType")String logoType);

    List<MaterialDTO> selectPageByUserId(@Param("userId")String userId);
}
