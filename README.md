drools-Example
==============

Example project to show Drools Expert knowledge based rule engine

==============

The example can be run in 2 ways

	1. Run it as a maven project by calling "mvn clean install" and then executing the relevant classes through the command line. Please note that you would need to add the generated jar (drools-Examples-1.0.jar) and all the dependent jars in the classpath for the examples to run.
	2. Import the project in Eclipe (using the import utility) and then executing the corresponding source files.


The examples are

	1. com.anil.drools.service.FireAlarmService
		a. The example shows the knowlege based engine using a fire alarm system example. Once you run the example, the log4j output is generated in the "droolsExample.log" file in the root folder.
		b. Refer to the source example and corresponding .drl file for the source.

	2. com.anil.drools.service.InsurancePolicyDecisionTableService
		a. The example uses a decision table to drive the knowlege based engine. The output is generated on the console.
		b. Refer to the source example and corresponding .xls file for the source.

	3. com.anil.drools.service.FireAlarmServiceWithChangeSet
		a. The example is same as the "FireAlarmService" example except that it assumes that the rules are defined in the Guvnor rule repository.
		b. Guvnor business rule management system is outside the scope of this demo. The example is added for reference only.
