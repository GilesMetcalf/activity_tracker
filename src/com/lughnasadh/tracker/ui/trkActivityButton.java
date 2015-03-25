package com.lughnasadh.tracker.ui;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import com.lughnasadh.tracker.config.trkActivity;

public class trkActivityButton extends JToggleButton {
	
	private String description;
	private String taskID1;
	private String taskID2;
	private String taskID3;
	private String startTimeStamp;
	
	public  trkActivityButton(trkActivity activity) {
		
		description = activity.getDescription();
		taskID1 = activity.getTaskID1();
		taskID2 = activity.getTaskID2();
		taskID3 = activity.getTaskID3();
		
		this.setText(activity.getDescription());
		this.setHorizontalAlignment(SwingConstants.LEFT);

		
		
	}

	public String getDescription() {
		return description;
	}

	public String getTaskID1() {
		return taskID1;
	}

	public String getTaskID2() {
		return taskID2;
	}

	public String getTaskID3() {
		return taskID3;
	}

	public String getStartTimeStamp() {
		return startTimeStamp;
	}

	public void setStartTimeStamp(String startTimeStamp) {
		this.startTimeStamp = startTimeStamp;
	}




}
