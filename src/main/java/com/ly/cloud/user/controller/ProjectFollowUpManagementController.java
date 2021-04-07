package com.ly.cloud.user.controller;

import javax.annotation.Resource;

import com.ly.cloud.user.dto.FollowUpDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.common.mybatisplus.plugins.PageInfo;
import com.ly.cloud.user.entity.ProjectFollowUpManagement;
import com.ly.cloud.user.service.ProjectFollowUpManagementService;
import com.ly.cloud.web.utils.WebResponse;

/**
 * <p>
 * 知识平台项目跟踪管理表#projectFollowUpManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-02-02
 */
@RestController
@Api(tags = "项目跟踪")
@RequestMapping("projectFollowUpManagement")
public class ProjectFollowUpManagementController {

	private final static Log logger = LogFactory.getLog(ProjectFollowUpManagementController.class);
	
	@Resource
	private ProjectFollowUpManagementService projectFollowUpManagementService;

//	@ApiOperation(value = "查询列表",notes = "")
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public WebResponse<PageInfo<ProjectFollowUpManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute ProjectFollowUpManagement dto) {
//		try {
//			Page<ProjectFollowUpManagement> page = projectFollowUpManagementService.selectPage(new Page<ProjectFollowUpManagement>(pageNum, pageSize), dto);
//			return new WebResponse<PageInfo<ProjectFollowUpManagement>>().success(PageInfo.toPageInfo(page));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new WebResponse<PageInfo<ProjectFollowUpManagement>>().failure(e.getMessage());
//		}
//	}

	@ApiOperation(value = "查询列表",notes = "type取值：currentWeek(本周走查项)、unqualified(未合格)、recentlySubmit(近期提交)、history(历史记录); 状态status: 0待走查，1合格，2未合格")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public WebResponse<PageInfo<FollowUpDTO>> queryAllPage(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String type) {
		try {
			Page<FollowUpDTO> page = projectFollowUpManagementService.selectAllPage(new Page<>(pageNum, pageSize), type);
			return new WebResponse<PageInfo<FollowUpDTO>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<FollowUpDTO>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "查询单条项目",notes = "")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<ProjectFollowUpManagement> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<ProjectFollowUpManagement>().success(projectFollowUpManagementService.selectById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<ProjectFollowUpManagement>().failure(e.getMessage());
        }
	}

	@ApiOperation(value = "新增项目跟踪",notes = "状态status: 0待走查，1合格，2未合格")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody ProjectFollowUpManagement dto, @RequestHeader(name = "userId", required = false) String userId) {
		try {
			dto.setSubmitterId(userId);
			return new WebResponse<Boolean>().success(projectFollowUpManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "删除",notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(projectFollowUpManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改",notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody ProjectFollowUpManagement dto) {
		try {
			return new WebResponse<Boolean>().success(projectFollowUpManagementService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	//	搜索
	@ApiOperation(value = "搜索", notes = "")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public WebResponse<PageInfo<FollowUpDTO>> selectBykeywords(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam(required = false) String ProjectName, @RequestParam(required = false) String department, @RequestParam(required = false) String time, @RequestParam(required = false) String status) {
		try {
			Page<FollowUpDTO> page = projectFollowUpManagementService.selectBykeywords(new Page<>(pageNum, pageSize), ProjectName, department, time, status);
			return new WebResponse<PageInfo<FollowUpDTO>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<FollowUpDTO>>().failure(e.getMessage());
		}
	}
}

