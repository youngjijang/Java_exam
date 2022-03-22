package kosta.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.BoardDTO;
import kosta.mvc.model.dto.ReplyDTO;
import kosta.mvc.model.service.BoardService;
import kosta.mvc.model.service.BoardServiceImpl;
import kosta.mvc.view.FailView;
import kosta.mvc.view.SuccessView;

public class BoardController {
   private static BoardService boardService = new BoardServiceImpl();
   
   /**
    * ��ü�˻�
    * */
   public static void boardSelectByAll() {
	   try {
	    List<BoardDTO> list = boardService.boardSelectAll();
	     SuccessView.selectPrint(list);
	   }catch (SQLException e) {
		  //e.printStackTrace();
		  FailView.errorMessage(e.getMessage()); 
	   }
   }

	public static void boardSelectByNo(int boardNo) {
		try {
		  BoardDTO boardDTO = boardService.boardSelectByNo(boardNo);
		  SuccessView.selectByNoPrint(boardDTO);
		  
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	public static void boardSelectBySubject(String word) {
		try {
			List<BoardDTO> list = boardService.boardSelectBySubject(word);
			SuccessView.selectPrint(list);
		  
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * �Խù� ����ϱ� 
	 * */
	public static void boardInsert(BoardDTO board) {
		try {
		   boardService.boardInsert(board);
		   SuccessView.messagePrint("��ϵǾ����ϴ�.");
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	public static void boardUpdate(BoardDTO board) {
		try {
		  boardService.boardUpdate(board);
		  SuccessView.messagePrint("������ �����Ͽ����ϴ�.");
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
		
	}
	
	public static void boardDelete(int no) {
		try {
		  boardService.boardDelete(no);
		  SuccessView.messagePrint("�����Ǿ����ϴ�.");
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * ��۵���ϱ�
	 * */
	public static void replyInsert(ReplyDTO replyDTO) {
		try {
	       boardService.replyInsert(replyDTO);  
	       SuccessView.messagePrint(replyDTO.getBoardNo()+" �ۿ� ����� ��ϵǾ����ϴ�.");
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage("��۵�Ͽ� �����Ͽ����ϴ�.");
			
		}catch (RuntimeException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void replySelectByParentNo(int boardNo) {
		try {
		  BoardDTO boardDTO = boardService.replySelectByParentNo(boardNo);
		  SuccessView.selectReplyPrint(boardDTO);
		  
		}catch(SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
}//Ŭ������



















