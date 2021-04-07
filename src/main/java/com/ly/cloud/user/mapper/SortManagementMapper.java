package com.ly.cloud.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.SortManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台分类管理表#SortManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
public interface SortManagementMapper extends BaseMapper<SortManagement> {

	List<SortManagement> selectPage(@Param("page")Page<SortManagement> page, @Param("dto")SortManagement dto);

}
