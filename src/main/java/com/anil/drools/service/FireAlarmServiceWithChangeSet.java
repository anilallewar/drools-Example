package com.anil.drools.service;

import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

import com.anil.drools.model.Fire;
import com.anil.drools.model.Room;
import com.anil.drools.model.Sprinkler;
import com.anil.drools.util.StreamUtils;
import com.anil.drools.util.XMLParseUtils;

public class FireAlarmServiceWithChangeSet {

	private static Logger LOGGER = Logger
			.getLogger(FireAlarmServiceWithChangeSet.class.getName());

	public void execute() {

		final InputStream is = StreamUtils
				.streamFromClasspathResource("com/anil/drools/service/FireAlarm_ChangeSet.xml");
		if (is == null) {
			throw new RuntimeException(String.format(
					"The drools changeset %s could not be found.",
					"FireAlarm_ChangeSet.xml"));
		}
		final Document doc = XMLParseUtils.parse(is);

		String changeSetXMLString = doc.asXML();

		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();

		/*
		 * The KnowlegeBase is a repository of all the application's knowledge
		 * definitions. It will contain rules, processes, functions, and type
		 * models but NOT DATA. Creating the KnowlegeBase can be heavy, whereas
		 * session creation is very light, so it is recommended that Knowledge
		 * Bases be cached where possible to allow for repeated session
		 * creation.
		 */
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

		kbuilder.add(ResourceFactory.newReaderResource(new StringReader(
				changeSetXMLString)), ResourceType.CHANGE_SET);

		if (kbuilder.hasErrors()) {
			LOGGER.error(kbuilder.getErrors().toString());
		}
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		/*
		 * StatefulKnowledgeSession will maintain the reference to the objects
		 * that were added in the current session
		 */
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

		// Advanced debugging options
		/*
		 * ksession.addEventListener(new DefaultWorkingMemoryEventListener());
		 * ksession.addEventListener(new DebugAgendaEventListener());
		 */

		String[] names = new String[] { "kitchen", "bedroom", "office",
				"livingroom" };

		Map<String, Room> name2room = new HashMap<String, Room>();

		for (String name : names) {
			Room room = new Room(name);
			name2room.put(name, room);
		}

		LOGGER.debug("----------------------------------");
		LOGGER.debug("Before calling fireAllOk()");
		this.fireAllOk(ksession, name2room);

		LOGGER.debug("----------------------------------");
		LOGGER.debug("Before calling startAndStopSprinkler()");
		this.startAndStopSprinkler(ksession, name2room);

		LOGGER.debug("----------------------------------");

		ksession.dispose();

	}

	/**
	 * Check the rules when there is no fire
	 * 
	 * @param ksession
	 * @param name2room
	 */
	private void fireAllOk(StatefulKnowledgeSession ksession,
			Map<String, Room> name2room) {

		Entry<String, Room> entry;
		String key;
		Sprinkler sprinkler = null;

		Iterator<Map.Entry<String, Room>> roomNameIterator = name2room
				.entrySet().iterator();

		while (roomNameIterator.hasNext()) {

			entry = roomNameIterator.next();
			key = entry.getKey();
			/*
			 * If we comment out the code below, i.e, there are NO sprinklers
			 * then all Sprinkler rules will NOT be run. Otherwise, if we add
			 * only Sprinklers for "bedroom", then only that rule gets fired.
			 */

			if ("bedroom".equalsIgnoreCase(key)
					|| "office".equalsIgnoreCase(key)) {
				sprinkler = new Sprinkler(entry.getValue());
				/*
				 * When you insert a fact, it is examined for matches against
				 * the rules. This means all of the work for deciding about
				 * firing or not firing a rule is done during insertion; no
				 * rule, however, is executed until you call fireAllRules(),
				 * which you call after you have finished inserting your facts.
				 */
				ksession.insert(sprinkler);
			}

		}
		ksession.fireAllRules();
	}

	/**
	 * Check the rules by adding Fire and Sprinker objects and then by
	 * retracting the Fire objects.
	 * 
	 * @param ksession
	 * @param name2room
	 */
	private void startAndStopSprinkler(StatefulKnowledgeSession ksession,
			Map<String, Room> name2room) {

		Fire bedroomFire = new Fire(name2room.get("bedroom"));
		Fire officeFire = new Fire(name2room.get("office"));

		FactHandle bedroomFireHandle = ksession.insert(bedroomFire);
		FactHandle officeFireHandle = ksession.insert(officeFire);

		for (Object object : ksession.getObjects()) {
			LOGGER.debug("Knowledge Session contains: " + object);
		}

		ksession.fireAllRules();

		LOGGER.debug("----------------------------------");
		LOGGER.debug("Before retracing the data from session");
		ksession.retract(bedroomFireHandle);
		ksession.retract(officeFireHandle);

		for (Object object : ksession.getObjects()) {
			LOGGER.debug("Knowledge Session contains: " + object);
		}

		ksession.fireAllRules();
	}

	public static void main(String[] args) {
		FireAlarmServiceWithChangeSet alarmService = new FireAlarmServiceWithChangeSet();
		alarmService.execute();
	}
}
