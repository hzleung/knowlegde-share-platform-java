package com.ly.cloud.user.service;

import com.ly.cloud.user.dto.ReviewDTO;
import com.ly.cloud.user.entity.ReviewManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 知识平台评论管理表#ReviewManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
public interface ReviewManagementService extends IService<ReviewManagement> {

	Page<ReviewManagement> selectPage(Page<ReviewManagement> page, ReviewManagement dto);

    List<ReviewDTO> queryAllReview(String articleId, String trainId);
}
