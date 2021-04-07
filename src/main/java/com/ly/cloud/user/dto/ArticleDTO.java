package com.ly.cloud.user.dto;


import com.ly.cloud.user.entity.FileManagement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: lianghaizhong
 * @mail: lianghaizhong@ly-sky.com
 * @since 2020/11/03 10:02
 */


public class ArticleDTO {

    private String articleId;
    /**
     * 发布时间#articlePublicTime#
     */
    private Date articlePublicTime;
    /**
     * 发布用户#userId#
     */
    private String userId;
    /**
     * 文章标题#title#
     */
    private String title;
    /**
     * 文章所属标签#label#
     */
    private String label;
    /**
     * 文章所属分类#type#
     */
    private String type;
    /**
     * 点赞量#articleLike#
     */
    private BigDecimal articleLike;
    /**
     * 评论数#articleReview#
     */
    private BigDecimal articleReview;
    /**
     * 浏览量#articleVisit#
     */
    private BigDecimal articleVisit;
    /**
     * 用户头像#articleUserAvatar#
     */
    private String articleUserAvatar;
    /**
     * 文章内容#content#
     */
    private String content;
    /**
     * 附件#files#
     */
    private String files;
    /**
     * 封面#banner#
     */
    private String banner;

    /**
     * 发布用户名#author#
     */
    private String author;

    /**
     * 点赞量#likeCount#
     */
    private BigDecimal likeCount;
    /**
     * 点赞量#articleStatus#
     */
    private BigDecimal articleStatus;
    /**
     * 点赞量#isLike#
     */
    private BigDecimal isLike;
    /**
     * 文章附件#articleFiles#
     */
    private List<FileManagement> articleFiles;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Date getArticlePublicTime() {
        return articlePublicTime;
    }

    public void setArticlePublicTime(Date articlePublicTime) {
        this.articlePublicTime = articlePublicTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getArticleLike() {
        return articleLike;
    }

    public void setArticleLike(BigDecimal articleLike) {
        this.articleLike = articleLike;
    }

    public BigDecimal getArticleReview() {
        return articleReview;
    }

    public void setArticleReview(BigDecimal articleReview) {
        this.articleReview = articleReview;
    }

    public BigDecimal getArticleVisit() {
        return articleVisit;
    }

    public void setArticleVisit(BigDecimal articleVisit) {
        this.articleVisit = articleVisit;
    }

    public String getArticleUserAvatar() {
        return articleUserAvatar;
    }

    public void setArticleUserAvatar(String articleUserAvatar) {
        this.articleUserAvatar = articleUserAvatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(BigDecimal likeCount) {
        this.likeCount = likeCount;
    }

    public BigDecimal getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(BigDecimal articleStatus) {
        this.articleStatus = articleStatus;
    }

    public BigDecimal getIsLike() {
        return isLike;
    }

    public void setIsLike(BigDecimal isLike) {
        this.isLike = isLike;
    }

    public List<FileManagement> getArticleFiles() {
        return articleFiles;
    }

    public void setArticleFiles(List<FileManagement> articleFiles) {
        this.articleFiles = articleFiles;
    }
}
