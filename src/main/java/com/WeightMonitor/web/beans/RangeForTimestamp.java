package com.WeightMonitor.web.beans;

import java.sql.Timestamp;

public class RangeForTimestamp {

	public RangeForTimestamp() {
		// TODO Auto-generated constructor stub
	}
	
	private String timeStampFrom;
	private String timeStampTo;
    private Timestamp tsFrom;
    private Timestamp tsTo;
	
	public String getTimeStampFrom() {
		return timeStampFrom;
	}
	public void setTimeStampFrom(String timeStampFrom) {
		this.timeStampFrom = timeStampFrom;
	}
	public String getTimeStampTo() {
		return timeStampTo;
	}
	public void setTimeStampTo(String timeStampTo) {
		this.timeStampTo = timeStampTo;
	}
	public Timestamp getTsFrom() {
		return tsFrom;
	}
	public void setTsFrom(Timestamp tsFrom) {
		this.tsFrom = tsFrom;
	}
	public Timestamp getTsTo() {
		return tsTo;
	}
	public void setTsTo(Timestamp tsTo) {
		this.tsTo = tsTo;
	}
	

}
