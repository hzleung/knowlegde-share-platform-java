package com.ly.cloud.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.RoleManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台角色管理表#RoleManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-21
 */
public interface RoleManagementMapper extends BaseMapper<RoleManagement> {

	List<RoleManagement> selectPage(@Param("page")Page<RoleManagement> page, @Param("dto")RoleManagement dto);

	List<RoleManagement> queryAllRole();

    String selectRoleIdByUserId(@Param("userId")String userId);
}
