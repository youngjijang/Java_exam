package kosta.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.BoardDTO;
import kosta.mvc.model.dto.ReplyDTO;

public interface BoardDAO {
	/**
	 * 레코드 전체검색
	 */
	List<BoardDTO> boardSelectAll() throws SQLException;
	
	/**
	 * 제목에 특정 문자열이 포함된 레코드 검색 - 0개이상의 레코드 리턴!!!
	 * select * from board where subject like ?
	 */
	List<BoardDTO> boardSelectBySubject(String keyWord) throws SQLException;
	
	/**
	 * 글번호에 해당하는 레코드 검색 - pk를 대상으로 조건 ( 레코드수가 0 아니면 1)
	 * select * from board where board_no = ? 
	 */
	BoardDTO boardSelectByNo(int boardNo) throws SQLException;
	
	/**
	 * 게시물 등록하기
	 * insert into board (board_no, subject, writer, content, board_date) 
	 * values (board_seq.nextval, ?, ?, ?, sysdate)
	 */
	int boardInsert(BoardDTO boardDTO) throws SQLException;
	
	/**
	 * 글번호에 해당하는 게시물 내용 수정하기
	 * update board set content = ? where board_no = ?
	 */
	int boardUpdate(BoardDTO boardDTO) throws SQLException;
	
	/**
	 * 글번호에 해당하는 레코드 삭제
	 * delete from board where board_no = ?
	 */
	int boardDelete(int boardNo) throws SQLException;
	
	/**
	 * 댓글 등록하기
	 * */
	int replyInsert(ReplyDTO replyDTO) throws SQLException;
	
	/**
	 * 부모글에 해당하는 댓글정보 검색하기
	 *   select * from board where board_no=?
	 *   select * from reply where board_no=?
	 *   
	 *   
	 *   조인경우 : select * from board join reply using(board_no)  where board_no=?
	 * */
	BoardDTO replySelectByParentNo(int boardNo) throws SQLException;
	
}

















