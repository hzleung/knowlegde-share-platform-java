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
import com.ly.cloud.user.entity.MaterialTypeManagement;
import com.ly.cloud.user.service.MaterialTypeManagementService;
import com.ly.cloud.web.utils.WebResponse;

/**
 * <p>
 * 知识平台素材类型管理表#MaterialTypeManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-01-07
 */
@RestController
@Api(tags = "素材类型管理")
@RequestMapping("materialTypeManagement")
public class MaterialTypeManagementController {

	private final static Log logger = LogFactory.getLog(MaterialTypeManagementController.class);
	
	@Resource
	private MaterialTypeManagementService materialTypeManagementService;

	@ApiOperation(value = "分页获取素材类型列表", notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public WebResponse<PageInfo<MaterialTypeManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute MaterialTypeManagement dto) {
		try {
			Page<MaterialTypeManagement> page = materialTypeManagementService.selectPage(new Page<MaterialTypeManagement>(pageNum, pageSize), dto);
			return new WebResponse<PageInfo<MaterialTypeManagement>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<MaterialTypeManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "获取某条素材类型信息", notes = "")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<MaterialTypeManagement> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<MaterialTypeManagement>().success(materialTypeManagementService.selectById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<MaterialTypeManagement>().failure(e.getMessage());
        }
	}

	@ApiOperation(value = "新增素材类型", notes = "")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody MaterialTypeManagement dto) {
		try {
			return new WebResponse<Boolean>().success(materialTypeManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "删除素材类型", notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(materialTypeManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改名称", notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody MaterialTypeManagement dto) {
		try {
			return new WebResponse<Boolean>().success(materialTypeManagementService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

}

