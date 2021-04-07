package com.ly.cloud.user.mapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ly.cloud.user.dto.AuthorityDTO;
import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台用户表#User# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-20
 */
public interface UserMapper extends BaseMapper<User> {

	List<User> selectPage(@Param("page")Page<User> page, @Param("dto")User dto);

	User selectUserByYhyxAndMM (@Param("yhyx") String yhyx,@Param("mm") String mm);

	User selectUserByYhyx (@Param("yhyx") String yhyx);

	User selectUserByuserId (@Param("userId") String userId);

    boolean updatePassword(@Param("userEmail")String userEmail, @Param("changePassword")String changePassword);

    boolean updateXP(@Param("userId")String userId, @Param("xp") BigDecimal xp);

	boolean updateUserAvatar(@Param("userId")String userId, @Param("userAvatar")String userAvatar);

	List<User> queryByKeywords(@Param("page")Page<User> page, @Param("userName")String userName, @Param("department")String department);

    String selectUserAvatarByUserId(@Param("userId")String userId);

    boolean updateUserStatus(@Param("userId")String userId, @Param("userStatus")String userStatus, @Param("userType")String userType);

	void updateLastLoginTime(@Param("yhyx")String yhyx, @Param("lastLoginTime")Date lastLoginTime);

	List<Map<String, Object>> queryAuthority(@Param("userId")String userId);

	List<Map<String, Object>> queryMenuTypeList(@Param("menuId")String menuId);

    boolean setProjectTypeAuthority(@Param("list") List<Map<String, Object>> produceType);

	List<Map<String, Object>> getAdminAuthority();

	String getProjectMapMenu(@Param("userId")String userId);

	List<Map<String, Object>> getSpecialAuthority(@Param("userId")String userId);

	boolean setSpecialAuthority(@Param("userList") List<Map<String, Object>> userList);

	List<Map<String, Object>> queryAuthorityuserList();

	Map<String, Object> queryByUserIdAndProjectId(@Param("userId")String userId, @Param("projectId")String projectId);

	boolean deleteSpecialAuthority(@Param("projectId")String projectId, @Param("userId")String userId);

    void deleteSpecialAuthorityByUserId(@Param("userId")Serializable userId);

	List<Map<String, Object>> getProduceTypeAuthority(String menuId);

	boolean deleteMapAuthority(String menuId);

	String getUserType(@Param("userId")String materialPublishUerId);

//	boolean insertProduceTypeAuthority(@Param("menuId")String menuId, @Param("userId")String userId);
}
