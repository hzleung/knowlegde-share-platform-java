package com.ly.cloud.user.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ly.cloud.user.entity.FileManagement;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 知识平台附件管理表#FileManagement# Mapper 接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-26
 */
public interface FileManagementMapper extends BaseMapper<FileManagement> {

	List<FileManagement> queryByIds(@Param("ids") List<String> ids);

	List<FileManagement> selectPage(@Param("page")Page<FileManagement> page, @Param("dto")FileManagement dto);

	Boolean batchInsert(@Param("list") List<FileManagement> filePOS);

    boolean updateFileNameById(@Param("fileId")String fileId, @Param("fileName")String fileName);

	List<FileManagement> queryAllFileIdById(@Param("id")String id);

	boolean deleteFilesByTypeId(@Param("id")Serializable id);

    String getFilePath(@Param("id")Serializable id);

	List<String> getFilePathList(@Param("id") String id);

    List<FileManagement> getFileByProjectFollowUpId(@Param("typeId")String typeId);
}
