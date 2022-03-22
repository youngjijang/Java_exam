package kosta.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.BoardDTO;
import kosta.mvc.model.dto.ReplyDTO;

public interface BoardDAO {
	/**
	 * ���ڵ� ��ü�˻�
	 */
	List<BoardDTO> boardSelectAll() throws SQLException;
	
	/**
	 * ���� Ư�� ���ڿ��� ���Ե� ���ڵ� �˻� - 0���̻��� ���ڵ� ����!!!
	 * select * from board where subject like ?
	 */
	List<BoardDTO> boardSelectBySubject(String keyWord) throws SQLException;
	
	/**
	 * �۹�ȣ�� �ش��ϴ� ���ڵ� �˻� - pk�� ������� ���� ( ���ڵ���� 0 �ƴϸ� 1)
	 * select * from board where board_no = ? 
	 */
	BoardDTO boardSelectByNo(int boardNo) throws SQLException;
	
	/**
	 * �Խù� ����ϱ�
	 * insert into board (board_no, subject, writer, content, board_date) 
	 * values (board_seq.nextval, ?, ?, ?, sysdate)
	 */
	int boardInsert(BoardDTO boardDTO) throws SQLException;
	
	/**
	 * �۹�ȣ�� �ش��ϴ� �Խù� ���� �����ϱ�
	 * update board set content = ? where board_no = ?
	 */
	int boardUpdate(BoardDTO boardDTO) throws SQLException;
	
	/**
	 * �۹�ȣ�� �ش��ϴ� ���ڵ� ����
	 * delete from board where board_no = ?
	 */
	int boardDelete(int boardNo) throws SQLException;
	
	/**
	 * ��� ����ϱ�
	 * */
	int replyInsert(ReplyDTO replyDTO) throws SQLException;
	
	/**
	 * �θ�ۿ� �ش��ϴ� ������� �˻��ϱ�
	 *   select * from board where board_no=?
	 *   select * from reply where board_no=?
	 *   
	 *   
	 *   ���ΰ�� : select * from board join reply using(board_no)  where board_no=?
	 * */
	BoardDTO replySelectByParentNo(int boardNo) throws SQLException;
	
}

















