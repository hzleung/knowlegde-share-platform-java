package com.ly.cloud.user.service;

import com.ly.cloud.user.entity.LikeManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 知识平台点赞管理表#LikeManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
public interface LikeManagementService extends IService<LikeManagement> {

	Page<LikeManagement> selectPage(Page<LikeManagement> page, LikeManagement dto);

    String selectByArticleIdAndUserId(String likeArticleId, String likerUserId);
}
