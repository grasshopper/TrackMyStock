/**
 * 
 */
package com.coolisland.trackmystocks.utils;

/**
 * @author Silvio
 * 
 */
public enum MovingAveragePeriod {
	DAYS_20(20, "20 Day"), DAYS_50(50, "50 Day"), DAYS_100(100, "100 Day"), DAYS_200(
			200, "200 Day");

	private String displayValue;
	private int id;

	private MovingAveragePeriod(int id, String value) {
		final String method = "MovingAveragePeriod";
		System.out.println("Starting " + method);
		
		displayValue = value;
		this.id = id;
	}

	public static MovingAveragePeriod getMovingAveragePeriodById(int id) {
		MovingAveragePeriod[] periods = MovingAveragePeriod.values();
		for (MovingAveragePeriod period : periods) {
			if (period.getId() == id) {
//				displayValue = period.getDisplayValue();
//				id = period.getId();
				return period;
			}
		}
		
		return MovingAveragePeriod.DAYS_200;
	}

	@Override
	public String toString() {
		final String method = "MovingAveragePeriod.toString";
		System.out.println("Starting " + method);
		
		System.out.println(id + "-" + displayValue);
		return displayValue;
	}

	public int getId() {
		final String method = "MovingAveragePeriod.getId";
		System.out.println("Starting " + method);
		
		return id;
	}

	public String getDisplayValue() {
		final String method = "MovingAveragePeriod.getDisplayValue";
		System.out.println("Starting " + method);
		
		return displayValue;
	}
}
