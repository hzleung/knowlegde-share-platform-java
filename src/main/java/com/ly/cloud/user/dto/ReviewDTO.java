package com.ly.cloud.user.dto;

import java.util.Date;

/**
 * @author: lianghaizhong
 * @mail: lianghaizhong@ly-sky.com
 * @since 2020/11/03 10:02
 */


public class ReviewDTO {
    /**
     * 评论id#reviewId#
     */
    private String reviewId;
    /**
     * 评论时间#reviewTime#
     */
    private Date reviewTime;
    /**
     * 评论文章id#reviewArticleId#
     */
    private String reviewArticleId;
    /**
     * 评论培训id#reviewTrainId#
     */
    private String reviewTrainId;
    /**
     * 父评论id#reviewParentId#
     */
    private String reviewParentId;
    /**
     * 发表用户id#reviewerId#
     */
    private String reviewerId;
    /**
     * 评论内容#reviewContent#
     */
    private String reviewContent;
    /**
     * 用户id#userId#
     */
    private String userId;
    /**
     * 用户名#userName#
     */
    private String userName;
    /**
     * 用户头像#userAvatar#
     */
    private String userAvatar;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}