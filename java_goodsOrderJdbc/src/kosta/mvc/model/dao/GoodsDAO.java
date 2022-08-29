package kosta.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.Goods;

public interface GoodsDAO {
  /**
   * ��ü ��ǰ
   * */
	List<Goods> goodsSelect() throws SQLException;
	
  /**
   * goodsId�� �ش��ϴ� ���� �˻�
   * */
	Goods goodsSelectBygoodsId(String goodsId)throws SQLException;
}
