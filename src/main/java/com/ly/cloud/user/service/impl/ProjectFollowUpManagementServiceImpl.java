package com.ly.cloud.user.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ly.cloud.user.dto.FollowUpDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.ProjectFollowUpManagement;
import com.ly.cloud.user.mapper.ProjectFollowUpManagementMapper;
import com.ly.cloud.user.service.ProjectFollowUpManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台项目跟踪管理表#projectFollowUpManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-02-02
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class ProjectFollowUpManagementServiceImpl extends ServiceImpl<ProjectFollowUpManagementMapper, ProjectFollowUpManagement> implements ProjectFollowUpManagementService {

    private final static Log logger = LogFactory.getLog(ProjectFollowUpManagementServiceImpl.class);

	@Autowired
	private ProjectFollowUpManagementMapper projectFollowUpManagementMapper;

	@Override
	public Page<ProjectFollowUpManagement> selectPage(Page<ProjectFollowUpManagement> page, ProjectFollowUpManagement dto) {
		page.setRecords(projectFollowUpManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public Page<FollowUpDTO> selectAllPage(Page<FollowUpDTO> page, String type) {
		List<FollowUpDTO> list = projectFollowUpManagementMapper.selectAllPage(page, type);
//		Long total = Long.valueOf(list.size());
		page.setRecords(list);
//		page.setTotal(total);
		return page;
	}

	@Override
	public ProjectFollowUpManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(ProjectFollowUpManagement entity) {
		entity.setProjectId(UUID.randomUUID().toString());
		entity.setStatus("0");
		entity.setSubmitTime(new Date());
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(ProjectFollowUpManagement entity) {
		// 判断是否是走查操作，当走查为不合格的时候，记录”上次走查时间“
		if (entity.getStatus() != null) {
			if ("2".equals(entity.getStatus())) {
				entity.setLastCheckTime(new Date());
			}
		}
		return super.updateById(entity);
	}

	// 搜索
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public Page<FollowUpDTO> selectBykeywords(Page<FollowUpDTO> page, String projectName, String department, String time, String status) {
		String startTime = null;
		String endTime = null;
		if (time != null) {
			String[] timeArr = time.split(",");
			startTime = timeArr[0];
			endTime = timeArr[1];
		}
		List<FollowUpDTO> list = projectFollowUpManagementMapper.queryByKeywords(page, projectName, department, startTime, endTime, status);
		page.setRecords(list);
		return page;
	}
}
