package com.ly.cloud.user.controller;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.common.mybatisplus.plugins.PageInfo;
import com.ly.cloud.user.dto.ArticleDTO;
import com.ly.cloud.user.entity.LikeManagement;
import com.ly.cloud.user.mapper.ArticleManagementMapper;
import com.ly.cloud.user.mapper.LikeManagementMapper;
import com.ly.cloud.user.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import com.ly.cloud.user.entity.ArticleManagement;
import com.ly.cloud.user.service.ArticleManagementService;
import com.ly.cloud.web.utils.WebResponse;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 知识平台文章管理表#ArticleManagement# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@RestController
@Api(tags = "文章管理")
@RequestMapping("articleManagement")
public class ArticleManagementController {

	private final static Log logger = LogFactory.getLog(ArticleManagementController.class);

	@Resource
	private ArticleManagementService articleManagementService;

	@Resource
	private ArticleManagementMapper articleManagementMapper;
	@Resource
	private LikeManagementMapper likeManagementMapper;

	@Resource
	private UserMapper userMapper;

	@ApiOperation(value = "分页查询文章列表", notes = "hot传1表示获取最热文章排序列表，如果需要按最新时间排序，则不需要传hot. 为了区分首页和分享页，requireType有两个值：首页“homePage”, 分享页“sharePage”")
	@RequestMapping(value = "/getArticleList", method = RequestMethod.GET)
	public WebResponse<PageInfo<ArticleDTO>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam(required = false) String userId, @RequestParam(required = false) String hot, @RequestParam(required = false) String requireType) {
		try {
			Page<ArticleDTO> page = articleManagementService.selectArticlePage(new Page<ArticleDTO>(pageNum, pageSize), hot, requireType);
				List<ArticleDTO> pageList = PageInfo.toPageInfo(page).getList();
				for(ArticleDTO item: pageList){
					ArticleManagement entity = articleManagementMapper.selectById(item.getArticleId());
					String articleId = item.getArticleId();
					//登录后会有userId, 判断访问人是否点赞了改文章
					if (userId != null) {
						boolean isLike = likeManagementMapper.queryLikeByArticleIdAndUserId(articleId, userId);
						if(isLike) {
							if("0".equals(entity.getIsLike().toString()) || entity.getIsLike() == null) {
								Number hadlike = 1;
								articleManagementMapper.updateIsLikeById(entity.getArticleId(), hadlike);
							}
						}else {
							Number hadlike = 0;
							articleManagementMapper.updateIsLikeById(entity.getArticleId(), hadlike);
						}
					}else {
						Number hadlike = 0;
						articleManagementMapper.updateIsLikeById(entity.getArticleId(), hadlike);
					}
				}
				Page<ArticleDTO> newPage = articleManagementService.selectArticlePage(new Page<ArticleDTO>(pageNum, pageSize), hot, requireType);
				return new WebResponse<PageInfo<ArticleDTO>>().success(PageInfo.toPageInfo(newPage));
//			return new WebResponse<PageInfo<ArticleDTO>>().success(PageInfo.toPageInfo(newPage));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<ArticleDTO>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "文章详情", notes = "")
	@RequestMapping(value = "getArticleDetail", method = RequestMethod.GET)
	public WebResponse<ArticleDTO> get(@RequestParam("id") String id, @RequestParam(required = false) String userId) {
        try {
        	return new WebResponse<ArticleDTO>().success(articleManagementService.queryById(id, userId));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<ArticleDTO>().failure(e.getMessage());
        }
	}

	@ApiOperation(value = "获取所有文章", notes = "userId,是为了前台个人中心所发布的所有的个人文章；articleStatus为1时，代表已发布的文章，articleStatus为0时，代表为草稿箱的文章")
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public WebResponse<List<ArticleDTO>> selectAll(@RequestParam int articleStatus, @RequestParam(required = false) String userId) {
		try {
//			userId,是为了前台个人中心所发布的所有的个人文章
			List<ArticleDTO> list = articleManagementService.selectAll(articleStatus, userId);
			return new WebResponse<List<ArticleDTO>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<ArticleDTO>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "发布文章", notes = "")
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public WebResponse<Boolean> add(@RequestBody ArticleManagement entity) {
		try {
			entity.setArticlePublicTime(new Date());
			entity.setArticleVisit(BigDecimal.valueOf(0));
			entity.setArticleReview(BigDecimal.valueOf(0));
			entity.setArticleLike(BigDecimal.valueOf(0));
//			将banner拼成链接的形式再保存到数据库
			String bannerUrl = "http://192.168.35.93:8002/fileManagement/download?id="+entity.getBanner();
			entity.setBanner(bannerUrl);
//			先去用户表拿头像回来
			String userAvatar = userMapper.selectUserAvatarByUserId(entity.getUserId());
			if(userAvatar != null){
				entity.setArticleUserAvatar(userAvatar);
			}
			return new WebResponse<Boolean>().success(articleManagementService.insert(entity));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "删除文章", notes = "")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(articleManagementService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改文章", notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody ArticleManagement dto) {
		try {
			if(dto.getBanner() != null) {
				//	将banner拼成链接的形式再保存到数据库
				String bannerUrl = "http://192.168.35.93:8002/fileManagement/download?id="+dto.getBanner();
				dto.setBanner(bannerUrl);
			}
			return new WebResponse<Boolean>().success(articleManagementService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "相关推荐", notes = "")
	@RequestMapping(value = "/recommend", method = RequestMethod.GET)
	public WebResponse<List<ArticleManagement>> selectTopTen() {
		try {
			List<ArticleManagement> list = articleManagementService.selectTopTen();
			return new WebResponse<List<ArticleManagement>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<ArticleManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "搜索", notes = "")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public WebResponse<List<ArticleManagement>> selectByKeywords(@RequestParam(required = false) String keywords, @RequestParam(required = false) String articleType, @RequestParam(required = false) String articleLabel) {
		try {
			List<ArticleManagement> list = articleManagementService.selectByKeywords(keywords, articleType, articleLabel);
			return new WebResponse<List<ArticleManagement>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<ArticleManagement>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "数据汇总", notes = "")
	@RequestMapping(value = "/dataSum", method = RequestMethod.GET)
	public WebResponse<Map<String, Object>> selectData() {
		try {
			Map<String, Object> list = articleManagementService.selectData();
			return new WebResponse<Map<String, Object>>().success(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Map<String, Object>>().failure(e.getMessage());
		}
	}

}

