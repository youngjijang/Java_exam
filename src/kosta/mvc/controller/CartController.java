package kosta.mvc.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;



import kosta.mvc.model.dto.Goods;
import kosta.mvc.model.service.GoodsService;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;
import kosta.mvc.view.EndView;
import kosta.mvc.view.FailView;

public class CartController {
  private static GoodsService goodsService = new GoodsService();
  
   public static void putCart(String id, String goodsId, int quantity) {
		
		try {
			//��ǰ��ȣ�� �ش� ��ǰã��
			Goods goods = goodsService.goodsSelectBygoodsId(goodsId);
			//A01	�����	1500	4	20/09/04
			
			if(goods.getStock() < quantity) {
				throw new SQLException("��� �������� ��ٱ��Ͽ� ������ �����ϴ�.");
			}
			//id�� �ش��ϴ� ����ã��
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(id);	
			
			//���ǿ��� ��ٱ��� ã��
			Map<Goods, Integer> cart =	(Map<Goods,Integer>)session.getAttribute("cart"); //��ǰ , ���� ���� 
			
			//��ٱ��ϰ� ������ ��ٱ��� ����
			if(cart == null) { 
				cart = new HashMap<>(); 
				session.setAttribute("cart", cart);
			}
			
			
			//��ٱ��Ͽ��� ��ǰã��
			Integer oldQuantity = cart.get(goods);
			if(oldQuantity != null) { //��ٱ��Ͽ� �̹� ��ǰ�� �ִٸ�
				quantity += oldQuantity; //������ ����
			}
			
			cart.put(goods, quantity); //��ٱ��Ͽ� ��ǰ �ֱ�
			EndView.printMessage("��ٱ��Ͽ� ��ҽ��ϴ�");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
   
   /**
    * ��ٱ��� ����
    * */
   public static void viewCart(String id) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		
		Map<Goods,Integer> cart = (Map<Goods, Integer>) session.getAttribute("cart");
		if(cart == null ) { // ��ٱ��ϰ� ���� ��
			FailView.errorMessage("��ٱ��ϰ� ������ϴ�");
		}else {
			EndView.printViewCart(id , cart);
		}
	}
}




