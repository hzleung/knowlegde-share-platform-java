package com.ly.cloud.user.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台部门消息表#NoticeManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-13
 */
@TableName("LY_LDD_XXGLB")
public class NoticeManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息id#noticeId#
     */
    @TableId("ID")
	private String noticeId;
    /**
     * 消息类型#noticeType#
     */
	@TableField("XXLX")
	private BigDecimal noticeType;
    /**
     * 消息状态#noticeStatus#
     */
	@TableField("XXZT")
	private BigDecimal noticeStatus;
    /**
     * 消息发送者#userId#
     */
	@TableField("FBYH")
	private String userId;
    /**
     * 消息内容#noticeContent#
     */
	@TableField("XXNR")
	private String noticeContent;
    /**
     * 消息所属文章id#articleId#
     */
	@TableField("XXSSWZ")
	private String articleId;
    /**
     * 消息发布时间#noticePublishTime#
     */
	@TableField("XXFBSJ")
	private Date noticePublishTime;
	/**
	 * 消息所属点赞id#likeId#
	 */
	@TableField("XXSSDZ")
	private String likeId;
	/**
	 * 消息所属评论id#reviewId#
	 */
	@TableField("XXSSPL")
	private String reviewId;
	/**
	 * 消息所属评论id#reviewId#
	 */
	@TableField("XXDX")
	private String targetId;
	/**
	 * 消息所属文章id#articleId#
	 */
	@TableField("XXSSPX")
	private String trainId;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public BigDecimal getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(BigDecimal noticeType) {
		this.noticeType = noticeType;
	}

	public BigDecimal getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(BigDecimal noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public Date getNoticePublishTime() {
		return noticePublishTime;
	}

	public void setNoticePublishTime(Date noticePublishTime) {
		this.noticePublishTime = noticePublishTime;
	}

	public String getLikeId() {
		return likeId;
	}

	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	@Override
	public String toString() {
		return "NoticeManagement{" +
				"noticeId='" + noticeId + '\'' +
				", noticeType=" + noticeType +
				", noticeStatus=" + noticeStatus +
				", userId='" + userId + '\'' +
				", noticeContent='" + noticeContent + '\'' +
				", articleId='" + articleId + '\'' +
				", noticePublishTime=" + noticePublishTime +
				", likeId='" + likeId + '\'' +
				", reviewId='" + reviewId + '\'' +
				", targetId='" + targetId + '\'' +
				", trainId='" + trainId + '\'' +
				'}';
	}
}
