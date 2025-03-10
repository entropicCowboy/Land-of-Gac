package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.Utility;

public class Witch extends AutoPerson {
	
	private Place pond;
	
	public Witch(String name, Place place, int threshold, Place pond) {
		super(name, place, threshold);
		this.pond = pond;
	}

	@Override
	public void act() {
		List<Person> others = otherPeopleAtSamePlace();
		if (!others.isEmpty()) {
			Person victim = others.get(Utility.randInt(others.size()));
			curse(victim);
		} else {
			super.act();
		}
	}

	public void curse(Person person) {
		if (person.getIsFrog()) {
			say("I'm sending you back to the pond!");
			person.moveTo(pond);
		}
		else {
			say("Hah hah hah, I'm going to turn you into a frog, " + person);
			if (person.getClass().equals(Princess.class)) {	// don't let witch curse Princess Tiana
				say("Why didn't that work? Guess I'll find another victim");
			}
			else {
				turnIntoFrog(person);
				say("Hee hee " + person + " looks better in green!");
			}
		}
	}
	
	public void turnIntoFrog(Person person) {
		person.setIsFrog(true);
		// need to copy person.getPossessions() in order to avoid a ConcurrentModificationException
		List<Thing> personsPossessions = new ArrayList<Thing>(person.getPossessions());
		for (Thing thing : personsPossessions) {
			person.lose(thing);
		}
		person.say("Ribbitt!");
		person.moveTo(pond);
	}
}
