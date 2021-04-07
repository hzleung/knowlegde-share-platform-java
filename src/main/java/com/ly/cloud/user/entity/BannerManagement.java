package com.ly.cloud.user.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台banner表#BannerManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-12
 */
@TableName("LY_LDD_HFGLB")
public class BannerManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 横幅编号#bannerId#
     */
    @TableId("HFBH")
	private String bannerId;
    /**
     * 横幅图片#banner#
     */
	@TableField("HFTP")
	private String banner;
    /**
     * 横幅标题#bannerTitle#
     */
	@TableField("HFBT")
	private String bannerTitle;
    /**
     * 横幅副标题#bannerSubtitle#
     */
	@TableField("HFFBT")
	private String bannerSubtitle;
    /**
     * 横幅上传时间#bannerPublishTime#
     */
	@TableField("SCSJ")
	private Date bannerPublishTime;
    /**
     * 横幅更新时间#bannerUpdateTime#
     */
	@TableField("GXSJ")
	private Date bannerUpdateTime;
    /**
     * 横幅状态#bannerStatus#
     */
	@TableField("SFSX")
	private BigDecimal bannerStatus;


	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getBannerTitle() {
		return bannerTitle;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}

	public String getBannerSubtitle() {
		return bannerSubtitle;
	}

	public void setBannerSubtitle(String bannerSubtitle) {
		this.bannerSubtitle = bannerSubtitle;
	}

	public Date getBannerPublishTime() {
		return bannerPublishTime;
	}

	public void setBannerPublishTime(Date bannerPublishTime) {
		this.bannerPublishTime = bannerPublishTime;
	}

	public Date getBannerUpdateTime() {
		return bannerUpdateTime;
	}

	public void setBannerUpdateTime(Date bannerUpdateTime) {
		this.bannerUpdateTime = bannerUpdateTime;
	}

	public BigDecimal getBannerStatus() {
		return bannerStatus;
	}

	public void setBannerStatus(BigDecimal bannerStatus) {
		this.bannerStatus = bannerStatus;
	}

	@Override
	public String toString() {
		return "BannerManagement{" +
			", bannerId=" + bannerId +
			", banner=" + banner +
			", bannerTitle=" + bannerTitle +
			", bannerSubtitle=" + bannerSubtitle +
			", bannerPublishTime=" + bannerPublishTime +
			", bannerUpdateTime=" + bannerUpdateTime +
			", bannerStatus=" + bannerStatus +
			"}";
	}
}
