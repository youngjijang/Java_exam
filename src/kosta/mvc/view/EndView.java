package kosta.mvc.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kosta.mvc.controller.OrderController;
import kosta.mvc.model.dto.Goods;
import kosta.mvc.model.dto.OrderLine;
import kosta.mvc.model.dto.Orders;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;

public class EndView {
  /**
   * ��ǰ ��ü ���
   * */
	public static void printGoodsList(List<Goods> list) {
		System.out.println("-----��ǰ "+ list.size() +"�� -------------");
		for(Goods goods : list) {
			System.out.println(goods);
		}
		
		System.out.println();
	}
	
	
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * ��ٱ��� ����
	 * */
	public static void printViewCart(String id , Map<Goods,Integer> cart) {
		System.out.println("��ٱ��ϳ���....");
		
		for(Goods goods: cart.keySet()) {
			String goodsId = goods.getGoodsId();//��ǰ��ȣ
			String name = goods.getGoodsName();//��ǰ�̸�
			int price = goods.getGoodsPrice();//��ǰ����
			
			int quantity = cart.get(goods);//
			System.out.println(goodsId+" : "+name+" : "+price+" \t "+quantity);
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1.�ֹ��ϱ�  |  9.������");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			
			 System.out.print("����ּ� : ");
			 String address = sc.nextLine();
			
			 Orders orders = new Orders(0, null, id, address, 0);
			 
			 List<OrderLine> orderLineList = orders.getOrderLineList();
			 
			 for(Goods goodsKey : cart.keySet()) {
				 int qty = cart.get(goodsKey);
				 OrderLine orderLine = new OrderLine(0, 0, goodsKey.getGoodsId() , 0, qty, 0);
				 orderLineList.add(orderLine);
			 }
			 
			 System.out.println("orderLineList ���� : " + orderLineList.size());
			 OrderController.insertOrders(orders);
			 
			 //��ٱ��Ϻ���
			 SessionSet ss = SessionSet.getInstance();
			 Session session = ss.get(id);
			 session.removeAttribute("cart");
			break;
			
		case 9:
			break;
		}
		
		//System.out.println(id);
	
	}
 
	/**
	 * �ֹ� �󼼺���
	 * */
	public static void printOrderByUserId(List<Orders> orderList) {
	   for(Orders order : orderList) {
		   System.out.println(order.getOrderId()+ " | " + order.getOrderDate() +" | " + order.getTotalAmount() +" | " + order.getAddress());
		   for(OrderLine orderLine : order.getOrderLineList()) {
			   System.out.println("  �� "+orderLine);
		   }
		   System.out.println();
	   }
		
	}
}












