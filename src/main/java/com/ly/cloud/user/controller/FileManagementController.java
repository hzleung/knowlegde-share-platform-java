package com.ly.cloud.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.common.mybatisplus.plugins.PageInfo;
import com.ly.cloud.user.entity.FileManagement;
import com.ly.cloud.user.service.FileManagementService;
import com.ly.cloud.web.utils.WebResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 知识平台附件管理表#FileManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-26
 */
@RestController
@Api(tags = "文件处理")
@RequestMapping("fileManagement")
public class FileManagementController {

	private final static Log logger = LogFactory.getLog(FileManagementController.class);
	
	@Resource
	private FileManagementService fileManagementService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "文件查询列表", notes = "")
	public WebResponse<PageInfo<FileManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute FileManagement dto) {
		try {
			Page<FileManagement> page = fileManagementService.selectPage(new Page<FileManagement>(pageNum, pageSize), dto);
			return new WebResponse<PageInfo<FileManagement>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<FileManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "查询某个文件", notes = "")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<FileManagement> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<FileManagement>().success(fileManagementService.selectById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<FileManagement>().failure(e.getMessage());
        }
	}

//	@ApiOperation(value = "添加文件", notes = "")
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	public WebResponse<Boolean> add(@RequestBody FileManagement dto) {
//		try {
//			return new WebResponse<Boolean>().success(fileManagementService.insert(dto));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new WebResponse<Boolean>().failure(e.getMessage());
//		}
//	}

	@ApiOperation(value = "文件上传")
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public WebResponse<List<FileManagement>> uploadFile(@RequestParam("files") MultipartFile[] files, @RequestParam(value = "uuid", required = false)String uuid, @RequestParam(value = "type", required = false) String type) {
		try {
			List<FileManagement> res = fileManagementService.uploadFile(files, uuid, type);
			return new WebResponse<List<FileManagement>>().success(res);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new WebResponse<List<FileManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "文件删除", notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(fileManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "重命名", notes = "")
	@RequestMapping(value = "/updateFileName", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestParam String fileId, @RequestParam String fileName) {
		try {
			return new WebResponse<Boolean>().success(fileManagementService.updateFileNameById(fileId, fileName));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "文件下载")
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public WebResponse<Boolean> download(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			Boolean res = fileManagementService.downloadFile(id, request, response);
			return new WebResponse<Boolean>().success(res);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "批量打包下载")
	@RequestMapping(value = "/packageDownload", method = RequestMethod.GET)
	public WebResponse<Boolean> packageDownload(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) {
		try {
//			拿到id去附件表查询，获取所有该id下的所有fileId
			List<FileManagement> fileIds = fileManagementService.selectAllFileIdById(id);
			List<String> list = new ArrayList<>();
			if(fileIds != null && fileIds.size() > 0){
				for (FileManagement fileId : fileIds){
					list.add(fileId.getFilePath());
				}
			}
			Boolean res = false;
			if (list != null && list.size() > 0) {
				res = fileManagementService.downloadZipFiles(response, list, "allFiles.zip");
			}
				return new WebResponse<Boolean>().success(res);
//			return throw new BusinessException("文件下载失败");
//			Boolean res = fileManagementService.downloadFile(id, request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

}

