package com.ly.cloud.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 知识平台附件管理表#FileManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-26
 */
@TableName("LY_LDD_FJGLB")
public class FileManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 附件编号#fileId#
     */
    @TableId("FJBH")
	private String fileId;
    /**
     * 附件名称#fileName#
     */
	@TableField("FJMC")
	private String fileName;
    /**
     * 附件后缀名#fileType#
     */
	@TableField("FJHZM")
	private String fileType;
    /**
     * 附件存储路径#filePath#
     */
	@TableField("FJCCLJ")
	private String filePath;
    /**
     * 附件大小#fileSize#
     */
	@TableField("FJDX")
	private BigDecimal fileSize;
	/**
     * 附件大小#typeId#
     */
	@TableField("SSBH")
	private String typeId;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public BigDecimal getFileSize() {
		return fileSize;
	}

	public void setFileSize(BigDecimal fileSize) {
		this.fileSize = fileSize;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "FileManagement{" +
				"fileId='" + fileId + '\'' +
				", fileName='" + fileName + '\'' +
				", fileType='" + fileType + '\'' +
				", filePath='" + filePath + '\'' +
				", fileSize=" + fileSize +
				", typeId='" + typeId + '\'' +
				'}';
	}
}
