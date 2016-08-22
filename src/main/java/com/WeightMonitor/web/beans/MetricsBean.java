package com.WeightMonitor.web.beans;

import java.sql.Timestamp;

public class MetricsBean {

	public MetricsBean() {
		// TODO Auto-generated constructor stub
	}
	private String value;
	private String timeStamp;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}
