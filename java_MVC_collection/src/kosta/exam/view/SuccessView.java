package kosta.exam.view;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import kosta.exam.model.dto.Board;


public class SuccessView {
public static void printBoard(Map<String, Map<String, Board>> allMap) {
		
		for (String key : allMap.keySet()) {
			System.out.println(key+"Boad의 모든 게시물 LIST**********************");
			Map<String, Board> kindMap = allMap.get(key);
			for(String k : kindMap.keySet()) {
				System.out.println("key = " + k +"[ " + kindMap.get(k)  +" ] ");
			}
			/* 내림차순
			TreeMap treeMap= (TreeMap<String,Board>) kindMap;
			Iterator<String> it = treeMap.descendingKeySet().iterator();
			while(it.hasNext()) {
				String k = it.next();//key
				System.out.println("key = " + k +"[ " + kindMap.get(k)  +" ] ");	
			}*/
			System.out.println();
			
		}
		/*
		System.out.println("========================================");
		 if(allMap instanceof TreeMap ) {
			 TreeMap treeMap= (TreeMap<String, Map<String, Board>>)allMap;
			 
			Iterator<String> it = treeMap.descendingKeySet().iterator();
			while(it.hasNext()) {
				String key =it.next();//key
				System.out.println(key); 
			}
         }*/
	    
	}

	/**
	 * 게시판유형에 해당하는 게시물 출력
	 */
	public static void printboardByKind(String kind, Map<String, Board> kindMap) {
		System.out.println("----" + kind + "유형의 게시물 List -----------");
		
		for (String key : kindMap.keySet()) {
			System.out.println(kindMap.get(key));
		}
		
		System.out.println();
	}

	/**
	 * 게시판유형에서 글번호에 해당하는 게시물 출력하기
	 */
	public static void printBoardByNo(Board board) {
		System.out.println(board);
	}

	/**
	 * 등록, 수정, 삭제인경우에 성공메시지 출력
	 */
	public static void printMessage(String message) {
		System.out.println(message);
	}
}
