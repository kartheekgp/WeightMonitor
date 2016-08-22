package com.WeightMonitor.web.rules;

import java.util.EmptyStackException;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.easyrules.api.RuleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.WeightMonitor.web.beans.Alerts;
import com.WeightMonitor.web.dao.WeightOperations;
import com.WeightMonitor.web.daoImpl.WeightOperationsImpl;
import com.WeightMonitor.web.exceptions.UnderweightException;
import com.WeightMonitor.web.utils.WeightUtils;

@Rule(name = "WeightWatcher",
description = "Watching Weight")
@Component
public class WeightRules implements RuleListener{

	@Autowired
	WeightUtils weightUtils;
	
	@Autowired
	WeightOperations weightOperations;
	
	 /**
     * The user input which represents the data
     * that the rule will operate on.
     */
    private Alerts alertEntry;

    @Condition
    public boolean checkAlert() throws Exception{
        //The rule should be applied only if
        //the user's response is yes (duke friend)
    	if(alertEntry.getStatus() == null){
    		return true;
    	}
    	//throw new Exception();
    	//org.easyrules.api.Rule rule = null;
    	///Exception e = weightUtils.returnValidException(alertEntry.getStatus());
    	this.onFailure(null, null);
    	return false;
        //return true;
    }

    @Action
    public void weightAction() throws Exception {
        //Person is in the weight range
    	if(alertEntry.getStatus() != null){
    		throw new Exception();
    	}
    }

	@Override
	public void beforeExecute(org.easyrules.api.Rule rule) {
		// TODO Auto-generated method stub
		System.out.println("Checking the WeightRules");
	}

	@Override
	public void onSuccess(org.easyrules.api.Rule rule) {
		// TODO Auto-generated method stub
		System.out.println("Success");
	}

	@Override
	public void onFailure(org.easyrules.api.Rule rule, Exception exception) {
		// TODO Auto-generated method stub
		try{
			System.out.println("ALERT ----- person is == "+alertEntry.getStatus());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			
		}
		
	}
	
	public Alerts getAlertEntry() {
		return alertEntry;
	}

	public void setAlertEntry(Alerts alertEntry) {
		this.alertEntry = alertEntry;
	}

}
