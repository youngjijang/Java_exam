package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dao.BoardDAO;
import kosta.mvc.model.dao.BoardDAOImpl;
import kosta.mvc.model.dto.BoardDTO;
import kosta.mvc.model.dto.ReplyDTO;

public class BoardServiceImpl implements BoardService {
   private BoardDAO boardDAO = new BoardDAOImpl();
   
	@Override
	public List<BoardDTO> boardSelectAll() throws SQLException {
		 List<BoardDTO>  list = boardDAO.boardSelectAll();
		 //dao�� ���� ����� ������ ����
		 if(list.size()==0 || list.isEmpty()) {
			 throw new SQLException("�Խù��� ������ ���� �˻��Ҽ� �����ϴ�.");
		 }
		
		return list;
	}

	@Override
	public List<BoardDTO> boardSelectBySubject(String keyWord) throws SQLException {
		List<BoardDTO> list = boardDAO.boardSelectBySubject(keyWord);
		if(list.isEmpty()) throw new SQLException(keyWord+"�ܾ ������ ���ڵ��� ���� �����ϴ�.");
		return list;
	}

	@Override
	public BoardDTO boardSelectByNo(int boardNo) throws SQLException {
		BoardDTO boardDTO = boardDAO.boardSelectByNo(boardNo);
		if(boardDTO==null)throw new SQLException(boardNo+"�ش��ϴ� ������ �����ϴ�.");
		return boardDTO;
	}

	
	@Override
	public void boardInsert(BoardDTO boardDTO) throws SQLException {
		int result = boardDAO.boardInsert(boardDTO);
		if(result==0)throw new SQLException("��ϵ����ʾҽ��ϴ�.^^");

	}
	

	@Override
	public void boardUpdate(BoardDTO boardDTO) throws SQLException {
		int result = boardDAO.boardUpdate(boardDTO);
		if(result==0)throw new SQLException("�������� �ʾҽ��ϴ�.");

	}

	@Override
	public void boardDelete(int boardNo) throws SQLException {
		int result = boardDAO.boardDelete(boardNo);
		if(result==0)throw new SQLException("�������� �ʾҽ��ϴ�.");

	}

	@Override
	public void replyInsert(ReplyDTO replyDTO) throws SQLException  {
	    int result = boardDAO.replyInsert(replyDTO);
		if(result==0)throw new RuntimeException("��� ��Ͽ� �����Ͽ����ϴ�.");
	}

	
	@Override
	public BoardDTO replySelectByParentNo(int boardNo) throws SQLException {
		BoardDTO boardDTO = boardDAO.replySelectByParentNo(boardNo);
		if(boardDTO==null)throw new SQLException(boardNo+"�� �ش��ϴ� �θ�� ������ �����ϴ�.");
		
	    if(boardDTO.getRepliesList().size()==0)throw new SQLException(boardNo+"�� �ش��ϴ� ��������� �����ϴ�.");
		
		return boardDTO;
	}

}
















