package com.coolisland.trackmystocks.yahoo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import au.com.bytecode.opencsv.CSVReader;

import com.coolisland.trackmystocks.database.StockQuoteHistoryBO;
//import com.coolisland.trackmystocks.utils.QuoteData;

public class ParseYahooCsvFileQuotes {
	private static final String YAHOO_DATE_FORMAT = "yyyy-MM-dd";
	// private String[] columnTitles = null;
	private String csvFileName = null;
	private CSVReader reader = null;
	private int row = 0;

	public ParseYahooCsvFileQuotes(String fileName) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(fileName)) {
			System.out.println("ERROR: File name is empty");
			return;
		}

		csvFileName = fileName;
		getColumnTitles(fileName);
	}

	private void getColumnTitles(String fileName) {
		String[] colTitles;

		try {
			reader = new CSVReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: unable to open file: " + fileName);
			e.printStackTrace();
		}

		// columnTitles = reader.readNext();
		row++;

		// System.out.println("Column Titles: ");
		//
		// for (int ndx = 0; ndx < columnTitles.length; ndx++) {
		// System.out.println("\t" + columnTitles[ndx]);
		// }

	}

	public void closeReader() {
		// close the reader
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int parseHistoricalFile(String fileName) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(fileName)) {
			System.out.println("ERROR: File name is empty");
			return row;
		}

		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
				row++;

				StringBuffer out = new StringBuffer();
				out.append(row + ": ");
				for (int ndx = 0; ndx < nextLine.length; ndx++) {
					out.append(" " + ndx + ": " + nextLine[ndx]);
				}

				// System.out.println(out.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return row;
	}

//	public void mapCsvTo(QuoteData quoteDataBean) {
//		if (quoteDataBean == null) {
//			return;
//		}
//
//		ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
//
//		strat.setType(quoteDataBean.getClass());
//
//		// the fields to bind to in your JavaBean
//		String[] columns = quoteDataBean.getColumnNamesForYahoo();
//
//		strat.setColumnMapping(columns);
//
//		CsvToBean csv = new CsvToBean();
//
//		try {
//			reader = new CSVReader(new FileReader(csvFileName));
//		} catch (FileNotFoundException e) {
//			System.out.println("ERROR: unable to open file: " + csvFileName);
//			e.printStackTrace();
//		}
//
//		List list = csv.parse(strat, reader);
//
//		for (int ndx = 0; ndx < list.size(); ndx++) {
//			Object o = list.get(ndx);
//			System.out.println("Class: " + o.getClass());
//		}
//
//		// close the reader
//		try {
//			reader.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public boolean getNextCsvRow(StockQuoteHistoryBO quoteDataBean)
			throws Exception {
		if (quoteDataBean == null) {
			quoteDataBean = new StockQuoteHistoryBO();
		}

		if (reader == null) {
			return false;
		}

		String[] nextLine;
		try {
			// nextLine[] is an array of values from the line
			nextLine = reader.readNext();

			if (nextLine != null) {
				row++;

				// 3-Jan-11
				quoteDataBean.setLastTradeDateTime(nextLine[0],
						new SimpleDateFormat(YAHOO_DATE_FORMAT));
				quoteDataBean.setQuoteDate(nextLine[0], new SimpleDateFormat(
						YAHOO_DATE_FORMAT));
				quoteDataBean.setOpenAmount(nextLine[1]);
				quoteDataBean.setDayHighAmount(nextLine[2]);
				quoteDataBean.setDayLowAmount(nextLine[3]);
				quoteDataBean.setLastTradeAmount(nextLine[4]);
				quoteDataBean.setVolume(nextLine[5]);
			} else {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean hasData() {
		boolean returnVal = true;

		if (reader == null) {
			returnVal = false;
		}

		return returnVal;
	}
}
