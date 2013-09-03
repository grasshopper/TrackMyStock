package com.coolisland.trackmystocks.beans;

import java.util.List;

import com.coolisland.trackmystocks.database.StockBO;

public class TickerBean extends StockBO {
	
	private boolean viewTicker;

	public TickerBean() {
		viewTicker = false;
	}
	
	public TickerBean(StockBO bo) {
		super(bo);
		
		viewTicker = false;
	}
	
	public TickerBean(List<StockBO> tickerBoList) {
		viewTicker = false;
	}
	
	
	public boolean isViewTicker() {
		return viewTicker;
	}

	public boolean getViewTicker() {
		return viewTicker;
	}

	public void setViewTicker(boolean viewTicker) {
		this.viewTicker = viewTicker;
	}
}
