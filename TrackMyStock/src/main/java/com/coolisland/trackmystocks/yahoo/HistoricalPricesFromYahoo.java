package com.coolisland.trackmystocks.yahoo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;


public class HistoricalPricesFromYahoo {
	private static String url = "http://ichart.finance.yahoo.com/table.csv";
	private static String stockParamName = "s";
	private static String fromMonthParamName = "a";
	private static String fromDayParamName = "b";
	private static String fromYearParamName = "c";
	private static String toMonthParamName = "d";
	private static String toDayParamName = "e";
	private static String toYearParamName = "f";
	private static String dailyParam = "g=d";
	private static String fileTypeParam = "ignore=.csv";

	/**
	 * 
	 * @param urlString
	 * @param writer
	 */
	private void saveToFile(String urlString, FileWriter writer) {
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (url == null) {
			System.out.println("Bad URL: " + urlString);
			return;
		}

		try {
			InputStream is = null;
			URLConnection connection = null;
			try {
				connection = url.openConnection();
				if (connection == null) {
					System.out.println("Unable to open connection to " + urlString);
					return;
				}

				try {
					is = connection.getInputStream();
				} catch (FileNotFoundException fnfe) {
				}

				if (is == null) {
					System.out.println("\t" + "Unable to get input string from " + urlString);
					return;
				}

			} catch (IOException e) {
				System.out.println("Exception caught trying to connect to: ");

				if (urlString != null) {
					System.out.println("\t" + urlString);
				} else {
					System.out.println("\t" + "URL is null");
				}

				if (connection == null) {
					System.out.println("\t" + "Connection is null");
				} else {
					System.out.println("\t" + connection);
				}

				e.printStackTrace();
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					// System.out.println(line);
					writer.write(line);
					writer.write("\n");
				}

				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				reader.close();
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param stockName
	 * @param fromMonth
	 * @param fromDay
	 * @param fromYear
	 * @param toMonth
	 * @param toDay
	 * @param toYear
	 * @param filePath
	 */
	public void saveHistoricalPricesToFile(String stockName, String fromMonth, String fromDay, String fromYear,
			String toMonth, String toDay, String toYear, String filePath) {

		String fullUrl = url + "?";
		fullUrl += stockParamName + "=" + stockName;
		fullUrl += "&" + fromMonthParamName + "=" + fromMonth;
		fullUrl += "&" + fromDayParamName + "=" + fromDay;
		fullUrl += "&" + fromYearParamName + "=" + fromYear;

		fullUrl += "&" + toMonthParamName + "=" + toMonth;
		fullUrl += "&" + toDayParamName + "=" + toDay;
		fullUrl += "&" + toYearParamName + "=" + toYear;

		fullUrl += "&" + dailyParam;

		fullUrl += "&" + fileTypeParam;

		// System.out.println("URL: " + fullUrl);

		FileWriter writer = null;
		try {
			writer = new FileWriter(filePath);

			// save the historical prices to file
			saveToFile(fullUrl, writer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HistoricalPricesFromYahoo prices = new HistoricalPricesFromYahoo();

		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		String filePath = "C:\\temp\\YahooPrices\\VPL-" + year + month + day + ".csv";

		String toMonth = String.valueOf(month);
		String toDay = String.valueOf(day);
		String toYear = String.valueOf(year);

		prices.saveHistoricalPricesToFile("VPL", "00", "01", "2011", toMonth, toDay, toYear, filePath);
	}
}
