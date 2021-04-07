package com.ly.cloud.user.service.impl;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ly.cloud.exception.biz.BusinessException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.entity.FileManagement;
import com.ly.cloud.user.mapper.FileManagementMapper;
import com.ly.cloud.user.service.FileManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 知识平台附件管理表#FileManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-26
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class FileManagementServiceImpl extends ServiceImpl<FileManagementMapper, FileManagement> implements FileManagementService {

    private final static Log logger = LogFactory.getLog(FileManagementServiceImpl.class);

	@Value("${file.path}")
	private String path;

//	@Autowired
//	private HttpServletRequest request;

	@Autowired
	private FileManagementMapper fileManagementMapper;

	@Override
	public Page<FileManagement> selectPage(Page<FileManagement> page, FileManagement dto) {
		page.setRecords(fileManagementMapper.selectPage(page, dto));
		return page;
	}

	@Override
	public FileManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(FileManagement entity) {
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public boolean deleteById(Serializable id) {
		// 删除时不仅仅需要删除数据库的数据记录，还需要删除硬盘中对应的文件
		// 先根据id获取对应文件的位置
		String filePath = fileManagementMapper.getFilePath(id);
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
			System.out.println("===========删除成功=================");
		} else {
			System.out.println("===============删除失败==============");
		}
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public boolean deleteFileByTypeId(String typeId) {
		// 根据所属id，获取对应的文件所在的路径，返回List
		List<String> filePathList = fileManagementMapper.getFilePathList(typeId);
		// 删除对应的文件，以及在数据库中的记录
		for (String filePathItem : filePathList) {
			File file = new File(filePathItem);
			if (file.exists()) {
				file.delete();
				System.out.println("===========删除成功=================");
			} else {
				System.out.println("===============删除失败==============");
			}
		}
 		return fileManagementMapper.deleteFilesByTypeId(typeId);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateById(FileManagement entity) {
		return super.updateById(entity);
	}


	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateFileNameById(String fileId, String fileName) {
		return fileManagementMapper.updateFileNameById(fileId, fileName);
	}

	@Override
	public List<FileManagement> queryByIds(List<String> ids) {
		return this.fileManagementMapper.queryByIds(ids);
	}

	@Override
	public List<FileManagement> selectAllFileIdById(String id) {
		return fileManagementMapper.queryAllFileIdById(id);
	}

	@Override
	public List<FileManagement> uploadFile(MultipartFile[] files, String uuid, String type) {
		// 先判断type类型，项目跟踪(projectFollowUpManage)
		if (type != null) {
			if ("projectFollowUpManagement".equals(type)) {
				// 项目跟踪模块的附件 根据uuid(projectId)先去查表，看看是否已有附件，若有附件，则删除，相当于覆盖
				List<FileManagement> fileList =  fileManagementMapper.getFileByProjectFollowUpId(uuid);
				for (FileManagement fileItem : fileList) {
					String fileItemId = fileItem.getFileId();
					super.deleteById(fileItemId);
				}
			}
		}
		List<FileManagement> res = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String filepath = path + "/" + simpleDateFormat.format(new Date());
		File filePath = new File(filepath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		try {
			for (MultipartFile file : files) {
				FileManagement filePO = new FileManagement();
				if (StringUtils.isEmpty(file.getOriginalFilename())) {
					continue;
				}
				String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				filePO.setFileId(UUID.randomUUID().toString());
				filePO.setFileName(file.getOriginalFilename());
//				filePO.setFileType(fileSuffix.replaceAll(".", ""));
				filePO.setFileType(fileSuffix.substring(1));
				filePO.setFileSize(new BigDecimal(file.getSize()));
				filePO.setTypeId(uuid);
				String fileName = filePO.getFileId() + fileSuffix;
				File trFile = new File(filepath + "/" + fileName);
				filePO.setFilePath(filepath + "/" + fileName);
				file.transferTo(trFile);
				res.add(filePO);
			}
			if (res.size() > 0) {
				this.fileManagementMapper.batchInsert(res);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException("附件上传失败");
		}
		return res;
	}

	@Override
	public Boolean downloadFile(String id, HttpServletRequest request, HttpServletResponse response) {
		List<String> ids = new ArrayList<>();
		ids.add(id);
		List<FileManagement> res = this.queryByIds(ids);
		if (res.size() > 0) {
			FileManagement filePO = res.get(0);
			File file = new File(filePO.getFilePath());
			if (file.exists()) {
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				byte[] buffer = new byte[1024];
				try {
					String finalFileName = URLEncoder.encode(filePO.getFileName(), "UTF-8");
					response.setContentType("application/force-download");// 设置强制下载不打开
					response.addHeader("Content-Disposition", "attachment; filename*=utf-8'zh_cn'" + finalFileName);
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
					System.out.println("下载成功...");
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					throw new BusinessException("文件下载失败");
				} finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
						}
					}
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
						}
					}
				}
			}
			return false;
		}
		return false;
	}

	/**
	 * 把文件打成压缩包并输出到客户端浏览器中
	 */
	public Boolean downloadZipFiles(HttpServletResponse response, List<String> srcFiles, String zipFileName) {
		try {
			response.reset(); // 重点突出
			response.setCharacterEncoding("UTF-8"); // 重点突出
			response.setContentType("application/x-msdownload"); // 不同类型的文件对应不同的MIME类型 // 重点突出
			// 对文件名进行编码处理中文问题
			zipFileName = new String(zipFileName.getBytes(), StandardCharsets.UTF_8);
			// inline在浏览器中直接显示，不提示用户下载
			// attachment弹出对话框，提示用户进行下载保存本地
			// 默认为inline方式
			response.setHeader("Content-Disposition", "attachment;filename=" + zipFileName);
			// --设置成这样可以不用保存在本地，再输出， 通过response流输出,直接输出到客户端浏览器中。
			ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
			Boolean res = zipFile(srcFiles, zos);
			if(res) {
				return true;
			}else{
				throw new BusinessException("文件下载失败");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("文件下载失败");
		}
	}

	/**
	 * 压缩文件
	 *
	 * @param filePaths 需要压缩的文件路径集合
	 * @throws IOException
	 */
	public Boolean zipFile(List<String> filePaths, ZipOutputStream zos) {
		//设置读取数据缓存大小
		byte[] buffer = new byte[4096];
		try {
			//循环读取文件路径集合，获取每一个文件的路径
			for (String filePath : filePaths) {
				File inputFile = new File(filePath);
				//判断文件是否存在
				if (inputFile.exists()) {
					//判断是否属于文件，还是文件夹
					if (inputFile.isFile()) {
						//创建输入流读取文件
						BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
						//将文件写入zip内，即将文件进行打包
						zos.putNextEntry(new ZipEntry(inputFile.getName()));
						//写入文件的方法，同上
						int size = 0;
						//设置读取数据缓存大小
						while ((size = bis.read(buffer)) > 0) {
							zos.write(buffer, 0, size);
						}
						//关闭输入输出流
						zos.closeEntry();
						bis.close();
					} else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip
						File[] files = inputFile.listFiles();
						List<String> filePathsTem = new ArrayList<String>();
						for (File fileTem : files) {
							filePathsTem.add(fileTem.toString());
						}
						zipFile(filePathsTem, zos);
					}
				}
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("文件下载失败");
		} finally {
			if (null != zos) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new BusinessException("没有文件可以下载");
				}
			}
		}
	}
}
