package com.ly.cloud.user.dto;

import com.ly.cloud.user.entity.FileManagement;
import com.ly.cloud.user.entity.ProjectFollowUpManagement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: lianghaizhong
 * @mail: lianghaizhong@ly-sky.com
 * @since 2020/11/03 10:02
 */

public class FollowUpDTO {
    /**
     * 项目编号#projectId#
     */
    private String projectId;
    /**
     * 项目名称#projectName#
     */
    private String projectName;
    /**
     * 项目所属部门#projectDepartment#
     */
    private String projectDepartment;
    /**
     * 提交人#submitterId#
     */
    private String submitterId;
    /**
     * 项目提交人#submitter#
     */
    private String submitter;
    /**
     * 提交时间#submitTime#
     */
    private Date submitTime;
    /**
     * 项目负责人#projectDirector#
     */
    private String projectDirector;
    /**
     * 走查时间#checkTime#
     */
    private Date checkTime;
    /**
     * 上次走查时间#lastCheckTime#
     */
    private Date lastCheckTime;
    /**
     * 状态#status#
     */
    private String status;
    /**
     * 设计图地址#designUrl#
     */
    private String designUrl;
    /**
     * 项目访问地址#visitUrl#
     */
    private String visitUrl;
    /**
     * 项目走查报告#report#
     */
    private List<FileManagement> report;

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

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
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

    public List<FileManagement> getReport() {
        return report;
    }

    public void setReport(List<FileManagement> report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "FollowUpDTO{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectDepartment='" + projectDepartment + '\'' +
                ", submitterId='" + submitterId + '\'' +
                ", submitter='" + submitter + '\'' +
                ", submitTime=" + submitTime +
                ", projectDirector='" + projectDirector + '\'' +
                ", checkTime=" + checkTime +
                ", lastCheckTime=" + lastCheckTime +
                ", status='" + status + '\'' +
                ", designUrl='" + designUrl + '\'' +
                ", visitUrl='" + visitUrl + '\'' +
                ", report=" + report +
                '}';
    }
}
