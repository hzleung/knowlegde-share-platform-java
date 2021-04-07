package com.ly.cloud.user.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ly.cloud.exception.biz.BusinessException;
import com.ly.cloud.user.dto.MaterialDTO;
import com.ly.cloud.user.entity.ArticleManagement;
import com.ly.cloud.user.mapper.FileManagementMapper;
import com.ly.cloud.user.mapper.MaterialTypeManagementMapper;
import com.ly.cloud.user.mapper.UserMapper;
import com.ly.cloud.user.utils.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.MaterialManagement;
import com.ly.cloud.user.mapper.MaterialManagementMapper;
import com.ly.cloud.user.service.MaterialManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台素材管理表#MaterialManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-01-07
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class MaterialManagementServiceImpl extends ServiceImpl<MaterialManagementMapper, MaterialManagement> implements MaterialManagementService {

    private final static Log logger = LogFactory.getLog(MaterialManagementServiceImpl.class);

	@Value("${download-path.path}")
	private String downloadPath;

	@Autowired
	private MaterialManagementMapper materialManagementMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MaterialTypeManagementMapper materialTypeManagementMapper;

	@Autowired
	private FileManagementMapper fileManagementMapper;

	@Override
	public Page<MaterialDTO> selectPage(Page<MaterialDTO> page, String type, String logoType, String materialStatus, String userId) {
		Long total;
		// 判断userId是否存在
		if (userId != null) {
			List<MaterialDTO> list = materialManagementMapper.selectPageByUserId(userId);
			total = Long.valueOf(list.size());
			page.setRecords(list);
		} else {
			// 判断logoType是否存在
			if (logoType != null) {
				List<MaterialDTO> logoList = materialManagementMapper.selectLogoPage(page, logoType);
				total = Long.valueOf(logoList.size());
				page.setRecords(logoList);
			} else {
				List<MaterialDTO> list = materialManagementMapper.selectPage(page, type, materialStatus);
				total = Long.valueOf(list.size());
				page.setRecords(list);
			}
		}
		page.setTotal(total);
		return page;
	}

	@Override
	public MaterialDTO selectDetailById(Serializable id) {
		return materialManagementMapper.selectDetailById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(MaterialManagement entity) {
		//  点击保存的时候也是调用此方法，因此，每一次调用该方法的时候应该先去数据库查一下有没有存在这篇文章
		MaterialManagement materialItem = super.selectById(entity.getMaterialId());
		if (materialItem != null) {
			//  存在，先删掉，再保存
			super.deleteById(entity.getMaterialId());
		}
		entity.setMaterialPublishTime(new Date());
		entity.setMaterialStatus(String.valueOf(1));  // 默认为已发布状态
		// 判断是否存在banner，不存在，则使用 对应类型的 默认banner
		if(entity.getMaterialBanner() != null) {
			String banner = downloadPath + entity.getMaterialBanner();
			entity.setMaterialBanner(banner);
		} else {
			// 不存在，使用默认banner,需要判断是哪个类型，logo还是ppt
			String banner  = "";
			if ("LOGO".equals(entity.getMateriaType())) {
				banner  = downloadPath + "default-logo-20200107";
			} else if ("PPT".equals(entity.getMateriaType())) {
				banner  = downloadPath + "default-PPT-20200107";
			}
			entity.setMaterialBanner(banner);
		}
		// 判断materialFile是否存在
		if (entity.getMaterialFile() != null) {
			String filePath = downloadPath + entity.getMaterialFile();
			entity.setMaterialFile(filePath);
		}
		// 在插入之前，先去用户表查看当前用户角色类型是否为设计师或者系统管理员，如果不是，则无法插入
		String userType = userMapper.getUserType(entity.getMaterialPublishUerId());
		if(!"3".equals(userType) && !"5".equals(userType)) {
			throw new BusinessException("你没有权限发布素材，请联系管理员！");
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
	public boolean updateById(MaterialManagement entity) {
//		if ("LOGO".equals(entity.getMateriaType())) {
//			if("".equals(entity.getMaterialBanner())) {
//				String banner = downloadPath + "default-logo-20200107";
//				entity.setMaterialBanner(banner);
//			}
//		} else if ("PPT".equals(entity.getMateriaType())) {
//			if("".equals(entity.getMaterialBanner())) {
//				String banner = downloadPath + "default-PPT-20200107";
//				entity.setMaterialBanner(banner);
//			}
//		}
		// 判断是否存在banner
		if(!"".equals(entity.getMaterialBanner())) {
			String banner = downloadPath + entity.getMaterialBanner();
			entity.setMaterialBanner(banner);
		}
		// 判断materialFile是否存在
		if (!"".equals(entity.getMaterialFile())) {
			String filePath = downloadPath + entity.getMaterialFile();
			entity.setMaterialFile(filePath);
		}
		return super.updateById(entity);
	}

	@Override
	public Page<MaterialDTO> selectPageBykeywords(Page<MaterialDTO> page, String keywords, String materialType, String logoType) {
		page.setRecords(materialManagementMapper.queryPageBykeywords(page, keywords, materialType, logoType));
		return page;
	}
}
