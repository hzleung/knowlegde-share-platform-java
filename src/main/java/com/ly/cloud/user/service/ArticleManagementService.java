package com.ly.cloud.user.service;

import com.ly.cloud.user.dto.ArticleDTO;
import com.ly.cloud.user.entity.ArticleManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识平台文章管理表#ArticleManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
public interface ArticleManagementService extends IService<ArticleManagement> {

	Page<ArticleManagement> selectPage(Page<ArticleManagement> page, ArticleManagement dto);

	List<ArticleDTO> selectAll(Number articleStatus, String userId);

    List<ArticleManagement> selectTopTen();

	ArticleDTO queryById(Serializable id, String userId);

	List<ArticleManagement> selectByKeywords(String keywords, String articleType, String articleLabel);

	Page<ArticleDTO> selectArticlePage(Page<ArticleDTO> articleDTOPage, String hot, String requireType);

    Map<String, Object> selectData();

//	boolean updateArticleVisit(Serializable id, BigDecimal visit);
}
