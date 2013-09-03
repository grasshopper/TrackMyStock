package com.coolisland.trackmystocks.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;


public class DataBaseManager {
	private static class DataBaseManagerSingleton {
		public static final DataBaseManager instance = new DataBaseManager();
	}

	private static java.sql.Connection conn = null;
	private final static String password = "passw0rd";
	private final static String url = "jdbc:mysql://localhost/stocks";

	private final static String userName = "stock_app";

	public static DataBaseManager getInstance() {
		return DataBaseManagerSingleton.instance;
	}

	private DataBaseManager() {
		try {
			conn = getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Database connection terminated");
			} catch (Exception e) { /* ignore close errors */
			}
		}
	}

	public boolean executeInsert(PreparedStatement statement) throws SQLException {
		boolean success = false;

		success = statement.execute();
		conn.commit();

		return success;
	}

	public int executeInsert(String sql) throws SQLException {
		int rowsInserted = 0;

		try {
			Statement s = conn.createStatement();

			rowsInserted = s.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("SQL: " + sql);

			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		} finally {
			conn.commit();
		}

		return rowsInserted;
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		ResultSet resultSet = null;

		try {
			Statement s = conn.createStatement();

			resultSet = s.executeQuery(sql);

			if (resultSet == null) {
				System.out.println("SQL statement: " + sql);
				System.out.println("No Results Found");
			}

		} catch (SQLException e) {
			System.out.println("SQL statement: " + sql);
			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		} finally {
			conn.commit();
		}

		return resultSet;
	}

	/**
	 * 
	 * @param pstmt
	 * @return number of rows updated
	 * @throws SQLException
	 */
	public int executeUpdate(PreparedStatement pstmt) throws SQLException {
		int rowsUpdated = -1;

		try {
			int result = pstmt.executeUpdate();
			System.out.println("result: " + result);

			ResultSet resultSet = pstmt.getResultSet();
			if (resultSet != null) {
				System.out.println("ResultSet: " + resultSet);
			}

			rowsUpdated = pstmt.getUpdateCount();
			System.out.println("getUpdateCount(): " + rowsUpdated);

			SQLWarning warnings = pstmt.getWarnings();
			if (warnings != null) {
				System.out.println("Warnings: " + warnings);
			}
		} catch (SQLException e) {
			System.out.println("Error update record(s).");
			System.out.println("SQL: " + pstmt.toString());

			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		} finally {
			conn.commit();
		}

		return rowsUpdated;
	}

	public Connection getConnection() throws SQLException {
		if (conn == null) {
			openConnection();
		}

		// is the connection alive
		if (!isAlive()) {
			openConnection();
		}

		return conn;
	}

	private boolean isAlive() {
		final String sql = "SELECT 1";
		Statement s;
		boolean alive = false;

		if (conn == null) {
			return alive;
		}

		try {
			s = conn.createStatement();
			ResultSet resultSet = s.executeQuery(sql);

			if (resultSet != null) {
				alive = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alive = false;
		}

		return alive;
	}

	private void openConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			// System.out.println("Database connection established");

			isAlive();

			conn.setAutoCommit(false);
		} catch (Exception e) {
			System.err.println("Cannot connect to database server");
			throw new SQLException(e.getMessage(), e.getCause());
		}
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		PreparedStatement preparedStmt = null;

		try {
			preparedStmt = conn.prepareStatement(sql);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		}

		return preparedStmt;
	}
}
