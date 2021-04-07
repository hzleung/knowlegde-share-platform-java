package com.ly.cloud.user.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

import com.ly.cloud.user.dto.ArticleDTO;
import com.ly.cloud.user.entity.*;
import com.ly.cloud.user.mapper.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.service.ArticleManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台文章管理表#ArticleManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class ArticleManagementServiceImpl extends ServiceImpl<ArticleManagementMapper, ArticleManagement> implements ArticleManagementService {

    private final static Log logger = LogFactory.getLog(ArticleManagementServiceImpl.class);

	@Autowired
	private ArticleManagementMapper articleManagementMapper;

	@Autowired
	private LikeManagementMapper likeManagementMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ProduceTypeMapper produceTypeMapper;

	@Autowired
	private ProjectMapManagementMapper projectMapManagementMapper;

	@Autowired
	private FileManagementMapper fileManagementMapper;


	@Override
	public Page<ArticleManagement> selectPage(Page<ArticleManagement> page, ArticleManagement dto) {
		page.setRecords(articleManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public Page<ArticleDTO> selectArticlePage(Page<ArticleDTO> page, String hot, String requireType) {
		//		判断访问人是否点赞了改文章
//		if (userId != null) {
//			String articleId = (String) id;
//			LikeManagement isLike = likeManagementMapper.queryLikeByArticleIdAndUserId(articleId, userId);
//			if(isLike != null) {
//				if("0".equals(entity.getIsLike().toString()) || entity.getIsLike() == null) {
//					Number hadlike = 1;
////					entity.setIsLike(BigDecimal.valueOf(1));
//					articleManagementMapper.updateIsLikeById(entity.getArticleId(), hadlike);
//				}
//			}else {
//				Number hadlike = 0;
//				articleManagementMapper.updateIsLikeById(entity.getArticleId(), hadlike);
//			}
//		}
		if(hot != null) {
			page.setRecords(articleManagementMapper.selectHotArticlePage(page));
		} else {
			page.setRecords(articleManagementMapper.selectArticlePage(page, requireType));
		}
		return page;
	}

//	@Override
//	public ArticleManagement selectById(Serializable id) {
//		ArticleManagement entity = super.selectById(id);
//		Long visit = Long.valueOf(entity.getArticleVisit().toString());
//		BigDecimal lll = BigDecimal.valueOf(visit+1);
//		articleManagementMapper.updateArticleVisit(id, lll);
//		return articleManagementMapper.queryById(id);
//	}

	@Override
	public ArticleDTO queryById(Serializable id, String userId) {
		ArticleManagement entity = articleManagementMapper.selectById(id);
		Long visit = Long.valueOf(entity.getArticleVisit().toString());
		BigDecimal lll = BigDecimal.valueOf(visit+1);
		articleManagementMapper.updateArticleVisit(id, lll);
//		ArticleDTO articleList = articleManagementMapper.queryById(id);
//		String fileId = articleList.getFiles();
//		List<FileManagement> fileVOS = fileManagementMapper.queryByEventId(fileId);
//		判断访问人是否点赞了改文章
		if (userId != null) {
			String articleId = (String) id;
			boolean isLike = likeManagementMapper.queryLikeByArticleIdAndUserId(articleId, userId);
			if(isLike) {
				if("0".equals(entity.getIsLike().toString()) || entity.getIsLike() == null) {
					Number hadlike = 1;
//					entity.setIsLike(BigDecimal.valueOf(1));
					articleManagementMapper.updateIsLikeById(entity.getArticleId(), hadlike);
				}
			}else {
				Number hadlike = 0;
				articleManagementMapper.updateIsLikeById(entity.getArticleId(), hadlike);
			}
		}
		return articleManagementMapper.queryById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(ArticleManagement entity) {
		//  点击保存的时候也是调用此方法，因此，每一次调用该方法的时候应该先去数据库查一下有没有存在这篇文章
		ArticleManagement articleItem = super.selectById(entity.getArticleId());
		if (articleItem != null) {
			//  存在，先删掉，再保存
			super.deleteById(entity.getArticleId());
		}
		User userDto = userMapper.selectUserByuserId(entity.getUserId());
		BigDecimal xp = BigDecimal.valueOf(Long.valueOf(userDto.getUserXP().toString()) + 2);
		userMapper.updateXP(entity.getUserId(), xp);
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		// 删除的时候，需要同时删除对应的 banner 以及 附件
		boolean hasDelete = fileManagementMapper.deleteFilesByTypeId(id);
		if(hasDelete) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(ArticleManagement entity) {
		entity.setArticlePublicTime(new Date());
		return super.updateById(entity);
	}

	@Override
	public List<ArticleDTO> selectAll(Number articleStatus, String userId) {
		if(userId != null) {
			return articleManagementMapper.queryAllByUserId(articleStatus, userId);
		}
		return articleManagementMapper.queryAll(articleStatus);
	}

	@Override
	public List<ArticleManagement> selectTopTen() {
		return articleManagementMapper.queryTopTen();
	}

	@Override
	public List<ArticleManagement> selectByKeywords(String keywords, String articleType, String articleLabel) {
		return articleManagementMapper.queryByKeywords(keywords, articleType, articleLabel);
	}

	@Override
	public Map<String, Object> selectData() {
//		汇总页面顶部的数据
		Map<String, Object> res = articleManagementMapper.queryData();
//		汇总页面中间文章分类的数据，先去拿文章中所有的类型回来
		Map<String, Object> articleMap = new HashMap<>();
		List<String> articleType = articleManagementMapper.queryArticleType();
		for (String articleTypeItem : articleType) {
		//	将每一项带入去查对应类型的总数数据
			String articleTypeSum = articleManagementMapper.queryArticleSumByType(articleTypeItem);
			articleMap.put(articleTypeItem, articleTypeSum);
		}
//		汇总页面中间产品分类的数据，先去把所有产品类型拿回来
		Map<String, Object> produceMap = new HashMap<>();
		List<String> produceType = produceTypeMapper.queryProduceType();
		for (String produceTypeItem : produceType) {
		//	将每一项带入去查对应类型的总数数据
			String produceTypeSum = projectMapManagementMapper.queryProduceSumByType(produceTypeItem);
			produceMap.put(produceTypeItem, produceTypeSum);
		}
//		汇总页面底部访问量的数据
		Map<String, Object> recordSum = new HashMap<>();
//		按月份输出
		List<Map<String, Object>> monthRecordMap = articleManagementMapper.queryRecordBymonth();
//		按周输出，以当前日期为起点，往前6天，包括今天刚好为7天
		List<Map<String, Object>> weekRecordMap = articleManagementMapper.queryRecordByWeek();
		recordSum.put("monthSum", monthRecordMap);
		recordSum.put("weekSum", weekRecordMap);
		res.put("recordSum", recordSum);
		res.put("articleSum", articleMap);
		res.put("produceSum", produceMap);
		return res;
	}

//	@Override
//	public boolean updateArticleVisit(Serializable id, BigDecimal lll) {
//		return articleManagementMapper.updateArticleVisit(id, lll);
//	}
}
