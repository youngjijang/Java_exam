package kosta.exam.view;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import kosta.exam.model.dto.Board;


public class SuccessView {
public static void printBoard(Map<String, Map<String, Board>> allMap) {
		
		for (String key : allMap.keySet()) {
			System.out.println(key+"Boad�� ��� �Խù� LIST**********************");
			Map<String, Board> kindMap = allMap.get(key);
			for(String k : kindMap.keySet()) {
				System.out.println("key = " + k +"[ " + kindMap.get(k)  +" ] ");
			}
			/* ��������
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
	 * �Խ��������� �ش��ϴ� �Խù� ���
	 */
	public static void printboardByKind(String kind, Map<String, Board> kindMap) {
		System.out.println("----" + kind + "������ �Խù� List -----------");
		
		for (String key : kindMap.keySet()) {
			System.out.println(kindMap.get(key));
		}
		
		System.out.println();
	}

	/**
	 * �Խ����������� �۹�ȣ�� �ش��ϴ� �Խù� ����ϱ�
	 */
	public static void printBoardByNo(Board board) {
		System.out.println(board);
	}

	/**
	 * ���, ����, �����ΰ�쿡 �����޽��� ���
	 */
	public static void printMessage(String message) {
		System.out.println(message);
	}
}
