package com.kh.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

public interface BoardDAO {

	List<Map<String, String>> selectBoardList(int cPage, int numPerPage);

	int selectCount();

	int insertBoard(Board board);

	int inserAttachment(Attachment a);

	List<Map<String, String>> selectOne(int boardNo);

	int selectOneAttachCount(int boardNo);

	List<Map<String, String>> selectOneAttach(int boardNo);


}
