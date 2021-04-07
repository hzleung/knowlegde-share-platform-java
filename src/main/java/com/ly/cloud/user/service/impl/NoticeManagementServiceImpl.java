package com.ly.cloud.user.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ly.cloud.user.dto.NoticeDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.NoticeManagement;
import com.ly.cloud.user.mapper.NoticeManagementMapper;
import com.ly.cloud.user.service.NoticeManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台部门消息表#NoticeManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-13
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class NoticeManagementServiceImpl extends ServiceImpl<NoticeManagementMapper, NoticeManagement> implements NoticeManagementService {

    private final static Log logger = LogFactory.getLog(NoticeManagementServiceImpl.class);

	@Autowired
	private NoticeManagementMapper noticeManagementMapper;

	@Override
	public Page<NoticeManagement> selectPage(Page<NoticeManagement> page, NoticeManagement dto) {
		page.setRecords(noticeManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public List<NoticeDTO> selectAllNotice(String userId) {
		return noticeManagementMapper.queryAllNotice(userId);
	}
	@Override

	public Integer selectNoticesNum(String userId) {
		return noticeManagementMapper.queryNoticesNum(userId);
	}

	@Override
	public NoticeManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(NoticeManagement entity) {
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(NoticeManagement entity) {
		return super.updateById(entity);
	}
}
