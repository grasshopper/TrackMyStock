package com.coolisland.trackmystocks;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.coolisland.trackmystocks.utils.ControllerUtils;

/**
 * Handles requests for the application Buy Stock page.
 */
@Controller
@SessionAttributes("menuState")
public class BuyStockController {
	private static final Logger logger = LoggerFactory.getLogger(BuyStockController.class);
	private static final String JSP_PAGE_NAME = "buyStock";
	
	/**
	 * Simply selects the view to render by returning its name.
	 */
	@RequestMapping(value = "/buyStock", method = RequestMethod.GET)
	public String buyStock(Locale locale, Model model) {
		String method = "buyStock";
		logger.info("Buy Stocks Page The client locale is {}.", locale);
		System.out.println("Starting " + method);
		ControllerUtils.logModelAttributes(model);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );

		// update the menu state 
		MenuState menuState = MenuState.BUY;
		model.addAttribute("menuState", menuState);
		System.out.println("menuState: " + menuState);

		return JSP_PAGE_NAME;
	}
	
}
