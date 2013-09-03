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
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("menuState")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(Locale locale, Model model) {
		String method = "root"; 
		System.out.println("Starting " + method);
		ControllerUtils.logModelAttributes(model);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
	
		// direct the user to the home page
		MenuState menuState = MenuState.HOME;
		model.addAttribute("menuState", menuState);
		System.out.println("menuState: " + menuState);
		
		return "home";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		String method = "home"; 
		logger.info("Welcome home! The client locale is {}.", locale);
		System.out.println("Starting " + method);
		ControllerUtils.logModelAttributes(model);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		MenuState menuState = MenuState.HOME;
		model.addAttribute("menuState", menuState);
		System.out.println("menuState: " + menuState);
		
		return "home";
	}
	
}
