package com.coolisland.trackmystocks.beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coolisland.trackmystocks.database.StockBO;
import com.coolisland.trackmystocks.database.StockDao;


public class StockPriceHistoryBean extends StockBO {
	List<PriceBean> movingAverage = new ArrayList<PriceBean>();
	
	public StockPriceHistoryBean(String stockId) {
		// initialize StockBO
		try {
			StockDao dao = new StockDao();
			StockBO stock = dao.getStockTicker(stockId);
			this.load(stock);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// initialize the moving average
		
	}
}
