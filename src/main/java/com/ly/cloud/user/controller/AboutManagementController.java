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
import com.ly.cloud.user.entity.AboutManagement;
import com.ly.cloud.user.service.AboutManagementService;
import com.ly.cloud.web.utils.WebResponse;

import java.util.List;

/**
 * <p>
 * 知识平台关于模块表#AboutManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@RestController
@Api(tags = "关于模块")
@RequestMapping("aboutManagement")
public class AboutManagementController {

	private final static Log logger = LogFactory.getLog(AboutManagementController.class);
	
	@Resource
	private AboutManagementService aboutManagementService;

//	@ApiOperation(value = "关于信息查询",notes = "")
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public WebResponse<PageInfo<AboutManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute AboutManagement dto) {
//		try {
//			Page<AboutManagement> page = aboutManagementService.selectPage(new Page<AboutManagement>(pageNum, pageSize), dto);
//			return new WebResponse<PageInfo<AboutManagement>>().success(PageInfo.toPageInfo(page));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new WebResponse<PageInfo<AboutManagement>>().failure(e.getMessage());
//		}
//	}

	@ApiOperation(value = "查询关于信息",notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public WebResponse<List<AboutManagement>> query() {
		try {
			List<AboutManagement> list = aboutManagementService.selectAll();
			return new WebResponse<List<AboutManagement>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<AboutManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "根据id查询",notes = "")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<AboutManagement> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<AboutManagement>().success(aboutManagementService.selectById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<AboutManagement>().failure(e.getMessage());
        }
	}

	@ApiOperation(value = "新增",notes = "")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody AboutManagement dto) {
		try {
			return new WebResponse<Boolean>().success(aboutManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "删除",notes = "根据id删除用户")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(aboutManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改",notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody AboutManagement dto) {
		try {
			return new WebResponse<Boolean>().success(aboutManagementService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

}

