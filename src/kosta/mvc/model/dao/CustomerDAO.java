package kosta.mvc.model.dao;

import java.sql.SQLException;

import kosta.mvc.model.dto.Customer;

public interface CustomerDAO {
  /**
   * �α����ϱ�
   * */
	Customer login(String userId, String userPwd)throws SQLException;
}
