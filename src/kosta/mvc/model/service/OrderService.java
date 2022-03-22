package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dao.OrderDAO;
import kosta.mvc.model.dao.OrderDAOImpl;
import kosta.mvc.model.dto.Orders;

public class OrderService {
	OrderDAO orderDao = new OrderDAOImpl();
   /**
    * �ֹ��ϱ�
    * */
	 public void insertOrders(Orders orders) throws SQLException{
		int result =  orderDao.orderInsert(orders);
		if(result==0)throw new SQLException("�ֹ��ϱⰡ �����Ͽ����ϴ�.");
	 }
	 
	 /**
	  * �ֹ� ��������
	  * */
	 public List<Orders> selectOrdersByUserId(String userId)throws SQLException{
		 List<Orders> list = orderDao.selectOrdersByUserId(userId);
		 if(list==null || list.size()==0)throw new SQLException(userId+"�� �ֹ������� �����ϴ�.");
		 return list;
	 }
}
