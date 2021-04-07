package com.ly.cloud.user.service.impl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.TrainContentManagement;
import com.ly.cloud.user.mapper.TrainContentManagementMapper;
import com.ly.cloud.user.service.TrainContentManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台培训内容管理表#TrainContentManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-29
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class TrainContentManagementServiceImpl extends ServiceImpl<TrainContentManagementMapper, TrainContentManagement> implements TrainContentManagementService {

    private final static Log logger = LogFactory.getLog(TrainContentManagementServiceImpl.class);

	@Autowired
	private TrainContentManagementMapper trainContentManagementMapper;

	@Override
	public Page<TrainContentManagement> selectPage(Page<TrainContentManagement> page, TrainContentManagement dto) {
		page.setRecords(trainContentManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public TrainContentManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(TrainContentManagement entity) {
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(TrainContentManagement entity) {
		return super.updateById(entity);
	}
}
