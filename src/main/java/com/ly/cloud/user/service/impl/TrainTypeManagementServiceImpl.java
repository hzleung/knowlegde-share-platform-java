package com.ly.cloud.user.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.*;

import com.ly.cloud.user.dto.TrainDTO;
import com.ly.cloud.user.entity.*;
import com.ly.cloud.user.mapper.FileManagementMapper;
import com.ly.cloud.user.mapper.TrainContentManagementMapper;
import com.ly.cloud.user.mapper.UserMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ly.cloud.user.mapper.TrainTypeManagementMapper;
import com.ly.cloud.user.service.TrainTypeManagementService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


/**
 * <p>
 * 知识平台培训类型管理表#TrainTypeManagement# 服务实现类
 * </p>
 *
 * @author lianghaizhong
 * @since 2020-10-29
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class TrainTypeManagementServiceImpl extends ServiceImpl<TrainTypeManagementMapper, TrainTypeManagement> implements TrainTypeManagementService {

    private final static Log logger = LogFactory.getLog(TrainTypeManagementServiceImpl.class);

	@Value("${download-path.path}")
	private String downloadPath;

	@Autowired
	private TrainTypeManagementMapper trainTypeManagementMapper;

	@Autowired
	private TrainContentManagementMapper trainContentManagementMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private FileManagementMapper fileManagementMapper;

	@Override
	public Page<TrainTypeManagement> selectPage(Page<TrainTypeManagement> page, String userId) {
		page.setRecords(trainTypeManagementMapper.selectPage(page, userId));
		return page;
	}

	@Override
	public TrainTypeManagement selectById(Serializable id) {
		return super.selectById(id);
	}

	@Override
	public TrainDTO selectDetailById(String id) {
		TrainContentManagement entity = trainContentManagementMapper.queryById(id);
		Long visit = Long.valueOf(entity.getTrainVisit().toString());
		BigDecimal lll = BigDecimal.valueOf(visit + 1);
		trainContentManagementMapper.updateTrainVisit(id, lll);
		return trainTypeManagementMapper.queryDetailById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insert(TrainTypeManagement entity) {
		return super.insert(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteById(Serializable id) {
		// 删除的时候，需要同时删除对应的 banner 以及 附件
		boolean hasDelete = fileManagementMapper.deleteFilesByTypeId(id);
		if(hasDelete) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
		return super.deleteById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateByTrainId(TrainDTO entity) {
		TrainTypeManagement typeManagementPo = new TrainTypeManagement();
		typeManagementPo.setTrainType(entity.getTrainType());
		typeManagementPo.setTrainRouteType(entity.getTrainRouteType());
		typeManagementPo.setTrainId(entity.getTrainId());

		TrainContentManagement contentManagementPo = new TrainContentManagement();
		contentManagementPo.setTrainStatus(entity.getTrainStatus());
		contentManagementPo.setTrainVisit(entity.getTrainVisit());
		contentManagementPo.setAuthor(entity.getAuthor());
		contentManagementPo.setBanner(entity.getBanner());
		contentManagementPo.setTrainContent(entity.getTrainContent());
		contentManagementPo.setTrainContentId(entity.getTrainId());
		contentManagementPo.setTrainFiles(entity.getTrainFiles());
		contentManagementPo.setTrainId(entity.getTrainId());
		contentManagementPo.setTrainName(entity.getTrainName());
		contentManagementPo.setTrainPlace(entity.getTrainPlace());
		contentManagementPo.setTrainProfile(entity.getTrainProfile());
//		修改后，时间要对应更新
		contentManagementPo.setTrainPublishTime(new Date());
		contentManagementPo.setTrainPublishUserAvatar(entity.getTrainPublishUserAvatar());
		contentManagementPo.setTrainPublishUserId(entity.getTrainPublishUserId());
		contentManagementPo.setTrainStartTime(entity.getTrainStartTime());
		contentManagementPo.setTrainVideo(entity.getTrainVideo());
		trainContentManagementMapper.updateById(contentManagementPo);
		return super.updateById(typeManagementPo);
	}

	@Override
	public List<TrainDTO> selectTopTen() {
		return trainTypeManagementMapper.queryTopTen();
	}

	@Override
	public List<TrainDTO> selectByKeywords(String title, String author, String type) {
		return trainTypeManagementMapper.selectByKeywords(title, author, type);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean insertTypeAndContent(TrainDTO dto) {
		//  点击保存的时候也是调用此方法，因此，每一次调用该方法的时候应该先去数据库查一下有没有存在这篇文章
		TrainTypeManagement trainItem = super.selectById(dto.getTrainId());
		if (trainItem != null) {
			//  存在，先删掉，再保存
			super.deleteById(dto.getTrainId());
		}
		TrainTypeManagement typeManagementPo = new TrainTypeManagement();
//		String pkid = UUID.randomUUID().toString();
		typeManagementPo.setTrainId(dto.getTrainId());
		typeManagementPo.setTrainRouteType(dto.getTrainRouteType());
		typeManagementPo.setTrainType(dto.getTrainType());
//		if(dto.getTrainType() != null) {
//			if("线下培训".equals(dto.getTrainType())) {
//				typeManagementPo.setTrainType("offLineTrain");
//			} else if("产品培训".equals(dto.getTrainType())) {
//				typeManagementPo.setTrainType("produceTrain");
//			} else if("培训路线".equals(dto.getTrainType())) {
//				typeManagementPo.setTrainType("trainRouter");
//			}
//		}
		super.insert(typeManagementPo);

		TrainContentManagement contentManagementPo = new TrainContentManagement();
		contentManagementPo.setTrainId(dto.getTrainId());
		contentManagementPo.setTrainContentId(dto.getTrainId());
		contentManagementPo.setTrainStartTime(dto.getTrainStartTime());
		contentManagementPo.setTrainPublishTime(new Date());
		contentManagementPo.setTrainContent(dto.getTrainContent());
//		将banner拼成链接的形式再保存到数据库
		String bannerUrl = downloadPath + dto.getBanner();
		contentManagementPo.setBanner(bannerUrl);
		contentManagementPo.setTrainFiles(dto.getTrainFiles());
		contentManagementPo.setTrainName(dto.getTrainName());
		contentManagementPo.setTrainPlace(dto.getTrainPlace());
		contentManagementPo.setTrainProductProfile(dto.getTrainProductProfile());
		contentManagementPo.setTrainProfile(dto.getTrainProfile());
		contentManagementPo.setTrainPublishUserId(dto.getTrainPublishUserId());
		contentManagementPo.setTrainVideo(dto.getTrainVideo());
		contentManagementPo.setAuthor(dto.getAuthor());
		contentManagementPo.setTrainVisit(BigDecimal.valueOf(0));
		contentManagementPo.setTrainStatus(dto.getTrainStatus());
		String userAvatar = userMapper.selectUserAvatarByUserId(dto.getTrainPublishUserId());
		if(userAvatar != null){
			contentManagementPo.setTrainPublishUserAvatar(userAvatar);
		}else{
			contentManagementPo.setTrainPublishUserAvatar(dto.getTrainPublishUserAvatar());
		}
		User userDto = userMapper.selectUserByuserId(dto.getTrainPublishUserId());
		BigDecimal xp = BigDecimal.valueOf(Long.valueOf(userDto.getUserXP().toString()) + 2);
		userMapper.updateXP(dto.getTrainPublishUserId(), xp);
		trainContentManagementMapper.insert(contentManagementPo);
		return true;
	}


	@Override
	public Map<String, Object> selectAll(String trainType, String userId, String trainStatus) {
		List<TrainDTO> list = new ArrayList<>();
		if(userId == null) {
			list = trainTypeManagementMapper.selectAllTrain();
		}else {
			list = trainTypeManagementMapper.selectAllTrainByUserId(userId, trainStatus);
		}
			List<TrainDTO> offLineTrainList = new ArrayList<>();
			List<TrainDTO> produceTrainList = new ArrayList<>();
			List<TrainDTO> trainRouterList = new ArrayList<>();
			for(TrainDTO trainItem : list) {
//				trainItem.setBanner(trainItem.getBanner());
//				if(trainItem.getTrainPublishUserAvatar() != null && "".equals(trainItem.getTrainPublishUserAvatar())) {
//					trainItem.setTrainPublishUserAvatar(trainItem.getTrainPublishUserAvatar());
//				} else {
//					trainItem.setTrainPublishUserAvatar("");
//				}
//					trainItem.setTrainPublishUserAvatar(trainItem.getTrainPublishUserAvatar());
//					trainItem.put("trainPublishUserAvatar",trainItem.get("trainPublishUserAvatar") == null || "".equals(trainItem.get("trainPublishUserAvatar")) ? "" : ClobToString((Clob)trainItem.get("trainPublishUserAvatar")));
				if (trainItem.getFiles() != null) {
					List<FileManagement> files = trainItem.getFiles();
					List<FileManagement> newFiles = new ArrayList<>();
					for (FileManagement fileItem : files) {
						String bannerFileId = trainItem.getBanner().toString().split("\\=")[1];
						String fileId = fileItem.getFileId().toString();
						if (!fileId.equals(bannerFileId)) {
							newFiles.add(fileItem);
						}
					}
					trainItem.setFiles(newFiles);
				}
				if("线下培训".equals(trainItem.getTrainType().toString())) {
					offLineTrainList.add(trainItem);
				} else if("产品培训".equals(trainItem.getTrainType().toString())) {
					produceTrainList.add(trainItem);
				} else if("培训路线".equals(trainItem.getTrainType().toString())) {
					trainRouterList.add(trainItem);
				}
			}
			Map<String, Object> resultList = new HashMap<>();
		if(trainType != null || trainType != "") {
			if ("线下培训".equals(trainType)) {
				resultList.put("线下培训", offLineTrainList);
				return resultList;
			} else if ("产品培训".equals(trainType)) {
				resultList.put("产品培训", produceTrainList);
				return resultList;
			} else if ("培训路线".equals(trainType)) {
				resultList.put("培训路线", trainRouterList);
				return resultList;
			}
		}
		resultList.put("offLineTrain", offLineTrainList);
		resultList.put("produceTrain", produceTrainList);
		resultList.put("trainRouter", trainRouterList);
		return resultList;
	}

	/**
	 * // Clob类型 转String
	 */
	public String ClobToString(Clob clob) throws SQLException, IOException {
		String reString = "";
		Reader is = clob.getCharacterStream();
		BufferedReader br = new BufferedReader(is);
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		while (s != null) {
			sb.append(s);
			s = br.readLine();
		}
		reString = sb.toString();
		if(br!=null){
			br.close();
		}
		if(is!=null){
			is.close();
		}
		return reString;
	}
}
