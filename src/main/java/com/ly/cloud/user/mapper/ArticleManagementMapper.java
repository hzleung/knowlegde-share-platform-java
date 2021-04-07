package com.ly.cloud.user.mapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ly.cloud.user.dto.ArticleDTO;
import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.ArticleManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台文章管理表#ArticleManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
public interface ArticleManagementMapper extends BaseMapper<ArticleManagement> {

	List<ArticleManagement> selectPage(@Param("page")Page<ArticleManagement> page, @Param("dto")ArticleManagement dto);

	List<ArticleDTO> queryAll(@Param("articleStatus") Number articleStatus);

	ArticleDTO queryById(@Param("articleId") Serializable articleId);

	boolean updateArticleVisit(@Param("id") Serializable id, @Param("lll") BigDecimal lll);

	List<ArticleManagement> queryTopTen();

	List<ArticleDTO> queryAllByUserId(@Param("articleStatus") Number articleStatus,@Param("userId") String userId);

    List<ArticleManagement> queryByKeywords(@Param("keywords")String keywords, @Param("articleType")String articleType, @Param("articleLabel")String articleLabel);

	void updateIsLikeById(@Param("articleId")String articleId, @Param("hadlike")Number hadlike);

	//根据requireType判别是首页获取还是分享页获取
	List<ArticleDTO> selectArticlePage(@Param("page")Page<ArticleDTO> page, @Param("requireType") String requireType);

//	获取最热的文章
    List<ArticleDTO> selectHotArticlePage(@Param("page")Page<ArticleDTO> page);
//	获取汇总数据
    Map<String, Object> queryData();

	List<String> queryArticleType();

	String queryArticleSumByType(@Param("articleType")String articleType);

	List<Map<String, Object>> queryRecordBymonth();

	List<Map<String, Object>> queryRecordByWeek();

//	boolean updateArticleVisit(Serializable id, BigDecimal lll);
}
