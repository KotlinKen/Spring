package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

public interface BoardService {

	List<Map<String, String>> selectBoardList(int cPage, int numPerPage);
	int selectCount();
	int insertBoard(Board board, List<Attachment> attachList);
	List<Map<String, String>> selectOne(int boardNo);
	
}
