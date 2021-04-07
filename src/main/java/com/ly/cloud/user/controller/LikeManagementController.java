package com.ly.cloud.user.controller;

import javax.annotation.Resource;

import com.ly.cloud.user.entity.NoticeManagement;
import com.ly.cloud.user.mapper.NoticeManagementMapper;
import com.ly.cloud.user.service.NoticeManagementService;
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
import com.ly.cloud.user.entity.LikeManagement;
import com.ly.cloud.user.service.LikeManagementService;
import com.ly.cloud.web.utils.WebResponse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 知识平台点赞管理表#LikeManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@RestController
@Api(tags = "点赞管理")
@RequestMapping("likeManagement")
public class LikeManagementController {

	private final static Log logger = LogFactory.getLog(LikeManagementController.class);
	
	@Resource
	private LikeManagementService likeManagementService;

	@Resource
	private NoticeManagementService noticeManagementService;

	@Resource
	private NoticeManagementMapper noticeManagementMapper;

//	@ApiOperation(value = "查询点赞列表", notes = "")
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public WebResponse<PageInfo<LikeManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute LikeManagement dto) {
//		try {
//			Page<LikeManagement> page = likeManagementService.selectPage(new Page<LikeManagement>(pageNum, pageSize), dto);
//			return new WebResponse<PageInfo<LikeManagement>>().success(PageInfo.toPageInfo(page));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new WebResponse<PageInfo<LikeManagement>>().failure(e.getMessage());
//		}
//	}

//	@ApiOperation(value = "根据id查询单条点赞", notes = "")
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public WebResponse<LikeManagement> get(@PathVariable("id") String id) {
//        try {
//        	return new WebResponse<LikeManagement>().success(likeManagementService.selectById(id));
//        } catch (Exception e) {
//        	logger.error(e.getMessage(), e);
//			return new WebResponse<LikeManagement>().failure(e.getMessage());
//        }
//	}

	@ApiOperation(value = "点赞", notes = "")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody LikeManagement dto) {
		try {
			String id = UUID.randomUUID().toString();
			Date publishTime = new Date();
			dto.setLikeId(id);
			dto.setLikeTime(publishTime);
//			点赞的同时插入消息表
			NoticeManagement noticePO = new NoticeManagement();
			noticePO.setNoticeId(UUID.randomUUID().toString());
			noticePO.setArticleId(dto.getLikeArticleId());
			noticePO.setUserId(dto.getLikerUserId());
			noticePO.setNoticePublishTime(publishTime);
			noticePO.setNoticeStatus(BigDecimal.valueOf(0));
			noticePO.setNoticeType(BigDecimal.valueOf(1));
			noticePO.setLikeId(id);
			noticePO.setTargetId(dto.getTargetId());
			noticeManagementService.insert(noticePO);
			return new WebResponse<Boolean>().success(likeManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "取消某条点赞", notes = "")
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@RequestBody LikeManagement dto) {
		try {
			String likeArticleId = dto.getLikeArticleId();
			String likerUserId = dto.getLikerUserId();
			String likeId = likeManagementService.selectByArticleIdAndUserId(likeArticleId, likerUserId);
			noticeManagementMapper.deleteByLikeId(likeId);
			return new WebResponse<Boolean>().success(likeManagementService.deleteById(likeId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

}

