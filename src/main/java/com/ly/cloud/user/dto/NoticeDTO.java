package com.ly.cloud.user.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: lianghaizhong
 * @mail: lianghaizhong@ly-sky.com
 * @since 2020/11/17 9:02
 */


public class NoticeDTO {
    /**
     * 消息id#noticeId#
     */
    private String noticeId;
    /**
     * 消息类型#noticeType#
     */
    private BigDecimal noticeType;
    /**
     * 消息状态#noticeStatus#
     */
    private BigDecimal noticeStatus;
    /**
     * 消息发送者#userId#
     */
    private String userId;
    /**
     * 消息内容#noticeContent#
     */
    private String noticeContent;
    /**
     * 消息所属文章id#articleId#
     */
    private String articleId;
    /**
     * 消息所属培训id#trainId#
     */
    private String trainId;
    /**
     * 消息发布时间#noticePublishTime#
     */
    private Date noticePublishTime;
    /**
     * 消息所属点赞id#likeId#
     */
    private String likeId;
    /**
     * 消息所属评论id#reviewId#
     */
    private String reviewId;
    /**
     * 消息所属评论id#reviewId#
     */
    private String targetId;
    /**
     * 用户名#userName#
     */
    private String userName;
    /**
     * 用户头像#userAvatar#
     */
    private String userAvatar;
    /**
     * 文章标题#title#
     */
    private String title;
    /**
     * 培训标题#trainName#
     */
    private String trainName;

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

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
}