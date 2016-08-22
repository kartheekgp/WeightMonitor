package com.WeightMonitor.web.beans;

import java.sql.Timestamp;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;
@Entity("alerts")
//@Index can be added if needed
public class Alerts {
	@Reference
	long baseWeight;
	@Reference
	Timestamp timestamp;
	@Reference
	String status;
	@Reference
	long currentWeight;
	
	public long getBaseWeight() {
		return baseWeight;
	}
	public void setBaseWeight(long baseWeight) {
		this.baseWeight = baseWeight;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getCurrentWeight() {
		return currentWeight;
	}
	public void setCurrentWeight(long currentWeight) {
		this.currentWeight = currentWeight;
	}

}
