package com.ly.cloud.user.service.impl;

import java.io.Serializable;

import com.ly.cloud.exception.biz.BusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.LikeManagement;
import com.ly.cloud.user.mapper.LikeManagementMapper;
import com.ly.cloud.user.service.LikeManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台点赞管理表#LikeManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class LikeManagementServiceImpl extends ServiceImpl<LikeManagementMapper, LikeManagement> implements LikeManagementService {

    private final static Log logger = LogFactory.getLog(LikeManagementServiceImpl.class);

	@Autowired
	private LikeManagementMapper likeManagementMapper;

	@Override
	public Page<LikeManagement> selectPage(Page<LikeManagement> page, LikeManagement dto) {
		page.setRecords(likeManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public LikeManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(LikeManagement entity) {
		if(entity.getLikerUserId() == null){
			throw new BusinessException("likeUserId不能为空");
		} else if(entity.getLikeArticleId() == null) {
			throw new BusinessException("likeArticleId");
		}
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public String selectByArticleIdAndUserId(String likeArticleId, String likerUserId) {
		return likeManagementMapper.queryByArticleIdAndUserId(likeArticleId, likerUserId);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(LikeManagement entity) {
		return super.updateById(entity);
	}
}
