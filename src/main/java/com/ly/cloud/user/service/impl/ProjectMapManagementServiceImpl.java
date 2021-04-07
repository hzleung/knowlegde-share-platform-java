package com.ly.cloud.user.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

import com.ly.cloud.exception.biz.BusinessException;
import com.ly.cloud.user.dto.ArticleDTO;
import com.ly.cloud.user.dto.ProjectDTO;
import com.ly.cloud.user.entity.ArticleManagement;
import com.ly.cloud.user.entity.FileManagement;
import com.ly.cloud.user.entity.User;
import com.ly.cloud.user.mapper.FileManagementMapper;
import com.ly.cloud.user.mapper.ProduceTypeMapper;
import com.ly.cloud.user.mapper.UserMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.ProjectMapManagement;
import com.ly.cloud.user.mapper.ProjectMapManagementMapper;
import com.ly.cloud.user.service.ProjectMapManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * 知识平台项目地图管理表#ProjectMapManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-22
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class ProjectMapManagementServiceImpl extends ServiceImpl<ProjectMapManagementMapper, ProjectMapManagement> implements ProjectMapManagementService {

    private final static Log logger = LogFactory.getLog(ProjectMapManagementServiceImpl.class);

	@Value("${download-path.path}")
	private String downloadPath;

	@Autowired
	private ProjectMapManagementMapper projectMapManagementMapper;

	@Autowired
	private FileManagementMapper fileManagementMapper;


	@Autowired
	private ProduceTypeMapper produceTypeMapper;

	@Override
	public Page<ProjectMapManagement> selectPage(Page<ProjectMapManagement> page, String userId) {
		if(userId == null){
			userId = "c1a15625-5f5e-4e23-a11a-bc0dc1d0289b";
		}
		page.setRecords(projectMapManagementMapper.selectPage(page, userId));
		return page;
	}

	@Override
	public ProjectMapManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(ProjectMapManagement entity) {
//		entity.setProjectId(UUID.randomUUID().toString());
//		插入之前，先根据id去数据库查，看这个id的项目存不存在（避免重复，因为保存的时候调的接口也是这个）
		ProjectMapManagement projectItem = super.selectById(entity.getProjectId());
		if(projectItem != null){
//			该项目id存在数据库，删除
			super.deleteById(entity.getProjectId());
//			ProjectMapManagement projectMapManagement = new ProjectMapManagement();
//			projectMapManagement.setProjectId(entity.getProjectId());
//			projectMapManagement.setProjectStatus(String.valueOf(1));
//			return super.updateById(projectMapManagement);
		}
		entity.setProjectPublicTime(new Date());
		//  将banner拼成链接的形式再保存到数据库
		String bannerUrl = downloadPath + entity.getProjectBanner();
		entity.setProjectBanner(bannerUrl);
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
	public boolean updateById(ProjectMapManagement entity) {
		entity.setProjectPublicTime(new Date());
		return super.updateById(entity);
	}

	@Override
	public List<ProjectMapManagement> selectByKeywords(String keywords, String type, String area, String userId) {
		return projectMapManagementMapper.queryByKeywords(keywords, type, area, userId);
	}

	@Override
	public ProjectDTO queryById(Serializable id) {
		ProjectDTO item = projectMapManagementMapper.queryById(id);
		//将banner从设计图中删除掉，先找到banner对应的fileId
		String fileIdByBanner = item.getProjectBanner().toString().split("\\=")[1].toString();
		// 创建一个新的数组
		List<FileManagement> newProjectFiles = new ArrayList<>();
		List<FileManagement> oldProjectFiles = item.getProjectFiles();
		for(FileManagement fileItem : oldProjectFiles) {
			if(!fileIdByBanner.equals(fileItem.getFileId().toString())) {
				newProjectFiles.add(fileItem);
			}
		}
		item.setProjectFiles(newProjectFiles);
		return  projectMapManagementMapper.queryById(id);
	}

	@Override
	public List<ProjectDTO> selectAll(String id, String projectStatus) {
		return  projectMapManagementMapper.queryAll(id, projectStatus);
	}

	@Override
	public Page<ProjectMapManagement> searchPageByProduceType(Page<ProjectMapManagement> page, String produceTypeName) {
//		先去取出所有的产品类型
//		List<Map<String, Object>> resList = projectMapManagementMapper.queryPageByProduceType(produceTypeName);
		page.setRecords(projectMapManagementMapper.queryPageByProduceType(page, produceTypeName));
		return page;
	}
	@Override
	public Page<ProjectMapManagement> selectByAdmin(Page<ProjectMapManagement> page, String projectName, String version, String area, String projectType) {
		page.setRecords(projectMapManagementMapper.queryByAdmin(page, projectName, version, area, projectType));
		return page;
	}

}
