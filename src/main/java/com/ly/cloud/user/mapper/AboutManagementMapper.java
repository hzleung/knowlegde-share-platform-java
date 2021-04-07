package com.ly.cloud.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.AboutManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台关于模块表#AboutManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
public interface AboutManagementMapper extends BaseMapper<AboutManagement> {

	List<AboutManagement> selectPage(@Param("page")Page<AboutManagement> page, @Param("dto")AboutManagement dto);

    List<AboutManagement> query();
}
