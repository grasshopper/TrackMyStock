package com.coolisland.trackmystocks.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.coolisland.trackmystocks.stockquotes.MovingAverageValues;
import com.coolisland.trackmystocks.stockquotes.MovingAverages;

public class StockPriceHistoryListBean {
	HashMap<String, StockPriceHistoryBean> stockPriceHistoryMap = new HashMap<String, StockPriceHistoryBean>();
	Date historyFromDate = null;
	Date historyToDate = null;
	DaysMovingAverageBean movingAverage = null;

	public StockPriceHistoryListBean() {
		Calendar cal = new GregorianCalendar();

		historyToDate = cal.getTime();
		cal.add(Calendar.YEAR, -1);
		historyFromDate = cal.getTime();
	}

	public void update(List<String> stockIds, DaysMovingAverageBean newMovingAvg) {

		// remove stocks from the map that are not in the stocksIds list
		removeOldStocksFromList(stockIds);

		// add new stocks to the map that we will track
		List<String> stocksToAddToMap = addNewStocksToList(stockIds);

		// get historical prices for the new stocks being tracked
		for (String tickerId : stocksToAddToMap) {
			this.getHistoricalMovingAverage(tickerId);
		}

		if ((newMovingAvg == null)
				|| (newMovingAvg.getId() != movingAverage.getId())) {
			// new number of days to use in moving average.
			// we'll have to re-generate the moving average data
			initMovingAverage(newMovingAvg);
		}

	}

	/**
	 * Remove stocks from stockPriceHistoryMap that we are no longer tracking
	 * 
	 * @param stockIds
	 */
	private void removeOldStocksFromList(List<String> stockIds) {
		Iterator<Entry<String, StockPriceHistoryBean>> it = stockPriceHistoryMap
				.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, StockPriceHistoryBean> pairs = it.next();

			System.out.println(pairs.getKey() + " = " + pairs.getValue());

			// are we still tracking this stock
			boolean stillTracking = false;
			for (String trackStockId : stockIds) {
				if (trackStockId.equals(pairs.getKey())) {
					stillTracking = true;
					break;
				}
			}
			if (!stillTracking) {
				System.out.println("Removing stock from list: "
						+ pairs.getKey() + " - " + pairs.getValue());
				it.remove();
			}
		}
	}

	private List<String> addNewStocksToList(List<String> stockIds) {
		List<String> newStockIdsToTrack = new ArrayList<String>();

		for (String trackStockId : stockIds) {
			boolean alreadyTracking = false;
			if (stockPriceHistoryMap.containsKey(trackStockId)) {
				alreadyTracking = true;
			}

			if (!alreadyTracking) {
				newStockIdsToTrack.add(trackStockId);
			}
		}

		return newStockIdsToTrack;
	}

	private void initMovingAverage(DaysMovingAverageBean newMovingAvg) {
		movingAverage = new DaysMovingAverageBean(newMovingAvg.getId(),
				newMovingAvg.getName());
	}

	private void getHistoricalMovingAverage(String tickerId) {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.YEAR, -1);

		MovingAverages movingAvg = new MovingAverages();
		MovingAverageValues avgValues = new MovingAverageValues();
		movingAvg.updateMovingAverage(new BigDecimal(tickerId), avgValues);

	}

}
