package kosta.mvc.model.dto;

import java.util.List;

public class BoardDTO {

	private int boardNo; // �۹�ȣ
	private String subject; // ����
	private String writer; // �ۼ���
	private String content; // ����
	private String boardDate; // �����
	
	
	//1 : ���ΰ��
	private List<ReplyDTO> repliesList ;  //�θ�� �ϳ��� �������� ����� ��ϵɼ� �ִ�!!!
	
	
	public BoardDTO() {}

	public BoardDTO(int boardNo, String subject, String writer, String content, String boardDate) {
		super();
		this.boardNo = boardNo;
		this.subject = subject;
		this.writer = writer;
		this.content = content;
		this.boardDate = boardDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	
	
	
	public List<ReplyDTO> getRepliesList() {
		return repliesList;
	}

	public void setRepliesList(List<ReplyDTO> repliesList) {
		this.repliesList = repliesList;
	}
	

	@Override
	public String toString() {
		return boardNo + " | " + writer + "|" + subject + "|" + "|" + content + "|" + boardDate;
	}
}
