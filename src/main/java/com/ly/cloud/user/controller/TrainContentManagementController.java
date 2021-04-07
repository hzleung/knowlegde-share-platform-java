package com.ly.cloud.user.controller;

import javax.annotation.Resource;

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
import com.ly.cloud.user.entity.TrainContentManagement;
import com.ly.cloud.user.service.TrainContentManagementService;
import com.ly.cloud.web.utils.WebResponse;

/**
 * <p>
 * 知识平台培训内容管理表#TrainContentManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-29
 */
@RestController
@RequestMapping("trainContentManagement")
public class TrainContentManagementController {

	private final static Log logger = LogFactory.getLog(TrainContentManagementController.class);
	
	@Resource
	private TrainContentManagementService trainContentManagementService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public WebResponse<PageInfo<TrainContentManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute TrainContentManagement dto) {
		try {
			Page<TrainContentManagement> page = trainContentManagementService.selectPage(new Page<TrainContentManagement>(pageNum, pageSize), dto);
			return new WebResponse<PageInfo<TrainContentManagement>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<TrainContentManagement>>().failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<TrainContentManagement> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<TrainContentManagement>().success(trainContentManagementService.selectById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<TrainContentManagement>().failure(e.getMessage());
        }
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody TrainContentManagement dto) {
		try {
			return new WebResponse<Boolean>().success(trainContentManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(trainContentManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody TrainContentManagement dto) {
		try {
			return new WebResponse<Boolean>().success(trainContentManagementService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

}

