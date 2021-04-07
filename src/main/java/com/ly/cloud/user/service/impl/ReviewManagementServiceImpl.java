package com.ly.cloud.user.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ly.cloud.user.dto.ReviewDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.ReviewManagement;
import com.ly.cloud.user.mapper.ReviewManagementMapper;
import com.ly.cloud.user.service.ReviewManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台评论管理表#ReviewManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class ReviewManagementServiceImpl extends ServiceImpl<ReviewManagementMapper, ReviewManagement> implements ReviewManagementService {

    private final static Log logger = LogFactory.getLog(ReviewManagementServiceImpl.class);

	@Autowired
	private ReviewManagementMapper reviewManagementMapper;

	@Override
	public Page<ReviewManagement> selectPage(Page<ReviewManagement> page, ReviewManagement dto) {
		page.setRecords(reviewManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public ReviewManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(ReviewManagement entity) {
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(ReviewManagement entity) {
		return super.updateById(entity);
	}

	@Override
	public List<ReviewDTO> queryAllReview(String articleId, String trainId) {
		return reviewManagementMapper.selectAllReview(articleId, trainId);
	}
}
