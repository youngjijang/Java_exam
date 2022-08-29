package kosta.exam.model.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.TreeMap;

import kosta.exam.model.dto.ArchiveBoard;
import kosta.exam.model.dto.Board;
import kosta.exam.model.dto.PhotoBoard;
import kosta.exam.model.util.DuplicateException;
import kosta.exam.model.util.InexistentException;

public class BoardServiceImpl implements BoardService {
	private static BoardServiceImpl instance = new BoardServiceImpl(); //instance������
	
	Map<String, Map<String, Board>> allBoardList = new TreeMap<String, Map<String, Board>>();
	
	/**
	 * �Խ����� �ʱ� ������ �ε�
	 */
	public BoardServiceImpl() {
		
		Map<String,Board> archiveMap = new TreeMap<String,Board>();
		Map<String,Board> photoMap = new TreeMap<String,Board>();
		
		//1. properties���� �ε�
		ResourceBundle rb = ResourceBundle.getBundle("kosta\\exam\\model\\service\\archiveInfo");
		
		//2. �о�� value�� ���� �޸� �������� �и��ؼ�(value.split(",")) board��ü�� �����Ѵ�. 
		
		for(String key : rb.keySet()) {
			String value = rb.getString(key);
			String [] v = value.split(",");
			
			//3. ������ board�� kind ������ �ش��ϴ� map�� ����
			Board board = new ArchiveBoard( Integer.parseInt(v[0]), v[1], v[2], v[3], v[4], v[5], Integer.parseInt(v[6]));
			archiveMap.put(key, board);
		}
		
		///////////////////////////
		
		rb = ResourceBundle.getBundle("kosta\\exam\\model\\service\\photoInfo");
		for(String key : rb.keySet()) {
			String value = rb.getString(key);
			String [] v = value.split(",");
			
			Board board = new PhotoBoard(Integer.parseInt(v[0]), v[1], v[2], v[3], v[4], v[5]);
			photoMap.put(key, board);
		}
		//4. allBoardList�� 3���� map�� ���� - allBoardList.put(key, map)
		allBoardList.put("archive", archiveMap);
		allBoardList.put("photo", photoMap);
		
		//System.out.println(allBoardList);
	}
	
//	public static void main(String[] args) {
//		BoardServiceImpl.getInstance();
//	}
	public static BoardServiceImpl getInstance() {
		return instance;
		
	}

	@Override
	public Map<String, Map<String, Board>> getBoardList() throws InexistentException {
		// TODO Auto-generated method stub
		if(allBoardList == null || allBoardList.isEmpty()) {
			throw new InexistentException("����� �Խù��� ������ �����ϴ�. ");
		}
		return allBoardList;
	}

	@Override
	public Map<String, Board> getBoardByKind(String kind) throws InexistentException {
		// TODO Auto-generated method stub
		if(allBoardList.get(kind) == null) {
			throw new InexistentException(kind + "������ �Խ����� ���������ʽ��ϴ�.");
		}else if(allBoardList.get(kind).isEmpty()) {
			throw new InexistentException(kind + "������ �Խù��� �����ϴ�.");
		}
		return allBoardList.get(kind);
	}

	@Override
	public Board getBoardByNo(String kind, int no) throws InexistentException {
		// TODO Auto-generated method stub
		Map<String, Board> map = this.getBoardByKind(kind);
		if(map==null) {
			throw new InexistentException(kind + "������ �Խ��ǿ� "+no + "��ȣ�� �Խù��� �����ϴ�.");
		}else {
			return map.get(String.valueOf(no));//return map.get(no+"");
		}
		
	}

	@Override
	public void insertBoard(String kind, Board board) throws DuplicateException, InexistentException {
		// TODO Auto-generated method stub
		Map<String, Board> map = this.getBoardByKind(kind);
		if(duplicateByNo(kind, board.getNo())) {
			throw new DuplicateException(kind + "������ �Խ����� "+board.getNo()+"�� ��ȣ�� �ߺ��̹Ƿ� ����Ҽ������ϴ�.");
		}else {
			map.put(board.getNo()+"", board);
			/*
			if(kind.equals("archive")) {
				allBoardList.get(kind).put(board.getNo()+"",board);
			}else if(kind.equals("photo")) {
				allBoardList.get(kind).put(board.getNo()+"", board);
			}else {
				throw new InexistentException(kind + "���� �Խ����� �����̹Ƿ� ����Ҽ� �����ϴ�.");
			}*/
		}

	}
	
	private void propertiesFileStore(String a, Board board) {
		
	}

	@Override
	public boolean duplicateByNo(String kind, int no) {
		
		for (String key : allBoardList.keySet()) {
			
			Map<String, Board> kindMap = allBoardList.get(key);
			for(String k : kindMap.keySet()) {
				if(kindMap.get(k).getNo() == no) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void deleteBoard(String kind, int no) throws InexistentException {
		// TODO Auto-generated method stub
		if(allBoardList.get(kind) == null) {
			throw new InexistentException(kind +"�Խ����� " + no + "��ȣ �Խù� ���������ʾҽ��ϴ�.");
		}else {
			if(duplicateByNo(kind, no)){
				allBoardList.get(kind).remove(no+"");
			}else {
				throw new InexistentException(kind +"�Խ����� " + no + "��ȣ �Խù� ���������ʾҽ��ϴ�.");
			}
		}
	}

	@Override
	public void updateBoard(Board board, String kind) throws InexistentException {
		Map<String, Board> map = this.getBoardByKind(kind);
		// TODO Auto-generated method stub
		if(duplicateByNo(kind,board.getNo())) {
			map.put(board.getNo()+"", board);
			///put�� ����ϸ� �ȵǴ� ����....! �ּҰ��� ����Ǹ� �ƿ��ٸ� ���尡 �ȴ�. garbage collection �� ������
			// �����Ƶ� set �Լ��� ȣ���ؾ� ��
		
		}else {
			throw new InexistentException("�������� �ʾҽ��ϴ�.");
		}
		
	}

}
