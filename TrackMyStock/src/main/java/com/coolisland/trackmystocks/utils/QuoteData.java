//package com.coolisland.trackmystocks.utils;
//
//import java.text.SimpleDateFormat;
//
//public class QuoteData implements java.io.Serializable {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 611903183137008994L;
//
//	private java.lang.String stockSymbol;
//
//	private java.math.BigDecimal lastTradeAmount;
//
//	private java.util.Calendar lastTradeDateTime;
//
//	private java.math.BigDecimal stockChange;
//
//	private java.math.BigDecimal openAmount;
//
//	private java.math.BigDecimal dayHigh;
//
//	private java.math.BigDecimal dayLow;
//
//	private int stockVolume;
//
//	private java.math.BigDecimal prevCls;
//
//	private java.lang.String changePercent;
//
//	private java.lang.String fiftyTwoWeekRange;
//
//	private java.math.BigDecimal earnPerShare;
//
//	private java.math.BigDecimal PE;
//
//	private java.lang.String companyName;
//
//	private boolean quoteError;
//
//	public QuoteData() {
//	}
//
//	public QuoteData(java.lang.String stockSymbol, java.math.BigDecimal lastTradeAmount,
//			java.util.Calendar lastTradeDateTime, java.math.BigDecimal stockChange, java.math.BigDecimal openAmount,
//			java.math.BigDecimal dayHigh, java.math.BigDecimal dayLow, int stockVolume, java.math.BigDecimal prevCls,
//			java.lang.String changePercent, java.lang.String fiftyTwoWeekRange, java.math.BigDecimal earnPerShare,
//			java.math.BigDecimal PE, java.lang.String companyName, boolean quoteError) {
//		this.stockSymbol = stockSymbol;
//		this.lastTradeAmount = lastTradeAmount;
//		this.lastTradeDateTime = lastTradeDateTime;
//		this.stockChange = stockChange;
//		this.openAmount = openAmount;
//		this.dayHigh = dayHigh;
//		this.dayLow = dayLow;
//		this.stockVolume = stockVolume;
//		this.prevCls = prevCls;
//		this.changePercent = changePercent;
//		this.fiftyTwoWeekRange = fiftyTwoWeekRange;
//		this.earnPerShare = earnPerShare;
//		this.PE = PE;
//		this.companyName = companyName;
//		this.quoteError = quoteError;
//	}
//
//	/**
//	 * Gets the stockSymbol value for this QuoteData.
//	 * 
//	 * @return stockSymbol
//	 */
//	public java.lang.String getStockSymbol() {
//		return stockSymbol;
//	}
//
//	/**
//	 * Sets the stockSymbol value for this QuoteData.
//	 * 
//	 * @param stockSymbol
//	 */
//	public void setStockSymbol(java.lang.String stockSymbol) {
//		this.stockSymbol = stockSymbol;
//	}
//
//	/**
//	 * Gets the lastTradeAmount value for this QuoteData.
//	 * 
//	 * @return lastTradeAmount
//	 */
//	public java.math.BigDecimal getLastTradeAmount() {
//		return lastTradeAmount;
//	}
//
//	/**
//	 * Sets the lastTradeAmount value for this QuoteData.
//	 * 
//	 * @param lastTradeAmount
//	 */
//	public void setLastTradeAmount(java.math.BigDecimal lastTradeAmount) {
//		this.lastTradeAmount = lastTradeAmount;
//	}
//
//	/**
//	 * Sets the lastTradeAmount value for this QuoteData.
//	 * 
//	 * @param lastTradeAmount
//	 */
//	public void setLastTradeAmount(String lastTradeAmount) {
//		java.math.BigDecimal temp = new java.math.BigDecimal(lastTradeAmount);
//		this.lastTradeAmount = temp;
//	}
//
//	/**
//	 * Gets the lastTradeDateTime value for this QuoteData.
//	 * 
//	 * @return lastTradeDateTime
//	 */
//	public java.util.Calendar getLastTradeDateTime() {
//		return lastTradeDateTime;
//	}
//
//	/**
//	 * Sets the lastTradeDateTime value for this QuoteData.
//	 * 
//	 * @param lastTradeDateTime
//	 */
//	public void setLastTradeDateTime(java.util.Calendar lastTradeDateTime) {
//		this.lastTradeDateTime = lastTradeDateTime;
//	}
//
//	/**
//	 * Sets the lastTradeDateTime value for this QuoteData.
//	 * 
//	 * @param lastTradeDateTime
//	 */
//	public void setLastTradeDateTime(String dateTime, SimpleDateFormat format) {
//		java.util.Calendar temp = StringUtils.dateString2Calendar(dateTime, format);
//
//		this.lastTradeDateTime = temp;
//	}
//
//	/**
//	 * Gets the stockChange value for this QuoteData.
//	 * 
//	 * @return stockChange
//	 */
//	public java.math.BigDecimal getStockChange() {
//		return stockChange;
//	}
//
//	/**
//	 * Sets the stockChange value for this QuoteData.
//	 * 
//	 * @param stockChange
//	 */
//	public void setStockChange(java.math.BigDecimal stockChange) {
//		this.stockChange = stockChange;
//	}
//
//	/**
//	 * Gets the openAmount value for this QuoteData.
//	 * 
//	 * @return openAmount
//	 */
//	public java.math.BigDecimal getOpenAmount() {
//		return openAmount;
//	}
//
//	/**
//	 * Sets the openAmount value for this QuoteData.
//	 * 
//	 * @param openAmount
//	 */
//	public void setOpenAmount(java.math.BigDecimal openAmount) {
//		this.openAmount = openAmount;
//	}
//
//	/**
//	 * Sets the openAmount value for this QuoteData.
//	 * 
//	 * @param openAmount
//	 */
//	public void setOpenAmount(String openAmount) {
//		java.math.BigDecimal temp = new java.math.BigDecimal(openAmount);
//		this.openAmount = temp;
//	}
//
//	/**
//	 * Gets the dayHigh value for this QuoteData.
//	 * 
//	 * @return dayHigh
//	 */
//	public java.math.BigDecimal getDayHigh() {
//		return dayHigh;
//	}
//
//	/**
//	 * Sets the dayHigh value for this QuoteData.
//	 * 
//	 * @param dayHigh
//	 */
//	public void setDayHigh(java.math.BigDecimal dayHigh) {
//		this.dayHigh = dayHigh;
//	}
//
//	/**
//	 * Sets the dayHigh value for this QuoteData.
//	 * 
//	 * @param dayHigh
//	 */
//	public void setDayHigh(String dayHigh) {
//		java.math.BigDecimal temp = new java.math.BigDecimal(dayHigh);
//		this.dayHigh = temp;
//	}
//
//	/**
//	 * Gets the dayLow value for this QuoteData.
//	 * 
//	 * @return dayLow
//	 */
//	public java.math.BigDecimal getDayLow() {
//		return dayLow;
//	}
//
//	/**
//	 * Sets the dayLow value for this QuoteData.
//	 * 
//	 * @param dayLow
//	 */
//	public void setDayLow(java.math.BigDecimal dayLow) {
//		this.dayLow = dayLow;
//	}
//
//	/**
//	 * Sets the dayLow value for this QuoteData.
//	 * 
//	 * @param dayLow
//	 */
//	public void setDayLow(String dayLow) {
//		java.math.BigDecimal temp = new java.math.BigDecimal(dayLow);
//		this.dayLow = temp;
//	}
//
//	/**
//	 * Gets the stockVolume value for this QuoteData.
//	 * 
//	 * @return stockVolume
//	 */
//	public int getStockVolume() {
//		return stockVolume;
//	}
//
//	/**
//	 * Sets the stockVolume value for this QuoteData.
//	 * 
//	 * @param stockVolume
//	 */
//	public void setStockVolume(int stockVolume) {
//		this.stockVolume = stockVolume;
//	}
//
//	/**
//	 * Sets the stockVolume value for this QuoteData.
//	 * 
//	 * @param stockVolume
//	 */
//	public void setStockVolume(String stockVolume) {
//		Integer temp = new Integer(stockVolume);
//
//		this.stockVolume = temp;
//	}
//
//	/**
//	 * Gets the prevCls value for this QuoteData.
//	 * 
//	 * @return prevCls
//	 */
//	public java.math.BigDecimal getPrevCls() {
//		return prevCls;
//	}
//
//	/**
//	 * Sets the prevCls value for this QuoteData.
//	 * 
//	 * @param prevCls
//	 */
//	public void setPrevCls(java.math.BigDecimal prevCls) {
//		this.prevCls = prevCls;
//	}
//
//	/**
//	 * Gets the changePercent value for this QuoteData.
//	 * 
//	 * @return changePercent
//	 */
//	public java.lang.String getChangePercent() {
//		return changePercent;
//	}
//
//	/**
//	 * Sets the changePercent value for this QuoteData.
//	 * 
//	 * @param changePercent
//	 */
//	public void setChangePercent(java.lang.String changePercent) {
//		this.changePercent = changePercent;
//	}
//
//	/**
//	 * Gets the fiftyTwoWeekRange value for this QuoteData.
//	 * 
//	 * @return fiftyTwoWeekRange
//	 */
//	public java.lang.String getFiftyTwoWeekRange() {
//		return fiftyTwoWeekRange;
//	}
//
//	/**
//	 * Sets the fiftyTwoWeekRange value for this QuoteData.
//	 * 
//	 * @param fiftyTwoWeekRange
//	 */
//	public void setFiftyTwoWeekRange(java.lang.String fiftyTwoWeekRange) {
//		this.fiftyTwoWeekRange = fiftyTwoWeekRange;
//	}
//
//	/**
//	 * Gets the earnPerShare value for this QuoteData.
//	 * 
//	 * @return earnPerShare
//	 */
//	public java.math.BigDecimal getEarnPerShare() {
//		return earnPerShare;
//	}
//
//	/**
//	 * Sets the earnPerShare value for this QuoteData.
//	 * 
//	 * @param earnPerShare
//	 */
//	public void setEarnPerShare(java.math.BigDecimal earnPerShare) {
//		this.earnPerShare = earnPerShare;
//	}
//
//	/**
//	 * Gets the PE value for this QuoteData.
//	 * 
//	 * @return PE
//	 */
//	public java.math.BigDecimal getPE() {
//		return PE;
//	}
//
//	/**
//	 * Sets the PE value for this QuoteData.
//	 * 
//	 * @param PE
//	 */
//	public void setPE(java.math.BigDecimal PE) {
//		this.PE = PE;
//	}
//
//	/**
//	 * Gets the companyName value for this QuoteData.
//	 * 
//	 * @return companyName
//	 */
//	public java.lang.String getCompanyName() {
//		return companyName;
//	}
//
//	/**
//	 * Sets the companyName value for this QuoteData.
//	 * 
//	 * @param companyName
//	 */
//	public void setCompanyName(java.lang.String companyName) {
//		this.companyName = companyName;
//	}
//
//	/**
//	 * Gets the quoteError value for this QuoteData.
//	 * 
//	 * @return quoteError
//	 */
//	public boolean isQuoteError() {
//		return quoteError;
//	}
//
//	/**
//	 * Sets the quoteError value for this QuoteData.
//	 * 
//	 * @param quoteError
//	 */
//	public void setQuoteError(boolean quoteError) {
//		this.quoteError = quoteError;
//	}
//
//	private java.lang.Object __equalsCalc = null;
//
//	@Override
//	public synchronized boolean equals(java.lang.Object obj) {
//		if (!(obj instanceof QuoteData)) {
//			return false;
//		}
//		QuoteData other = (QuoteData) obj;
//		if (obj == null) {
//			return false;
//		}
//		if (this == obj) {
//			return true;
//		}
//		if (__equalsCalc != null) {
//			return (__equalsCalc == obj);
//		}
//		__equalsCalc = obj;
//		boolean _equals;
//		_equals = true
//				&& ((this.stockSymbol == null && other.getStockSymbol() == null) || (this.stockSymbol != null && this.stockSymbol
//						.equals(other.getStockSymbol())))
//				&& ((this.lastTradeAmount == null && other.getLastTradeAmount() == null) || (this.lastTradeAmount != null && this.lastTradeAmount
//						.equals(other.getLastTradeAmount())))
//				&& ((this.lastTradeDateTime == null && other.getLastTradeDateTime() == null) || (this.lastTradeDateTime != null && this.lastTradeDateTime
//						.equals(other.getLastTradeDateTime())))
//				&& ((this.stockChange == null && other.getStockChange() == null) || (this.stockChange != null && this.stockChange
//						.equals(other.getStockChange())))
//				&& ((this.openAmount == null && other.getOpenAmount() == null) || (this.openAmount != null && this.openAmount
//						.equals(other.getOpenAmount())))
//				&& ((this.dayHigh == null && other.getDayHigh() == null) || (this.dayHigh != null && this.dayHigh
//						.equals(other.getDayHigh())))
//				&& ((this.dayLow == null && other.getDayLow() == null) || (this.dayLow != null && this.dayLow
//						.equals(other.getDayLow())))
//				&& this.stockVolume == other.getStockVolume()
//				&& ((this.prevCls == null && other.getPrevCls() == null) || (this.prevCls != null && this.prevCls
//						.equals(other.getPrevCls())))
//				&& ((this.changePercent == null && other.getChangePercent() == null) || (this.changePercent != null && this.changePercent
//						.equals(other.getChangePercent())))
//				&& ((this.fiftyTwoWeekRange == null && other.getFiftyTwoWeekRange() == null) || (this.fiftyTwoWeekRange != null && this.fiftyTwoWeekRange
//						.equals(other.getFiftyTwoWeekRange())))
//				&& ((this.earnPerShare == null && other.getEarnPerShare() == null) || (this.earnPerShare != null && this.earnPerShare
//						.equals(other.getEarnPerShare())))
//				&& ((this.PE == null && other.getPE() == null) || (this.PE != null && this.PE.equals(other.getPE())))
//				&& ((this.companyName == null && other.getCompanyName() == null) || (this.companyName != null && this.companyName
//						.equals(other.getCompanyName()))) && this.quoteError == other.isQuoteError();
//		__equalsCalc = null;
//		return _equals;
//	}
//
//	private boolean __hashCodeCalc = false;
//
//	@Override
//	public synchronized int hashCode() {
//		if (__hashCodeCalc) {
//			return 0;
//		}
//		__hashCodeCalc = true;
//		int _hashCode = 1;
//		if (getStockSymbol() != null) {
//			_hashCode += getStockSymbol().hashCode();
//		}
//		if (getLastTradeAmount() != null) {
//			_hashCode += getLastTradeAmount().hashCode();
//		}
//		if (getLastTradeDateTime() != null) {
//			_hashCode += getLastTradeDateTime().hashCode();
//		}
//		if (getStockChange() != null) {
//			_hashCode += getStockChange().hashCode();
//		}
//		if (getOpenAmount() != null) {
//			_hashCode += getOpenAmount().hashCode();
//		}
//		if (getDayHigh() != null) {
//			_hashCode += getDayHigh().hashCode();
//		}
//		if (getDayLow() != null) {
//			_hashCode += getDayLow().hashCode();
//		}
//		_hashCode += getStockVolume();
//		if (getPrevCls() != null) {
//			_hashCode += getPrevCls().hashCode();
//		}
//		if (getChangePercent() != null) {
//			_hashCode += getChangePercent().hashCode();
//		}
//		if (getFiftyTwoWeekRange() != null) {
//			_hashCode += getFiftyTwoWeekRange().hashCode();
//		}
//		if (getEarnPerShare() != null) {
//			_hashCode += getEarnPerShare().hashCode();
//		}
//		if (getPE() != null) {
//			_hashCode += getPE().hashCode();
//		}
//		if (getCompanyName() != null) {
//			_hashCode += getCompanyName().hashCode();
//		}
//		_hashCode += (isQuoteError() ? Boolean.TRUE : Boolean.FALSE).hashCode();
//		__hashCodeCalc = false;
//		return _hashCode;
//	}
//
//	// Type metadata
//	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
//			QuoteData.class, true);
//
//	static {
//		typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.cdyne.com/", "QuoteData"));
//		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("stockSymbol");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "StockSymbol"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
//		elemField.setMinOccurs(0);
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("lastTradeAmount");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "LastTradeAmount"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("lastTradeDateTime");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "LastTradeDateTime"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("stockChange");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "StockChange"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("openAmount");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "OpenAmount"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("dayHigh");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "DayHigh"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("dayLow");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "DayLow"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("stockVolume");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "StockVolume"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("prevCls");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "PrevCls"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("changePercent");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "ChangePercent"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
//		elemField.setMinOccurs(0);
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("fiftyTwoWeekRange");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "FiftyTwoWeekRange"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
//		elemField.setMinOccurs(0);
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("earnPerShare");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "EarnPerShare"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("PE");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "PE"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("companyName");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "CompanyName"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
//		elemField.setMinOccurs(0);
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//		elemField = new org.apache.axis.description.ElementDesc();
//		elemField.setFieldName("quoteError");
//		elemField.setXmlName(new javax.xml.namespace.QName("http://ws.cdyne.com/", "QuoteError"));
//		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
//		elemField.setNillable(false);
//		typeDesc.addFieldDesc(elemField);
//	}
//
//	/**
//	 * Return type metadata object
//	 */
//	public static org.apache.axis.description.TypeDesc getTypeDesc() {
//		return typeDesc;
//	}
//
//	/**
//	 * Get Custom Serializer
//	 */
//	public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType,
//			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
//		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
//	}
//
//	/**
//	 * Get Custom Deserializer
//	 */
//	public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType,
//			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
//		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
//	}
//
//	public String[] getColumnNamesForYahoo() {
//
//		String[] yahooColNames = { "lastTradeDateTime", "openAmount", "dayHigh", "dayLow", "lastTradeAmount",
//				"stockVolume" };
//
//		return yahooColNames;
//	}
//}
