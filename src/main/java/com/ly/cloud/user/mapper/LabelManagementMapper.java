package com.ly.cloud.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.LabelManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台标签管理表#LabelManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
public interface LabelManagementMapper extends BaseMapper<LabelManagement> {

	List<LabelManagement> selectPage(@Param("page")Page<LabelManagement> page, @Param("dto")LabelManagement dto);

}
