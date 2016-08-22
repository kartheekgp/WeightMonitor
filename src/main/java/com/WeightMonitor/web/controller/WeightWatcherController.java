package com.WeightMonitor.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.WeightMonitor.web.beans.Alerts;
import com.WeightMonitor.web.beans.Metrics;
import com.WeightMonitor.web.beans.MetricsBean;
import com.WeightMonitor.web.beans.RangeForTimestamp;
import com.WeightMonitor.web.service.Addweight;

@Controller
public class WeightWatcherController {

	@Autowired
	Addweight addweight;
	
	private static final Logger logger = Logger.getLogger(WeightWatcherController.class);
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		logger.info("Call made :- /test");
		return "Test";

	}
	//Reads and displays data from metrics collection
	@RequestMapping(value = "/metrics/readData", method = RequestMethod.GET)
	public String readData(ModelMap model) {
		logger.info("Call made :- /metrics/readData");
		List<Metrics> metricsList = addweight.readData();
		model.addAttribute("dataList", metricsList);
		return "readData";
	}
	
	//Reads and displays data from alerts collection
	@RequestMapping(value = "/alert/readData", method = RequestMethod.GET)
	public String readAlertData(ModelMap model) {
		logger.info("Call made :- /alert/readData");
		List<Alerts> alertsList = addweight.readAlertData();
		model.addAttribute("dataList", alertsList);
		return "readAlertData";
	}
	
//	@RequestMapping(value = "/Experiment", method = RequestMethod.GET)
//	public String experiment(ModelMap model) {
//		MetricsBean metricsBean = new MetricsBean();
//		metricsBean.setTimeStamp("1628062848734");
//		metricsBean.setValue("164");
//		addweight.insertWeightData(metricsBean);
//		System.out.println("Experiment");
//		return null;
//	}
	
	//http://localhost:8080/WeightWatchers/metrics/readByTimeRange?from=1408062848734&to=1658062848734
	//Reads from metrics collection for the given time range
	//GET method
	@RequestMapping(value = "/metrics/readByTimeRange", method = RequestMethod.GET)
	public String metricsReadByTimeRange(ModelMap model,@RequestParam("from") String from,@RequestParam("to") String to) {
		logger.info("Call made :- /metrics/readByTimeRange");
		logger.info("from   :- "+from);
		logger.info("to     :- "+to);
		RangeForTimestamp rangeForTimestamp = new RangeForTimestamp();
		rangeForTimestamp.setTimeStampFrom(from);
		rangeForTimestamp.setTimeStampTo(to);
		List<Metrics> metricsList =  addweight.metricsReadByTimeRange(rangeForTimestamp);
		model.addAttribute("dataList", metricsList);
		return "readData";
		//model.addAttribute("message", "Spring 3 MVC Hello World");
	}
	
	//http://localhost:8080/WeightWatchers/alerts/readByTimeRange?from=1408062848734&to=1658062848734
	//Reads from alerts collection for the given time range
	//GET method
	@RequestMapping(value = "/alerts/readByTimeRange", method = RequestMethod.GET)
	public String alertsReadByTimeRange(ModelMap model,@RequestParam("from") String from,@RequestParam("to") String to) {
		logger.info("Call made :- /alerts/readByTimeRange");
		logger.info("from   :- "+from);
		logger.info("to     :- "+to);
		RangeForTimestamp rangeForTimestamp = new RangeForTimestamp();
		rangeForTimestamp.setTimeStampFrom(from);
		rangeForTimestamp.setTimeStampTo(to);
		List<Alerts> alertsList =  addweight.alertsReadByTimeRange(rangeForTimestamp);
		model.addAttribute("dataList", alertsList);
		//model.addAttribute("message", "Spring 3 MVC Hello World");
		return "readAlertData";
	}
	
	//Reads from metrics collection for the given time range
	//POST method
	@RequestMapping(value = "/metrics/readByTimeRangePOST", method = RequestMethod.POST)
	public String metricsReadByTimeRangePOST(ModelMap model,@RequestBody(required = true) RangeForTimestamp rangeForTimestamp) {
		logger.info("Call made :- /metrics/readByTimeRangePOST");
		List<Metrics> metricsList =  addweight.metricsReadByTimeRange(rangeForTimestamp);
		model.addAttribute("dataList", metricsList);
		return "readData";
		//model.addAttribute("message", "Spring 3 MVC Hello World");
	}
	
	//Reads from alerts collection for the given time range
	//POST method
	@RequestMapping(value = "/alerts/readByTimeRangePOST", method = RequestMethod.POST)
	public String alertsReadByTimeRangePOST(ModelMap model,@RequestBody(required = true) RangeForTimestamp rangeForTimestamp) {
		logger.info("Call made :- /alerts/readByTimeRangePOST");
		List<Alerts> alertsList =  addweight.alertsReadByTimeRange(rangeForTimestamp);
		model.addAttribute("dataList", alertsList);
		//model.addAttribute("message", "Spring 3 MVC Hello World");
		return "readAlertData";
	}
	
	//Creates new data
	//POST method
	@RequestMapping(value = "/create", method = RequestMethod.POST)//, consumes = "text/html", headers = "Content-Type=application/json;odata=verbose")
	public String insertWeightdata(ModelMap model,@RequestBody(required = true) MetricsBean metricsBean) {
		logger.info("Call made :- create");
		addweight.insertWeightData(metricsBean);
		System.out.println("Write Data");
		return null;
	}
	
	//http://localhost:8080/WeightWatchers/createGET?timeStamp=1408062848734&value=155
	//Creates new data
	//GET method
	@RequestMapping(value = "/createGET", method = RequestMethod.GET)
	public String insertWeightdataGET(ModelMap model,@RequestParam("timeStamp") String timeStamp,@RequestParam("value") String value) {
		logger.info("Call made :- createGET");
		logger.info("timeStamp :- "+timeStamp);
		logger.info("value     :- "+value);
		MetricsBean metricsBean = new MetricsBean();
		metricsBean.setTimeStamp(timeStamp);
		metricsBean.setValue(value);
		addweight.insertWeightData(metricsBean);
		System.out.println("Write Data");
		return null;
	}
	
	//http://localhost:8080/WeightWatchers/createWithWeightOnly?value=155
	//Creates new data wherein only weight is sent
	//GET method
	@RequestMapping(value = "/createWithWeightOnly", method = RequestMethod.GET)
	public String insertWeightdataBaseWeightOnly(ModelMap model,@RequestParam("value") String value) {
		logger.info("Call made :- createWithWeightOnly");
		logger.info("value     :- "+value);
		addweight.insertWeightDataWithValue(value);
		System.out.println("Write Data");
		return null;
	}

}