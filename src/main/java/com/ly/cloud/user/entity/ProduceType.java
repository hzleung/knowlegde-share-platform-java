package com.ly.cloud.user.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台产品类型表#ProduceType#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-11-06
 */
@TableName("LY_LDD_CPLXGLB")
public class ProduceType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品类型编号#produceTypeId#
     */
    @TableId("CPLXBH")
	private String produceTypeId;
    /**
     * 产品类型名称#produceTypeName#
     */
	@TableField("CPLXMC")
	private String produceTypeName;
    /**
     * 产品类型创建时间#produceTypeTime#
     */
	@TableField("CPLXCJSJ")
	private Date produceTypeTime;
    /**
     * 产品类型状态#produceTypeStatus#
     */
	@TableField("CPLXZT")
	private BigDecimal produceTypeStatus;
	/**
     * 产品版本号#produceTypeVersion#
     */
	@TableField("CPLXBBH")
	private String produceTypeVersion;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getProduceTypeId() {
		return produceTypeId;
	}

	public void setProduceTypeId(String produceTypeId) {
		this.produceTypeId = produceTypeId;
	}

	public String getProduceTypeName() {
		return produceTypeName;
	}

	public void setProduceTypeName(String produceTypeName) {
		this.produceTypeName = produceTypeName;
	}

	public Date getProduceTypeTime() {
		return produceTypeTime;
	}

	public void setProduceTypeTime(Date produceTypeTime) {
		this.produceTypeTime = produceTypeTime;
	}

	public BigDecimal getProduceTypeStatus() {
		return produceTypeStatus;
	}

	public void setProduceTypeStatus(BigDecimal produceTypeStatus) {
		this.produceTypeStatus = produceTypeStatus;
	}

	public String getProduceTypeVersion() {
		return produceTypeVersion;
	}

	public void setProduceTypeVersion(String produceTypeVersion) {
		this.produceTypeVersion = produceTypeVersion;
	}

	@Override
	public String toString() {
		return "ProduceType{" +
				"produceTypeId='" + produceTypeId + '\'' +
				", produceTypeName='" + produceTypeName + '\'' +
				", produceTypeTime=" + produceTypeTime +
				", produceTypeStatus=" + produceTypeStatus +
				", produceTypeVersion='" + produceTypeVersion + '\'' +
				'}';
	}
}
