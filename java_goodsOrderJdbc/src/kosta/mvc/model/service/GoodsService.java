package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dao.GoodsDAO;
import kosta.mvc.model.dao.GoodsDAOImpl;
import kosta.mvc.model.dto.Goods;

public class GoodsService {
	GoodsDAO goodsDao = new GoodsDAOImpl();
    /**
     * ��ü ��ǰ��ȸ
     * */
	public List<Goods> goodsSelect() throws NotFoundException , SQLException{
		List<Goods> list=goodsDao.goodsSelect();
		if(list.size()==0)throw new NotFoundException("���� ��ǰ�� �����ϴ�.");
		return list;
	}
	
	/**
	 * ��ǰ��ȣ�� �ش��ϴ� ��ǰ�˻�
	 * */
	public Goods goodsSelectBygoodsId(String goodsId) throws  SQLException{
		Goods goods=goodsDao.goodsSelectBygoodsId(goodsId);
		if(goods==null)throw new SQLException(goodsId + " ���� ��ǰ�� �����ϴ�.");
		return goods;
	}
}
