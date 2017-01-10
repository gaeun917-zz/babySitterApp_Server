//package com.babySitter.model.dao;
//
//import java.sql.Date;
//import java.util.List;
//
//import com.babySitter.model.dto.Page;
//import com.babySitter.model.dto.PageImage;
//import com.babySitter.model.dto.PageMenu;
//
//public interface PageDao {
//
//	List<Page> selectPageNoByMemberNo(int memberNo);
//	List<PageMenu> selectPageMenubyPageNo(int pageNo);
//	PageMenu selectMemberPageByMenuNo(int menuno);
//	int insertPageMenuByAjax(PageMenu menu);
//	PageMenu selectPageMenuByPageNoNotice(int pageNo);
//	void insertImageFile(PageImage pi);
//	int selectMemberPageCountByMemberNo(int memberNo);
//	int insertPage(Page page);
//	void insertMemberPageByMemberNo(int memberNo, int pageNo, String name);
//	void insertPageMenuNotice(int pageNo);
//	int selectPageNo();
//}