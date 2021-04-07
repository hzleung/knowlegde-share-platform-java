package com.ly.cloud.user.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 知识平台用户表#User#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-20
 */
@TableName("LY_LDD_YHB")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id#userId#
     */
    @TableId("YHID")
	private String userId;
    /**
     * 用户IP#userIP#
     */
	@TableField("IP")
	private String userIP;
    /**
     * 用户名#userName#
     */
	@TableField("YHM")
	private String userName;
    /**
     * 用户密码，md5加密#password#
     */
	@TableField("MM")
	private String password;
    /**
     * 用户昵称#nickName#
     */
	@TableField("NC")
	private String nickName;
    /**
     * 用户邮箱#userEmail#
     */
	@TableField("YHYX")
	private String userEmail;
    /**
     * 用户手机号码#phone#
     */
	@TableField("YHSJ")
	private BigDecimal phone;
    /**
     * 用户所属部门编号#apartmentNumber#
     */
	@TableField("BMBH")
	private String apartmentNumber;
    /**
     * 用户所属部门名称#apartmentName#
     */
	@TableField("BMMC")
	private String apartmentName;
    /**
     * 用户职务#userPost#
     */
	@TableField("YHZW")
	private String userPost;
    /**
     * 用户注册时间#registerTime#
     */
	@TableField("ZCSJ")
	private Date registerTime;
    /**
     * 用户上次登录时间#lastLoginTime#
     */
	@TableField("SCDLSJ")
	private String lastLoginTime;
    /**
     * 用户生日#userBirthday#
     */
	@TableField("YHSR")
	private String userBirthday;
    /**
     * 用户年龄#userAge#
     */
	@TableField("YHNL")
	private BigDecimal userAge;
    /**
     * 用户头像#userAvatar#
     */
	@TableField("YHTX")
	private String userAvatar;
	/**
	 * 审核状态#userStatus#：审核状态:0：未审核  1：通过  2：未通过（默认0）
	 */
	@TableField("YHZT")
	private BigDecimal userStatus;

	/**
	 * 用户类型#userType#
	 */
	@TableField("YHLX")
	private BigDecimal userType;

	/**
	 * 用户性别#userSex#
	 */
	@TableField("YHXB")
	private BigDecimal userSex;

	/**
	 * 用户经验值#userXP#
	 */
	@TableField("YHJYZ")
	private BigDecimal userXP;
	/**
	 * 用户经验值#userTypeName#
	 */
	private String userTypeName;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public BigDecimal getPhone() {
		return phone;
	}

	public void setPhone(BigDecimal phone) {
		this.phone = phone;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getUserPost() {
		return userPost;
	}

	public void setUserPost(String userPost) {
		this.userPost = userPost;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public BigDecimal getUserAge() {
		return userAge;
	}

	public void setUserAge(BigDecimal userAge) {
		this.userAge = userAge;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public BigDecimal getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(BigDecimal userStatus) {
		this.userStatus = userStatus;
	}

	public BigDecimal getUserType() {
		return userType;
	}

	public void setUserType(BigDecimal userType) {
		this.userType = userType;
	}

	public BigDecimal getUserSex() {
		return userSex;
	}

	public void setUserSex(BigDecimal userSex) {
		this.userSex = userSex;
	}

	public BigDecimal getUserXP() {
		return userXP;
	}

	public void setUserXP(BigDecimal userXP) {
		this.userXP = userXP;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId='" + userId + '\'' +
				", userIP='" + userIP + '\'' +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", nickName='" + nickName + '\'' +
				", userEmail='" + userEmail + '\'' +
				", phone=" + phone +
				", apartmentNumber='" + apartmentNumber + '\'' +
				", apartmentName='" + apartmentName + '\'' +
				", userPost='" + userPost + '\'' +
				", registerTime=" + registerTime +
				", lastLoginTime='" + lastLoginTime + '\'' +
				", userBirthday='" + userBirthday + '\'' +
				", userAge=" + userAge +
				", userAvatar='" + userAvatar + '\'' +
				", userStatus=" + userStatus +
				", userType=" + userType +
				", userSex=" + userSex +
				", userXP=" + userXP +
				", userTypeName='" + userTypeName + '\'' +
				'}';
	}
}
