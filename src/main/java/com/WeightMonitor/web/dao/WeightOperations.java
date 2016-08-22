package com.WeightMonitor.web.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.WeightMonitor.web.beans.Alerts;
import com.WeightMonitor.web.beans.Metrics;
import com.WeightMonitor.web.beans.RangeForTimestamp;

@Component
public interface WeightOperations {
	public List<Metrics> readData();
	public List<Alerts> readAlertData();
	public void insertWeightData(Metrics metrics);
	public void insertAlertData(Alerts alerts);
	public List<Metrics> returnMetricsForRange(RangeForTimestamp rangeForTimestamp);
	public List<Alerts> returnAlertsForRange(RangeForTimestamp rangeForTimestamp);
}
