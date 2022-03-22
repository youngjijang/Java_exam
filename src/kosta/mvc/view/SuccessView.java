package kosta.mvc.view;

import java.util.List;

import kosta.mvc.model.dto.BoardDTO;
import kosta.mvc.model.dto.ReplyDTO;

public class SuccessView {

	public static void selectPrint(List<BoardDTO> list) {
		System.out.println("----Board LIST ("+list.size()+") �� ------------------");
		for(BoardDTO board : list) {
			System.out.println(board);//board.toString()ȣ��
		}
		
	}

	public static void messagePrint(String message) {
		System.out.println(message);
		
	}

	public static void selectByNoPrint(BoardDTO boardDTO) {
		System.out.println(boardDTO);
		
	}

	public static void selectReplyPrint(BoardDTO boardDTO) {
		System.out.println(boardDTO);
		System.out.println("��� ���� : " + boardDTO.getRepliesList().size() +"�� ---------");
		for(ReplyDTO reply : boardDTO.getRepliesList()) {
			System.out.println("       �� " + reply );
		}
		
	}

}












