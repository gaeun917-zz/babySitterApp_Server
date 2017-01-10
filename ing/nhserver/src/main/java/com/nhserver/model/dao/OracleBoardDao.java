package com.nhserver.model.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.nhserver.model.dto.Board;
import com.nhserver.model.dto.BoardComment;
import com.nhserver.model.mapper.BoardMapper;

@Repository("oracleBoardDao")
public class OracleBoardDao implements BoardDao {

	@Autowired
	@Qualifier("boardMapper")
	private BoardMapper boardMapper;
	
	public int insertBoard(Board board) {		
		boardMapper.insertBoard(board);
		return board.getBoardNo();
	}
	
	public int insertReply(Board board) {
		boardMapper.increaseStepNo(board);
		boardMapper.insertBoardReply(board);
		return board.getBoardNo();
	}
	
	///////////////////////////////////
	
	public List<Board> selectBoardList() {
		return null;
	}
	
	public List<Board> selectBoardList2(int start, int last) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("last", last);
		List<Board> boardList = boardMapper.selectBoardList(map);
		
		return boardList;
	}

	///////////////////////////////////////////////
	
	public Board selectBoardByBoardNo(int number) {
		
		Board board = boardMapper.selectBoardByBoardNo(number);
		return board;

	}	
	
	public int getBoardCount() {
		int count = boardMapper.selectBoardCount();
		return count;
	}
	
	public int deleteBoard(int number) {
		boardMapper.deleteBoard(number);
		return 0;
	}
	
	public void updateBoardReadCount(int number) {
		boardMapper.increaseBoardReadCount(number);
	}
	
	public int updateBoard(Board board) {
		boardMapper.updateBoard(board);
		return 0;
	}
	
	public void insertComment(BoardComment comment) {
	
		boardMapper.insertBoardComment(comment);
	}
	
	public void updateComment(BoardComment comment) {
		
		boardMapper.updateComment(comment);
	}
	
	public void deleteComment(int commentNo) {
		
		boardMapper.deleteComment(commentNo);
	}
	

	
}