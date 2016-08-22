package com.WeightMonitor.web.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.easyrules.api.RuleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.WeightMonitor.web.beans.Alerts;
import com.WeightMonitor.web.constants.Constants;
import com.WeightMonitor.web.dao.WeightOperations;
import com.WeightMonitor.web.daoImpl.WeightOperationsImpl;
import com.WeightMonitor.web.utils.WeightUtils;

@Rule(name = "OverWeightWatcher",
description = "This rule is fired and followed if the person is overweight")
@Component
public class OverweightRule implements RuleListener{

	//@Autowired
	WeightUtils weightUtils;
	
	//@Autowired
	WeightOperations weightOperations;
	
	 /**
     * The user input which represents the data
     * that the rule will operate on.
     */
    private Alerts alertEntry;

    @Condition
    public boolean checkAlert() throws Exception{
        //The rule should be applied only if
        //user's weight is overweight
    	return alertEntry.getStatus().equals(Constants.OVERWEIGHT);
    }

    @Action
    public void weightAction() throws Exception {
    }

	@Override
	public void beforeExecute(org.easyrules.api.Rule rule) {
		// TODO Auto-generated method stub
		System.out.println("Checking the WeightRules");
	}

	@Override
	public void onSuccess(org.easyrules.api.Rule rule) {
		// TODO Auto-generated method stub
		System.out.println("Person is Overerweight!!");
		if(weightOperations == null){
			weightOperations = new WeightOperationsImpl(new WeightUtils());
		}
		weightOperations.insertAlertData(alertEntry);
	}
	
	@Action
	public void insertOverweightDataIfNeeded() throws Exception  {
		// TODO Auto-generated method stub
		System.out.println("Person is Overweight!!");
		this.weightOperations.insertAlertData(alertEntry);
	}

	@Override
	public void onFailure(org.easyrules.api.Rule rule, Exception exception) {
	}
	
	public Alerts getAlertEntry() {
		return alertEntry;
	}

	public void setAlertEntry(Alerts alertEntry) {
		this.alertEntry = alertEntry;
	}

	public WeightUtils getWeightUtils() {
		return weightUtils;
	}

	public void setWeightUtils(WeightUtils weightUtils) {
		this.weightUtils = weightUtils;
	}

	public WeightOperations getWeightOperations() {
		return weightOperations;
	}

	public void setWeightOperations(WeightOperations weightOperations) {
		this.weightOperations = weightOperations;
	}

}
