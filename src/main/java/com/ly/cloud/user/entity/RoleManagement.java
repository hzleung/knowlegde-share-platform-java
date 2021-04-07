package com.ly.cloud.user.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台角色管理表#RoleManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-21
 */
@TableName("LY_LDD_JSGLB")
public class RoleManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id#roleId#
     */
    @TableId("JSID")
	private String roleId;
    /**
     * 角色名称#roleName#
     */
	@TableField("JSMC")
	private String roleName;
    /**
     * 角色编号#roleNumber#
     */
	@TableField("JSBH")
	private BigDecimal roleNumber;
    /**
     * 角色状态#roleStatus#
     */
	@TableField("JSZT")
	private BigDecimal roleStatus;


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public BigDecimal getRoleNumber() {
		return roleNumber;
	}

	public void setRoleNumber(BigDecimal roleNumber) {
		this.roleNumber = roleNumber;
	}

	public BigDecimal getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(BigDecimal roleStatus) {
		this.roleStatus = roleStatus;
	}

	@Override
	public String toString() {
		return "RoleManagement{" +
			", roleId=" + roleId +
			", roleName=" + roleName +
			", roleNumber=" + roleNumber +
			", roleStatus=" + roleStatus +
			"}";
	}
}
