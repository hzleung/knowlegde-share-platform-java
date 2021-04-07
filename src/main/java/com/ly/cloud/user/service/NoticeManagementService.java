package com.ly.cloud.user.service;

import com.ly.cloud.user.dto.NoticeDTO;
import com.ly.cloud.user.entity.NoticeManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 知识平台部门消息表#NoticeManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-13
 */
public interface NoticeManagementService extends IService<NoticeManagement> {

	Page<NoticeManagement> selectPage(Page<NoticeManagement> page, NoticeManagement dto);

    List<NoticeDTO> selectAllNotice(String userId);

    Integer selectNoticesNum(String userId);
}
