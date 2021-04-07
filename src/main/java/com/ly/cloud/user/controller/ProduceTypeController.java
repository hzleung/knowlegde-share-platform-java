package com.ly.cloud.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.net.HttpHeaders;
import com.ly.cloud.common.mybatisplus.plugins.PageInfo;
import com.ly.cloud.user.entity.ProduceType;
import com.ly.cloud.user.service.ProduceTypeService;
import com.ly.cloud.web.utils.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import com.baomidou.mybatisplus.plugins.Page;
//import com.ly.cloud.common.mybatisplus.plugins.PageInfo;
//import com.ly.cloud.user.entity.ProduceType;
//import com.ly.cloud.user.service.ProduceTypeService;
//import com.ly.cloud.web.utils.WebResponse;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识平台产品类型表#ProduceType# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-06
 */
@RestController
@Api(tags = "产品类型")
@RequestMapping("produceType")
public class ProduceTypeController {

	private final static Log logger = LogFactory.getLog(ProduceTypeController.class);
	
	@Resource
	private ProduceTypeService produceTypeService;

	@Autowired
		private HttpServletRequest request;

//	@ApiOperation(value = "分页查询产品类型列表",notes = "")
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public WebResponse<PageInfo<ProduceType>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute ProduceType dto) {
//		try {
//			Page<ProduceType> page = produceTypeService.selectPage(new Page<ProduceType>(pageNum, pageSize), dto);
//			return new WebResponse<PageInfo<ProduceType>>().success(PageInfo.toPageInfo(page));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new WebResponse<PageInfo<ProduceType>>().failure(e.getMessage());
//		}
//	}

	@ApiOperation(value = "查询所有产品类型",notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public WebResponse<List<Map<String, Object>>> queryAll(@RequestHeader(name = "userId") String userId) {
		try {
//			System.out.println(userId);
//			System.out.println("from parameter:" + headers.get("userId"));
			List<Map<String, Object>> list = produceTypeService.selectAll(userId);
			return new WebResponse<List<Map<String, Object>>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<Map<String, Object>>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "后台查询所有产品类型",notes = "")
	@RequestMapping(value = "getAllProduceType", method = RequestMethod.GET)
	public WebResponse<List<Map<String, Object>>> selectAllByAdmin() {
		try {
			List<Map<String, Object>> list = produceTypeService.selectAllByAdmin();
			return new WebResponse<List<Map<String, Object>>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<Map<String, Object>>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "查询单条类型",notes = "")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<ProduceType> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<ProduceType>().success(produceTypeService.selectById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<ProduceType>().failure(e.getMessage());
        }
	}

	@ApiOperation(value = "新增产品类型",notes = "")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody ProduceType dto) {
		try {
			return new WebResponse<Boolean>().success(produceTypeService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "删除产品类型",notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(produceTypeService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改产品类型",notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody ProduceType dto) {
		try {
			return new WebResponse<Boolean>().success(produceTypeService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

}

