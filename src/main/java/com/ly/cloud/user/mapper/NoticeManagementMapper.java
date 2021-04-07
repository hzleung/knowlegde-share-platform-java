package com.ly.cloud.user.mapper;

import java.util.List;

import com.ly.cloud.user.dto.NoticeDTO;
import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.NoticeManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台部门消息表#NoticeManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-13
 */
public interface NoticeManagementMapper extends BaseMapper<NoticeManagement> {

	List<NoticeManagement> selectPage(@Param("page")Page<NoticeManagement> page, @Param("dto")NoticeManagement dto);

	void deleteByLikeId(@Param("likeId")String likeId);

	void deleteByReviewId(@Param("reviewId")String reviewId);

	List<NoticeDTO> queryAllNotice(@Param("userId")String userId);

	Integer queryNoticesNum(@Param("userId")String userId);

	void updateStatus();
}
