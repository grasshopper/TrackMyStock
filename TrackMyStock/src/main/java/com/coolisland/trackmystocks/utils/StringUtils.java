package com.coolisland.trackmystocks.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class StringUtils {
	public static final String LINE_FEED = "\n";
	public static final String INDENT = "   ";
	private static final String HEADER_STR = "====================================";


	public static String appendHeader(StringBuffer out, String subject, String indent) {
		appendNameValueLine(out, HEADER_STR, "", indent);
		appendNameValueLine(out, subject, "", indent);
		appendNameValueLine(out, HEADER_STR, "", indent);

		return out.toString();
	}

	public static void appendNameValueLine(StringBuffer out, String name, String value, String indent) {
		out.append(indent + name + ": " + value + LINE_FEED);
	}

	public static void appendNameValue(StringBuffer out, String name, String value, String indent) {
		out.append(indent + name + ": " + value);
	}

	public static void appendNameValueLine(StringBuffer out, String name, Boolean value, String indent) {
		out.append(indent + name + ": " + value + LINE_FEED);
	}

	public static void appendNameValueLine(StringBuffer out, String name, int value, String myIndent) {
		appendNameValueLine(out, name, ": " + value, myIndent);
	}

	public static void appendNameValueLine(StringBuffer out, String name, BigDecimal value, String myIndent) {
		if (value != null) {
			appendNameValueLine(out, name, value.toString(), myIndent);
		} else {
			appendNameValueLine(out, name, "null", myIndent);
		}
	}


	public static String xmlToString(String xml) {
		StringBuffer out = new StringBuffer(100);
		String myIndent = StringUtils.INDENT;

		StringUtils.appendHeader(out, "XML", myIndent);

		// get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// Using factory get an instance of document builder
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();

			Document dom = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
			if (dom != null) {
				NamedNodeMap attributes = dom.getAttributes();

				if (attributes != null) {
					StringUtils.appendNameValueLine(out, "Attributes", attributes.toString(), myIndent);
				}

				xmlChildNodesToString(out, dom.getChildNodes(), myIndent);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return out.toString();
	}

	private static void xmlChildNodesToString(StringBuffer out, NodeList childNodes, String indent) {
		// String myIndent = StringUtils.INDENT + indent;
		String myIndent = indent;

		if (childNodes != null) {
			// StringUtils.appendNameValueLine(out, "Attributes",
			// childNodes.toString(), indent);

			for (int ndx = 0; ndx < childNodes.getLength(); ndx++) {
				Node item = childNodes.item(ndx);
				xmlItemToString(out, item, myIndent);

				Node node = item.getFirstChild();
				while (node != null) {
					xmlItemToString(out, item, myIndent);
					node = item.getNextSibling();
				}

			}
		}
	}

	private static void xmlItemToString(StringBuffer out, Node item, String indent) {
		String myIndent = StringUtils.INDENT + indent;

		if (item != null) {
			xmlAttributesToString(out, item.getAttributes(), myIndent);

			StringUtils.appendNameValueLine(out, "Item Content: ", item.getTextContent(), myIndent);
			StringUtils.appendNameValueLine(out, "Node Value: ", item.getNodeValue(), myIndent);
			StringUtils.appendNameValueLine(out, "Text Content: ", item.getTextContent(), myIndent);

			// xmlChildNodesToString(out, item.getChildNodes(), indent);
		}
	}

	private static void xmlAttributesToString(StringBuffer out, NamedNodeMap attributes, String indent) {
		String myIndent = StringUtils.INDENT + indent;

		if (attributes != null && attributes.getLength() > 0) {
			StringUtils.appendNameValueLine(out, "Attributes: ", attributes.toString(), myIndent);
			StringUtils.appendNameValueLine(out, "Number of Attributes: ", attributes.getLength(), myIndent);
		}
	}

	public static void appendNameValue(StringBuffer out, String name, BigDecimal id, String indent) {
		if (id != null) {
			appendNameValue(out, name, id.toString(), indent);
		} else {
			appendNameValue(out, name, "null", indent);
		}
	}

	public static void appendNameValue(StringBuffer out, String name, Date date, String indent) {
		if (date != null) {
			appendNameValue(out, name, date.toString(), indent);
		} else {
			appendNameValue(out, name, "null", indent);
		}
	}

	public static Calendar dateString2Calendar(String s, SimpleDateFormat dateFormat) {
		Calendar cal = Calendar.getInstance();

		Date date = null;
		try {
			date = dateFormat.parse(s);
			cal.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return cal;
	}

	public static String intToString(int num, int digits) {
		String format = String.format("%%0%dd", digits);
		String result = String.format(format, num);
		return result;
	}

	/**
	 * Truncate a string to a max length of maxLength
	 * 
	 * @param str
	 * @param maxLength
	 * @return
	 */
	public static String truncateString(String str, int maxLength) {
		String truncatedString = str;

		if (str != null && str.length() > maxLength) {
			// truncate the string to maxLenght
			if (maxLength > 3) {
				truncatedString = str.substring(0, maxLength - 3);
				truncatedString += "...";
			} else {
				truncatedString = str.substring(0, maxLength);
			}
		}

		return truncatedString;
	}
}
