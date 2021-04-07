package com.ly.cloud.user.service;

import com.ly.cloud.user.dto.TrainDTO;
import com.ly.cloud.user.entity.TrainTypeManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识平台培训类型管理表#TrainTypeManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-29
 */
public interface TrainTypeManagementService extends IService<TrainTypeManagement> {

	Page<TrainTypeManagement> selectPage(Page<TrainTypeManagement> page, String userId);

	boolean insertTypeAndContent(TrainDTO entity);

	Map<String, Object> selectAll(String trainType, String userId, String trainStatus);

	TrainDTO selectDetailById(String id);

    List<TrainDTO> selectTopTen();

	boolean updateByTrainId(TrainDTO dto);

    List<TrainDTO> selectByKeywords(String title, String author, String type);
}
