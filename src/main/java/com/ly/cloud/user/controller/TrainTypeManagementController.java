package com.ly.cloud.user.controller;

import javax.annotation.Resource;

import com.ly.cloud.user.entity.ArticleManagement;
import com.ly.cloud.user.entity.ProjectMapManagement;
import com.ly.cloud.user.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.common.mybatisplus.plugins.PageInfo;
import com.ly.cloud.user.entity.TrainTypeManagement;
import com.ly.cloud.user.service.TrainTypeManagementService;
import com.ly.cloud.user.dto.TrainDTO;
import com.ly.cloud.web.utils.WebResponse;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识平台培训类型管理表#TrainTypeManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-29
 */
@RestController
@Api(tags = "培训管理")
@RequestMapping("trainTypeManagement")
public class TrainTypeManagementController {

	private final static Log logger = LogFactory.getLog(TrainTypeManagementController.class);
	
	@Resource
	private TrainTypeManagementService trainTypeManagementService;

	@Resource
	private UserMapper userMapper;

	@ApiOperation(value = "个人中心分页查询所有培训数据", notes = "")
	@RequestMapping(value = "getAllTrains", method = RequestMethod.GET)
	public WebResponse<PageInfo<TrainTypeManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @RequestHeader(name = "userId", required = false) String userId) {
		try {
			Page<TrainTypeManagement> page = trainTypeManagementService.selectPage(new Page<TrainTypeManagement>(pageNum, pageSize), userId);
			return new WebResponse<PageInfo<TrainTypeManagement>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<TrainTypeManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "查看详情", notes = "")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<TrainDTO> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<TrainDTO>().success(trainTypeManagementService.selectDetailById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<TrainDTO>().failure(e.getMessage());
        }
	}

	@ApiOperation(value = "发布", notes = "")
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody TrainDTO dto) {
		try {
			//			先去用户表拿头像回来
			String userAvatar = userMapper.selectUserAvatarByUserId(dto.getTrainPublishUserId());
			if(userAvatar != null){
				dto.setUserAvatar(userAvatar);
			}
			return new WebResponse<Boolean>().success(trainTypeManagementService.insertTypeAndContent(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "删除", notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(trainTypeManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改", notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody TrainDTO dto) {
		try {
			return new WebResponse<Boolean>().success(trainTypeManagementService.updateByTrainId(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "获取所有培训", notes = "")
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public WebResponse<Map<String, Object>> selectAll(@RequestParam(required = false) String trainType, @RequestParam(required = false) String userId, @RequestParam(required = false)String trainStatus) {
		try {
			Map<String, Object> list = trainTypeManagementService.selectAll(trainType, userId, trainStatus);
			return new WebResponse<Map<String, Object>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Map<String, Object>>().failure(e.getMessage());
		}
	}


	@ApiOperation(value = "相关推荐", notes = "")
	@RequestMapping(value = "/recommend", method = RequestMethod.GET)
	public WebResponse<List<TrainDTO>> selectTopTen() {
		try {
			List<TrainDTO> list = trainTypeManagementService.selectTopTen();
			return new WebResponse<List<TrainDTO>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<TrainDTO>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "后端培训搜索", notes = "")
	@RequestMapping(value = "/searchByAdmin", method = RequestMethod.GET)
	public WebResponse<List<TrainDTO>> selectByKeywords(@RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) String type) {
		try {
			List<TrainDTO> list = trainTypeManagementService.selectByKeywords(title, author, type);
			return new WebResponse<List<TrainDTO>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<TrainDTO>>().failure(e.getMessage());
		}
	}

}

