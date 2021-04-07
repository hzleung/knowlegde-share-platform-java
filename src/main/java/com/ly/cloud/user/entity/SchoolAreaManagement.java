package com.ly.cloud.user.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台高校大区管理表#SchoolAreaManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-12-02
 */
@TableName("LY_LDD_GXDQGLB")
public class SchoolAreaManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 高校ID#schoolId#
     */
    @TableId("GXID")
	private String schoolId;
    /**
     * 高校名称#schoolName#
     */
	@TableField("GXMC")
	private String schoolName;
    /**
     * 所属大区#schoolArea#
     */
	@TableField("SSDQ")
	private String schoolArea;
    /**
     * 创建时间#createdTime#
     */
	@TableField("CJSJ")
	private Date createdTime;
    /**
     * 状态#status#
     */
	@TableField("ZT")
	private BigDecimal status;


	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public BigDecimal getStatus() {
		return status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SchoolAreaManagement{" +
			", schoolId=" + schoolId +
			", schoolName=" + schoolName +
			", schoolArea=" + schoolArea +
			", createdTime=" + createdTime +
			", status=" + status +
			"}";
	}
}
