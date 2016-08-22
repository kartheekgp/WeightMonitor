package com.WeightMonitor.web.serviceImpl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.WeightMonitor.web.beans.Alerts;
import com.WeightMonitor.web.beans.Metrics;
import com.WeightMonitor.web.beans.MetricsBean;
import com.WeightMonitor.web.beans.RangeForTimestamp;
import com.WeightMonitor.web.dao.WeightOperations;
import com.WeightMonitor.web.rules.WeightRulesLauncher;
import com.WeightMonitor.web.service.Addweight;
import com.WeightMonitor.web.utils.Validator;
import com.WeightMonitor.web.utils.WeightUtils;
@Component
public class AddWeightImpl implements Addweight{

	@Autowired
	WeightOperations weightOperations;
	
	@Autowired
	Validator validator;
	
	@Autowired
	WeightUtils weightUtils;
	
	@Autowired
	WeightRulesLauncher weightRulesLauncher;
	
	private static final Logger logger = Logger.getLogger(AddWeightImpl.class);
	
	public AddWeightImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Metrics> readData(){
		logger.info("Entering readData()");
		return weightOperations.readData();
	}
	
	public List<Alerts> readAlertData(){
		logger.info("Entering readAlertData()");
		return weightOperations.readAlertData();
	}
	
	public void insertWeightData(MetricsBean metricsBean){
		logger.info("Entering insertWeightData");
		if(validator.validateMetricsBeanRequest(metricsBean)){
			Metrics metrics = new Metrics();
			java.util.Date sentDate = new Timestamp(Long.parseLong(metricsBean.getTimeStamp()));
			int weightValue = Integer.parseInt(metricsBean.getValue());
			java.sql.Timestamp ts = new Timestamp(sentDate.getTime());
			metrics.setTimeStamp(ts);
			metrics.setCurrentWeight(weightValue);
			metrics.setBaseWeight(weightUtils.getBaseWeight());
			if(validator.validateWeightRequest(metrics)){
				weightOperations.insertWeightData(metrics);
				//Validate if an alert is to be triggered
				Alerts alert = weightUtils.createAlert(metrics);
				//Use EasyRule to create an alert
				weightRulesLauncher.setAlertEntry(alert);
				weightRulesLauncher.setWeightOperations(weightOperations);
				weightRulesLauncher.setWeightUtils(weightUtils);
				weightRulesLauncher.alertTrigger();
			}
		}
	}
	
	
	public void insertWeightDataWithValue(String value){
		logger.info("Entering insertWeightData");
			Metrics metrics = new Metrics();
			java.util.Date date= new java.util.Date();
			java.util.Date sentDate = new Timestamp(date.getTime());
			int weightValue = Integer.parseInt(value);
			java.sql.Timestamp ts = new Timestamp(sentDate.getTime());
			metrics.setTimeStamp(ts);
			metrics.setCurrentWeight(weightValue);
			metrics.setBaseWeight(weightUtils.getBaseWeight());
			if(validator.validateWeightRequest(metrics)){
				weightOperations.insertWeightData(metrics);
				//Validate if an alert is to be triggered
				Alerts alert = weightUtils.createAlert(metrics);
				//Use EasyRule to create an alert
				weightRulesLauncher.setAlertEntry(alert);
				weightRulesLauncher.setWeightOperations(weightOperations);
				weightRulesLauncher.setWeightUtils(weightUtils);
				weightRulesLauncher.alertTrigger();
			}
	}
	
	public List<Metrics> metricsReadByTimeRange(RangeForTimestamp rangeForTimestamp){
		logger.info("Entering metricsReadByTimeRange");
			return weightOperations.returnMetricsForRange(weightUtils.getTimestampFromStringForBean(rangeForTimestamp));
	}
	
	public List<Alerts> alertsReadByTimeRange(RangeForTimestamp rangeForTimestamp){
		logger.info("Entering alertsReadByTimeRange");
		return weightOperations.returnAlertsForRange(weightUtils.getTimestampFromStringForBean(rangeForTimestamp));
	}
	
}
