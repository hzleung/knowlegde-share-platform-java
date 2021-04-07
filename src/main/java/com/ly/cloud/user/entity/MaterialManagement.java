package com.ly.cloud.user.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台素材管理表#MaterialManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-01-07
 */
@TableName("LY_LDD_SCB")
public class MaterialManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 素材ID#materialId#
     */
    @TableId("SCID")
	private String materialId;
    /**
     * 素材类型#materiaType#
     */
	@TableField("SCLX")
	private String materiaType;
    /**
     * logo类型#logoType#
     */
	@TableField("LOGOLX")
	private String logoType;
    /**
     * 素材标题#materialTitle#
     */
	@TableField("SCBT")
	private String materialTitle;
    /**
     * 素材说明#materialDescribe#
     */
	@TableField("SCSM")
	private String materialDescribe;
    /**
     * 素材附件#materialFile#
     */
	@TableField("SCFJ")
	private String materialFile;
    /**
     * 素材封面#materialBanner#
     */
	@TableField("SCFM")
	private String materialBanner;
    /**
     * 素材发布用户#materialPublishUerId#
     */
	@TableField("FBYH")
	private String materialPublishUerId;
    /**
     * 素材发布时间#materialPublishTime#
     */
	@TableField("FBSJ")
	private Date materialPublishTime;
    /**
     * 素材状态#materialStatus#
     */
	@TableField("SCZT")
	private String materialStatus;


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

	@Override
	public String toString() {
		return "MaterialManagement{" +
			", materialId=" + materialId +
			", materiaType=" + materiaType +
			", logoType=" + logoType +
			", materialTitle=" + materialTitle +
			", materialDescribe=" + materialDescribe +
			", materialFile=" + materialFile +
			", materialBanner=" + materialBanner +
			", materialPublishUerId=" + materialPublishUerId +
			", materialPublishTime=" + materialPublishTime +
			", materialStatus=" + materialStatus +
			"}";
	}
}
