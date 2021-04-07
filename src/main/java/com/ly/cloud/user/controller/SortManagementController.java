package com.ly.cloud.user.controller;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.common.mybatisplus.plugins.PageInfo;
import com.ly.cloud.user.entity.SortManagement;
import com.ly.cloud.user.service.SortManagementService;
import com.ly.cloud.web.utils.WebResponse;

/**
 * <p>
 * 知识平台分类管理表#SortManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@RestController
@Api(tags = "分类管理")
@RequestMapping("sortManagement")
public class SortManagementController {

	private final static Log logger = LogFactory.getLog(SortManagementController.class);
	
	@Resource
	private SortManagementService sortManagementService;

	@ApiOperation(value = "查询分类列表", notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public WebResponse<PageInfo<SortManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute SortManagement dto) {
		try {
			Page<SortManagement> page = sortManagementService.selectPage(new Page<SortManagement>(pageNum, pageSize), dto);
			return new WebResponse<PageInfo<SortManagement>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<SortManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "查询分类信息", notes = "")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<SortManagement> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<SortManagement>().success(sortManagementService.selectById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<SortManagement>().failure(e.getMessage());
        }
	}

	@ApiOperation(value = "新增分类", notes = "")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody SortManagement dto) {
		try {
			return new WebResponse<Boolean>().success(sortManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "删除某条分类", notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(sortManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改分类信息", notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody SortManagement dto) {
		try {
			return new WebResponse<Boolean>().success(sortManagementService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

}

