package com.ly.cloud.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台关于模块表#AboutManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@TableName("LY_LDD_GYB")
public class AboutManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关于模块id#aboutId#
     */
    @TableId("GYID")
	private String aboutId;
    /**
     * 标题#aboutTitle#
     */
	@TableField("BT")
	private String aboutTitle;
    /**
     * 内容#aboutContent#
     */
	@TableField("NR")
	private String aboutContent;
    /**
     * 项目附件（多张图片）#aboutFiles#
     */
	@TableField("FJ")
	private Object aboutFiles;
    /**
     * 项目封面#aboutBanner#
     */
	@TableField("FM")
	private String aboutBanner;


	public String getAboutId() {
		return aboutId;
	}

	public void setAboutId(String aboutId) {
		this.aboutId = aboutId;
	}

	public String getAboutTitle() {
		return aboutTitle;
	}

	public void setAboutTitle(String aboutTitle) {
		this.aboutTitle = aboutTitle;
	}

	public String getAboutContent() {
		return aboutContent;
	}

	public void setAboutContent(String aboutContent) {
		this.aboutContent = aboutContent;
	}

	public Object getAboutFiles() {
		return aboutFiles;
	}

	public void setAboutFiles(Object aboutFiles) {
		this.aboutFiles = aboutFiles;
	}

	public String getAboutBanner() {
		return aboutBanner;
	}

	public void setAboutBanner(String aboutBanner) {
		this.aboutBanner = aboutBanner;
	}

	@Override
	public String toString() {
		return "AboutManagement{" +
				"aboutId='" + aboutId + '\'' +
				", aboutTitle='" + aboutTitle + '\'' +
				", aboutContent='" + aboutContent + '\'' +
				", aboutFiles=" + aboutFiles +
				", aboutBanner='" + aboutBanner + '\'' +
				'}';
	}
}
