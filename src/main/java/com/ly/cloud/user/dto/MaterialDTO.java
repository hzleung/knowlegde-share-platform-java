package com.ly.cloud.user.dto;

import com.ly.cloud.user.entity.FileManagement;

import java.util.Date;
import java.util.List;

public class MaterialDTO {
    /**
     * 素材ID#materialId#
     */
    private String materialId;
    /**
     * 素材类型#materiaType#
     */
    private String materiaType;
    /**
     * logo类型#logoType#
     */
    private String logoType;
    /**
     * 素材标题#materialTitle#
     */
    private String materialTitle;
    /**
     * 素材说明#materialDescribe#
     */
    private String materialDescribe;
    /**
     * 素材附件#materialFile#
     */
    private String materialFile;
    /**
     * 素材封面#materialBanner#
     */
    private String materialBanner;
    /**
     * 素材发布用户#materialPublishUerId#
     */
    private String materialPublishUerId;
    /**
     * 素材发布时间#materialPublishTime#
     */
    private Date materialPublishTime;
    /**
     * 素材状态#materialStatus#
     */
    private String materialStatus;
    /**
     * 素材附件#files#
     */
    private List<FileManagement> files;
    /**
     * 作者#author#
     */
    private String author;
    /**
     * 作者头像#authorAvatar#
     */
    private String authorAvatar;

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMateriaType() {
        return materiaType;
    }

    public void setMateriaType(String materiaType) {
        this.materiaType = materiaType;
    }

    public String getLogoType() {
        return logoType;
    }

    public void setLogoType(String logoType) {
        this.logoType = logoType;
    }

    public String getMaterialTitle() {
        return materialTitle;
    }

    public void setMaterialTitle(String materialTitle) {
        this.materialTitle = materialTitle;
    }

    public String getMaterialDescribe() {
        return materialDescribe;
    }

    public void setMaterialDescribe(String materialDescribe) {
        this.materialDescribe = materialDescribe;
    }

    public String getMaterialFile() {
        return materialFile;
    }

    public void setMaterialFile(String materialFile) {
        this.materialFile = materialFile;
    }

    public String getMaterialBanner() {
        return materialBanner;
    }

    public void setMaterialBanner(String materialBanner) {
        this.materialBanner = materialBanner;
    }

    public String getMaterialPublishUerId() {
        return materialPublishUerId;
    }

    public void setMaterialPublishUerId(String materialPublishUerId) {
        this.materialPublishUerId = materialPublishUerId;
    }

    public Date getMaterialPublishTime() {
        return materialPublishTime;
    }

    public void setMaterialPublishTime(Date materialPublishTime) {
        this.materialPublishTime = materialPublishTime;
    }

    public String getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
    }

    public List<FileManagement> getFiles() {
        return files;
    }

    public void setFiles(List<FileManagement> files) {
        this.files = files;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }
}
