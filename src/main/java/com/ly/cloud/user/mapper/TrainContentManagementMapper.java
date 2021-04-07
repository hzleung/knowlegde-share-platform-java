package com.ly.cloud.user.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.TrainContentManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台培训内容管理表#TrainContentManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-29
 */
public interface TrainContentManagementMapper extends BaseMapper<TrainContentManagement> {

	List<TrainContentManagement> selectPage(@Param("page")Page<TrainContentManagement> page, @Param("dto")TrainContentManagement dto);

    void updateTrainVisit(@Param("id")String id, @Param("lll")BigDecimal lll);

    TrainContentManagement queryById(@Param("id")String id);

}
