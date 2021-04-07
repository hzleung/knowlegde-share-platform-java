package com.ly.cloud.user.mapper;

import java.util.List;

import com.ly.cloud.user.dto.ReviewDTO;
import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.ReviewManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台评论管理表#ReviewManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
public interface ReviewManagementMapper extends BaseMapper<ReviewManagement> {

	List<ReviewManagement> selectPage(@Param("page")Page<ReviewManagement> page, @Param("dto")ReviewManagement dto);

    List<ReviewDTO> selectAllReview(@Param("articleId")String articleId, @Param("trainId")String trainId);
}
