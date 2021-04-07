package com.ly.cloud.user.dto;

import com.ly.cloud.user.entity.FileManagement;

import java.util.Date;
import java.util.List;

/**
 * @author: lianghaizhong
 * @mail: lianghaizhong@ly-sky.com
 * @since 2020/11/03 10:02
 */


public class ProjectDTO {
    /**
     * 项目id#projectId#
     */
    private String projectId;
    /**
     * 发布时间#projectPublicTime#
     */
    private Date projectPublicTime;
    /**
     * 发布用户#projectPublicUser#
     */
    private String projectPublicUser;
    /**
     * 项目名称#projectName#
     */
    private String projectName;
    /**
     * 项目链接#projectUrl#
     */
    private String projectUrl;
    /**
     * 项目所属大区#projectArea#
     */
    private String projectArea;
    /**
     * 项目类型#projectType#
     */
    private String projectType;
    /**
     * 项目版本号#projectVersion#
     */
    private String projectVersion;
    /**
     * 项目封面#projectBanner#
     */
    private String projectBanner;
    /**
     * 项目内容#projectContent#
     */
    private String projectContent;
    /**
     * 项目状态#projectStatus#
     */
    private String projectStatus;
    /**
     * 项目状态#author#
     */
    private String author;
    /**
     * 文章附件#projectFiles#
     */
    private List<FileManagement> projectFiles;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Date getProjectPublicTime() {
        return projectPublicTime;
    }

    public void setProjectPublicTime(Date projectPublicTime) {
        this.projectPublicTime = projectPublicTime;
    }

    public String getProjectPublicUser() {
        return projectPublicUser;
    }

    public void setProjectPublicUser(String projectPublicUser) {
        this.projectPublicUser = projectPublicUser;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(String projectArea) {
        this.projectArea = projectArea;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }

    public String getProjectBanner() {
        return projectBanner;
    }

    public void setProjectBanner(String projectBanner) {
        this.projectBanner = projectBanner;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<FileManagement> getProjectFiles() {
        return projectFiles;
    }

    public void setProjectFiles(List<FileManagement> projectFiles) {
        this.projectFiles = projectFiles;
    }
}