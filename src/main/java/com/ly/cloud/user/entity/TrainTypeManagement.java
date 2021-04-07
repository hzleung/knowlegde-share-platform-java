package com.ly.cloud.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 知识平台培训类型管理表#TrainTypeManagement#
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-29
 */
@TableName("LY_LDD_PXLXGLB")
public class TrainTypeManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 培训编号#trainId#
     */
    @TableId("PXBH")
	private String trainId;
    /**
     * 培训类型#trainType#
     */
	@TableField("PXLX")
	private String trainType;
    /**
     * 培训路线类型#trainRouteType#
     */
	@TableField("PXLXFL")
	private String trainRouteType;


	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public String getTrainRouteType() {
		return trainRouteType;
	}

	public void setTrainRouteType(String trainRouteType) {
		this.trainRouteType = trainRouteType;
	}

	@Override
	public String toString() {
		return "TrainTypeManagement{" +
			", trainId=" + trainId +
			", trainType=" + trainType +
			", trainRouteType=" + trainRouteType +
			"}";
	}
}
