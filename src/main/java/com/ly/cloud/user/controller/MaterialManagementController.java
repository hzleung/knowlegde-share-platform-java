package com.ly.cloud.user.controller;

import javax.annotation.Resource;

import com.ly.cloud.user.dto.MaterialDTO;
import com.ly.cloud.user.service.FileManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.common.mybatisplus.plugins.PageInfo;
import com.ly.cloud.user.entity.MaterialManagement;
import com.ly.cloud.user.service.MaterialManagementService;
import com.ly.cloud.web.utils.WebResponse;

/**
 * <p>
 * 知识平台素材管理表#MaterialManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-01-07
 */
@RestController
@Api(tags = "素材管理")
@RequestMapping("materialManagement")
public class MaterialManagementController {

	private final static Log logger = LogFactory.getLog(MaterialManagementController.class);

	@Resource
	private MaterialManagementService materialManagementService;

	@Resource
	private FileManagementService fileManagementService;

	@ApiOperation(value = "分页获取素材列表", notes = "type取值为：LOGO、图标库、摄影素材、PPT； logoType(非必填)取值为 公司LOGO, 产品LOGO, 学校LOGO;  userId 是为了在个人中心获取属于当前用户发布的素材")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public WebResponse<PageInfo<MaterialDTO>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam(required = false) String type, @RequestParam(required = false) String logoType, @RequestParam(required = false) String materialStatus, @RequestParam(required = false) String userId) {
		try {
			Page<MaterialDTO> page = materialManagementService.selectPage(new Page<>(pageNum, pageSize), type, logoType, materialStatus, userId);
			return new WebResponse<PageInfo<MaterialDTO>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<MaterialDTO>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "获取某条素材", notes = "")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<MaterialDTO> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<MaterialDTO>().success(materialManagementService.selectDetailById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<MaterialDTO>().failure(e.getMessage());
        }
	}

	@ApiOperation(value = "添加素材", notes = "")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody MaterialManagement dto, @RequestHeader(name = "userId",required = false) String userId) {
		try {
			dto.setMaterialPublishUerId(userId);  // 获取header上的userId，就是当前登录的用户id
			return new WebResponse<Boolean>().success(materialManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "删除", notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			// 需要将对应的文件和banner一起删掉
			fileManagementService.deleteFileByTypeId(id);
			return new WebResponse<Boolean>().success(materialManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改", notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody MaterialManagement dto) {
		try {
			return new WebResponse<Boolean>().success(materialManagementService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "搜索", notes = "keywords 字段将匹配标题")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public WebResponse<PageInfo<MaterialDTO>> searchBykeywords(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam(required = false) String keywords, @RequestParam(required = false) String materialType, @RequestParam(required = false) String logoType) {
		try {
			Page<MaterialDTO> page = materialManagementService.selectPageBykeywords(new Page<MaterialDTO>(pageNum, pageSize), keywords, materialType, logoType);
			return new WebResponse<PageInfo<MaterialDTO>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<MaterialDTO>>().failure(e.getMessage());
		}
	}

}

