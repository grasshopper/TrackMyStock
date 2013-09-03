package com.coolisland.trackmystocks.beans;

import java.util.ArrayList;
import java.util.List;

import com.coolisland.trackmystocks.utils.MovingAveragePeriod;

public class MovingAverageSelectionListBean {
	private List<MovingAveragePeriod> movingAverageOptions = new ArrayList<MovingAveragePeriod>();
	private MovingAveragePeriod selectedAverage;

	public MovingAverageSelectionListBean() {
		initializeMovingAverageOptions();
		selectedAverage = MovingAveragePeriod.DAYS_200;
	}
	
	private void initializeMovingAverageOptions() {
		if (movingAverageOptions == null) {
			movingAverageOptions = new ArrayList<MovingAveragePeriod>();
		}
		
		if (movingAverageOptions.size() == 0) {
			for (MovingAveragePeriod period : MovingAveragePeriod.values()) {
				movingAverageOptions.add(period);
			}
		}
		}
	
		public List<MovingAveragePeriod> getMovingAverageOptions() {
			return movingAverageOptions;
		}
	
		public void setMovingAverageOptions(List<MovingAveragePeriod> movingAverageOptions) {
		this.movingAverageOptions = movingAverageOptions;
	}

	public MovingAveragePeriod getSelectedAverage() {
		System.out.println("getSelectedAverage(): " + selectedAverage);

		return selectedAverage;
	}

	public void setSelectedAverage(MovingAveragePeriod newValue) {
		System.out.println("setSelectedAverage() newValue: " + newValue);
		
		this.selectedAverage = newValue;
	}

}
