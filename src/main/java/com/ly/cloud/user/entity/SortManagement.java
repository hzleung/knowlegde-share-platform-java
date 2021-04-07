package com.ly.cloud.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台分类管理表#SortManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@TableName("LY_LDD_FLB")
public class SortManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id#sortId#
     */
    @TableId("ID")
	private String sortId;
    /**
     * 分类名称#sortName#
     */
	@TableField("FLMC")
	private String sortName;
    /**
     * 分类别名#sortAlias#
     */
	@TableField("FLBM")
	private String sortAlias;
    /**
     * 分类描述#sortDescription#
     */
	@TableField("FLMS")
	private String sortDescription;
    /**
     * 父分类id#sortParentId#
     */
	@TableField("FFLID")
	private String sortParentId;


	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortAlias() {
		return sortAlias;
	}

	public void setSortAlias(String sortAlias) {
		this.sortAlias = sortAlias;
	}

	public String getSortDescription() {
		return sortDescription;
	}

	public void setSortDescription(String sortDescription) {
		this.sortDescription = sortDescription;
	}

	public String getSortParentId() {
		return sortParentId;
	}

	public void setSortParentId(String sortParentId) {
		this.sortParentId = sortParentId;
	}

	@Override
	public String toString() {
		return "SortManagement{" +
			", sortId=" + sortId +
			", sortName=" + sortName +
			", sortAlias=" + sortAlias +
			", sortDescription=" + sortDescription +
			", sortParentId=" + sortParentId +
			"}";
	}
}
