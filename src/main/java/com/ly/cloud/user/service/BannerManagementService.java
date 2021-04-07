package com.ly.cloud.user.service;

import com.ly.cloud.user.dto.ArticleDTO;
import com.ly.cloud.user.entity.BannerManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 知识平台banner表#BannerManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-12
 */
public interface BannerManagementService extends IService<BannerManagement> {

	Page<BannerManagement> selectPage(Page<BannerManagement> page, BannerManagement dto);

    List<BannerManagement> selectAllBanner(String bannerStatus);

    List<BannerManagement> selectByKeywords(String keywords);
}
