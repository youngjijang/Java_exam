package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kosta.mvc.model.dto.Goods;
import kosta.mvc.model.dto.OrderLine;
import kosta.mvc.model.dto.Orders;
import kosta.mvc.util.DbUtil;

public class OrderDAOImpl implements OrderDAO {
	
	GoodsDAO goodsDao = new GoodsDAOImpl();
    /**
     * �ֹ��ϱ� 
     *        1) orders���̺� insert
     *        2) order_line���̺� insert
     *        3) goods�� ��� ����(update)
     * */
	@Override
	public int orderInsert(Orders order)throws SQLException {
		 Connection con=null;
		  PreparedStatement ps=null;
		  String sql="INSERT INTO ORDERS(ORDER_ID, ORDER_DATE,USER_ID, ADDRESS, TOTAL_AMOUNT)" + 
		  		"  VALUES(ORDER_ID_SEQ.NEXTVAL ,sysdate,?,?, ?)";
		  int result=0;
		 try {
			
		   con = DbUtil.getConnection();
		   con.setAutoCommit(false);
		   
		   ps = con.prepareStatement(sql);
		   ps.setString(1, order.getUserId());
		   ps.setString(2, order.getAddress());
		   ps.setInt(3, getTotalAmount(order));//�ѱ��űݾױ��ϴ� �޼ҵ� ȣ��
		   
		   result = ps.executeUpdate();
		   if(result==0) {
			   con.rollback();
			   throw new SQLException("�ֹ� ����...");
		   }
		   else {
			   int re [] = orderLineInsert(con, order); //�ֹ��� ����ϱ� 
			   for(int i : re) {
				   if(i != 1) {//
					   con.rollback();
					   throw new SQLException("�ֹ� �Ҽ� �����ϴ�....");
				   }
			   }
			   //�ֹ�������ŭ ��� �����ϱ�
			   decrementStock(con, order.getOrderLineList());
			   con.commit();
		   }
		   
      }finally {
    	  con.commit();
      	DbUtil.close(con, ps , null);
      }
		
		return result;
	}
	
	/**
	 * �ֹ��� ����ϱ� 
	 * */
	public int[] orderLineInsert(Connection con  , Orders order) throws SQLException{
		
		  PreparedStatement ps=null;
		  String sql="insert into order_line(order_line_id,order_id, goods_id,unit_price, qty, amount)" + 
		  		"  values(ORDER_LINE_ID_SEQ.nextval ,ORDER_ID_SEQ.currval , ?, ? , ? , ? )";
		  int result [] =null;
		 try {
			 ps = con.prepareStatement(sql);
		  for( OrderLine orderline : order.getOrderLineList() ) {
			 Goods goods = goodsDao.goodsSelectBygoodsId(orderline.getGoodsId());
			  
			   ps.setString(1, orderline.getGoodsId());
			   ps.setInt(2, goods.getGoodsPrice());//����
			   ps.setInt(3, orderline.getQty());//�ѱ��űݾ�
			   ps.setInt(4,  goods.getGoodsPrice()*orderline.getQty());//�ѱ��űݾ�
			   ps.addBatch(); //�ϰ�ó���� �۾��� �߰�
			   ps.clearParameters();
			   
		  }
		  result = ps.executeBatch();//�ϰ�ó��
		  
		   
    }finally {
    	DbUtil.close(null, ps , null);
    }
		
		return result;
		
	}
	
	/**
	 * ��ǰ���� ��� ���ҽ�ŰŰ
	 * */
	public int[] decrementStock(Connection con , List<OrderLine> orderLineList)throws SQLException {
		 PreparedStatement ps=null;
		  String sql="update goods set stock = stock-? where goods_id=?";
		  int result [] =null;
		 try {
		  ps = con.prepareStatement(sql);
		  for( OrderLine orderline : orderLineList ) {
			   ps.setInt(1, orderline.getQty());
			   ps.setString(2, orderline.getGoodsId());
			   
			   ps.addBatch(); //�ϰ�ó���� �۾��� �߰�
			   ps.clearParameters();
		  }
		  
		  result = ps.executeBatch();//�ϰ�ó��
		 }finally {
			 DbUtil.close(null, ps, null);
		 }
		
		return result;
	}
	
	/**
	 * ��ǰ �ѱ��űݾ� ���ϱ�
	 * */
	public int getTotalAmount(Orders order) throws SQLException {
		List<OrderLine> orderLineList= order.getOrderLineList();
	    int total=0;
		for(OrderLine line : orderLineList) {
			Goods goods =goodsDao.goodsSelectBygoodsId(line.getGoodsId());
			if(goods==null)throw new SQLException("��ǰ��ȣ �����Դϴ�.... �ֹ� ����..");
			else if(goods.getStock() <  line.getQty())throw new SQLException("��� �����Դϴ�...");
			
	    	total += line.getQty() * goods.getGoodsPrice() ;
	    }
		return total;
	}
	
	/**
	 * �ֹ����� ����
	 * */
	public List<Orders> selectOrdersByUserId(String userId)throws SQLException{
		Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  List<Orders> list = new ArrayList<>();
		 try {
		   con = DbUtil.getConnection();
		   ps= con.prepareStatement("select * from orders where user_id=?");
		   ps.setString(1, userId);
	       rs = ps.executeQuery(); 
	        
	        while(rs.next()) {
	        	Orders orders  = new Orders(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
	        	
	        	//�ֹ���ȣ�� �ش��ϴ� ������ ��������
	        	List<OrderLine> orderLineList = selectOrderLine(orders.getOrderId());//�޼ҵ� ȣ��
	        	
	        	orders.setOrderLineList(orderLineList);
	        	list.add(orders);
	        }
    }finally {
    	DbUtil.close(con, ps, rs);
    }
		return list;
		
	}
	
	/**
	 * �ֹ���ȣ�� �ش��ϴ� �ֹ��� ��������
	 * */
	public List<OrderLine> selectOrderLine(int orderId)throws SQLException{
		Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  List<OrderLine> list = new ArrayList<>();
		 try {
		   con = DbUtil.getConnection();
		   ps= con.prepareStatement("select * from order_line where  order_id=?");
		   ps.setInt(1, orderId);
	       rs = ps.executeQuery(); 
	        
	        while(rs.next()) {
	        	OrderLine orderLine  = new OrderLine(rs.getInt(1), rs.getInt(2), 
	        			rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
	        	list.add(orderLine);
	        }
    }finally {
    	DbUtil.close(con, ps, rs);
    }
		return list;
		
	}
}







