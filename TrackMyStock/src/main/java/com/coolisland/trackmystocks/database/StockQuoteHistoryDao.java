package com.coolisland.trackmystocks.database;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class StockQuoteHistoryDao {

	// private static final String SELECT_STATMENT =
	// "SELECT * FROM STOCK_QUOTE_HISTORY";
	private static final String INSERT_STATMENT = "INSERT INTO STOCK_QUOTE_HISTORY ";
	private static final String INSERT_ALL_COLUMNS = "(TICKER_SYMBOL_ID, QUOTE_DATE, LAST_TRADE_AMOUNT, "
			+ "LAST_TRADE_DATE_TIME, CHANGE_AMOUNT, OPEN_AMOUNT, DAY_HIGH_AMOUNT, DAY_LOW_AMOUNT, "
			+ "VOLUME, PREVIOUS_CLOSE, CHANGE_PERCENT, FIFTY_TWO_WEEK_RANGE, EARNING_PER_SHARE, "
			+ "PRICE_PER_EARNINGS, AVERAGE_DAILY_VOLUME) ";

	private static final String SELECT_SIMPLE_200_DAY_AVG = "SELECT SUM(LAST_TRADE_AMOUNT) / 200 "
			+ "FROM (SELECT LAST_TRADE_AMOUNT FROM STOCK_QUOTE_HISTORY WHERE TICKER_SYMBOL_ID = ? "
			+ "ORDER BY last_trade_date_time DESC LIMIT 200) AS SUBQUERY";

	private static final String SELECT_SIMPLE_200_DAY_AVG_FOR_DATE = "SELECT SUM(LAST_TRADE_AMOUNT) / 200 "
			+ "FROM (SELECT LAST_TRADE_AMOUNT FROM STOCK_QUOTE_HISTORY WHERE TICKER_SYMBOL_ID = ? "
			+ "AND last_trade_date_time < ? "
			+ "ORDER BY last_trade_date_time DESC LIMIT 200) AS SUBQUERY";
	
	
	private static final String SELECT_LAST_TRADE_AMOUNT_VARIABLE = "SELECT LAST_TRADE_AMOUNT "
			+ "FROM STOCK_QUOTE_HISTORY WHERE TICKER_SYMBOL_ID = ? ORDER BY last_trade_date_time DESC LIMIT ?";

	// Getting the last quote date
	private static final String SELECT_LAST_QUOTE_STATMENT = "SELECT MAX(QUOTE_DATE) FROM STOCK_QUOTE_HISTORY ";
	private static final String WHERE_LAST_QUOTE_STATEMENT = "WHERE TICKER_SYMBOL_ID = ";

	// Getting the last close date
	private static final String SELECT_LAST_CLOSE_STATMENT = "SELECT MAX(LAST_TRADE_DATE_TIME) FROM STOCK_QUOTE_HISTORY ";
	private static final String WHERE_LAST_CLOSE_STATEMENT = "WHERE TICKER_SYMBOL_ID = ";

	private static String INSERT_ALL_VALUES = "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final DataBaseManager dbManager = DataBaseManager.getInstance();

	public StockQuoteHistoryDao() throws SQLException {
	}

	public boolean addTickerHistory(StockQuoteHistoryBO quote) throws SQLException {
		boolean success = false;
		String sql = INSERT_STATMENT + INSERT_ALL_COLUMNS + INSERT_ALL_VALUES;

		PreparedStatement pstmt = null;
		try {
			pstmt = dbManager.prepareStatement(sql);

			// Set the values
			pstmt.setBigDecimal(1, quote.getTickerSymbolId());
			pstmt.setTimestamp(2, quote.getQuoteSqlDate());
			pstmt.setString(3, quote.getLastTradeAmountAsString());
			pstmt.setTimestamp(4, quote.getLastTradeSqlDateTime());
			pstmt.setString(5, quote.getChangeAmountAsString());
			pstmt.setString(6, quote.getOpenAmountAsString());
			pstmt.setString(7, quote.getDayHighAmountAsString());
			pstmt.setString(8, quote.getDayLowAmountAsString());
			pstmt.setString(9, quote.getVolumeAsString());
			pstmt.setString(10, quote.getPreviousCloseAsString());
			pstmt.setString(11, quote.getChangePercentAsString());
			pstmt.setString(12, quote.getFiftyTwoWeekRange());
			pstmt.setString(13, quote.getEarningsPerShareAsString());
			pstmt.setString(14, quote.getPricePerEarningsAsString());
			pstmt.setString(15, quote.getVolumeAsString());

			success = dbManager.executeInsert(pstmt);
		} catch (MySQLIntegrityConstraintViolationException e) {
			// this is ok
			// System.out.println("duplicate entry... this is ok");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(quote.toString());
			System.out.println(pstmt);

			throw new SQLException(e.getMessage(), e.getCause());
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println(quote.toString());

			throw new NullPointerException(e.getMessage());
		} finally {
			// System.out.println(pstmt.toString());
		}

		return success;
	}

	public Date getLastQuoteDate(BigDecimal tickerId) throws SQLException {
		Date lastDate = null;
		String sql = SELECT_LAST_QUOTE_STATMENT + WHERE_LAST_QUOTE_STATEMENT;
		ResultSet result = null;

		sql += tickerId;

		// System.out.println("SQL statement: " + sql);

		result = dbManager.executeQuery(sql);

		if (result.first()) {
			lastDate = result.getDate(1);
		}

		System.out.println("Last quote date for ticker id " + tickerId + " is: " + lastDate);

		return lastDate;
	}

	public Double get200DaySimpleMovingAverage(BigDecimal tickerId) throws SQLException {
		Double average = null;
		String sql = SELECT_SIMPLE_200_DAY_AVG;
		ResultSet result = null;

		sql += tickerId;

		PreparedStatement pstmt = null;
		pstmt = dbManager.prepareStatement(sql);

		// Set the values
		pstmt.setBigDecimal(1, tickerId);

		// System.out.println("SQL statement: " + pstmt.toString());

		result = pstmt.executeQuery();

		if (result.first()) {
			average = result.getDouble(1);
		}

		return average;
	}
	
	
	/**
	 * Gets the moving average for a specified stock for a specified date 
	 * 
	 * @param tickerId
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public Double get200DaySimpleMovingAverageForDate(BigDecimal tickerId, java.util.Date date) throws SQLException {
		String method = "get200DaySimpleMovingAverageForDate";
		
		Double average = null;
		String sql = SELECT_SIMPLE_200_DAY_AVG_FOR_DATE;
		ResultSet result = null;

		System.out.println("Starting " + method);
		System.out.println("tickerId: " + tickerId);
		System.out.println("date: " + date);
		
		
		PreparedStatement pstmt = null;
		pstmt = dbManager.prepareStatement(sql);

		// Set the values
		pstmt.setBigDecimal(1, tickerId);
		
		Date sqlDate = new Date(date.getTime());
		pstmt.setDate(2, sqlDate);

		System.out.println("SQL statement: " + pstmt.toString());

		result = pstmt.executeQuery();

		System.out.println("result: " + result.toString());

		if (result.first()) {
			average = result.getDouble(1);
		}

		return average;
	}

	
	public List<Double> getClosingPrices(BigDecimal tickerId, int numDays) throws SQLException {
		String sql = SELECT_LAST_TRADE_AMOUNT_VARIABLE;
		ResultSet result = null;
		List<Double> closingPrices = new ArrayList<Double>();

		PreparedStatement pstmt = dbManager.prepareStatement(sql);

		// Set the values
		pstmt.setBigDecimal(1, tickerId);
		pstmt.setInt(2, numDays);

		// System.out.println("SQL statement: " + pstmt.toString());

		result = pstmt.executeQuery();

		while (result.next()) {
			closingPrices.add(result.getDouble(1));
		}

		return closingPrices;
	}

	public Date getLastCloseDate(BigDecimal tickerId) throws SQLException {
		Date lastDate = null;
		String sql = SELECT_LAST_CLOSE_STATMENT + WHERE_LAST_CLOSE_STATEMENT;
		ResultSet result = null;

		sql += tickerId;

		// System.out.println("SQL statement: " + sql);

		result = dbManager.executeQuery(sql);

		if (result.first()) {
			lastDate = result.getDate(1);
		}

		// System.out.println("Last close date for ticker id " + tickerId +
		// " is: " + lastDate);

		return lastDate;
	}
}
