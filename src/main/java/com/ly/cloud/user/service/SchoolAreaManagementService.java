package com.ly.cloud.user.service;

import com.ly.cloud.user.entity.SchoolAreaManagement;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 知识平台高校大区管理表#SchoolAreaManagement# 服务接口
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-12-02
 */
public interface SchoolAreaManagementService extends IService<SchoolAreaManagement> {

	Page<SchoolAreaManagement> selectPage(Page<SchoolAreaManagement> page, SchoolAreaManagement dto);

	List<SchoolAreaManagement> selectAll();

    Page<SchoolAreaManagement> selectBykeywords(Page<SchoolAreaManagement> page, String schoolName, String area, String time);

    List<SchoolAreaManagement> importExcel(MultipartFile file);
}
