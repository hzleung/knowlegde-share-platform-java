package com.ly.cloud.user.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ly.cloud.user.entity.FileManagement;
import com.ly.cloud.user.mapper.FileManagementMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.ToolManagement;
import com.ly.cloud.user.mapper.ToolManagementMapper;
import com.ly.cloud.user.service.ToolManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台工具管理表#ToolManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-22
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class ToolManagementServiceImpl extends ServiceImpl<ToolManagementMapper, ToolManagement> implements ToolManagementService {

    private final static Log logger = LogFactory.getLog(ToolManagementServiceImpl.class);

	@Autowired
	private ToolManagementMapper toolManagementMapper;

	@Autowired
	private FileManagementMapper fileManagementMapper;

	@Override
	public Page<Map<String, Object>> selectAllPage(Page<Map<String, Object>> page) {
		List<Map<String, Object>> newList = toolManagementMapper.selectAllPage(page);
		for (Map<String, Object> item : newList) {
			if(item.get("toolFile") != null && !"".equals(item.get("toolFile"))) {
				String fileId = item.get("toolFile").toString().split("\\=")[1];
				// 根据fileId去查对应的附件信息
				FileManagement fileInfo = fileManagementMapper.selectById(fileId);
				String fileName = fileInfo.getFileName();
				item.put("fileName", fileName);
			}
		}
		page.setRecords(newList);
		return page;
	}

	@Override
	public List<ToolManagement> selectAll() {
		return toolManagementMapper.queryAll();
	}

	@Override
	public ToolManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(ToolManagement entity) {
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(ToolManagement entity) {
		return super.updateById(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public List<ToolManagement> selectByKeywords(String keywords) {
		return toolManagementMapper.queryByKeywords(keywords);
	}
}
