package kosta.mvc.session;

import java.util.HashSet;
import java.util.Set;

public class SessionSet {//�̱���

	private static SessionSet ss = new SessionSet();
	private Set<Session> set;
	
	private SessionSet() {
		set = new HashSet<>();
	}
	
	public static SessionSet getInstance() {
		return ss;
	}
	
	
	/**
	 * ����� ã��
	 * */
	public Session get(String sessionId) {
		for(Session session : set) {
			if(session.getSessionId().equals(sessionId)) {
				return session;
			}
		}
		return null;
	}
	
//���� ��ü�� ��ȯ
	public Set<Session> getSet(){
		return set;
	}

	/**
	 * �α��� �� ����� �߰�
	 * */
	public void add(Session session) {
		set.add(session);
	}
	
	/**
	 * ����� ���� - �α׾ƿ�
	 * */
	public void remove(Session session) {
		set.remove(session);
	}
	
	
}
