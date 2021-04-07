package com.ly.cloud.user.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台文章管理表#ArticleManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-23
 */
@TableName("LY_LDD_WZGLB")
public class ArticleManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id#articleId#
     */
    @TableId("ID")
	private String articleId;
    /**
     * 发布时间#articlePublicTime#
     */
	@TableField("FBSJ")
	private Date articlePublicTime;
    /**
     * 发布用户#userId#
     */
	@TableField("FBYH")
	private String userId;
    /**
     * 文章标题#title#
     */
	@TableField("WZBT")
	private String title;
    /**
     * 文章所属标签#label#
     */
	@TableField("WZSSBQ")
	private String label;
    /**
     * 文章所属分类#type#
     */
	@TableField("WZSSFL")
	private String type;
    /**
     * 点赞量#articleLike#
     */
	@TableField("DZL")
	private BigDecimal articleLike;
    /**
     * 评论数#articleReview#
     */
	@TableField("PLS")
	private BigDecimal articleReview;
    /**
     * 浏览量#articleVisit#
     */
	@TableField("LLL")
	private BigDecimal articleVisit;
    /**
     * 用户头像#articleUserAvatar#
     */
	@TableField("YHTX")
	private Object articleUserAvatar;
    /**
     * 文章内容#content#
     */
	@TableField("WZNR")
	private String content;
    /**
     * 附件#articleFiles#
     */
	@TableField("FJ")
	private String files;
    /**
     * 封面#banner#
     */
	@TableField("FM")
	private String banner;

	/**
	 * 发布用户名#author#
	 */
	@TableField("FBYHM")
	private String author;
	/**
	 * 浏览量#articleStatus#
	 */
	@TableField("WZZT")
	private BigDecimal articleStatus;
	/**
	 * 浏览量#isLike#
	 */
	@TableField("SFDZ")
	private BigDecimal isLike;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

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

	public Object getArticleUserAvatar() {
		return articleUserAvatar;
	}

	public void setArticleUserAvatar(Object articleUserAvatar) {
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

	@Override
	public String toString() {
		return "ArticleManagement{" +
				"articleId='" + articleId + '\'' +
				", articlePublicTime=" + articlePublicTime +
				", userId='" + userId + '\'' +
				", title='" + title + '\'' +
				", label='" + label + '\'' +
				", type='" + type + '\'' +
				", articleLike=" + articleLike +
				", articleReview=" + articleReview +
				", articleVisit=" + articleVisit +
				", articleUserAvatar=" + articleUserAvatar +
				", content='" + content + '\'' +
				", files='" + files + '\'' +
				", banner='" + banner + '\'' +
				", author='" + author + '\'' +
				", articleStatus=" + articleStatus +
				", isLike=" + isLike +
				'}';
	}
}
