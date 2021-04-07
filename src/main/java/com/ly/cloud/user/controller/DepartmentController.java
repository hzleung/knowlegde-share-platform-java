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
import com.ly.cloud.user.entity.Department;
import com.ly.cloud.user.service.DepartmentService;
import com.ly.cloud.web.utils.WebResponse;

/**
 * <p>
 * 知识平台部门类别表#Department# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-22
 */
@RestController
@RequestMapping("department")
@Api(tags = "部门类别")
public class DepartmentController {

	private final static Log logger = LogFactory.getLog(DepartmentController.class);
	
	@Resource
	private DepartmentService departmentService;

	@ApiOperation(value = "分页查询部门列表",notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public WebResponse<PageInfo<Department>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize) {
		try {
			Page<Department> page = departmentService.selectPage(new Page<Department>(1, 50));
			return new WebResponse<PageInfo<Department>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<Department>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "根据id查询部门",notes = "")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<Department> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<Department>().success(departmentService.selectById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<Department>().failure(e.getMessage());
        }
	}

	@ApiOperation(value = "新增部门",notes = "")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody Department dto) {
		try {
			return new WebResponse<Boolean>().success(departmentService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "根据id删除部门",notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(departmentService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改",notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody Department dto) {
		try {
			return new WebResponse<Boolean>().success(departmentService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

}

