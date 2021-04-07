package com.ly.cloud.user.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.ly.cloud.exception.biz.BusinessException;
import com.ly.cloud.user.dto.AuthorityDTO;
import com.ly.cloud.user.entity.FileManagement;
import com.ly.cloud.user.mapper.RoleManagementMapper;
import jdk.nashorn.internal.runtime.arrays.ArrayIndex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.User;
import com.ly.cloud.user.mapper.UserMapper;
import com.ly.cloud.user.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * 知识平台用户表#User# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-20
 */
@Service
//@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final static Log logger = LogFactory.getLog(UserServiceImpl.class);

	@Value("${download-path.path}")
	private String downloadPath;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleManagementMapper roleManagementMapper;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	//用户登录次数计数  redisKey 前缀
	private String SHIRO_LOGIN_COUNT = "shiro_login_count_";

	//用户登录是否被锁定  redisKey 前缀
	private String SHIRO_IS_LOCK = "shiro_is_lock_";

	@Override
	public Page<User> selectPage(Page<User> page, User dto) {
		List<User> user = userMapper.selectPage(page, dto);
		page.setRecords(user);
		return page;
	}

	@Override
	public User selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(User entity) {
		if(entity.getPassword() == null){
			 String defaultPassword = "e10adc3949ba59abbe56e057f20f883e";
			 entity.setPassword(defaultPassword);
		} else {
//			String password = DigestUtils.md5DigestAsHex(entity.getPassword().getBytes());  // md5加密
			String password = entity.getPassword();
			entity.setPassword(password);
		}
		entity.setUserId(UUID.randomUUID().toString()); // 为每个用户自动生成UUID
//		entity.setUserStatus(BigDecimal.valueOf(1));
		entity.setRegisterTime(new Date());
//		将头像拼成链接的形式再保存到数据库
		String avatarUrl = "http://192.168.35.93:8002/fileManagement/download?id=d4794ad1-0f03-4112-a482-8ab3947a9ea2";
		entity.setUserAvatar(avatarUrl);
		User hasUser = userMapper.selectUserByYhyx(entity.getUserEmail());
		if(hasUser != null) {
			throw new BusinessException("账号已存在");
		}
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
//		删除用户需要把对应的特殊权限也删除掉,通过用户id删除特殊权限
		userMapper.deleteSpecialAuthorityByUserId(id);
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(User entity) {
		return super.updateById(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public User login(String yhyx , String mm){
		//校验
//		User user = userMapper.selectUserByYhyxAndMM(yhyx,DigestUtils.md5DigestAsHex(mm.getBytes()));
		User user = userMapper.selectUserByYhyxAndMM(yhyx, mm);
		User hasUser = userMapper.selectUserByYhyx(yhyx);
		if(user == null){
			if(hasUser == null) {
				throw new BusinessException("账号不存在");
			}
			throw new BusinessException("密码错误");
		} else {
			if("0".equals(user.getUserStatus().toString())) {
				throw new BusinessException("账号还在审核中，请联系管理员！");
			} else if("2".equals(user.getUserStatus().toString())) {
				throw new BusinessException("账号未通过审核，请联系管理员！");
			}
		}
		Date lastLoginTime = new Date();
		userMapper.updateLastLoginTime(yhyx, lastLoginTime);
		return user;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public User adminLogin(String yhyx , String mm){
		//校验
//		User user = userMapper.selectUserByYhyxAndMM(yhyx,DigestUtils.md5DigestAsHex(mm.getBytes()));
		User user = userMapper.selectUserByYhyxAndMM(yhyx, mm);
		User hasUser = userMapper.selectUserByYhyx(yhyx);
		if(hasUser == null) {
			throw new BusinessException("账号不存在");
		} else {
			if(user == null){  // 如果密码不正确
				throw new BusinessException("密码错误");
			} else {
				System.out.println(user.getUserType());
				if("5".equals(user.getUserType().toString())) {
					return user;
				} else {
					throw new BusinessException("非管理员，无法登录系统！");
				}
			}
		}
//		if(user == null){
//			if(hasUser == null) {
//				throw new BusinessException("账号不存在");
//			}
//			throw new BusinessException("密码错误");
//		} else {
//			if("5".equals(user.getUserType())) {
//				throw new BusinessException("非管理员，无法登录系统！");
//			}
//		}
//		return user;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updatePasswordById(String userEmail, String oldPassword, String newPassword){
		//校验
		User currentUser = userMapper.selectUserByYhyx(userEmail);
		if(currentUser == null) {
			throw new BusinessException("用户不存在！");
		}
//		if(DigestUtils.md5DigestAsHex(oldPassword.getBytes()).equals(currentUser.getPassword())){
		if(oldPassword.equals(currentUser.getPassword())){
//			String changePassword =DigestUtils.md5DigestAsHex(newPassword.getBytes());
			String changePassword =newPassword;
			if (changePassword.equals(currentUser.getPassword())) {
				throw new BusinessException("新密码不能与旧密码一致！");
			}
			return  userMapper.updatePassword(userEmail, changePassword);
		} else {
			throw new BusinessException("旧密码输入错误！");
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateUserAvatar(String userId, String userAvatar){
		boolean isUpdate = userMapper.updateUserAvatar(userId, userAvatar);
//		if(isUpdate){
//
//		}
		String userAvatarUrl = downloadPath + userAvatar;
		return  userMapper.updateUserAvatar(userId, userAvatarUrl);
	}


	@Override
	public Page<User> selectByKeywords(Page<User> page, String userName, String department) {
		List<User> list = userMapper.queryByKeywords(page, userName, department);
		page.setRecords(list);
		return page;
	}

	@Override
	public boolean updateUserStatus(String userId, String userStatus, String userType) {
		return userMapper.updateUserStatus(userId, userStatus, userType);
	}

	@Override
	public List<Map<String, Object>> selectAuthority(String userId) {
//		先拿到该角色对应的权限菜单
		List<Map<String, Object>> list = userMapper.queryAuthority(userId);
//		以下是特殊处理
//		先判断该用户对应的角色有没有项目地图的权限,如果没有说明是内部员工（游客没有userId，不用理）
		String hasProjectMap = userMapper.getProjectMapMenu(userId);
		if (hasProjectMap == null || hasProjectMap == "") {
//			如果该角色没有权限，则去查项目地图权限表，看看是否有特殊权限
			List<Map<String, Object>> specialAuthority =  userMapper.getSpecialAuthority(userId);
			if (specialAuthority.size() != 0) {
				/*有特殊权限，手动插入“项目地图”菜单*/
				Map<String, Object> menuItem = new HashMap<>();
				menuItem.put("menuId", "ly-zsfxpt_projectmap_special");
				menuItem.put("menuName", "项目地图");
				list.add(menuItem);
			}
		}
		return list;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)  // 这个声明的意思是: 当该方法存在异常时,修改的数据会回滚
	public boolean setProjectTypeAuthority(List<Map<String, Object>> produceType) {
//		在新增之前，先把数据库中存在的数据删除，然后再执行插入
//		先拿到传进来的menuId
		String menuId = "";
		for (Map<String, Object> produceTypeItem : produceType) {
			menuId = produceTypeItem.get("menuId").toString();
		}
		boolean hasDelete = userMapper.deleteMapAuthority(menuId);
		if (hasDelete) {
			System.out.println("删除成功!");
		} else {
			System.out.println("删除失败!");
		}
		return userMapper.setProjectTypeAuthority(produceType);
	}

	@Override
	public Map<String, Object> getAdminAuthority() {
		List<Map<String, Object>> list = userMapper.getAdminAuthority();
		List<Map<String, Object>> adminList = new ArrayList<>();
		List<Map<String, Object>> directorList = new ArrayList<>();
		List<Map<String, Object>> designerList = new ArrayList<>();
		List<Map<String, Object>> implementList = new ArrayList<>();
		List<Map<String, Object>> internalList = new ArrayList<>();
		List<Map<String, Object>> guestList = new ArrayList<>();
		for (Map<String, Object> authorityItem : list){
			if(authorityItem.get("menuId").toString().indexOf("projectmap") != -1) {
//				如果是项目地图，则根据菜单编号（menuId）去查找对应的项目地图权限
				String menuId = authorityItem.get("menuId").toString();
				List<Map<String, Object>> produceTypeAuthority = userMapper.getProduceTypeAuthority(menuId);
				authorityItem.put("produceType", produceTypeAuthority);
			}
			if ("ly-zsfxpt-js_admin".equals(authorityItem.get("roleId"))) {
				adminList.add(authorityItem);
			} else if ("ly-zsfxpt-js_director".equals(authorityItem.get("roleId"))) {
				directorList.add(authorityItem);
			} else if ("ly-zsfxpt-js_designer".equals(authorityItem.get("roleId"))) {
				designerList.add(authorityItem);
			} else if ("ly-zsfxpt-js_implementer".equals(authorityItem.get("roleId"))) {
				implementList.add(authorityItem);
			} else if ("ly-zsfxpt-js_internalstaff".equals(authorityItem.get("roleId"))) {
				internalList.add(authorityItem);
			} else if ("ly-zsfxpt-js_guest".equals(authorityItem.get("roleId"))) {
				guestList.add(authorityItem);
			}
		}
		Map<String, Object> resultList = new HashMap<>();
		resultList.put("系统管理员", adminList);
		resultList.put("部门主管", directorList);
		resultList.put("设计师", designerList);
		resultList.put("实施人员", implementList);
		resultList.put("内部员工", internalList);
		resultList.put("游客", guestList);
		return resultList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)  // 这个声明的意思是: 当该方法存在异常时,修改的数据会回滚
	public boolean setSpecialAuthority(List<Map<String, Object>> userList) {
		//	先根据id去找到对应的角色id
		for (Map<String, Object> userItem : userList) {
			//	根据userId和projectId去项目权限表(XMDTQXB)查询该人在该项目中是否存在
			String userId = userItem.get("userId").toString();
			String projectId = userItem.get("projectId").toString();
			Map<String, Object> isExist = userMapper.queryByUserIdAndProjectId(userId, projectId);
			if(isExist != null) {
			//	若存在，则先删掉
				userMapper.deleteSpecialAuthority(projectId, userId);
			}
			String roleId = roleManagementMapper.selectRoleIdByUserId(userId);
			Date createdTime = new Date();
			userItem.put("roleId", roleId);
			userItem.put("createdTime", createdTime);
		}
		return userMapper.setSpecialAuthority(userList);
	}

	@Override
	public boolean deleteSpecialAuthority(String projectId, String userId) {
		return userMapper.deleteSpecialAuthority(projectId, userId);
	}

	@Override
	public List<Map<String, Object>> getAuthorityUserList(String projectId) {
//			先拿到内部人员和实施两个角色的所有人员
		List<Map<String, Object>> userList = userMapper.queryAuthorityuserList();
		for (Map<String, Object> userItem : userList) {
			String userId = userItem.get("userId").toString();
			Map<String, Object> specialUser = userMapper.queryByUserIdAndProjectId(userId, projectId);
			if (specialUser != null) {
				userItem.put("exist","true");
			} else {
				userItem.put("exist", "false");
			}
//			userItem.remove("userId");
		}
		return userList;
	}

//	@Override
//	public boolean logout(String userId, String email, String loginUserId) {
//		if(userId.equals(loginUserId)) {
//			stringRedisTemplate.delete(SHIRO_IS_LOCK + email);
//			stringRedisTemplate.delete(SHIRO_LOGIN_COUNT + email);
//		} else {
//			throw new BusinessException("未登录！");
//		}
//		return true;
//	}

//	@Override
//	public boolean insertProduceTypeAuthority(String menuId, String produceTypeId) {
//		return userMapper.insertProduceTypeAuthority(menuId, produceTypeId);
//	}
}
