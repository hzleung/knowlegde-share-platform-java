package com.ly.cloud.user.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台点赞管理表#LikeManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@TableName("LY_LDD_DZB")
public class LikeManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞id#likeId#
     */
    @TableId("ID")
	private String likeId;
    /**
     * 点赞时间#likeTime#
     */
	@TableField("DZSJ")
	private Date likeTime;
    /**
     * 点赞文章id#likeArticleId#
     */
	@TableField("DZWZID")
	private String likeArticleId;
    /**
     * 点赞评论id#likeReviewId#
     */
	@TableField("DZPLID")
	private String likeReviewId;
    /**
     * 发表用户id#likerUserId#
     */
	@TableField("FBYHID")
	private String likerUserId;
	/**
     * 点赞对象id#targetId#
     */
	@TableField("DZDXID")
	private String targetId;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getLikeId() {
		return likeId;
	}

	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}

	public Date getLikeTime() {
		return likeTime;
	}

	public void setLikeTime(Date likeTime) {
		this.likeTime = likeTime;
	}

	public String getLikeArticleId() {
		return likeArticleId;
	}

	public void setLikeArticleId(String likeArticleId) {
		this.likeArticleId = likeArticleId;
	}

	public String getLikeReviewId() {
		return likeReviewId;
	}

	public void setLikeReviewId(String likeReviewId) {
		this.likeReviewId = likeReviewId;
	}

	public String getLikerUserId() {
		return likerUserId;
	}

	public void setLikerUserId(String likerUserId) {
		this.likerUserId = likerUserId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	@Override
	public String toString() {
		return "LikeManagement{" +
				"likeId='" + likeId + '\'' +
				", likeTime=" + likeTime +
				", likeArticleId='" + likeArticleId + '\'' +
				", likeReviewId='" + likeReviewId + '\'' +
				", likerUserId='" + likerUserId + '\'' +
				", targetId='" + targetId + '\'' +
				'}';
	}
}
