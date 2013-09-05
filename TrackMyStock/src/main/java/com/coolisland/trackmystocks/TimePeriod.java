package com.coolisland.trackmystocks;

public enum TimePeriod {
	DAYS_1(1, "1 Day"), DAYS_5(5, "5 Days"), MONTH_1(30, "1 Month"), MONTH_3(90, "3 Months"),
	MONTH_6(180, "6 Months"), YEAR_1(365, "1 Year"), YEAR_2(730, "2 Years"), YEAR_5(1825, "5 Years");

	private String displayValue;
	private int id;

	private TimePeriod(int id, String value) {
		displayValue = value;
		this.id = id;
	}

	@Override
	public String toString() {
		return displayValue;
	}

	public int getValue() {
		return getId();
	}
	
	public int getId() {
		return id;
	}

	public String getDisplayValue() {
		return displayValue;
	}
}
