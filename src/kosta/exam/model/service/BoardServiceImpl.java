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
	private static BoardServiceImpl instance = new BoardServiceImpl(); //instance뭐더라
	
	Map<String, Map<String, Board>> allBoardList = new TreeMap<String, Map<String, Board>>();
	
	/**
	 * 게시판의 초기 데이터 로딩
	 */
	public BoardServiceImpl() {
		
		Map<String,Board> archiveMap = new TreeMap<String,Board>();
		Map<String,Board> photoMap = new TreeMap<String,Board>();
		
		//1. properties파일 로딩
		ResourceBundle rb = ResourceBundle.getBundle("kosta\\exam\\model\\service\\archiveInfo");
		
		//2. 읽어온 value의 값을 콤마 기준으로 분리해서(value.split(",")) board객체로 생성한다. 
		
		for(String key : rb.keySet()) {
			String value = rb.getString(key);
			String [] v = value.split(",");
			
			//3. 생성된 board를 kind 정류에 해당하는 map에 저장
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
		//4. allBoardList에 3번의 map을 저장 - allBoardList.put(key, map)
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
			throw new InexistentException("저장된 게시물의 정보가 없습니다. ");
		}
		return allBoardList;
	}

	@Override
	public Map<String, Board> getBoardByKind(String kind) throws InexistentException {
		// TODO Auto-generated method stub
		if(allBoardList.get(kind) == null) {
			throw new InexistentException(kind + "유형의 게시판은 존재하지않습니다.");
		}else if(allBoardList.get(kind).isEmpty()) {
			throw new InexistentException(kind + "유형에 게시물이 없습니다.");
		}
		return allBoardList.get(kind);
	}

	@Override
	public Board getBoardByNo(String kind, int no) throws InexistentException {
		// TODO Auto-generated method stub
		Map<String, Board> map = this.getBoardByKind(kind);
		if(map==null) {
			throw new InexistentException(kind + "유형의 게시판에 "+no + "번호의 게시물은 없습니다.");
		}else {
			return map.get(String.valueOf(no));//return map.get(no+"");
		}
		
	}

	@Override
	public void insertBoard(String kind, Board board) throws DuplicateException, InexistentException {
		// TODO Auto-generated method stub
		Map<String, Board> map = this.getBoardByKind(kind);
		if(duplicateByNo(kind, board.getNo())) {
			throw new DuplicateException(kind + "유형의 게시판의 "+board.getNo()+"번 번호는 중복이므로 등록할수없습니다.");
		}else {
			map.put(board.getNo()+"", board);
			/*
			if(kind.equals("archive")) {
				allBoardList.get(kind).put(board.getNo()+"",board);
			}else if(kind.equals("photo")) {
				allBoardList.get(kind).put(board.getNo()+"", board);
			}else {
				throw new InexistentException(kind + "유형 게시판이 오류이므로 등록할수 없습니다.");
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
			throw new InexistentException(kind +"게시판의 " + no + "번호 게시물 삭제되지않았습니다.");
		}else {
			if(duplicateByNo(kind, no)){
				allBoardList.get(kind).remove(no+"");
			}else {
				throw new InexistentException(kind +"게시판의 " + no + "번호 게시물 삭제되지않았습니다.");
			}
		}
	}

	@Override
	public void updateBoard(Board board, String kind) throws InexistentException {
		Map<String, Board> map = this.getBoardByKind(kind);
		// TODO Auto-generated method stub
		if(duplicateByNo(kind,board.getNo())) {
			map.put(board.getNo()+"", board);
			///put을 사용하면 안되는 이유....! 주소값이 변경되며 아예다른 보드가 된다. garbage collection 이 생성됨
			// 귀찮아도 set 함수를 호출해야 됨
		
		}else {
			throw new InexistentException("수정되지 않았습니다.");
		}
		
	}

}
