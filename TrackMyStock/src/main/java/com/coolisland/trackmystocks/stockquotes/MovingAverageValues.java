package com.coolisland.trackmystocks.stockquotes;


public class MovingAverageValues {
	public enum Direction {
		UP("+"), // up from previous day
		DOWN("-"), // down from previous day
		NO_CHANGE("="); // no change from previous day

		private String value;

		private Direction(String direction) {
			setValue(direction);
		}

		public String getValue() {
			return value;
		}

		private void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

	public enum MovingAverageDirection {
		UNINITIALIZED("Not Initialized"), // haven't been initialized
		AVERAGE_MOVING_UP("Trending Up"), // more up than down, or mostly
											// sideways
											// with recent
		// up
		AVERAGE_MOVING_DOWN("Trending Down"), // more down than up, or mostly
												// sideways with
		// recent down
		AVERAGE_MOVING_SIDEWAYS("Trending Sideways"), // equal movements up and
														// down
		AVERAGE_MOVING_lEANING_UP("Leaning Up"), // averages are moving up after
													// sideways
		AVERAGE_MOVING_lEANING_DOWN("Leaning Down"), // averages are moving down
														// after sideways
		AVERAGE_MOVING_ALL_UP("All Averages Up"), // all averages are moving up
		AVERAGE_MOVING_ALL_DOWN("All Averages Down"), // all averages are moving
														// down
		AVERAGE_MOVING_STRONG_UP("Strong Trend Up"), // most averages are moving
														// up
		AVERAGE_MOVING_STRONG_DOWN("Strong Trend Down"); // most averages are
															// moving down

		private String value;

		private MovingAverageDirection(String meaning) {
			value = meaning;
		}

		@Override
		public String toString() {
			return value;
		}
	}

	private static final int LEANING_FACTOR = 2;

	private boolean dataFound;
	private String stockName;
	private String tickerSymbol;
	private double movingAverage;
	private double closePrice;
	private String movingAverageRecommendation;
	private String movingAverageHistory;
	private MovingAverageDirection trend;

	public MovingAverageValues() {
		trend = MovingAverageDirection.UNINITIALIZED;
		setDataFound(false);
		stockName = "";
		tickerSymbol = "";
		movingAverage = 0;
		closePrice = 0;
		movingAverageRecommendation = "";
		movingAverageHistory = "";
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getTickerSymbol() {
		return tickerSymbol;
	}

	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}

	public double getMovingAverage() {
		return movingAverage;
	}

	public void setMovingAverage(double movingAverage) {
		this.movingAverage = movingAverage;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public String getMovingAverageRecommendation() {
		return movingAverageRecommendation;
	}

	public void setMovingAverageRecommendation(String movingAverageRecommendation) {
		this.movingAverageRecommendation = movingAverageRecommendation;
	}

	public String getMovingAverageHistory() {
		return movingAverageHistory;
	}

	public void setMovingAverageHistory(String movingAverageHistory) {
		this.movingAverageHistory = movingAverageHistory;
	}

	public boolean isDataFound() {
		return dataFound;
	}

	public void setDataFound(boolean dataFound) {
		this.dataFound = dataFound;
	}

	public void setDirection(MovingAverageDirection dir) {
		trend = dir;
	}

	public MovingAverageDirection getDirection() {
		return trend;
	}

	/**
	 * use cases:
	 * 
	 * 12345678901234
	 * 
	 * ++++++++++++++ should set strong up trend
	 * 
	 * -------------- should set strong down trend
	 * 
	 * ============== should set sideways trend
	 * 
	 * ++-=========== should set sideways trend
	 * 
	 * +--=========== should set sideways trend
	 * 
	 * ===========+++ should set sideways trend
	 * 
	 * =========+++++ should set leaning up trend - +'s * factor > ='s
	 * 
	 * =======+++++++ should set up trend - +'s >= ='s
	 * 
	 * 
	 */
	public void simpleMovingAverageDirectionEval() {
		int size = movingAverageHistory.length();

		int downCount = org.apache.commons.lang3.StringUtils.countMatches(movingAverageHistory, Direction.DOWN.getValue());
		int upCount = org.apache.commons.lang3.StringUtils.countMatches(movingAverageHistory, Direction.UP.getValue());
		int sidewaysCount = org.apache.commons.lang3.StringUtils.countMatches(movingAverageHistory, Direction.NO_CHANGE.getValue());

		if (size == downCount && size == upCount && size == sidewaysCount) {
			// something is wrong
			trend = MovingAverageDirection.UNINITIALIZED;
		} else if (upCount == size) {
			trend = MovingAverageDirection.AVERAGE_MOVING_ALL_UP;
		} else if (downCount == size) {
			trend = MovingAverageDirection.AVERAGE_MOVING_ALL_DOWN;
		} else if (sidewaysCount == size) {
			trend = MovingAverageDirection.AVERAGE_MOVING_SIDEWAYS;
		} else if (sidewaysCount > upCount || sidewaysCount > downCount) {
			/*
			 * what is the most recent trends
			 */

			if ((upCount * LEANING_FACTOR) > sidewaysCount) {
				trend = MovingAverageDirection.AVERAGE_MOVING_lEANING_UP;
			} else if ((downCount * LEANING_FACTOR) > sidewaysCount) {
				trend = MovingAverageDirection.AVERAGE_MOVING_lEANING_DOWN;
			}
		} else if (sidewaysCount == upCount) {
			trend = MovingAverageDirection.AVERAGE_MOVING_UP;
		} else if (sidewaysCount == upCount) {
			trend = MovingAverageDirection.AVERAGE_MOVING_DOWN;
		} else if (upCount > (downCount * LEANING_FACTOR)) {
			trend = MovingAverageDirection.AVERAGE_MOVING_STRONG_UP;
		} else if (upCount > downCount) {
			trend = MovingAverageDirection.AVERAGE_MOVING_UP;
		} else if (downCount > (upCount * LEANING_FACTOR)) {
			trend = MovingAverageDirection.AVERAGE_MOVING_STRONG_DOWN;
		} else if (downCount > downCount) {
			trend = MovingAverageDirection.AVERAGE_MOVING_DOWN;
		} else {
			trend = MovingAverageDirection.AVERAGE_MOVING_SIDEWAYS;
		}
	}
}
