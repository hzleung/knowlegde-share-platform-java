package com.ly.cloud.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.MaterialTypeManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台素材类型管理表#MaterialTypeManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-01-07
 */
public interface MaterialTypeManagementMapper extends BaseMapper<MaterialTypeManagement> {

	List<MaterialTypeManagement> selectPage(@Param("page")Page<MaterialTypeManagement> page, @Param("dto")MaterialTypeManagement dto);

    List<String> getMaterialTypeList();
}
