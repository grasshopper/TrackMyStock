package com.coolisland.trackmystocks.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MySqlTest {

	public static void simpleTest() throws SQLException {
		java.sql.Connection conn = null;

		try {
			String userName = "test";
			String password = "test";
			String url = "jdbc:mysql://localhost/test";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Database connection established");

			java.sql.Statement s = conn.createStatement();
			int count;
			// s.executeUpdate("DROP TABLE IF EXISTS animal");
			// s.executeUpdate("CREATE TABLE animal (" +
			// "id INT UNSIGNED NOT NULL AUTO_INCREMENT," + "PRIMARY KEY (id),"
			// + "name CHAR(40), category CHAR(40))");
			// count = s.executeUpdate("INSERT INTO animal (name, category)" +
			// " VALUES" + "('snake', 'reptile'),"
			// + "('frog', 'amphibian')," + "('tuna', 'fish')," +
			// "('racoon', 'mammal')");
			count = s.executeUpdate("SELECT * FROM animal");
			s.close();
			System.out.println(count + " rows were inserted");

		} catch (Exception e) {
			System.err.println("Cannot connect to database server");
		} finally {
			if (conn != null) {
				try {
					conn.close();
					System.out.println("Database connection terminated");
				} catch (Exception e) { /* ignore close errors */
				}
			}
		}

		DataBaseManager db = DataBaseManager.getInstance();
		db.getConnection();
	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		TickersDao tickers = null;
//		try {
//			tickers = new TickersDao();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		List<TickerBO> tickerList = null;
//		try {
//			// tickerList = tickers.getStockTickers();
//			tickerList = tickers.getAllNonIndexStockTickers();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("Number of Tickers found: " + tickerList.size());
//
//		for (TickerBO ticker : tickerList) {
//			System.out.println(ticker);
//
//			if (ticker.getTrack()) {
//				StockQuote quote = new StockQuote();
//				QuoteData quoteData = quote.getQuote(ticker.getSymbol());
//
//				// add quote data to the history
//				try {
//					StockQuoteHistoryDao quoteHistoryDao;
//
//					StockQuoteHistoryBO quoteHistoryBo = new StockQuoteHistoryBO(quoteData, ticker.getId());
//
//					quoteHistoryDao = new StockQuoteHistoryDao();
//
//					quoteHistoryDao.addTickerHistory(quoteHistoryBo);
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				System.out.println(quoteData.toString());
//			}
//		}
//	}

}
