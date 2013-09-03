/**
 * 
 */
package com.coolisland.trackmystocks.sellbuy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.coolisland.trackmystocks.database.AccountBO;
import com.coolisland.trackmystocks.database.AccountDao;
import com.coolisland.trackmystocks.database.StockBO;
import com.coolisland.trackmystocks.database.StockDao;

/**
 * @author Silvio
 * 
 */
public class BuyStock {

	private static final int RETRY_CHOICE = -1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BuyStock sell = new BuyStock();

		sell.run();
	}

	private int chooseAccount() {
		int choice = RETRY_CHOICE;
		AccountDao accountsDao = new AccountDao();

		List<AccountBO> accounts = null;
		try {
			accounts = accountsDao.getPrimaryAccounts();

			if (accounts != null && accounts.size() > 0) {
				HashMap<Integer, String> accountMap = createMapFromAccounts(accounts);

				while (choice == RETRY_CHOICE) {
					System.out.println("Choose the account for which to buy a stock from the list below:");
					printAccountList(accounts);

					choice = getAccountChoice(accountMap);
				}

				System.out.println("User chose: " + choice + " " + accountMap.get(new Integer(choice)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return choice;
	}

	private int chooseStock(int accountId) throws SQLException {
		StockDao stockTickerDao = new StockDao();
		List<StockBO> stockTickers = null;
		int choice = RETRY_CHOICE;

		try {
			stockTickers = stockTickerDao.getUnownedStockTickersForAccount(accountId);

			if (stockTickers != null && stockTickers.size() > 0) {
				HashMap<Integer, String> stockTickerMap = createMapFromTickers(stockTickers);

				while (choice == RETRY_CHOICE) {
					System.out.println("Choose the stock to buy from the list below:");
					printStockTickerList(stockTickers);

					choice = getStockTickerChoice(stockTickerMap);
				}

				System.out.println("User chose: " + choice + " " + stockTickerMap.get(new Integer(choice)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return choice;
	}

	private HashMap<Integer, String> createMapFromAccounts(List<AccountBO> accounts) {
		HashMap<Integer, String> accountIdMap = new HashMap<Integer, String>();

		if (accounts != null) {
			for (AccountBO act : accounts) {
				accountIdMap.put(new Integer(act.getId().intValue()), act.getName());
			}
		}

		return accountIdMap;
	}

	private HashMap<Integer, String> createMapFromTickers(List<StockBO> stockTickers) {
		HashMap<Integer, String> stockTickerIdMap = new HashMap<Integer, String>();

		if (stockTickers != null) {
			for (StockBO stock : stockTickers) {
				stockTickerIdMap.put(new Integer(stock.getId().intValue()), stock.getName());
			}
		}

		return stockTickerIdMap;
	}

	private int getAccountChoice(HashMap<Integer, String> accountMap) {
		int choice = RETRY_CHOICE;
		Scanner scanner = new Scanner(System.in);

		try {
			choice = scanner.nextInt();

			if (!accountMap.containsKey(choice)) {
				System.out.println("Bad choice. Try again");
				choice = RETRY_CHOICE;
			}

			// System.out.println("User entered: " + choice);
		} catch (InputMismatchException e) {
			System.out.println("Invalid Response");
		}

		return choice;
	}

	private int getStockTickerChoice(HashMap<Integer, String> stockMap) {
		int choice = RETRY_CHOICE;
		Scanner scanner = new Scanner(System.in);

		try {
			choice = scanner.nextInt();

			if (!stockMap.containsKey(choice)) {
				System.out.println("Bad choice. Try again");
				choice = RETRY_CHOICE;
			}

			// System.out.println("User entered: " + choice);
		} catch (InputMismatchException e) {
			System.out.println("Invalid Response");
		} finally {
			scanner.close();
		}

		return choice;
	}

	private void printAccountList(List<AccountBO> accounts) {
		for (AccountBO act : accounts) {
			System.out.println("\t" + act.getId() + ": " + act.getName());
		}
	}

	private void printStockTickerList(List<StockBO> stockTickers) {
		for (StockBO ticker : stockTickers) {
			System.out.println("\t" + ticker.getId() + "\t: " + ticker.getSymbol() + "\t " + ticker.getName());
		}
	}

	public void run() {

		try {
			// Which account does the user wish to sell stocks from?
			int accountId = chooseAccount();

			// Which stock from the account does the user wish to sell?
			int stockId = chooseStock(accountId);

			// Mark the stock as being sold
			buyStock(accountId, stockId);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void buyStock(int accountId, int stockId) {
		StockDao stockTickerDao;

		try {
			stockTickerDao = new StockDao();

			stockTickerDao.udateOwnership(accountId, stockId, StockDao.Ownership.BUY);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
