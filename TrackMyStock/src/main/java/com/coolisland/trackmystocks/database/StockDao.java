package com.coolisland.trackmystocks.database;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StockDao {
	public enum Ownership {
		BUY(true), SELL(false);

		private boolean value;

		private Ownership(boolean newValue) {
			value = newValue;
		}

		@Override
		public String toString() {
			switch (this) {
			case BUY:
				System.out.println("Buy");
				break;
			case SELL:
				System.out.println("Sell");
				break;
			}
			return super.toString();
		}

		public boolean getBoolean() {
			return value;
		}
	};

	private static final String SELECT_TICKER_STMT = "SELECT T.ID, T.SYMBOL, T.NAME, T.EXCHANGE, T.TRACK, T.OWN, A.NAME AS ACCOUNT, T.TICKER_TYPE_ID "
			+ "FROM TICKER T INNER JOIN ACCOUNT A ON A.ID = T.ACCOUNT_ID ";

	// private static final String SELECT_SIMPLE_TICKERS_STMT =
	// "SELECT T.ID, T.SYMBOL, T.NAME, T.EXCHANGE, T.TRACK, T.OWN, T.TICKER_TYPE_ID, T.ACCOUNT_ID "
	// + "FROM TICKER T ";

	private static final String UPDATE_TICKERS_STMT = "UPDATE TICKER ";

	private static final String UPDATE_OWNERSHIP = "SET OWN = ? ";

	private static final String UPDATE_BY_ACCOUNT_AND_STOCK = "WHERE ACCOUNT_ID = ? AND ID = ? ";

	private static final String WHERE_ACCOUNT = "WHERE T.OWN = TRUE AND T.ACCOUNT_ID = ? ";

	private static final String WHERE_NOT_OWNED_BY_ACCOUNT = "WHERE T.OWN = FALSE AND T.ACCOUNT_ID = ? ";

	private static final String WHERE_TICKER_TYPE = "WHERE TICKER_TYPE_ID = ? ";

	private static final String WHERE_NOT_TICKER_TYPE = "WHERE TICKER_TYPE_ID = ? ";

	private static final String WHERE_TRACK_TICKER = "WHERE TRACK = TRUE ";

	private static final String WHERE_SYMBOL = "WHERE SYMBOL = ";

	private static final String ORDER_BY_OWN_ACCOUNT = "ORDER BY OWN DESC, ACCOUNT_ID";

	private static final String ORDER_BY_TICKER_ID = "ORDER BY T.ID ASC";

	private static final String SELECT_TICKERS = "SELECT * FROM TICKERS ORDER BY OWN DESC, ACCOUNT";

	private static final String SELECT_TICKERS_TO_TRACK = SELECT_TICKER_STMT + WHERE_TRACK_TICKER
			+ ORDER_BY_OWN_ACCOUNT;

	private static final String SELECT_TICKER_FOR_SYMBOL = SELECT_TICKER_STMT + WHERE_SYMBOL;

	private static final String SELECT_FOR_ACCOUNT = SELECT_TICKER_STMT + WHERE_ACCOUNT + ORDER_BY_TICKER_ID;

	private static final String SELECT_UNOWNED_TICKERS_FOR_ACCOUNT = SELECT_TICKER_STMT + WHERE_NOT_OWNED_BY_ACCOUNT
			+ ORDER_BY_TICKER_ID;

	private static final int INDEX_TICKER_TYPE = 3;

	private final DataBaseManager dbManager = DataBaseManager.getInstance();

	public StockDao() throws SQLException {
	}

	public List<StockBO> getStockTickers() throws SQLException {
		List<StockBO> tickerList = new ArrayList<StockBO>();

		try {
			ResultSet rs = DataBaseManager.getInstance().executeQuery(SELECT_TICKERS);

			if (rs != null) {
				// Fetch each row from the result set
				while (rs.next()) {
					StockBO tickerBo = new StockBO(rs);
					tickerList.add(tickerBo);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		}

		return tickerList;
	}

	public List<StockBO> getAllNonIndexStockTickers() throws SQLException {
		List<StockBO> tickerList = new ArrayList<StockBO>();

		try {
			ResultSet result = null;
			PreparedStatement pstmt = null;

			String sql = SELECT_TICKER_STMT + WHERE_NOT_TICKER_TYPE;

			pstmt = dbManager.prepareStatement(sql);

			// Set the values
			pstmt.setInt(1, INDEX_TICKER_TYPE);

			System.out.println("SQL statement: " + pstmt.toString());

			result = pstmt.executeQuery();

			if (result != null) {
				// Fetch each row from the result set
				while (result.next()) {
					StockBO tickerBo = new StockBO(result);
					tickerList.add(tickerBo);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		}

		return tickerList;
	}

	public List<StockBO> getIndexStockTickers() throws SQLException {
		List<StockBO> tickerList = new ArrayList<StockBO>();

		try {
			ResultSet result = null;
			PreparedStatement pstmt = null;

			String sql = SELECT_TICKER_STMT + WHERE_TICKER_TYPE;

			pstmt = dbManager.prepareStatement(sql);

			// Set the values
			pstmt.setInt(1, INDEX_TICKER_TYPE);

			System.out.println("SQL statement: " + pstmt.toString());

			result = pstmt.executeQuery();

			if (result != null) {
				// Fetch each row from the result set
				while (result.next()) {
					StockBO tickerBo = new StockBO(result);
					tickerList.add(tickerBo);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		}

		return tickerList;
	}

	public StockBO getStockTicker(String tickerSymbol) throws SQLException {
		StockBO tickerBo = null;

		try {
			String query = SELECT_TICKER_FOR_SYMBOL + "'" + tickerSymbol + "'";
			ResultSet rs = DataBaseManager.getInstance().executeQuery(query);

			if (rs != null) {
				if (rs.first()) {
					tickerBo = new StockBO(rs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		}

		return tickerBo;
	}

	public List<StockBO> getStockTickersToTrack() throws SQLException {
		List<StockBO> tickerList = new ArrayList<StockBO>();

		try {
			ResultSet rs = DataBaseManager.getInstance().executeQuery(SELECT_TICKERS_TO_TRACK);

			if (rs != null) {
				// Fetch each row from the result set
				while (rs.next()) {
					StockBO tickerBo = new StockBO(rs);
					tickerList.add(tickerBo);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		}

		return tickerList;
	}

	public List<StockBO> getOwnedStockTickersForAccount(int accountId) throws SQLException {
		List<StockBO> tickerList = new ArrayList<StockBO>();
		ResultSet result = null;
		PreparedStatement pstmt = null;

		try {
			String sql = SELECT_FOR_ACCOUNT;

			pstmt = dbManager.prepareStatement(sql);

			// Set the values
			pstmt.setInt(1, accountId);

			System.out.println("SQL statement: " + pstmt.toString());

			result = pstmt.executeQuery();

			if (result != null) {
				// Fetch each row from the result set
				while (result.next()) {
					StockBO tickerBo = new StockBO(result);
					tickerList.add(tickerBo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		}

		return tickerList;
	}

	public List<StockBO> getUnownedStockTickersForAccount(int accountId) throws SQLException {
		List<StockBO> tickerList = new ArrayList<StockBO>();
		ResultSet result = null;
		PreparedStatement pstmt = null;

		try {
			String sql = SELECT_UNOWNED_TICKERS_FOR_ACCOUNT;

			pstmt = dbManager.prepareStatement(sql);

			// Set the values
			pstmt.setInt(1, accountId);

			System.out.println("SQL statement: " + pstmt.toString());

			result = pstmt.executeQuery();

			if (result != null) {
				// Fetch each row from the result set
				while (result.next()) {
					StockBO tickerBo = new StockBO(result);
					tickerList.add(tickerBo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		}

		return tickerList;
	}

	public void udateOwnership(int accountId, int stockId, Ownership buySell) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			String sql = UPDATE_TICKERS_STMT + UPDATE_OWNERSHIP + UPDATE_BY_ACCOUNT_AND_STOCK;

			pstmt = dbManager.prepareStatement(sql);

			// Set the values
			pstmt.setBoolean(1, buySell.getBoolean());
			pstmt.setBigDecimal(2, new BigDecimal(accountId));
			pstmt.setBigDecimal(3, new BigDecimal(stockId));

			dbManager.executeUpdate(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		}
	}

}
