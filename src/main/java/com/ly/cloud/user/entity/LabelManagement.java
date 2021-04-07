package com.ly.cloud.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台标签管理表#LabelManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@TableName("LY_LDD_BQB")
public class LabelManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签id#labelId#
     */
    @TableId("ID")
	private String labelId;
    /**
     * 标签名称#labelName#
     */
	@TableField("BQMC")
	private String labelName;
    /**
     * 标签别名#labelAlias#
     */
	@TableField("BQBM")
	private String labelAlias;
    /**
     * 标签描述#labelDescription#
     */
	@TableField("BQMS")
	private String labelDescription;


	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getLabelAlias() {
		return labelAlias;
	}

	public void setLabelAlias(String labelAlias) {
		this.labelAlias = labelAlias;
	}

	public String getLabelDescription() {
		return labelDescription;
	}

	public void setLabelDescription(String labelDescription) {
		this.labelDescription = labelDescription;
	}

	@Override
	public String toString() {
		return "LabelManagement{" +
			", labelId=" + labelId +
			", labelName=" + labelName +
			", labelAlias=" + labelAlias +
			", labelDescription=" + labelDescription +
			"}";
	}
}
