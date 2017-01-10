package com.nhserver.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.nhserver.model.dto.Board;
import com.nhserver.model.dto.BoardComment;

public interface BoardMapper {
	
	void insertBoard(Board board);
	List<Board> selectBoardList(HashMap<String, Object> map);
	Board selectBoardByBoardNo(int boardNo);
	List<BoardComment> selectBoardCommentsByBoardNo(int boardNo);
	int selectBoardCount();
	void deleteBoard(int boardNo);
	void updateBoard(Board board);
	void insertBoardReply(Board board);
	void increaseStepNo(Board board);
	void increaseBoardReadCount(int boardNo);
	void insertBoardComment(BoardComment comment);
	void updateComment(BoardComment comment);
	void deleteComment(int commentNo);
	

}
