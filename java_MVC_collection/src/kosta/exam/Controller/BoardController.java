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
		//서비스 호출! 그 결과를 받아서 결과뷰에 전달출력
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
			SuccessView.printMessage("등록이 완료되었습니다.");
		}catch(DuplicateException e) {
			FailView.errorMessage(e.getMessage());
		}catch(InexistentException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	public static void deleteBoard(String kind, int i) {
		try {
			boardService.deleteBoard(kind, i);
				SuccessView.printMessage(kind + "게시판의"+i+"번호 게시물이 삭제되었습니다.");
		}catch(InexistentException e) {
			FailView.errorMessage(e.getMessage());
		}
	
		
	}
	public static void updateBoard(Board board, String a) {
		try {
			boardService.updateBoard(board, a);
			SuccessView.printMessage("수정이 완료되었습니다.");
		}catch(InexistentException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}
