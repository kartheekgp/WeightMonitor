package com.WeightMonitor.web.beans;

import java.sql.Timestamp;
import java.util.*;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity("metrics")
//@Index can be added if needed
public class Metrics {
    @Reference
	private long currentWeight;
    @Reference
    private Timestamp timeStamp;
    @Reference
    private long baseWeight;
	
	public long getCurrentWeight() {
		return currentWeight;
	}
	public void setCurrentWeight(long currentWeight) {
		this.currentWeight = currentWeight;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public long getBaseWeight() {
		return baseWeight;
	}
	public void setBaseWeight(int baseWeight) {
		this.baseWeight = baseWeight;
	}
}
