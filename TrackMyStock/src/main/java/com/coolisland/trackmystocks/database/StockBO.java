package com.coolisland.trackmystocks.database;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.coolisland.trackmystocks.utils.StringUtils;


public class StockBO {
	private BigDecimal id;
	private String symbol;
	private String name;
	private String exchange;
	private Boolean track;
	private Boolean own;
	private String account;
	private BigDecimal tickerTypeId;
	private String itemValue;
	private String itemLabel;

	public StockBO() {

	}

	public StockBO(ResultSet rs) throws SQLException {
		load(rs);
	}

	public StockBO(StockBO bo) {
		load(bo);
	}

	
	/**
	 * Clones the object passed in into this one
	 * 
	 * @param bo
	 * @return
	 */
	public StockBO load(StockBO bo) {
		id = bo.getId();
		symbol = bo.getSymbol();
		name = bo.getName();
		exchange = bo.getExchange();
		track = bo.getTrack();
		own = bo.getOwn();
		account = bo.getAccount();
		tickerTypeId = bo.getTickerTypeId();
		itemValue = bo.getItemValue();
		itemLabel = bo.getItemLabel();
		
		return this;
	}

	public StockBO load(ResultSet rs) throws SQLException {
		try {
			id = rs.getBigDecimal("ID");
			symbol = rs.getString("SYMBOL");
			name = rs.getString("NAME");
			exchange = rs.getString("EXCHANGE");
			track = rs.getBoolean("TRACK");
			own = rs.getBoolean("OWN");
			account = rs.getString("ACCOUNT");
			setTickerTypeId(rs.getBigDecimal("TICKER_TYPE_ID"));
		} catch (SQLException e) {
			System.out.println(rs.toString());
			e.printStackTrace();
			throw new SQLException(e.getMessage(), e.getCause());
		}
		
		return this;
	}
	
	@Override
	public String toString() {
		StringBuffer out = new StringBuffer(200);
		String myIndent = StringUtils.INDENT;

		StringUtils.appendHeader(out, "Ticker", "");

		StringUtils.appendNameValueLine(out, "ID: ", id, myIndent);
		StringUtils.appendNameValueLine(out, "Symbol: ", symbol, myIndent);
		StringUtils.appendNameValueLine(out, "Name: ", name, myIndent);
		StringUtils.appendNameValueLine(out, "Exchange: ", exchange, myIndent);
		StringUtils.appendNameValueLine(out, "Track: ", track, myIndent);
		StringUtils.appendNameValueLine(out, "Own: ", own, myIndent);
		StringUtils.appendNameValueLine(out, "Account: ", account, myIndent);

		return out.toString();

	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public Boolean getTrack() {
		return track;
	}

	public void setTrack(Boolean track) {
		this.track = track;
	}

	public Boolean getOwn() {
		return own;
	}

	public void setOwn(Boolean own) {
		this.own = own;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getItemLabel() {
		return itemLabel;
	}

	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}

	public BigDecimal getTickerTypeId() {
		return tickerTypeId;
	}

	public void setTickerTypeId(BigDecimal tickerTypeId) {
		this.tickerTypeId = tickerTypeId;
	}
	
}
