package com.coolisland.trackmystocks.yahoo;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import com.coolisland.trackmystocks.database.StockQuoteHistoryBO;
import com.coolisland.trackmystocks.database.StockQuoteHistoryDao;
import com.coolisland.trackmystocks.database.StockBO;
import com.coolisland.trackmystocks.database.StockDao;
import com.coolisland.trackmystocks.utils.StringUtils;

public class PopulateHistoricalPrices {

	private StockQuoteHistoryBO createStockQuoteHistoryBO(String tickerSymbol) throws SQLException {
		StockQuoteHistoryBO historyBo = new StockQuoteHistoryBO();
		StockBO tickerBo = new StockBO();
		StockDao tickerDao = new StockDao();

		tickerBo = tickerDao.getStockTicker(tickerSymbol);
		historyBo.setTickerSymbolId(tickerBo.getId());

		return historyBo;
	}

	/**
	 * 
	 * @param stockTicker
	 * @return
	 * @throws Exception
	 */
	private Calendar getStartDateForTicker(BigDecimal tickerId) throws Exception {

		Calendar nextQuoteDate = null;
		boolean firstTimeQuote = false;

		try {
			Calendar cal = Calendar.getInstance();

			// get the last quote date
			StockQuoteHistoryDao history = new StockQuoteHistoryDao();

			Date lastQuotedDate = history.getLastQuoteDate(tickerId);

			if (lastQuotedDate == null) {
				// never retrieved historical data for this stock before
				cal.set(2010, 01, 01);
				lastQuotedDate = new Date(cal.getTimeInMillis());
				firstTimeQuote = true;
			}

			cal.setTime(lastQuotedDate);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			// cal.set(Calendar.HOUR_OF_DAY, 0);
			// cal.set(Calendar.MINUTE, 0);
			// cal.set(Calendar.SECOND, 0);
			// cal.set(Calendar.MILLISECOND, 0);

			Date sqlTommorow = null;
			if (firstTimeQuote) {
				Calendar today = Calendar.getInstance();
				sqlTommorow  = new Date(today.getTimeInMillis());
			} else {
				cal.setTime(lastQuotedDate);
				cal.add(Calendar.DAY_OF_YEAR, 1);
				// cal.set(Calendar.HOUR_OF_DAY, 0);
				// cal.set(Calendar.MINUTE, 0);
				// cal.set(Calendar.SECOND, 0);
				// cal.set(Calendar.MILLISECOND, 0);

				sqlTommorow = new java.sql.Date(cal.getTimeInMillis());
			}

			// System.out.println("Last quote date: " +
			// lastQuotedDate.toString());
			// System.out.println("Tommorrow: " + sqlTommorow.toString());

			nextQuoteDate = cal;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nextQuoteDate;
	}

	public BigDecimal getStockTickerId(String stockTicker) throws SQLException, Exception {
		// get the the ticker id
		StockDao ticker = new StockDao();

		StockBO tickerBo = ticker.getStockTicker(stockTicker);
		if (tickerBo == null) {
			throw new Exception("Unable to find ticker " + stockTicker);
		}
		BigDecimal tickerId = tickerBo.getId();
		if (tickerId == null) {
			throw new Exception("Unable to find ticker " + stockTicker);
		}
		return tickerId;
	}

	/**
	 * 
	 */
	public void updateAllHistory() {
		try {
			StockDao tickers = new StockDao();

			List<StockBO> listTickerBo = tickers.getStockTickersToTrack();
			for (StockBO tickerBo : listTickerBo) {
				System.out.println("Stock name: " + tickerBo.getName());

				int daysProcessed = populateStockHistory(tickerBo.getId(), tickerBo.getSymbol(), tickerBo.getName());

				System.out.println("\tProcessed " + daysProcessed + " days of data");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int populateStockHistory(BigDecimal tickerId, String tickerSymbol, String stockName) {
		int daysProcessed = 0;

		try {
			Calendar nextQuoteDate;
			nextQuoteDate = getStartDateForTicker(tickerId);

			// we have to look back one day because the last quote date may not
			// have included the close price for that day. For example, if we
			// get the prices before the market closed

			// nextQuoteDate.add(Calendar.DAY_OF_MONTH, -1);

			Calendar today = Calendar.getInstance();

			if (today.after(nextQuoteDate)) {
				// nextQuoteDate.add(Calendar.MONTH, 1);
				// today.add(Calendar.MONTH, +1);

				String fromMonth = StringUtils.intToString(nextQuoteDate.get(Calendar.MONTH), 2);
				String fromDay = StringUtils.intToString(nextQuoteDate.get(Calendar.DAY_OF_MONTH), 2);
				String fromYear = StringUtils.intToString(nextQuoteDate.get(Calendar.YEAR), 4);

				String toMonth = StringUtils.intToString(today.get(Calendar.MONTH), 2);
				String toDay = StringUtils.intToString(today.get(Calendar.DAY_OF_MONTH), 2);
				String toYear = StringUtils.intToString(today.get(Calendar.YEAR), 4);

				// String fromMonth =
				// String.valueOf(nextQuoteDate.get(Calendar.MONTH));
				// String fromDay =
				// String.valueOf(nextQuoteDate.get(Calendar.DAY_OF_MONTH));
				// String fromYear =
				// String.valueOf(nextQuoteDate.get(Calendar.YEAR));
				// String toMonth = String.valueOf(today.get(Calendar.MONTH));
				// String toDay = String.valueOf(today.get(Calendar.DATE));
				// String toYear = String.valueOf(today.get(Calendar.YEAR));

				String filePath = "C:\\temp\\YahooPrices\\" + tickerSymbol + "-" + toYear + toMonth + toDay + ".csv";

				HistoricalPricesFromYahoo prices = new HistoricalPricesFromYahoo();
				prices.saveHistoricalPricesToFile(tickerSymbol, fromMonth, fromDay, fromYear, toMonth, toDay, toYear,
						filePath);
				ParseYahooCsvFileQuotes yahooCsv = new ParseYahooCsvFileQuotes(filePath);

				StockQuoteHistoryBO quoteDataBean = null;

				try {
					// create an initialized history business object
					try {
						quoteDataBean = createStockQuoteHistoryBO(tickerSymbol);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					StockQuoteHistoryDao historyDao = new StockQuoteHistoryDao();

					if (!yahooCsv.hasData()) {
						System.out.println("\t" + "No data found in " + filePath + " to be processed.");
					}

					while (yahooCsv.getNextCsvRow(quoteDataBean)) {
						// System.out.println("quoteDataBean after getNextCsvRow: "
						// + quoteDataBean);

						historyDao.addTickerHistory(quoteDataBean);
						daysProcessed++;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("No updates necessary for " + stockName);
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		return daysProcessed;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		PopulateHistoricalPrices populate = new PopulateHistoricalPrices();

		populate.updateAllHistory();

	}

}
