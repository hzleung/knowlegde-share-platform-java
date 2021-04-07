package com.ly.cloud.user.controller;

import javax.annotation.Resource;

import com.ly.cloud.user.entity.FileManagement;
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
import com.ly.cloud.user.entity.ToolManagement;
import com.ly.cloud.user.service.ToolManagementService;
import com.ly.cloud.web.utils.WebResponse;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 知识平台工具管理表#ToolManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-22
 */
@RestController
@Api(tags = "工具管理")
@RequestMapping("toolManagement")
public class ToolManagementController {

	private final static Log logger = LogFactory.getLog(ToolManagementController.class);
	
	@Resource
	private ToolManagementService toolManagementService;

	@ApiOperation(value = "分页查询工具列表",notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public WebResponse<PageInfo<Map<String, Object>>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize) {
		try {
			Page<Map<String, Object>> page = toolManagementService.selectAllPage(new Page<Map<String, Object>>(pageNum, pageSize));
			return new WebResponse<PageInfo<Map<String, Object>>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<Map<String, Object>>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "查询所有工具",notes = "")
	@RequestMapping(value = "selectAll", method = RequestMethod.GET)
	public WebResponse<List<ToolManagement>> selectAll() {
		try {
			List<ToolManagement> list = toolManagementService.selectAll();
			return new WebResponse<List<ToolManagement>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<ToolManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "根据id查询工具",notes = "")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<ToolManagement> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<ToolManagement>().success(toolManagementService.selectById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<ToolManagement>().failure(e.getMessage());
        }
	}
	@ApiOperation(value = "新增工具",notes = "必传的参数有：发布用户toolPublicUser，工具名称toolName，工具封面toolBanner。如果上传附件，toolFile的值就是上传附件后返回的附件id")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody ToolManagement dto) {
		try {
			dto.setToolId(UUID.randomUUID().toString());
			dto.setToolPublicTime(new Date());
//			将头像拼成链接的形式再保存到数据库
			String BannerUrl = "http://192.168.35.93:8002/fileManagement/download?id="+dto.getToolBanner();
			dto.setToolBanner(BannerUrl);
			if(dto.getToolFile() != null && dto.getToolFile() != "") {
				dto.setToolFile("http://192.168.35.93:8002/fileManagement/download?id="+dto.getToolFile());
			}
			return new WebResponse<Boolean>().success(toolManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage( ));
		}
	}

	@ApiOperation(value = "删除工具",notes = "根据id删除工具")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(toolManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "更新当前工具信息",notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody ToolManagement dto) {
		try {
			dto.setToolBanner("http://192.168.35.93:8002/fileManagement/download?id="+dto.getToolBanner());
			if(dto.getToolFile() != null && dto.getToolFile() != "") {
				dto.setToolFile("http://192.168.35.93:8002/fileManagement/download?id="+dto.getToolFile());
			}
			return new WebResponse<Boolean>().success(toolManagementService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "搜索",notes = "")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public WebResponse<List<ToolManagement>> search(String keywords) {
		try {
			return new WebResponse<List<ToolManagement>>().success(toolManagementService.selectByKeywords(keywords));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<ToolManagement>>().failure(e.getMessage());
		}
	}

}

