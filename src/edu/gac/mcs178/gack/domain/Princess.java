package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

public class Princess extends AutoPerson {
	
	private Place pond;
	
	public Princess(String name, Place place, int threshold, Place pond) {
		super(name, place, threshold);
		this.pond = pond;
	}

	@Override
	public void act() {
		List<Person> others = otherPeopleAtSamePlace();
		if (!others.isEmpty()) {
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
			turnIntoFrog(person);
			say("Hee hee " + person + " looks better in green!");
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

