package com.ly.cloud.user.controller;

import javax.annotation.Resource;

import com.ly.cloud.exception.biz.BusinessException;
import com.ly.cloud.user.utils.ExcelTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.common.mybatisplus.plugins.PageInfo;
import com.ly.cloud.user.entity.SchoolAreaManagement;
import com.ly.cloud.user.service.SchoolAreaManagementService;
import com.ly.cloud.web.utils.WebResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识平台高校大区管理表#SchoolAreaManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-12-02
 */
@RestController
@Api(tags = "高校大区管理")
@RequestMapping("schoolAreaManagement")
public class SchoolAreaManagementController {

	private final static Log logger = LogFactory.getLog(SchoolAreaManagementController.class);
	
	@Resource
	private SchoolAreaManagementService schoolAreaManagementService;

	@ApiOperation(value = "获取所有高校及所属大区", notes = "")
	@RequestMapping(value = "/getAllSchoolAndArea", method = RequestMethod.GET)
	public WebResponse<List<SchoolAreaManagement>> queryAll() {
		try {
			List<SchoolAreaManagement> list = schoolAreaManagementService.selectAll();
			return new WebResponse<List<SchoolAreaManagement>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<SchoolAreaManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "新增高校及所属大区", notes = "")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody SchoolAreaManagement dto) {
		try {
			return new WebResponse<Boolean>().success(schoolAreaManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "分页获取高校及大区", notes = "")
	@RequestMapping(value = "/getAllPage", method = RequestMethod.GET)
	public WebResponse<PageInfo<SchoolAreaManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute SchoolAreaManagement dto) {
		try {
			Page<SchoolAreaManagement> page = schoolAreaManagementService.selectPage(new Page<SchoolAreaManagement>(pageNum, pageSize), dto);
			return new WebResponse<PageInfo<SchoolAreaManagement>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<SchoolAreaManagement>>().failure(e.getMessage());
		}
	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public WebResponse<SchoolAreaManagement> get(@PathVariable("id") String id) {
//        try {
//        	return new WebResponse<SchoolAreaManagement>().success(schoolAreaManagementService.selectById(id));
//        } catch (Exception e) {
//        	logger.error(e.getMessage(), e);
//			return new WebResponse<SchoolAreaManagement>().failure(e.getMessage());
//        }
//	}

	@ApiOperation(value = "删除", notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(schoolAreaManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改", notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody SchoolAreaManagement dto) {
		try {
			return new WebResponse<Boolean>().success(schoolAreaManagementService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "搜索", notes = "")
	@RequestMapping(value = "/selectBykeywords", method = RequestMethod.GET)
	public WebResponse<PageInfo<SchoolAreaManagement>> selectBykeywords(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam(required = false) String schoolName, @RequestParam(required = false) String area, @RequestParam(required = false) String time) {
		try {
			Page<SchoolAreaManagement> page = schoolAreaManagementService.selectBykeywords(new Page<SchoolAreaManagement>(pageNum, pageSize), schoolName, area, time);
			return new WebResponse<PageInfo<SchoolAreaManagement>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<SchoolAreaManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "导入excel", notes = "")
	@PostMapping("/import")
	public WebResponse<List<SchoolAreaManagement>> importExcel(MultipartFile file) {
		try {
			String postfix = ExcelTool.getPostfix(file.getOriginalFilename());
			if (!"xlsx".equals(postfix) && !"xls".equals(postfix)) {
				throw new BusinessException("导入失败，请选择正确的文件格式支持xlsx或xls");
			}
			List<SchoolAreaManagement> res = schoolAreaManagementService.importExcel(file);
			return new WebResponse<List<SchoolAreaManagement>>().success(res);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new WebResponse<List<SchoolAreaManagement>>().failure(e.getMessage());
		}
	}

}

