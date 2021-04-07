package com.ly.cloud.user.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.ProduceType;
import com.ly.cloud.user.mapper.ProduceTypeMapper;
import com.ly.cloud.user.service.ProduceTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 知识平台产品类型表#ProduceType# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-06
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class ProduceTypeServiceImpl extends ServiceImpl<ProduceTypeMapper, ProduceType> implements ProduceTypeService {

    private final static Log logger = LogFactory.getLog(ProduceTypeServiceImpl.class);

	@Autowired
	private ProduceTypeMapper produceTypeMapper;

	@Override
	public Page<ProduceType> selectPage(Page<ProduceType> page, ProduceType dto) {
		page.setRecords(produceTypeMapper.selectPage(page, dto));
		return page;
	}
	@Override
	public List<Map<String, Object>> selectAll(String userId) {
		List<Map<String, Object>> produceTypeList = produceTypeMapper.queryAll(userId);
		List<Map<String, Object>> newProduceTypeList = new ArrayList<>();
		for (Map<String, Object> produceTypeItem : produceTypeList) {
			if (produceTypeItem != null) {
				if (produceTypeItem.get("produceTypeVersion") != null) {
					String version[] = produceTypeItem.get("produceTypeVersion").toString().split(",");
					produceTypeItem.put("version", version);
				}else{
					produceTypeItem.put("version", new ArrayList<>());
				}
				produceTypeItem.remove("produceTypeVersion");
				newProduceTypeList.add(produceTypeItem);
			}
		}
		return newProduceTypeList;
	}

	@Override
	public List<Map<String, Object>> selectAllByAdmin() {
		List<Map<String, Object>> produceTypeList = produceTypeMapper.queryAllByAdmin();
		List<Map<String, Object>> newProduceTypeList = new ArrayList<>();
		for (Map<String, Object> produceTypeItem : produceTypeList) {
			if (produceTypeItem != null) {
				String produceTypeName = produceTypeItem.get("produceTypeName").toString();
				List<Map<String, Object>> version = new ArrayList<>();
				if (produceTypeItem.get("produceTypeVersion") != null) {
					String produceTypeVersion[] = produceTypeItem.get("produceTypeVersion").toString().split(",");
					for(String versionItem : produceTypeVersion) {
						Map<String, Object> versionPlus = new HashMap<>();
						String count = produceTypeMapper.queryProjectCountByTypeAndVersion(produceTypeName, versionItem);
						versionPlus.put("produceTypeVersion", versionItem);
						versionPlus.put("count", count);
						version.add(versionPlus);
					}
					produceTypeItem.put("version", version);
				} else {
					produceTypeItem.put("version", new ArrayList<>());
				}
				produceTypeItem.remove("produceTypeVersion");
				newProduceTypeList.add(produceTypeItem);
			}
		}
		return newProduceTypeList;
	}

	@Override
	public ProduceType selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(ProduceType entity) {
		String produceTypeId = "ly-zsfxpt-project-map_type_" + entity.getProduceTypeName().toString();
		entity.setProduceTypeId(produceTypeId);
		entity.setProduceTypeStatus(BigDecimal.valueOf(1));
		entity.setProduceTypeTime(new Date());
//		插入产品表的同时，也需要把这条插入菜单权限（CDQXB）中间表中，菜单编号为“ly-zsfxpt_all_projectmap”，这是为了在项目地图中能区分所有类型权限和部分类型权限
		produceTypeMapper.insertProduceTypeAuthority(produceTypeId);
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
//		同时也要删除对应的菜单权限
		produceTypeMapper.deleteProduceTypeAuthorityById(id);
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(ProduceType entity) {
		return super.updateById(entity);
	}
}
