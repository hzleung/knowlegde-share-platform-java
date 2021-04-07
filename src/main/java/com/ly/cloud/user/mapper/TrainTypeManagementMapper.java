package com.ly.cloud.user.mapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ly.cloud.user.dto.TrainDTO;
import com.ly.cloud.user.entity.FileManagement;
import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.TrainTypeManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 知识平台培训类型管理表#TrainTypeManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-29
 */
public interface TrainTypeManagementMapper extends BaseMapper<TrainTypeManagement> {

	List<TrainTypeManagement> selectPage(@Param("page")Page<TrainTypeManagement> page, @Param("userId")String userId);

	Boolean trainInsert(@Param("dto") TrainTypeManagement dto);

	List<TrainDTO> selectAllTrain();

    TrainDTO queryDetailById(String id);

    List<TrainDTO> queryTopTen();

	List<TrainDTO> selectAllTrainByUserId(@Param("userId")String userId, @Param("trainStatus")String trainStatus);

    List<TrainDTO> selectByKeywords(@Param("title")String title, @Param("author")String author, @Param("type")String type);
}
