package com.coolisland.trackmystocks.stockquotes;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coolisland.trackmystocks.database.StockQuoteHistoryDao;
import com.coolisland.trackmystocks.database.StockBO;
import com.coolisland.trackmystocks.database.StockDao;
import com.coolisland.trackmystocks.utils.StringUtils;

public class MovingAverages {
	private static final int ROWS_PER_PAGE = 15;
	private int rowsPrinted = 0;
	private static final int NUMBER_OF_DAYS_BACK = 100;

	private static final int DECIMAL_PT_DIGITS = 2;
	private static final int DATE_LEN = 10;

	private static final int OWNERSHIP_COL_LEN = 5;
	private static final String OWNERSHIP_FORMAT = "%-" + OWNERSHIP_COL_LEN
			+ "s ";

	private static final int ACCOUNT_COL_LEN = 28;
	private static final String ACCOUNT_FORMAT = "%-" + ACCOUNT_COL_LEN + "s ";

	private static final int STOCK_NAME_COL_LEN = 70;
	private static final String STOCK_NAME_FORMAT = "%-" + STOCK_NAME_COL_LEN
			+ "s ";

	private static final int STOCK_SYMBOL_COL_LEN = 6;
	private static final String STOCK_SYMBOL_FORMAT = " %-"
			+ STOCK_SYMBOL_COL_LEN + "s ";

	private static final int MOVING_AVG_LABEL_COL_LEN = 4;
	private static final String MOVING_AVG_DAY_LABEL_FORMAT = "%-"
			+ MOVING_AVG_LABEL_COL_LEN + "s ";

	private static final int WHOLE_NUM_LEN = 6;
	private static final String MOVING_AVG_200_DAY_FORMAT = "%" + WHOLE_NUM_LEN
			+ "." + DECIMAL_PT_DIGITS + "f ";
	private static final int MOVING_AVG_COL_LEN = MOVING_AVG_LABEL_COL_LEN
			+ WHOLE_NUM_LEN + 1;
	private static final String MOVING_AVG_HEADER_FORMAT = "%-"
			+ MOVING_AVG_COL_LEN + "." + MOVING_AVG_COL_LEN + "s ";

	private static final String CLOSE_PRICE_FORMAT = " %" + WHOLE_NUM_LEN + "."
			+ DECIMAL_PT_DIGITS + "f ";
	private static final int CLOSE_PRICE_COL_LEN = WHOLE_NUM_LEN + 1;
	private static final String CLOSE_PRICE_HEADER_FORMAT = "%-"
			+ CLOSE_PRICE_COL_LEN + "s ";

	private static final String CLOSE_DATE_FORMAT = " %" + DATE_LEN + "s";
	private static final int CLOSE_DATE_COL_LEN = DATE_LEN + 1;
	private static final String CLOSE_DATE_HEADER_FORMAT = "%-"
			+ CLOSE_DATE_COL_LEN + "s ";

	private static final int POSITION_COL_LEN = 8;
	private static final String POSITION_MSG_FORMAT = " %" + POSITION_COL_LEN
			+ "s ";
	private static final String POSITION_MSG_HEADER_FORMAT = " %-"
			+ POSITION_COL_LEN + "s ";

	private static final int EXPLANATION_COL_LEN = 18;
	private static final String EXPLANATION_MSG_FORMAT = " %"
			+ EXPLANATION_COL_LEN + "s ";
	private static final String EXPLANATION_MSG_HEADER_LABEL_FORMAT = " %-"
			+ EXPLANATION_COL_LEN + "s ";

	private static final int MOVING_AVG_DIRECTION_COL_LEN = NUMBER_OF_DAYS_BACK;
	private static final String MOVING_AVG_DIRECTION_FORMAT = " %"
			+ MOVING_AVG_DIRECTION_COL_LEN + "s ";
	private static final String MOVING_AVG_DIRECTION_HEADER_FORMAT = " %-"
			+ MOVING_AVG_DIRECTION_COL_LEN + "s ";

	private static final String MOVING_AVG_FORMAT = OWNERSHIP_FORMAT
			+ ACCOUNT_FORMAT + STOCK_NAME_FORMAT + STOCK_SYMBOL_FORMAT
			+ MOVING_AVG_DAY_LABEL_FORMAT + MOVING_AVG_200_DAY_FORMAT
			+ CLOSE_PRICE_FORMAT + CLOSE_DATE_FORMAT + POSITION_MSG_FORMAT
			+ EXPLANATION_MSG_FORMAT + MOVING_AVG_DIRECTION_FORMAT;

	private static final String HEADER_FORMAT = OWNERSHIP_FORMAT
			+ ACCOUNT_FORMAT + STOCK_NAME_FORMAT + STOCK_SYMBOL_FORMAT
			+ MOVING_AVG_HEADER_FORMAT + CLOSE_PRICE_HEADER_FORMAT
			+ CLOSE_DATE_HEADER_FORMAT + POSITION_MSG_HEADER_FORMAT
			+ EXPLANATION_MSG_HEADER_LABEL_FORMAT
			+ MOVING_AVG_DIRECTION_HEADER_FORMAT;

	private static final String MOVING_AVG_200_DAY_NO_INFO_LABEL_FORMAT = "%-80s ";
	private static final String MOVING_AVG_NO_INFO_FORMAT = OWNERSHIP_FORMAT
			+ ACCOUNT_FORMAT + STOCK_NAME_FORMAT + STOCK_SYMBOL_FORMAT
			+ MOVING_AVG_200_DAY_NO_INFO_LABEL_FORMAT;

	/**
	 * 
	 */
	private void printHeading() {

		final String colSeperatorChar = "_";
		final String ownership = "Own";
		final String account = "Account";
		final String stockName = "Stock / ETF Name";
		final String stockSymbol = "Ticker";
		final String movingAverage = "Average";
		final String closePrice = "Close";
		final String closeDate = "Date";
		final String movingAveragePosition = "Position";
		final String explanation = "Explanation";
		final String movingAverageHistory = "Moving Average History";

		System.out.println();
		String output = String.format(HEADER_FORMAT, ownership, account,
				stockName, stockSymbol, movingAverage, closePrice, closeDate,
				movingAveragePosition, explanation, movingAverageHistory);
		System.out.println(output);

		final String accountColSeperator = String.format(
				"%1$-" + ACCOUNT_COL_LEN + "s", " ").replace(" ",
				colSeperatorChar);
		final String ownershipColSeperator = String.format(
				"%1$-" + OWNERSHIP_COL_LEN + "s", " ").replace(" ",
				colSeperatorChar);
		final String stockNameColSeperator = String.format(
				"%1$-" + STOCK_NAME_COL_LEN + "s", " ").replace(" ",
				colSeperatorChar);
		final String stockTickerColSeperator = String.format(
				"%1$-" + STOCK_SYMBOL_COL_LEN + "s", " ").replace(" ",
				colSeperatorChar);
		final String movingAverageColSeperator = String.format(
				"%1$-" + MOVING_AVG_COL_LEN + "s", " ").replace(" ",
				colSeperatorChar);
		final String closePriceColSeperator = String.format(
				"%1$-" + CLOSE_PRICE_COL_LEN + "s", " ").replace(" ",
				colSeperatorChar);
		final String closeDateColSeperator = String.format(
				"%1$-" + CLOSE_DATE_COL_LEN + "s", " ").replace(" ",
				colSeperatorChar);
		final String recommendationColSeperator = String.format(
				"%1$-" + POSITION_COL_LEN + "s", " ").replace(" ",
				colSeperatorChar);
		final String explanationColSeperator = String.format(
				"%1$-" + EXPLANATION_COL_LEN + "s", " ").replace(" ",
				colSeperatorChar);
		final String movingAverageHistoryColSeperator = String.format(
				"%1$-" + MOVING_AVG_DIRECTION_COL_LEN + "s", " ").replace(" ",
				colSeperatorChar);

		output = String.format(HEADER_FORMAT, ownershipColSeperator,
				accountColSeperator, stockNameColSeperator,
				stockTickerColSeperator, movingAverageColSeperator,
				closePriceColSeperator, closeDateColSeperator,
				recommendationColSeperator, explanationColSeperator,
				movingAverageHistoryColSeperator);
		System.out.println(output);
	}

	public void updateMovingAverage(BigDecimal tickerId,
			MovingAverageValues avgValues) {
		
		List<Double> prices = new ArrayList<Double>();
		StockQuoteHistoryDao historyDao;
		int days = 200;

		try {
			historyDao = new StockQuoteHistoryDao();

			prices = historyDao.getClosingPrices(tickerId, days);

			if (prices.size() > 0) {
				double movingAverage200 = 0;
				for (Double price : prices) {
					movingAverage200 += price.doubleValue();
				}
				movingAverage200 /= days;

				avgValues.setMovingAverage(movingAverage200);
				avgValues.setClosePrice(prices.get(0));

				avgValues.simpleMovingAverageDirectionEval();

				if (avgValues.getClosePrice() < movingAverage200) {
					avgValues.setMovingAverageRecommendation("SELL!");
				} else if (avgValues.getClosePrice() > movingAverage200) {
					avgValues.setMovingAverageRecommendation("Above");
				} else {
					avgValues.setMovingAverageRecommendation("CROSSING!");
				}

				avgValues.setDataFound(true);
			} else {
				avgValues.setDataFound(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Double average(List<Double> prices, int from, int to) {
		Double average = null;
		double tempAverage = 0;
		int numDays = to - from;

		if (prices.size() < to || prices.size() < from || numDays < 0) {
			return average;
		}

		for (int ndx = from; ndx < to; ndx++) {
			tempAverage += prices.get(ndx);
		}

		tempAverage /= numDays;

		return average = new Double(tempAverage);
	}

	/**
	 * 
	 * @param tickerId
	 * @param stockSymbol
	 * @param stockName
	 * @param numberDays
	 * @return
	 */
	private List<Double> getMovingAverageHistory(BigDecimal tickerId,
			int numberDaysBack) {
		numberDaysBack++;
		List<Double> prices = new ArrayList<Double>();
		StockQuoteHistoryDao historyDao;
		int movingAvgDays = 200;
		int days = movingAvgDays + numberDaysBack;
		List<Double> averages = new ArrayList<Double>();

		try {
			historyDao = new StockQuoteHistoryDao();

			prices = historyDao.getClosingPrices(tickerId, days);

			for (int day = 0; day < numberDaysBack; day++) {
				Double movingAvg = average(prices, day, day + 200);
				averages.add(movingAvg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return averages;
	}

	private Date getLastClosePrice(BigDecimal tickerId) {
		StockQuoteHistoryDao historyDao;
		Date closeDate = null;

		try {
			historyDao = new StockQuoteHistoryDao();

			closeDate = historyDao.getLastCloseDate(tickerId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return closeDate;
	}

	/**
	 * 
	 * @param averages
	 */
	private String getMovingAverageHistorySummation(List<Double> averages) {
		StringBuffer summationOut = new StringBuffer();
		StringBuffer differenceOut = new StringBuffer();

		if (averages == null || averages.size() < 1 || averages.get(0) == null) {
			return summationOut.toString();
		}

		// get first price
		double previousAvg = averages.get(0);

		for (int ndx = 1; ndx < averages.size(); ndx++) {
			double current = 0;

			if (averages.get(ndx) == null) {
				summationOut.append(" ");
			} else {
				current = averages.get(ndx);
			}

			// get the difference between the current and the previous price
			double diff = previousAvg - current;
			String numFormat = "%" + WHOLE_NUM_LEN + "." + DECIMAL_PT_DIGITS
					+ "f ";
			String diffStr = String.format(numFormat, diff);
			double diffDouble = new Double(diffStr).doubleValue();

			if (diffDouble > 0) {
				summationOut.append(MovingAverageValues.Direction.UP);
			} else if (diffDouble < 0) {
				summationOut.append(MovingAverageValues.Direction.DOWN);
			} else {
				summationOut.append(MovingAverageValues.Direction.NO_CHANGE);
			}

			/*
			 * if (current < previousAvg) {
			 * summationOut.append(MovingAverageValues.Direction.UP); } else if
			 * (current > previousAvg) {
			 * summationOut.append(MovingAverageValues.Direction.DOWN); } else {
			 * summationOut.append(MovingAverageValues.Direction.NO_CHANGE); }
			 */

			differenceOut.append(String.format(numFormat, diffDouble));
		}

		// summationOut.append("\n");
		// summationOut.append(differenceOut.toString());

		return summationOut.toString();
	}

	/**
	 * 
	 */
	public void getAll200DayAverages() {
		try {
			StockDao tickers = new StockDao();

			List<StockBO> listTickerBo = tickers.getStockTickersToTrack();

			rowsPrinted = ROWS_PER_PAGE;
			for (StockBO tickerBo : listTickerBo) {
				// Print Column Header?
				if (rowsPrinted >= ROWS_PER_PAGE) {
					printHeading();
					rowsPrinted = 0;
				}
				rowsPrinted++;

				MovingAverageValues values = new MovingAverageValues();
				values.setStockName(tickerBo.getName());
				values.setTickerSymbol(tickerBo.getSymbol());

				List<Double> movingAverageHistory = getMovingAverageHistory(
						tickerBo.getId(), NUMBER_OF_DAYS_BACK);
				String historyStr = getMovingAverageHistorySummation(movingAverageHistory);
				values.setMovingAverageHistory(historyStr);

				updateMovingAverage(tickerBo.getId(), values);

				Date closePrice = getLastClosePrice(tickerBo.getId());

				String output;
				String own = tickerBo.getOwn() ? "Yes" : "No";

				if (values.isDataFound()) {
					String stockName = values.getStockName();

					if (stockName.length() > 60) {
						stockName = StringUtils.truncateString(stockName,
								STOCK_NAME_COL_LEN);
					}

					String account = tickerBo.getAccount();
					account = StringUtils.truncateString(account,
							ACCOUNT_COL_LEN);

					output = String.format(MOVING_AVG_FORMAT, own, account,
							stockName, values.getTickerSymbol(), "200",
							values.getMovingAverage(), values.getClosePrice(),
							closePrice.toString(),
							values.getMovingAverageRecommendation(),
							values.getDirection(),
							values.getMovingAverageHistory());
				} else {
					String stockName = values.getStockName();
					stockName = StringUtils.truncateString(stockName,
							STOCK_NAME_COL_LEN);

					String account = tickerBo.getAccount();
					account = StringUtils.truncateString(account,
							ACCOUNT_COL_LEN);

					output = String.format(MOVING_AVG_NO_INFO_FORMAT, own,
							account, stockName, values.getTickerSymbol(),
							"No prices found!");
				}

				System.out.println(output);
				System.out
						.println("http://finance.yahoo.com/q/ta?s=SPZ12.CME+Basic+Tech.+Analysis");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MovingAverages averages = new MovingAverages();

		averages.getAll200DayAverages();
	}

}
