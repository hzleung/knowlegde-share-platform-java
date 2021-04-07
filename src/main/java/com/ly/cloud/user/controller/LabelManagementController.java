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
import com.ly.cloud.user.entity.LabelManagement;
import com.ly.cloud.user.service.LabelManagementService;
import com.ly.cloud.web.utils.WebResponse;

/**
 * <p>
 * 知识平台标签管理表#LabelManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@RestController
@Api(tags = "标签管理")
@RequestMapping("labelManagement")
public class LabelManagementController {

	private final static Log logger = LogFactory.getLog(LabelManagementController.class);
	
	@Resource
	private LabelManagementService labelManagementService;

	@ApiOperation(value = "标签列表查询", notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public WebResponse<PageInfo<LabelManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute LabelManagement dto) {
		try {
			Page<LabelManagement> page = labelManagementService.selectPage(new Page<LabelManagement>(pageNum, pageSize), dto);
			return new WebResponse<PageInfo<LabelManagement>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<LabelManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "查询单条标签", notes = "")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<LabelManagement> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<LabelManagement>().success(labelManagementService.selectById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<LabelManagement>().failure(e.getMessage());
        }
	}

	@ApiOperation(value = "新增标签", notes = "")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody LabelManagement dto) {
		try {
			return new WebResponse<Boolean>().success(labelManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "删除某条标签", notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(labelManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改标签", notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody LabelManagement dto) {
		try {
			return new WebResponse<Boolean>().success(labelManagementService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

}

