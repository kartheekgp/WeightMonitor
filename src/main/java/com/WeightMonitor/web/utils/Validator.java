package com.WeightMonitor.web.utils;

import org.springframework.stereotype.Component;

import com.WeightMonitor.web.beans.Metrics;
import com.WeightMonitor.web.beans.MetricsBean;

@Component
public class Validator {

	public Validator() {
		// TODO Auto-generated constructor stub
	}

	public boolean validateWeightRequest(Metrics metrics){
		if(metrics== null){
			return false;
		}
		if(metrics.getCurrentWeight() <= 0 || metrics.getBaseWeight() <= 0 || metrics.getTimeStamp() == null){
			return false;
		}
		return true;
	}
	
	public boolean validateMetricsBeanRequest(MetricsBean metricsBean){
		if(metricsBean== null){
			return false;
		}
		if(metricsBean.getValue() == null || metricsBean.getValue() == "" || metricsBean.getTimeStamp() == null || metricsBean.getTimeStamp() == ""){
			return false;
		}
		return true;
	}
}
