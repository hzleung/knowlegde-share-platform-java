package com.ly.cloud.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.ly.cloud.user.dto.AuthorityDTO;
import com.ly.cloud.user.mapper.BannerManagementMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.common.mybatisplus.plugins.PageInfo;
import com.ly.cloud.user.entity.User;
import com.ly.cloud.user.service.UserService;
import com.ly.cloud.web.utils.WebResponse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 知识平台用户表#User# 前端控制器
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-20
 */

@RestController
@Api(tags = "登录/注册/用户操作")
@RequestMapping("user")
public class UserController {

	private final static Log logger = LogFactory.getLog(UserController.class);
	
	@Resource
	private UserService userService;

	@Resource
	private BannerManagementMapper bannerManagementMapper;

	@ApiOperation(value = "分页查询用户信息列表",notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET)

	public WebResponse<PageInfo<User>> queryPage(@RequestParam int pageNum, @RequestParam int pageSize, @ModelAttribute User dto) {
		try {
			Page<User> page = userService.selectPage(new Page<User>(pageNum, pageSize), dto);
			return new WebResponse<PageInfo<User>>().success(PageInfo.toPageInfo(page));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<User>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "根据id查询用户",notes = "")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebResponse<User> get(@PathVariable("id") String id) {
        try {
        	return new WebResponse<User>().success(userService.selectById(id));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return new WebResponse<User>().failure(e.getMessage());
        }
	}

	@ApiOperation(value = "注册",notes = "")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public WebResponse<Boolean> register(@RequestBody User dto) {
		try {
			dto.setUserStatus(BigDecimal.valueOf(0));
			dto.setUserType(BigDecimal.valueOf(0));
			dto.setUserXP(BigDecimal.valueOf(0));
			return new WebResponse<Boolean>().success(userService.insert(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "登录",notes = "")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public WebResponse<User> login(@RequestBody User dto) {
		try {
			String yhyx = dto.getUserEmail();
			String mm = dto.getPassword();
			return new WebResponse<User>().success(userService.login(yhyx, mm));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<User>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "后台登录",notes = "")
	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public WebResponse<User> adminLogin(@RequestBody User dto) {
		try {
			String yhyx = dto.getUserEmail();
			String mm = dto.getPassword();
			return new WebResponse<User>().success(userService.adminLogin(yhyx, mm));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<User>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "删除用户",notes = "根据id删除用户")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public WebResponse<Boolean> delete(@PathVariable("id") String id) {
		try {
			return new WebResponse<Boolean>().success(userService.deleteById(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "更新当前用户信息",notes = "")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestBody User dto) {
		try {
			dto.setUserSex(BigDecimal.valueOf(0));
			if(dto.getUserAvatar() != null){
				String userAvatarUrl = "http://192.168.35.93:8002/fileManagement/download?id=" + dto.getUserAvatar();
				dto.setUserAvatar(userAvatarUrl);
			}
			return new WebResponse<Boolean>().success(userService.updateById(dto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改密码",notes = "")
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public WebResponse<Boolean> update(@RequestParam String userEmail, @RequestParam String oldPassword, @RequestParam String newPassword) {
		try {
			return new WebResponse<Boolean>().success(userService.updatePasswordById(userEmail, oldPassword, newPassword));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改头像",notes = "")
	@RequestMapping(value = "/updateUserAvatar", method = RequestMethod.POST)
	public WebResponse<Boolean> updateUserAvatar(@RequestParam String userId, @RequestParam String userAvatar) {
		try {
			return new WebResponse<Boolean>().success(userService.updateUserAvatar(userId, userAvatar));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}


	@ApiOperation(value = "搜索", notes = "")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public WebResponse<PageInfo<User>> selectByKeywords(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam(required = false) String userName, @RequestParam(required = false) String department) {
		try {
			Page<User> list = userService.selectByKeywords(new Page<User>(pageNum, pageSize), userName, department);
			return new WebResponse<PageInfo<User>>().success(PageInfo.toPageInfo(list));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<PageInfo<User>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "用户审核",notes = "审核新注册的用户，通过后并分配角色;必传参数：userId(用户id),userStatus(用户状态),userType(用户类型，具体类型对应的编号可查看石墨接口文档)")
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	public WebResponse<Boolean> checkUser(@RequestBody User dto) {
		try {
			String userId = dto.getUserId();
			String userStatus = dto.getUserStatus().toString();
			String userType = dto.getUserType().toString();
			return new WebResponse<Boolean>().success(userService.updateUserStatus(userId, userStatus, userType));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "权限查询",notes = "")
	@RequestMapping(value = "/authority", method = RequestMethod.POST)
	public WebResponse<List<Map<String, Object>>> getAuthority(@RequestParam String userId, @RequestHeader(name = "X-Forwarded-For",required = false) String ip) {
		try {
//			由于权限是登录后必须要获取的接口，因此在这里处理访问记录的事情（同BannerController）
//			先通过IP取获取访问记录表（LY_LDD_FWJLB）,获取是否存在当天同一ip的访问记录数据，如果存在则可以记录，否则不能记录。
            if (ip != null) {
                Map<String, Object> recordMap = bannerManagementMapper.queryStaticsRecord(ip);
                if (recordMap == null) {
                    String recordId = UUID.randomUUID().toString();
                    Date createdTime = new Date();
                    bannerManagementMapper.insertRecord(recordId, ip, createdTime);
                }
            }
			return new WebResponse<List<Map<String, Object>>>().success(userService.selectAuthority(userId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<Map<String, Object>>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "项目地图权限设置",notes = "传数组，数组中每个对象包含键值对：菜单id(menuId)，产品类型id(produceTypeId)")
	@RequestMapping(value = "/setProjectTypeAuthority", method = RequestMethod.POST)
	public WebResponse<Boolean> setProjectTypeAuthority(@RequestBody List<Map<String, Object>> produceType) {
		try {
			return new WebResponse<Boolean>().success(userService.setProjectTypeAuthority(produceType));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "特殊权限设置",notes = "传二个参数：项目地图id(projectId)，被设置权限的用户id(userId)")
	@RequestMapping(value = "/setSpecialAuthority", method = RequestMethod.POST)
	public WebResponse<Boolean> setSpecialAuthority(@RequestBody List<Map<String, Object>> userList) {
		try {
			return new WebResponse<Boolean>().success(userService.setSpecialAuthority(userList));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "特殊权限删除",notes = "传二个参数：项目地图id(projectId)，被设置权限的用户id(userId)")
	@RequestMapping(value = "/{projectId}/{userId}/deleteSpecialAuthority", method = RequestMethod.POST)
	public WebResponse<Boolean> deleteSpecialAuthority(@PathVariable("projectId") String projectId, @PathVariable("userId") String userId) {
		try {
			return new WebResponse<Boolean>().success(userService.deleteSpecialAuthority(projectId, userId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Boolean>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "后台角色及角色权限范围查询",notes = "")
	@RequestMapping(value = "/adminAuthority", method = RequestMethod.POST)
	public WebResponse<Map<String, Object>> getAdminAuthority() {
		try {
			return new WebResponse<Map<String, Object>>().success(userService.getAdminAuthority());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<Map<String, Object>>().failure(e.getMessage());
		}
	}

	@ApiOperation(value = "可分配特殊权限人员列表",notes = "")
	@RequestMapping(value = "/authorityUserList", method = RequestMethod.GET)
	public WebResponse<List<Map<String, Object>>> getAuthorityUserList(@RequestParam String projectId){
		try {
			return new WebResponse<List<Map<String, Object>>>().success(userService.getAuthorityUserList(projectId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WebResponse<List<Map<String, Object>>>().failure(e.getMessage());
		}
	}

//	@ApiOperation(value = "注销",notes = "传登录名（即邮箱）")
//	@RequestMapping(value = "/logout", method = RequestMethod.POST)
//	public WebResponse<Boolean> logout(@RequestParam String userId, @RequestParam String email, @RequestHeader(name = "userId") String loginUserId, HttpSession session) {
//		try {
//			session.invalidate();
//			return new WebResponse<Boolean>().success(userService.logout(userId, email, loginUserId));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new WebResponse<Boolean>().failure(e.getMessage());
//		}
//	}

//	@ApiOperation(value = "产品类型权限分配",notes = "传两个参数：菜单编号menuId，权限编号produceTypeId（即产品类型的id）")
//	@RequestMapping(value = "/authorityProduceType", method = RequestMethod.POST)
//	public WebResponse<Boolean> insertProduceTypeAuthority(@RequestParam String menuId, @RequestParam String produceTypeId) {
//		try {
//			return new WebResponse<Boolean>().success(userService.insertProduceTypeAuthority(menuId, produceTypeId));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new WebResponse<Boolean>().failure(e.getMessage());
//		}
//	}
}

