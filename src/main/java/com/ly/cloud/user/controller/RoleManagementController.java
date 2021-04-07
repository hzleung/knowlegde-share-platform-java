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
import com.ly.cloud.user.entity.RoleManagement;
import com.ly.cloud.user.service.RoleManagementService;
import com.ly.cloud.web.utils.WebResponse;

import java.util.List;

/**
 * <p>
 * 知识平台角色管理表#RoleManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-21
 */
@RestController
@Api(tags = "角色类型管理")
@RequestMapping("roleManagement")
public class RoleManagementController {

	private final static Log logger = LogFactory.getLog(RoleManagementController.class);
	
	@Resource
	private RoleManagementService roleManagementService;
	
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public WebResponse<PageInfo<RoleManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute RoleManagement dto) {
//		try {
//			Page<RoleManagement> page = roleManagementService.selectPage(new Page<RoleManagement>(pageNum, pageSize), dto);
//			return new WebResponse<PageInfo<RoleManagement>>().success(PageInfo.toPageInfo(page));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new WebResponse<PageInfo<RoleManagement>>().failure(e.getMessage());
//		}
//	}

	@ApiOperation(value = "获取所有角色类型", notes = "")
	@RequestMapping(value = "/getAllRoles", method = RequestMethod.GET)
	public WebResponse<List<RoleManagement>> selectAllRole() {
		try {
			List<RoleManagement> list = roleManagementService.selectAllRole();
			return new WebResponse<List<RoleManagement>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<RoleManagement>>().failure(e.getMessage());
		}
	}

//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public WebResponse<RoleManagement> get(@PathVariable("id") String id) {
//        try {
//        	return new WebResponse<RoleManagement>().success(roleManagementService.selectById(id));
//        } catch (Exception e) {
//        	logger.error(e.getMessage(), e);
//			return new WebResponse<RoleManagement>().failure(e.getMessage());
//        }
//	}

	@ApiOperation(value = "新增角色类型", notes = "")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody RoleManagement dto) {
		try {
			return new WebResponse<Boolean>().success(roleManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}
	@ApiOperation(value = "删除角色类型", notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(roleManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改角色类型", notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody RoleManagement dto) {
		try {
			return new WebResponse<Boolean>().success(roleManagementService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

}

