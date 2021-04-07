package com.ly.cloud.user.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ly.cloud.exception.biz.BusinessException;
import com.ly.cloud.user.utils.ExcelTool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.SchoolAreaManagement;
import com.ly.cloud.user.mapper.SchoolAreaManagementMapper;
import com.ly.cloud.user.service.SchoolAreaManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 知识平台高校大区管理表#SchoolAreaManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-12-02
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class SchoolAreaManagementServiceImpl extends ServiceImpl<SchoolAreaManagementMapper, SchoolAreaManagement> implements SchoolAreaManagementService {

    private final static Log logger = LogFactory.getLog(SchoolAreaManagementServiceImpl.class);
	private NumberFormat numberFormat = null;

	@Autowired
	private SchoolAreaManagementMapper schoolAreaManagementMapper;

	@Override
	public Page<SchoolAreaManagement> selectPage(Page<SchoolAreaManagement> page, SchoolAreaManagement dto) {
		page.setRecords(schoolAreaManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public SchoolAreaManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(SchoolAreaManagement entity) {
		entity.setSchoolId(UUID.randomUUID().toString());
		entity.setCreatedTime(new Date());
		entity.setStatus(BigDecimal.valueOf(1));
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(SchoolAreaManagement entity) {
		return super.updateById(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public List<SchoolAreaManagement> selectAll() {
		return schoolAreaManagementMapper.selectAll();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public Page<SchoolAreaManagement> selectBykeywords(Page<SchoolAreaManagement> page, String schoolName, String area, String time) {
		String startTime = null;
		String endTime = null;
		if (time != null) {
			String[] timeArr = time.split(",");
			startTime = timeArr[0];
			endTime = timeArr[1];
		}
		List<SchoolAreaManagement> list = schoolAreaManagementMapper.queryByKeywords(page, schoolName, area, startTime, endTime);
		page.setRecords(list);
		return page;
	}

	@Override
	public List<SchoolAreaManagement> importExcel(MultipartFile file) {
		// 解析Excel数据
		List r = readDataFromExcel(file);

//		List list = (List) r.getData();
		List<SchoolAreaManagement> items = readDataFromExcel(file);
		if (items == null || items.size() <= 0) {
			throw new BusinessException("文件为空，没有数据！！！");
		}
		// 插入数据之前，先查一下是否存在，已存在的不存
		for (SchoolAreaManagement item : items) {
			String schoolName = item.getSchoolName();
			boolean schoolExist = schoolAreaManagementMapper.queryBySchoolName(schoolName);
			if (!schoolExist) {
				// 不存在，插入数据库
				schoolAreaManagementMapper.insert(item);
			}
		}
		return items;
	}

	/**
	 * 解析Excel数据
	 *
	 * @param file 文件
	 * @return
	 */
	public List readDataFromExcel(MultipartFile file) {
		POIFSFileSystem pfs = null;
		Workbook workbook = null;
		try {
			// 解析xls和xlsx不兼容问题
			workbook = ExcelTool.getWorkBook(pfs, workbook, file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("模板保存异常！");
		}
		if (workbook == null) {
			throw new BusinessException("请使用模板上传文件");
		}
		// 判断有记录的列数
		if (workbook.getSheetAt(0).getRow(0).getPhysicalNumberOfCells() != 2) {
			throw new BusinessException("请使用类型所对应的模板");
		}

		numberFormat = NumberFormat.getNumberInstance();

		List<SchoolAreaManagement> list = new ArrayList<>();
		// 获取表格第一个sheet的内容
		Sheet sheetAt = workbook.getSheetAt(0);
		// 获得sheet总行数
		int lastRowNum = sheetAt.getLastRowNum();
		if (lastRowNum < 1) {
			throw new BusinessException("数据错误");
		}
		// 开始读取,不读取表头所以从第二行开始
		for (int i = 1; i <= lastRowNum; i++) {
			// 如需验证表头，则 i 取 0 开始;

			// 获取每一行
			Row row = sheetAt.getRow(i);
			// 行为空不读取
			if (row == null) continue;
			Cell cell = row.getCell(0);
			//列为空不读取
			if (cell == null || StringUtils.isEmpty(convertData(cell))) continue;

			// 创建对象封装行数据
			SchoolAreaManagement projectItem = new SchoolAreaManagement();
			// 创建一个集合根据下标来确定每个单元格对应对象的什么属性
			List<String> rowList = new ArrayList<>();
			//添加数据
			for (int j = 0; j < 2; j++) {
				Cell cellOne = row.getCell(j);
				try {
					String item = convertData(cellOne);
					rowList.add(item);
				} catch (Exception e) {
					System.out.println("-------------------Err-----------------------");
					System.out.println(i + "行" + j + "列数据转换出现异常");
					rowList.add("");
				}
			}
			//规避行数数据后几行为空
			if (rowList.size() < 2) {
				for (int k = 0; k < 2 - rowList.size(); k++) {
					rowList.add("");
				}
			}

			// 添加数据
			projectItem.setSchoolId(UUID.randomUUID().toString());
			projectItem.setStatus(BigDecimal.valueOf(1));
			projectItem.setCreatedTime(new Date());
			projectItem.setSchoolArea(rowList.get(0).trim());
			projectItem.setSchoolName(rowList.get(1).trim());
			list.add(projectItem);
		}
		return list;
	}

	/**
	 * 表格数据转换
	 *
	 * @param cell
	 * @return
	 */
	public String convertData(Cell cell) {
		String str = "";

		switch (cell.getCellTypeEnum()) {
			case NUMERIC:
				//判断是否是整数
				str = numberFormat.format(cell.getNumericCellValue());
				break;
			case STRING:
				str = cell.getStringCellValue();
				break;
			case _NONE:
				str = "";
				break;
			case BLANK:
				str = "";
				break;
			case FORMULA:
				try {
					str = String.valueOf(cell.getNumericCellValue());
				} catch (IllegalArgumentException e) {
					str = String.valueOf(cell.getRichStringCellValue());
				}
				break;
			default:
				str = "";
		}
		return str;
	}
}
