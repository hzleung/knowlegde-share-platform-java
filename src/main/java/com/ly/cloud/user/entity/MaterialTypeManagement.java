package com.ly.cloud.user.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台素材类型管理表#MaterialTypeManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2021-01-07
 */
@TableName("LY_LDD_SCLXB")
public class MaterialTypeManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 素材类型编号#materialTypeId#
     */
    @TableId("SCLXID")
	private String materialTypeId;
    /**
     * 素材类型名称#materialTypeName#
     */
	@TableField("SCLXMC")
	private String materialTypeName;
    /**
     * 创建时间#materialTypeCreateTime#
     */
	@TableField("CJSJ")
	private Date materialTypeCreateTime;
    /**
     * 素材类型状态#materialTypeStatus#
     */
	@TableField("SCLXZT")
	private String materialTypeStatus;


	public String getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(String materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	public Date getMaterialTypeCreateTime() {
		return materialTypeCreateTime;
	}

	public void setMaterialTypeCreateTime(Date materialTypeCreateTime) {
		this.materialTypeCreateTime = materialTypeCreateTime;
	}

	public String getMaterialTypeStatus() {
		return materialTypeStatus;
	}

	public void setMaterialTypeStatus(String materialTypeStatus) {
		this.materialTypeStatus = materialTypeStatus;
	}

	@Override
	public String toString() {
		return "MaterialTypeManagement{" +
			", materialTypeId=" + materialTypeId +
			", materialTypeName=" + materialTypeName +
			", materialTypeCreateTime=" + materialTypeCreateTime +
			", materialTypeStatus=" + materialTypeStatus +
			"}";
	}
}
