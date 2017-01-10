package com.nhserver.model.dao;

import java.util.List;

import com.nhserver.model.dto.Board;
import com.nhserver.model.dto.BoardComment;

public interface BoardDao {

	int insertBoard(Board board);

	int insertReply(Board board);

	List<Board> selectBoardList();

	Board selectBoardByBoardNo(int number);

	List<Board> selectBoardList2(int start, int last);

	int getBoardCount();

	int deleteBoard(int number);

	void updateBoardReadCount(int number);

	int updateBoard(Board board);

	void insertComment(BoardComment comment);

	void updateComment(BoardComment comment);

	void deleteComment(int commentNo);

}