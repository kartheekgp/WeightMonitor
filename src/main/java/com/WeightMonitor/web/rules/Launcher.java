package com.WeightMonitor.web.rules;

import java.util.Scanner;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;

public class Launcher {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you a friend of duke?[yes/no]:");
        String input = scanner.nextLine();

        /**
         * Declare the rule
         */
        WeightRules wr = new WeightRules();

        /**
         * Set business data to operate on
         */
        //wr.setInput(input.trim());

        /**
         * Create a rules engine and register the business rule
         */
        //RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine().build();
        RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine()
        	    .withRuleListener(wr)
        	    .build();
        rulesEngine.registerRule(wr);

        /**
         * Fire rules
         */
        rulesEngine.fireRules();

    }
}