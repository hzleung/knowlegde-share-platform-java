package com.ly.cloud.user.controller;

import javax.annotation.Resource;

import com.ly.cloud.user.dto.ArticleDTO;
import com.ly.cloud.user.mapper.BannerManagementMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.common.mybatisplus.plugins.PageInfo;
import com.ly.cloud.user.entity.BannerManagement;
import com.ly.cloud.user.service.BannerManagementService;
import com.ly.cloud.web.utils.WebResponse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 知识平台banner表#BannerManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-12
 */
@RestController
@Api(tags = "banner管理")
@RequestMapping("bannerManagement")
public class BannerManagementController {

	private final static Log logger = LogFactory.getLog(BannerManagementController.class);
	
	@Resource
	private BannerManagementService bannerManagementService;

	@Resource
	private BannerManagementMapper bannerManagementMapper;
	
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public WebResponse<PageInfo<BannerManagement>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute BannerManagement dto) {
//		try {
//			Page<BannerManagement> page = bannerManagementService.selectPage(new Page<BannerManagement>(pageNum, pageSize), dto);
//			return new WebResponse<PageInfo<BannerManagement>>().success(PageInfo.toPageInfo(page));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new WebResponse<PageInfo<BannerManagement>>().failure(e.getMessage());
//		}
//	}

//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public WebResponse<BannerManagement> get(@PathVariable("id") String id) {
//        try {
//        	return new WebResponse<BannerManagement>().success(bannerManagementService.selectById(id));
//        } catch (Exception e) {
//        	logger.error(e.getMessage(), e);
//			return new WebResponse<BannerManagement>().failure(e.getMessage());
//        }
//	}

	@ApiOperation(value = "搜索", notes = "")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public WebResponse<List<BannerManagement>> selectByKeywords(@RequestParam String keywords) {
		try {
			List<BannerManagement> list = bannerManagementService.selectByKeywords(keywords);
			return new WebResponse<List<BannerManagement>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<BannerManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "获取所有banner", notes = "bannerStatus为1时，只拿到上线的banner；bannerStatus为0时，拿到所有的banner")
	@RequestMapping(value = "/selectAllBanner", method = RequestMethod.GET)
	public WebResponse<List<BannerManagement>> selectAllBanner(@RequestParam(required = false)String bannerStatus, @RequestHeader(name = "X-Forwarded-For",required = false) String ip) {
		try {
//			System.out.println(ip);
//			由于Banner是首页必须要获取的接口，因此在这里处理访问记录的事情
//			先通过IP取获取访问记录表（LY_LDD_FWJLB）,获取是否存在当天同一ip的访问记录数据，如果存在则可以记录，否则不能记录。
			if(ip != null) {
				Map<String, Object> recordMap = bannerManagementMapper.queryStaticsRecord(ip);
				if (recordMap == null) {
					String recordId = UUID.randomUUID().toString();
					Date createdTime = new Date();
					bannerManagementMapper.insertRecord(recordId, ip, createdTime);
				}
			}
//			以下是获取Banner的事情
			List<BannerManagement> list = bannerManagementService.selectAllBanner(bannerStatus);
			return new WebResponse<List<BannerManagement>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<BannerManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "新增banner", notes = "id,publishTime,updateTime,status不用传")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody BannerManagement dto) {
		try {
			dto.setBannerId(UUID.randomUUID().toString());
			dto.setBannerPublishTime(new Date());
			dto.setBannerUpdateTime(new Date());
			dto.setBannerStatus(BigDecimal.valueOf(0));
			dto.setBanner("http://192.168.35.93:8002/fileManagement/download?id=" + dto.getBanner());
			return new WebResponse<Boolean>().success(bannerManagementService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "删除", notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(bannerManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改", notes = "当置顶的时候，bannerUpdateTime传当前的时间戳；当上线的时候，bannerStatus传1，上线/下线的时候不要传时间戳")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody BannerManagement dto) {
		try {
			if(dto.getBannerUpdateTime() != null){
				dto.setBannerUpdateTime(new Date());
			}else if(dto.getBannerStatus() != null && dto.getBannerStatus().toString() == "0") {
				dto.setBannerStatus(BigDecimal.valueOf(1));
			}
			return new WebResponse<Boolean>().success(bannerManagementService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

}

