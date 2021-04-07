package com.ly.cloud.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.cloud.user.dto.AuthorityDTO;
import com.ly.cloud.user.entity.User;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识平台用户表#User# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-20
 */
@Component
public interface UserService extends IService<User> {

	Page<User> selectPage(Page<User> page, User dto);

//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    User login(String yhyx, String mm);
    User adminLogin(String yhyx, String mm);

    boolean updatePasswordById(String userEmail, String oldPassword, String newPassword);

	boolean updateUserAvatar(String userId, String userAvatar);

    Page<User> selectByKeywords(Page<User> page, String userName, String department);

    boolean updateUserStatus(String userId, String userStatus, String userType);

    List<Map<String, Object>> selectAuthority(String userId);

    boolean setProjectTypeAuthority(List<Map<String, Object>> produceType);

    Map<String, Object> getAdminAuthority();

    boolean setSpecialAuthority(List<Map<String, Object>> userList);

    List<Map<String, Object>> getAuthorityUserList(String projectId);

    boolean deleteSpecialAuthority(String projectId, String userId);

//    boolean logout(String userId, String email, String loginUserId);

//    boolean insertProduceTypeAuthority(String menuId, String produceTypeId);
//	boolean insert(User user);
}
