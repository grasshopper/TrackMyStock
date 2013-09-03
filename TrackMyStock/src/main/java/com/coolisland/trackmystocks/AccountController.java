package com.coolisland.trackmystocks;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.coolisland.trackmystocks.beans.MovingAverageSelectionListBean;
import com.coolisland.trackmystocks.beans.StockPriceHistoryListBean;
import com.coolisland.trackmystocks.beans.TickerBean;
import com.coolisland.trackmystocks.database.AccountBO;
import com.coolisland.trackmystocks.database.AccountDao;
import com.coolisland.trackmystocks.database.StockBO;
import com.coolisland.trackmystocks.database.StockDao;
import com.coolisland.trackmystocks.stockquotes.MovingAverages;
import com.coolisland.trackmystocks.utils.ControllerUtils;
import com.coolisland.trackmystocks.utils.MovingAveragePeriod;

/**
 * Handles Account requests for the application
 */
@Controller
@SessionAttributes("menuState")
public class AccountController {
	private static final Logger logger = LoggerFactory.getLogger(BuyStockController.class);
	
	// account selection page
	private static final String JSP_PAGE_NAME_SELECT_ACCOUNT = "selectAccount";
	private static final String PRIMARY_ACCOUNT_ATTRIBUTE = "PrimaryAccounts";
	
	// stock selection page
	private StockPriceHistoryListBean stocksToGraph = new StockPriceHistoryListBean(); 
	private static final String JSP_PAGE_NAME_ACCOUNT_STOCKS = "viewAccountStocks";
	private static final String ACCOUNT_OWNED_STOCK_ATTRIBUTE = "AccountOwnedStocks";

	// stocks moving average page
	private static MovingAverageSelectionListBean maSelectionOptions = new MovingAverageSelectionListBean();
	private static MovingAveragePeriod movingAverageOptions = MovingAveragePeriod.DAYS_200;
	private static final String JSP_PAGE_NAME_STOCKS_MOVING_AVERAGE = "viewStocksMovingAverage";
	private static final String MOVING_AVERAGE_SELECTION_ATTRIBUTE = "MovingAverageOptions";
	private static final String MOVING_DAY_AVERAGE_ATTRIBUTE = "MovingDayAverageOption";


	
	/**
	 * Simply selects the view to render by returning its name.
	 */
	@RequestMapping(value = "/selectAccount", method = RequestMethod.GET)
	public String selectAccount(Locale locale, Model model) {
		String method = "selectAccount";
		logger.info("Select Account Page. The client locale is {}.", locale);
		System.out.println("Starting " + method);
		
		ControllerUtils.logModelAttributes(model);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		AccountDao dao = new AccountDao();
		List<AccountBO> accounts = new ArrayList<AccountBO>();
		try {
			accounts = dao.getPrimaryAccounts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute(PRIMARY_ACCOUNT_ATTRIBUTE, accounts);

		MenuState menuState = MenuState.ACCOUNT;
		model.addAttribute("menuState", menuState);
		System.out.println("menuState: " + menuState);
		

		return JSP_PAGE_NAME_SELECT_ACCOUNT;
	}
	
	
	@RequestMapping(value = "/viewAccountStocks.html", method = RequestMethod.POST)
	public String viewAccountStocks(@RequestParam("accountId") int accountId, Model model) {
		final String method = "viewAccountStocks";
		
		System.out.println("Starting " + method);
		System.out.println("accountId: " + accountId);
		ControllerUtils.logModelAttributes(model);
		
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.US);
		Date date = new Date();
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		StockDao dao;
		List<StockBO> tickersBo = new ArrayList<StockBO>();
		try {
			dao = new StockDao();
			tickersBo = dao.getOwnedStockTickersForAccount(accountId);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
		List<TickerBean> tickersBean = new ArrayList<TickerBean>(tickersBo.size());
		for (StockBO bo : tickersBo) {
			TickerBean bean = new TickerBean(bo);
			tickersBean.add(bean);
		}
		
		model.addAttribute(ACCOUNT_OWNED_STOCK_ATTRIBUTE, tickersBean);
		model.addAttribute(tickersBean);

		// moving average options and selected option
		model.addAttribute(MOVING_AVERAGE_SELECTION_ATTRIBUTE, maSelectionOptions);
		System.out.println(">>>>>!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("maSelectionOptions: " + maSelectionOptions);
		System.out.println("maSelectionOptions selected average: " + maSelectionOptions.getSelectedAverage());
		System.out.println("maSelectionOptions options: " + maSelectionOptions.getMovingAverageOptions().toString());
		System.out.println("<<<<<!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		model.addAttribute(MOVING_DAY_AVERAGE_ATTRIBUTE, movingAverageOptions);
		System.out.println("movingAverageOptions selectedAverage: " + movingAverageOptions);

		return JSP_PAGE_NAME_ACCOUNT_STOCKS;
	}
	
	/**
	 * Responsible for displaying stock graphs with moving average
	 * 
	 * @param stockIds
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewStocksMovingAverage.html", method = RequestMethod.POST)
	public String viewStocksMovingAverage(@RequestParam("stockIds") List<String> stockIds, 
			Model model) {
		final String method = "viewStocksMovingAverage";
		
		System.out.println("Starting " + method);
		System.out.println("stockIds: " + stockIds);
		ControllerUtils.logModelAttributes(model);
		
		
//		stocksToGraph.update(stockIds, movingAverageOptions.get);
		
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.US);
		Date date = new Date();
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		// moving average options and selected option
		model.addAttribute(MOVING_AVERAGE_SELECTION_ATTRIBUTE, maSelectionOptions);
		System.out.println(">>>>>!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("maSelectionOptions: " + maSelectionOptions);
		System.out.println("maSelectionOptions selected average: " + maSelectionOptions.getSelectedAverage());
		System.out.println("maSelectionOptions options: " + maSelectionOptions.getMovingAverageOptions().toString());
		System.out.println("<<<<<!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		// let user choose which moving average they want to view
		model.addAttribute(MOVING_DAY_AVERAGE_ATTRIBUTE, movingAverageOptions);
		System.out.println("movingAverageOptions selectedAverage: " + movingAverageOptions);
		
		// get the moving average
		
		return JSP_PAGE_NAME_STOCKS_MOVING_AVERAGE;
	}

	
	@RequestMapping(value = "/updateStocksMovingAverage.html", method = RequestMethod.POST)
	public String updateStocksMovingAverage(@RequestParam("movingAvgDaysId") int daysId, 								
			Model model) {
		final String method = "updateStocksMovingAverage";
		
		System.out.println("Starting " + method);
		System.out.println("Moving Average Days ID: " + daysId);
		ControllerUtils.logModelAttributes(model);
		
		maSelectionOptions.setSelectedAverage(MovingAveragePeriod.getMovingAveragePeriodById(daysId));
		
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.US);
		Date date = new Date();
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		// let user choose which moving average they want to view
		model.addAttribute(MOVING_AVERAGE_SELECTION_ATTRIBUTE, maSelectionOptions);
		System.out.println("movingAverageOptions selectedAverage: " + maSelectionOptions.getSelectedAverage());
		
		model.addAttribute(MOVING_DAY_AVERAGE_ATTRIBUTE, movingAverageOptions);
		System.out.println("movingAverageOptions selectedAverage: " + movingAverageOptions);
		
		return JSP_PAGE_NAME_STOCKS_MOVING_AVERAGE;
	}
	
}
