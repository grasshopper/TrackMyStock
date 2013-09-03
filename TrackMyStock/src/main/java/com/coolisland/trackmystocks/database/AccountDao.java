/**
 * 
 */
package com.coolisland.trackmystocks.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Silvio
 * 
 */
public class AccountDao {
	private static final String SELECT_STATMENT = "SELECT * FROM ACCOUNT";
	private static final String SELECT_PRIMARY_STATMENT = "SELECT * FROM ACCOUNT WHERE PARENT_ID IS NULL";
	private final DataBaseManager dbManager = DataBaseManager.getInstance();

	public List<AccountBO> getAllAccounts() throws SQLException {
		List<AccountBO> accounts = new ArrayList<AccountBO>();

		String sql = SELECT_STATMENT;
		ResultSet result = null;

		PreparedStatement pstmt = null;
		pstmt = dbManager.prepareStatement(sql);

		result = pstmt.executeQuery();

		while (result.next()) {
			AccountBO account = new AccountBO(result);
			accounts.add(account);
		}

		return accounts;
	}

	public List<AccountBO> getPrimaryAccounts() throws SQLException {
		List<AccountBO> accounts = new ArrayList<AccountBO>();

		String sql = SELECT_PRIMARY_STATMENT;
		ResultSet result = null;

		PreparedStatement pstmt = null;
		pstmt = dbManager.prepareStatement(sql);

		result = pstmt.executeQuery();

		while (result.next()) {
			AccountBO account = new AccountBO(result);
			accounts.add(account);
		}

		return accounts;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AccountDao accountDao = new AccountDao();

		List<AccountBO> allAccounts;
		try {
			allAccounts = accountDao.getAllAccounts();

			for (AccountBO account : allAccounts) {
				System.out.println(account.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
