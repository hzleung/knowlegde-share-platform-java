package com.ly.cloud.user.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.LikeManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台点赞管理表#LikeManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
public interface LikeManagementMapper extends BaseMapper<LikeManagement> {

	List<LikeManagement> selectPage(@Param("page")Page<LikeManagement> page, @Param("dto")LikeManagement dto);

	String ArticleLikeCount(@Param("articleId") String articleId);

	String queryByArticleIdAndUserId(@Param("likeArticleId") String likeArticleId, @Param("likerUserId") String likerUserId);

	boolean queryLikeByArticleIdAndUserId(@Param("articleId") String articleId, @Param("userId") String userId);
}
