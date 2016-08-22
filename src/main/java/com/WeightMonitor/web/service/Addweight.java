package com.WeightMonitor.web.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.WeightMonitor.web.beans.Alerts;
import com.WeightMonitor.web.beans.Metrics;
import com.WeightMonitor.web.beans.MetricsBean;
import com.WeightMonitor.web.beans.RangeForTimestamp;

@Component
public interface Addweight {
	public List<Metrics> readData();
	public void insertWeightData(MetricsBean metrics);
	public List<Alerts> readAlertData();
	public List<Metrics> metricsReadByTimeRange(RangeForTimestamp rangeForTimestamp);
	public List<Alerts> alertsReadByTimeRange(RangeForTimestamp rangeForTimestamp);
	public void insertWeightDataWithValue(String value);
	
}
