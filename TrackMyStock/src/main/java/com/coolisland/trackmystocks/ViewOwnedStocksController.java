package com.coolisland.trackmystocks;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.coolisland.trackmystocks.database.AccountBO;
import com.coolisland.trackmystocks.database.AccountDao;
import com.coolisland.trackmystocks.utils.ControllerUtils;

/**
 * Handles requests for the application to display list of owned stocks
 */
@Controller
@SessionAttributes("menuState")
public class ViewOwnedStocksController {
	private static final Logger logger = LoggerFactory.getLogger(ViewOwnedStocksController.class);
	private static final String JSP_PAGE_NAME = "viewOwnedStocks";
	private static final String JSP_PAGE_NAME_VIEW_ACCOUNT_STOCKS = "viewAccountStocks";
	
	/**
	 * Simply selects the view to render by returning its name.
	 */
	@RequestMapping(value = "/viewOwnedStocks", method = RequestMethod.GET)
	public String buyStock(Locale locale, Model model) {
		String method = "buyStock";
		logger.info("View Owned Stocks Page. The client locale is {}.", locale);
		System.out.println("Starting " + method);
		ControllerUtils.logModelAttributes(model);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
		return JSP_PAGE_NAME;
	}
	
	
//	/**
//	 * Display stocks owned by the selected account
//	 */
//	@RequestMapping(value = "/viewAccountStocks", method = RequestMethod.GET)
//	public String viewAccountStocks(Locale locale, Model model) {
//		logger.info("View Stocks Owned by Account Page");
//		logger.info("Client locale: " + locale);
//		logger.info("Model: " + model.toString());
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
//		
//		return JSP_PAGE_NAME_VIEW_ACCOUNT_STOCKS;
//	}
	
}
