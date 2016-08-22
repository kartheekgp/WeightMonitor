package com.WeightMonitor.web.utils;

import java.sql.Timestamp;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import com.WeightMonitor.web.beans.Alerts;
import com.WeightMonitor.web.beans.Metrics;
import com.WeightMonitor.web.beans.RangeForTimestamp;
import com.WeightMonitor.web.constants.Constants;
import com.mongodb.MongoClient;
import com.WeightMonitor.web.exceptions.*;
import com.WeightMonitor.web.rules.WeightRulesLauncher;
@Component
@PropertySource("classpath:config.properties")
public class WeightUtils {

	@Value("${mongo.db.name}")
	private String mongodbName;
	
	@Value("${person.weight.inPounds}")
	private String weight;
	
	@Value("${person.overweight.threshold.percentage}")
	private String overWeightPercentage;
	
	@Value("${person.underweight.threshold.percentage}")
	private String underWeightPercentage;
	
	@Autowired
	WeightRulesLauncher weightRulesLauncher;
	
	public WeightUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public Datastore readDatastore(){
		final Morphia morphia = new Morphia();

		// tell Morphia where to find your classes
		// can be called multiple times with different packages or classes
		morphia.mapPackage("com.WeightMonitor.web");
		// create the Datastore connecting to the default port on the local host
		final Datastore datastore = morphia.createDatastore(new MongoClient(), mongodbName);
		datastore.ensureIndexes();
		return datastore;
	}
	
	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	public int getBaseWeight(){
		return Integer.parseInt(weight);
	} 
	
	public long getOverWeightPercentage(){
		return Long.parseLong(overWeightPercentage);
	} 
	
	public long getUnderWeightPercentage(){
		return Long.parseLong(underWeightPercentage);
	}
	
	public Alerts createAlert(Metrics metrics){
		Alerts alertEntry = convertFromMetricsToAlert(metrics);
		long weight  = metrics.getCurrentWeight();
		//Check for overweight
		if(isOverweight(weight)){
			alertEntry.setStatus(Constants.OVERWEIGHT);
		}else if(isUnderweight(weight)){
			//Check for underweight
			alertEntry.setStatus(Constants.UNDERWEIGHT);
		}else{
			alertEntry.setStatus(Constants.GOOD_WEIGHT);
		}
		return alertEntry;
	}
	
	public boolean isOverweight(long weight){
		if(weight >= (getBaseWeight()*(1+(getOverWeightPercentage()/100.00)))){
			return true;
		}
		return false;
	}
	
	public boolean isUnderweight(long weight){
		if(weight <= (getBaseWeight()*(1-(getOverWeightPercentage()/100.00)))){
			return true;
		}
		return false;
	}
	
	public Exception returnValidException(String weightString){
		if(weightString.equals(Constants.OVERWEIGHT)){
			return new OverweightException();
		}else if(weightString.equals(Constants.UNDERWEIGHT)){
			return new UnderweightException();
		}
		return null;
	}
	
	public Alerts convertFromMetricsToAlert(Metrics metrics){
		Alerts alertEntry = new Alerts();
		alertEntry.setCurrentWeight(metrics.getCurrentWeight());
		alertEntry.setTimestamp(metrics.getTimeStamp());
		alertEntry.setBaseWeight(metrics.getBaseWeight());
		return alertEntry;
	}
	
	public RangeForTimestamp getTimestampFromStringForBean(RangeForTimestamp rangeForTimestamp){
		java.util.Date fromDate = new Timestamp(Long.parseLong(rangeForTimestamp.getTimeStampFrom()));
		java.sql.Timestamp fromTs = new Timestamp(fromDate.getTime());
		java.util.Date toDate = new Timestamp(Long.parseLong(rangeForTimestamp.getTimeStampTo()));
		java.sql.Timestamp toTs = new Timestamp(toDate.getTime());
		rangeForTimestamp.setTsFrom(fromTs);
		rangeForTimestamp.setTsTo(toTs);
		return rangeForTimestamp;
	}

}
