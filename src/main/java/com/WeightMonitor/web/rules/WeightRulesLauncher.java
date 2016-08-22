package com.WeightMonitor.web.rules;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.WeightMonitor.web.beans.Alerts;
import com.WeightMonitor.web.dao.WeightOperations;
import com.WeightMonitor.web.utils.Validator;
import com.WeightMonitor.web.utils.WeightUtils;

@Component
public class WeightRulesLauncher {

	public WeightRulesLauncher() {
		// TODO Auto-generated constructor stub
	}
	
	Alerts alertEntry;
	WeightOperations weightOperations;
	WeightUtils weightUtils;
	
	public void alertTrigger(){

	        /**
	         * Declare the rule
	         */
	        UnderweightRule uw = new UnderweightRule();
	        OverweightRule ow = new OverweightRule();

	        /**
	         * Set business data to operate on
	         */
	        uw.setAlertEntry(this.alertEntry);
	        uw.setWeightOperations(this.weightOperations);
	        uw.setWeightUtils(this.weightUtils);
	        ow.setAlertEntry(this.alertEntry);
	        ow.setWeightOperations(this.weightOperations);
	        ow.setWeightUtils(this.weightUtils);
	        /**
	         * Create a rules engine and register the business rule
	         */
	        //RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine().build();
	        RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine().build();
	        rulesEngine.registerRule(uw);
	        rulesEngine.registerRule(ow);
//	        RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine()
//	        	    .withRuleListener(wr)
//	        	    .build();
	        //rulesEngine.registerRule(wr);

	        /**
	         * Fire rules
	         */
	        rulesEngine.fireRules();
	}

	public Alerts getAlertEntry() {
		return alertEntry;
	}

	public void setAlertEntry(Alerts alertEntry) {
		this.alertEntry = alertEntry;
	}

	public WeightOperations getWeightOperations() {
		return weightOperations;
	}

	public void setWeightOperations(WeightOperations weightOperations) {
		this.weightOperations = weightOperations;
	}

	public WeightUtils getWeightUtils() {
		return weightUtils;
	}

	public void setWeightUtils(WeightUtils weightUtils) {
		this.weightUtils = weightUtils;
	}

}
