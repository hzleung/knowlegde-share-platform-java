package com.ly.cloud.user.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台部门类别表#Department#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-22
 */
@TableName("LY_LDD_BMLBB")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键#departmentId#
     */
    @TableId("PKID")
	private String departmentId;
    /**
     * 部门代码#departmentCode#
     */
	@TableField("BMDM")
	private String departmentCode;
    /**
     * 部门名称#departmentName#
     */
	@TableField("BMMC")
	private String departmentName;
    /**
     * 部门简称#departmentAbbreviation#
     */
	@TableField("BMJC")
	private String departmentAbbreviation;
    /**
     * 部门类别#departmentType#
     */
	@TableField("BMLB")
	private String departmentType;
    /**
     * 排序号#sortNumber#
     */
	@TableField("PXH")
	private BigDecimal sortNumber;
    /**
     * 状态#status#
     */
	@TableField("ZT")
	private String status;
    /**
     * 最后修改时间#lastChangeTime#
     */
	@TableField("ZHXGSJ")
	private Date lastChangeTime;


	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentAbbreviation() {
		return departmentAbbreviation;
	}

	public void setDepartmentAbbreviation(String departmentAbbreviation) {
		this.departmentAbbreviation = departmentAbbreviation;
	}

	public String getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	public BigDecimal getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(BigDecimal sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastChangeTime() {
		return lastChangeTime;
	}

	public void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	@Override
	public String toString() {
		return "Department{" +
			", departmentId=" + departmentId +
			", departmentCode=" + departmentCode +
			", departmentName=" + departmentName +
			", departmentAbbreviation=" + departmentAbbreviation +
			", departmentType=" + departmentType +
			", sortNumber=" + sortNumber +
			", status=" + status +
			", lastChangeTime=" + lastChangeTime +
			"}";
	}
}
