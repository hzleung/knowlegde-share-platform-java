package com.ly.cloud.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.SchoolAreaManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台高校大区管理表#SchoolAreaManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-12-02
 */
public interface SchoolAreaManagementMapper extends BaseMapper<SchoolAreaManagement> {

	List<SchoolAreaManagement> selectPage(@Param("page")Page<SchoolAreaManagement> page, @Param("dto")SchoolAreaManagement dto);

	List<SchoolAreaManagement> selectAll();

    List<SchoolAreaManagement> queryByKeywords(@Param("page")Page<SchoolAreaManagement> page, @Param("schoolName")String schoolName, @Param("area")String area, @Param("startTime")String startTime, @Param("endTime")String endTime);

    boolean queryBySchoolName(@Param("schoolName")String schoolName);
}
