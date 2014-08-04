package com.anil.drools.service;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.anil.drools.model.decisiontable.Driver;
import com.anil.drools.model.decisiontable.Policy;

public class InsurancePolicyDecisionTableService {

	public void execute() {

		DecisionTableConfiguration dtconf = KnowledgeBuilderFactory
				.newDecisionTableConfiguration();
		dtconf.setInputType(DecisionTableInputType.XLS);

		KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		Resource xlsResource = ResourceFactory.newClassPathResource(
				"policyPrice.xls", getClass());

		knowledgeBuilder.add(xlsResource, ResourceType.DTABLE, dtconf);

		if (knowledgeBuilder.hasErrors()) {
			throw new RuntimeException(knowledgeBuilder.getErrors().toString());
		}

		KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(knowledgeBuilder
				.getKnowledgePackages());

		/*
		 * StatefulKnowledgeSession will maintain the reference to the objects
		 * that were added in the current session
		 */
		StatefulKnowledgeSession ksession = knowledgeBase
				.newStatefulKnowledgeSession();

		Driver driver = new Driver();
		driver.setAge(24);
		driver.setLocationRiskProfile("LOW");

		Policy policy = new Policy();
		policy.setPolicyType("COMPREHENSIVE");

		ksession.insert(driver);
		ksession.insert(policy);

		ksession.fireAllRules();
		ksession.dispose();

		System.out.println("The policy price set is: "
				+ policy.getPolicyBasePrice());

	}

	public static void main(String[] args) {
		InsurancePolicyDecisionTableService insuranceService = new InsurancePolicyDecisionTableService();
		insuranceService.execute();
	}
}
