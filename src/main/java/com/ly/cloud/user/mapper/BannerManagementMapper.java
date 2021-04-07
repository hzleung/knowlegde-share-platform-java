package com.ly.cloud.user.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.BannerManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台banner表#BannerManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-12
 */
public interface BannerManagementMapper extends BaseMapper<BannerManagement> {

	List<BannerManagement> selectPage(@Param("page")Page<BannerManagement> page, @Param("dto")BannerManagement dto);

    List<BannerManagement> queryAll();

    List<BannerManagement> queryAllByBannerStatus(@Param("bannerStatus")Number bannerStatus);

    List<BannerManagement> queryByKeywords(@Param("keywords")String keywords);

    Map<String, Object> queryStaticsRecord(@Param("ip")String ip);

    void insertRecord(@Param("recordId")String recordId, @Param("ip")String ip, @Param("createdTime")Date createdTime);
}
