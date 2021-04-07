package com.ly.cloud.user.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台评论管理表#ReviewManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@TableName("LY_LDD_PLB")
public class ReviewManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id#reviewId#
     */
    @TableId("ID")
	private String reviewId;
    /**
     * 评论时间#reviewTime#
     */
	@TableField("PLSJ")
	private Date reviewTime;
    /**
     * 评论文章id#reviewArticleId#
     */
	@TableField("PLWZID")
	private String reviewArticleId;
	/**
     * 评论培训id#reviewATrainId#
     */
	@TableField("PLPXID")
	private String reviewTrainId;
    /**
     * 父评论id#reviewParentId#
     */
	@TableField("FPLID")
	private String reviewParentId;
    /**
     * 点赞数#likeCount#
     */
	@TableField("DZS")
	private BigDecimal likeCount;
    /**
     * 发表用户id#reviewerId#
     */
	@TableField("FBYHID")
	private String reviewerId;
    /**
     * 评论内容#reviewContent#
     */
	@TableField("PLNR")
	private String reviewContent;
	/**
     * 评论对象id#targetId#
     */
	@TableField("PLDXID")
	private String targetId;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getReviewArticleId() {
		return reviewArticleId;
	}

	public void setReviewArticleId(String reviewArticleId) {
		this.reviewArticleId = reviewArticleId;
	}

	public String getReviewTrainId() {
		return reviewTrainId;
	}

	public void setReviewTrainId(String reviewTrainId) {
		this.reviewTrainId = reviewTrainId;
	}

	public String getReviewParentId() {
		return reviewParentId;
	}

	public void setReviewParentId(String reviewParentId) {
		this.reviewParentId = reviewParentId;
	}

	public BigDecimal getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(BigDecimal likeCount) {
		this.likeCount = likeCount;
	}

	public String getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(String reviewerId) {
		this.reviewerId = reviewerId;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	@Override
	public String toString() {
		return "ReviewManagement{" +
				"reviewId='" + reviewId + '\'' +
				", reviewTime=" + reviewTime +
				", reviewArticleId='" + reviewArticleId + '\'' +
				", reviewATrainId='" + reviewTrainId + '\'' +
				", reviewParentId='" + reviewParentId + '\'' +
				", likeCount=" + likeCount +
				", reviewerId='" + reviewerId + '\'' +
				", reviewContent='" + reviewContent + '\'' +
				", targetId='" + targetId + '\'' +
				'}';
	}
}
