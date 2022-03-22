package kosta.exam.Controller;

import java.util.Map;

import kosta.exam.model.dto.Board;
import kosta.exam.model.service.BoardService;
import kosta.exam.model.service.BoardServiceImpl;
import kosta.exam.model.util.DuplicateException;
import kosta.exam.model.util.InexistentException;
import kosta.exam.view.FailView;
import kosta.exam.view.SuccessView;

public class BoardController {
	private static BoardService boardService = BoardServiceImpl.getInstance();
	public static void getAllBoard() {
		//���� ȣ��! �� ����� �޾Ƽ� ����信 �������
		try {
			Map<String,Map<String, Board>> allBoardList = boardService.getBoardList();
			SuccessView.printBoard(allBoardList);;
		}catch(InexistentException e) {
			FailView.errorMessage(e.getMessage());
		}
		
		
	}
	public static void getBoardByKind(String kind) {
		try {
			Map<String, Board> map = boardService.getBoardByKind(kind);
			SuccessView.printboardByKind(kind, map);
		}catch(InexistentException e) {
			FailView.errorMessage(e.getMessage());
		}
			
	}
	public static void getBoardByno(String a, int no) {
		try {
			Board board = boardService.getBoardByNo(a, no);
			SuccessView.printBoardByNo(board);
		}catch(InexistentException e) {
			FailView.errorMessage(e.getMessage());
		}
		
		
	}
	public static void insertBoard(String a, Board board) {
		try {
			boardService.insertBoard(a, board);
			SuccessView.printMessage("����� �Ϸ�Ǿ����ϴ�.");
		}catch(DuplicateException e) {
			FailView.errorMessage(e.getMessage());
		}catch(InexistentException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	public static void deleteBoard(String kind, int i) {
		try {
			boardService.deleteBoard(kind, i);
				SuccessView.printMessage(kind + "�Խ�����"+i+"��ȣ �Խù��� �����Ǿ����ϴ�.");
		}catch(InexistentException e) {
			FailView.errorMessage(e.getMessage());
		}
	
		
	}
	public static void updateBoard(Board board, String a) {
		try {
			boardService.updateBoard(board, a);
			SuccessView.printMessage("������ �Ϸ�Ǿ����ϴ�.");
		}catch(InexistentException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}
