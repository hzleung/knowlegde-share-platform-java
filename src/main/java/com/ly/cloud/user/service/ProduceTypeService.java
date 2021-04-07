package com.ly.cloud.user.service;

import com.ly.cloud.user.entity.ProduceType;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识平台产品类型表#ProduceType# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-06
 */
public interface ProduceTypeService extends IService<ProduceType> {

	Page<ProduceType> selectPage(Page<ProduceType> page, ProduceType dto);

	List<Map<String, Object>> selectAll(String userId);

    List<Map<String, Object>> selectAllByAdmin();
}
