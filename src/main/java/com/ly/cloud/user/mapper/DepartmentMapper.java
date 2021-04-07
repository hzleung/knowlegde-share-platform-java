package com.ly.cloud.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.Department;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台部门类别表#Department# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-22
 */
public interface DepartmentMapper extends BaseMapper<Department> {

	List<Department> selectPage(@Param("page")Page<Department> page, @Param("dto")Department dto);

}
