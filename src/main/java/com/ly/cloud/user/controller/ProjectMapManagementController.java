package com.ly.cloud.user.controller;

import javax.annotation.Resource;

import com.ly.cloud.user.dto.ProjectDTO;
import com.ly.cloud.user.entity.ArticleManagement;
import com.ly.cloud.user.service.FileManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.common.mybatisplus.plugins.PageInfo;
import com.ly.cloud.user.entity.ProjectMapManagement;
import com.ly.cloud.user.service.ProjectMapManagementService;
import com.ly.cloud.web.utils.WebResponse;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识平台项目地图管理表#ProjectMapManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-22
 */
@RestController
@Api(tags = "项目地图")
@RequestMapping("projectMapManagement")
public class ProjectMapManagementController {

	private final static Log logger = LogFactory.getLog(ProjectMapManagementController.class);
	
	@Resource
	private ProjectMapManagementService projectMapManagementService;

	@Resource
	private FileManagementService fileManagementService;

	@ApiOperation(value = "分页查询项目地图列表",notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public WebResponse<PageInfo<ProjectMapManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @RequestHeader(name = "userId",required = false) String userId, @RequestParam(required = false) String produceTypeName) {
		try {
			Page<ProjectMapManagement> page;
			if(produceTypeName != null) {
				page = projectMapManagementService.searchPageByProduceType(new Page<ProjectMapManagement>(pageNum, pageSize), produceTypeName);
			} else {
				page = projectMapManagementService.selectPage(new Page<ProjectMapManagement>(pageNum, pageSize), userId);
			}
			return new WebResponse<PageInfo<ProjectMapManagement>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<ProjectMapManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "项目详情",notes = "")
	@RequestMapping(value = "getProjectDetail", method = RequestMethod.GET)
	public WebResponse<ProjectDTO> get(@RequestParam("id") String id) {
        try {
        	return new WebResponse<ProjectDTO>().success(projectMapManagementService.queryById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<ProjectDTO>().failure(e.getMessage());
        }
	}

	@ApiOperation(value = "新增项目",notes = "")
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody ProjectMapManagement dto) {
		try {
			return new WebResponse<Boolean>().success(projectMapManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "删除项目",notes = "根据id删除项目")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(projectMapManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "更新项目",notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody ProjectMapManagement dto) {
		try {
			return new WebResponse<Boolean>().success(projectMapManagementService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "搜索", notes = "")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public WebResponse<List<ProjectMapManagement>> selectByKeywords(@RequestParam(required = false) String keywords, @RequestParam(required = false) String type, @RequestParam(required = false) String area, @RequestHeader(name = "userId",required = false) String userId) {
		try {
			List<ProjectMapManagement> list = projectMapManagementService.selectByKeywords(keywords, type, area, userId);
			return new WebResponse<List<ProjectMapManagement>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<ProjectMapManagement>>().failure(e.getMessage());
		}
	}


	@ApiOperation(value = "重命名",notes = "")
	@RequestMapping(value = "/resetName", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestParam String fileId, @RequestParam String fileName) {
		try {
			return new WebResponse<Boolean>().success(fileManagementService.updateFileNameById(fileId, fileName));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "查询个人发布的所有项目地图",notes = "")
	@RequestMapping(value = "/selectAll", method = RequestMethod.POST)
	public WebResponse<List<ProjectDTO>> selectAll(@RequestParam String userId, @RequestParam(required = false) String projectStatus) {
		try {
			return new WebResponse<List<ProjectDTO>>().success(projectMapManagementService.selectAll(userId, projectStatus));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<ProjectDTO>>().failure(e.getMessage());
		}
	}


	@ApiOperation(value = "后台项目地图搜索", notes = "")
	@RequestMapping(value = "/searchByAdmin", method = RequestMethod.GET)
	public WebResponse<PageInfo<ProjectMapManagement>> selectByAdmin(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam(required = false) String projectName, @RequestParam(required = false) String version, @RequestParam(required = false) String area, @RequestParam(required = false) String projectType) {
		try {
			Page<ProjectMapManagement> page = projectMapManagementService.selectByAdmin(new Page<ProjectMapManagement>(pageNum, pageSize), projectName, version, area, projectType);
			return new WebResponse<PageInfo<ProjectMapManagement>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<ProjectMapManagement>>().failure(e.getMessage());
		}
	}

//	@ApiOperation(value = "项目地图后端查找对应类型",notes = "传类型名称")
//	@RequestMapping(value = "/searchByProduceType", method = RequestMethod.GET)
//	public WebResponse<List<Map<String, Object>>> searchByProduceType(@RequestParam String produceTypeName) {
//		try {
//			return new WebResponse<List<Map<String, Object>>>().success(projectMapManagementService.searchByProduceType(produceTypeName));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new WebResponse<List<Map<String, Object>>>().failure(e.getMessage());
//		}
//	}

//	@ApiOperation(value = "后端分页查询类型对应的项目地图",notes = "传类型名称")
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public WebResponse<PageInfo<ProjectMapManagement>> searchPageByProduceType(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String produceTypeName) {
//		try {
//			Page<ProjectMapManagement> page = projectMapManagementService.searchPageByProduceType(new Page<ProjectMapManagement>(pageNum, pageSize), produceTypeName);
//			return new WebResponse<PageInfo<ProjectMapManagement>>().success(PageInfo.toPageInfo(page));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new WebResponse<PageInfo<ProjectMapManagement>>().failure(e.getMessage());
//		}
//	}

}

