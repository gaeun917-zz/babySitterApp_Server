//package com.babySitter.model.dao;
//
//import java.util.List;
//
//import com.babySitter.model.dto.Upload;
//import com.babySitter.model.dto.UploadFile;
//
//public interface UploadDao {
//
//	int insertUpload(Upload upload);
//	void insertUploadFile(UploadFile file);
//
//	List<Upload> getUploadList(int memberpageno);
//	List<UploadFile> getUploadFilesByUploadNo(int uploadNo);
//
//	Upload getUploadByUploadNo(int uploadNo);
//	UploadFile getUploadFileByUploadFileNo(int uploadFileNo);
//	
//	int deleteUpload(int uploadNo);
//
//}