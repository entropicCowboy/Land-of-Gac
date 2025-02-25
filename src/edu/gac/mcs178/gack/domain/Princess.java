package edu.gac.mcs178.gack.domain;

import java.util.List;

public class Princess extends AutoPerson {

    // private AutoPerson auto;
    private boolean gaveConsent;

    public void setGaveConsent() { this.gaveConsent = true; }
    public Boolean getGaveConsent() { return this.gaveConsent; }
		
	public Princess(String name, Place place, int threshold) {
		super(name, place, threshold);
        this.gaveConsent = false;
	}

	@Override
	public void act() {
		List<Person> others = otherPeopleAtSamePlace();
		if (!others.isEmpty()) {
		} else {
			super.act();
		}
	}

	public Boolean kiss(Person person) {
		if (person.getIsFrog()) {
            // if the person is the player, they must have consent
            if (person.getClass() != AutoPerson.class) {
                if (this.gaveConsent) {
                    turnIntoHuman(person);
                }
                else {
                    say("Back off. You do not have my consent!");
                }
            }
            // person is an autoperson
            else {
                turnIntoHuman(person);
            }
		}
        return this.gaveConsent;
	}

    public void turnIntoHuman(Person person) {
        person.setIsFrog(false);
        say("Oh- a little slimey. Glad you're back to normal though.");
    }
	
}

