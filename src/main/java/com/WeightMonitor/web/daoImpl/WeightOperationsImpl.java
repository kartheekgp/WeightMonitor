package com.WeightMonitor.web.daoImpl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.WeightMonitor.web.beans.Alerts;
import com.WeightMonitor.web.beans.Metrics;
import com.WeightMonitor.web.beans.RangeForTimestamp;
import com.WeightMonitor.web.dao.WeightOperations;
import com.WeightMonitor.web.serviceImpl.AddWeightImpl;
import com.WeightMonitor.web.utils.WeightUtils;

@Component
public class WeightOperationsImpl implements WeightOperations{
	
	@Autowired
	WeightUtils weightUtils;
	
	private static final Logger logger = Logger.getLogger(AddWeightImpl.class);
	
	public WeightOperationsImpl(WeightUtils weightUtils) {
		// TODO Auto-generated constructor stub
		this.weightUtils = weightUtils;
	}
	
	public WeightOperationsImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Metrics> readData(){
		logger.info("Entering readData");
		final Datastore datastore = weightUtils.readDatastore();
		final Query<Metrics> query = datastore.createQuery(Metrics.class);
		List<Metrics> meticsList = query.asList();
		return meticsList;
	}
	
	@Override
	public List<Alerts> readAlertData(){
		logger.info("Entering readAlertData");
		final Datastore datastore = weightUtils.readDatastore();
		final Query<Alerts> query = datastore.createQuery(Alerts.class);
		List<Alerts> alertsList = query.asList();
		return alertsList;
	}
	
	@Override
	public void insertWeightData(Metrics metrics){
		logger.info("Entering insertWeightData");
		final Datastore datastore = weightUtils.readDatastore();
		datastore.save(metrics);
	}
	
	@Override
	public void insertAlertData(Alerts alerts){
		logger.info("Entering insertAlertData");
		final Datastore datastore = weightUtils.readDatastore();
		datastore.save(alerts);
	}
	
	public void insertAlertDataFromRule(Alerts alerts){
		logger.info("Entering insertAlertDataFromRule");
		WeightUtils weightUtils = new WeightUtils();
		weightUtils.getBaseWeight();
		final Datastore datastore = weightUtils.readDatastore();
		datastore.save(alerts);
	}
	
	public List<Metrics> returnMetricsForRange(RangeForTimestamp rangeForTimestamp){
		logger.info("Entering returnMetricsForRange");
		final Datastore datastore = weightUtils.readDatastore();
		final Query<Metrics> q = datastore.createQuery(Metrics.class);
		//q.put("date", new BasicDBObject("$gt", t1).append("$lte", t2));
		q.criteria("timeStamp").lessThanOrEq(rangeForTimestamp.getTsTo());
		q.criteria("timeStamp").greaterThanOrEq(rangeForTimestamp.getTsFrom());
		return q.asList();
	}
	
	public List<Alerts> returnAlertsForRange(RangeForTimestamp rangeForTimestamp){
		logger.info("Entering returnAlertsForRange");
		final Datastore datastore = weightUtils.readDatastore();
		final Query<Alerts> q = datastore.createQuery(Alerts.class);
		//q.put("date", new BasicDBObject("$gt", t1).append("$lte", t2));
		q.criteria("timestamp").lessThanOrEq(rangeForTimestamp.getTsTo());
		q.criteria("timestamp").greaterThanOrEq(rangeForTimestamp.getTsFrom());
		return q.asList();
	}
	
}
