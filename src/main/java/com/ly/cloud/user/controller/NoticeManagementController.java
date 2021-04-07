package com.ly.cloud.user.controller;

import javax.annotation.Resource;

import com.ly.cloud.user.dto.NoticeDTO;
import com.ly.cloud.user.mapper.NoticeManagementMapper;
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
import com.ly.cloud.user.entity.NoticeManagement;
import com.ly.cloud.user.service.NoticeManagementService;
import com.ly.cloud.web.utils.WebResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 知识平台部门消息表#NoticeManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-13
 */
@RestController
@Api(tags = "消息管理")
@RequestMapping("noticeManagement")
public class NoticeManagementController {

	private final static Log logger = LogFactory.getLog(NoticeManagementController.class);
	
	@Resource
	private NoticeManagementService noticeManagementService;

	@Resource
	private NoticeManagementMapper noticeManagementMapper;

//	@ApiOperation(value = "消息查询", notes = "")
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public WebResponse<PageInfo<NoticeManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute NoticeManagement dto) {
//		try {
//			Page<NoticeManagement> page = noticeManagementService.selectPage(new Page<NoticeManagement>(pageNum, pageSize), dto);
//			return new WebResponse<PageInfo<NoticeManagement>>().success(PageInfo.toPageInfo(page));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new WebResponse<PageInfo<NoticeManagement>>().failure(e.getMessage());
//		}
//	}

	@ApiOperation(value = "消息单条查询", notes = "")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<NoticeManagement> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<NoticeManagement>().success(noticeManagementService.selectById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<NoticeManagement>().failure(e.getMessage());
        }
	}

	@ApiOperation(value = "消息新增", notes = "")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody NoticeManagement dto) {
		try {
			return new WebResponse<Boolean>().success(noticeManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "消息删除", notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(noticeManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "查询本人所有的消息", notes = "传userId")
	@RequestMapping(value = "/selectAllNotices", method = RequestMethod.POST)
	public WebResponse<List<NoticeDTO>> selectAllNotice(@RequestParam("userId") String userId) {
		try {
			List<NoticeDTO> list = noticeManagementService.selectAllNotice(userId);
//			查询之前，先把状态设置为1，即已读。
			noticeManagementMapper.updateStatus();
			return new WebResponse<List<NoticeDTO>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<NoticeDTO>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "查询消息条数", notes = "传userId")
	@RequestMapping(value = "/selectNoticesNum", method = RequestMethod.POST)
	public WebResponse<Integer> selectNoticesNum(@RequestParam("userId") String userId) {
		try {
			Integer num = noticeManagementService.selectNoticesNum(userId);
			return new WebResponse<Integer>().success(num);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Integer>().failure(e.getMessage());
		}
	}

}

