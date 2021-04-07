package com.ly.cloud.user.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台项目跟踪管理表#projectFollowUpManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-02-02
 */
@TableName("LY_LDD_XMGZB")
public class ProjectFollowUpManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目编号#projectId#
     */
    @TableId("XMID")
	private String projectId;
    /**
     * 项目名称#projectName#
     */
	@TableField("XMMC")
	private String projectName;
    /**
     * 项目所属部门#projectDepartment#
     */
	@TableField("XMSSBM")
	private String projectDepartment;
    /**
     * 提交人#submitterId#
     */
	@TableField("TJYHID")
	private String submitterId;
    /**
     * 提交时间#submitTime#
     */
	@TableField("TJSJ")
	private Date submitTime;
    /**
     * 项目负责人#projectDirector#
     */
	@TableField("XMFZR")
	private String projectDirector;
    /**
     * 走查时间#checkTime#
     */
	@TableField("ZCSJ")
	private Date checkTime;
	/**
	 * 上次走查时间#lastCheckTime#
	 */
	@TableField("SCZCSJ")
	private Date lastCheckTime;
    /**
     * 状态#status#
     */
	@TableField("ZT")
	private String status;
    /**
     * 设计图地址#designUrl#
     */
	@TableField("SJTDZ")
	private String designUrl;
    /**
     * 项目访问地址#visitUrl#
     */
	@TableField("XMFWDZ")
	private String visitUrl;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDepartment() {
		return projectDepartment;
	}

	public void setProjectDepartment(String projectDepartment) {
		this.projectDepartment = projectDepartment;
	}

	public String getSubmitterId() {
		return submitterId;
	}

	public void setSubmitterId(String submitterId) {
		this.submitterId = submitterId;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getProjectDirector() {
		return projectDirector;
	}

	public void setProjectDirector(String projectDirector) {
		this.projectDirector = projectDirector;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(Date lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesignUrl() {
		return designUrl;
	}

	public void setDesignUrl(String designUrl) {
		this.designUrl = designUrl;
	}

	public String getVisitUrl() {
		return visitUrl;
	}

	public void setVisitUrl(String visitUrl) {
		this.visitUrl = visitUrl;
	}

	@Override
	public String toString() {
		return "ProjectFollowUpManagement{" +
				"projectId='" + projectId + '\'' +
				", projectName='" + projectName + '\'' +
				", projectDepartment='" + projectDepartment + '\'' +
				", submitterId='" + submitterId + '\'' +
				", submitTime=" + submitTime +
				", projectDirector='" + projectDirector + '\'' +
				", checkTime=" + checkTime +
				", lastCheckTime=" + lastCheckTime +
				", status='" + status + '\'' +
				", designUrl='" + designUrl + '\'' +
				", visitUrl='" + visitUrl + '\'' +
				'}';
	}
}
