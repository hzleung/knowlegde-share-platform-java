package com.ly.cloud.user.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.ProduceType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台产品类型表#ProduceType# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-06
 */
public interface ProduceTypeMapper extends BaseMapper<ProduceType> {

	List<ProduceType> selectPage(@Param("page")Page<ProduceType> page, @Param("dto")ProduceType dto);

	List<Map<String, Object>> queryAll(@Param("userId")String userId);

    List<String> queryProduceType();

    void insertProduceTypeAuthority(@Param("produceTypeId")String produceTypeId);

    void deleteProduceTypeAuthorityById(@Param("id")Serializable id);

    List<Map<String, Object>> queryAllByAdmin();

    String queryProjectCountByTypeAndVersion(@Param("produceTypeName")String produceTypeName, @Param("version")String version);
}
