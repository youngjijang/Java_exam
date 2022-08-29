package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.BoardDTO;
import kosta.mvc.model.dto.ReplyDTO;

public interface BoardService {
	/**
	 * ��� ���ڵ� �˻�
	 */
	List<BoardDTO> boardSelectAll() throws SQLException;

	/**
	 * ���� Ư�����ڿ� ������ ���ڵ� �˻�
	 */
	List<BoardDTO> boardSelectBySubject(String keyWord) throws SQLException;

	/**
	 * �۹�ȣ�� �ش��ϴ� ���ڵ� �˻�
	 */
	BoardDTO boardSelectByNo(int boardNo) throws SQLException;

	/**
	 * �Խù� ��� 
	 */
	void boardInsert(BoardDTO boardDTO) throws SQLException;

	/**
	 * �Խù� ����
	 */
	void boardUpdate(BoardDTO boardDTO) throws SQLException;

	/**
	 * �Խù� ����
	 */
	void boardDelete(int boardNo) throws SQLException;
	
	
	/**
	 * ��۵���ϱ�
	 * */
	void replyInsert(ReplyDTO replyDTO)throws SQLException;

	/**
	 * �θ�ۿ� �ش��ϴ� ��� ���� �������� 
	 * */
     BoardDTO replySelectByParentNo(int boardNo) throws SQLException;
}
















