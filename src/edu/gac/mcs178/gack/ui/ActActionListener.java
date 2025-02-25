package edu.gac.mcs178.gack.ui;

import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Princess;
import edu.gac.mcs178.gack.domain.Scroll;
import edu.gac.mcs178.gack.domain.Thing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;

public class ActActionListener implements ActionListener {
	
	private static final Scroll INTSRUCTIONS = new Scroll("Act ...");
	private Thing kiss = new Thing("Give Princess Tiana a kiss");
	private Thing askConsent = new Thing("Ask for consent");
	
	private GraphicalUserInterface gui;
	private Person player;
	private JComboBox actJComboBox;
	private boolean enabled;
	private List<Scroll> scrolls;
	private Princess princess;
	private List<Person> otherPeople;

	public ActActionListener(GraphicalUserInterface gui, Person player, JComboBox actJComboBox) {
		super();
		this.gui = gui;
		this.player = player;
		this.actJComboBox = actJComboBox;
		this.enabled = true;
		scrolls = Scroll.scrollsIn(player.getPlace());
		actJComboBox.addItem(INTSRUCTIONS);
		for (Scroll scroll : scrolls) {
			actJComboBox.addItem(scroll);
		}
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	public void updateJComboBox() {
		actJComboBox.removeAllItems();
		scrolls = Scroll.scrollsIn(player.getPlace());
		actJComboBox.addItem(INTSRUCTIONS);
		for (Scroll scroll : scrolls) {
			actJComboBox.addItem(scroll);
		}

		otherPeople = player.otherPeopleAtSamePlace();
		this.princess = null; // reset princess before searching
		for (Person person: otherPeople) {
			if (person.getName().equals("Princess Tiana")) {
				this.princess = (Princess) person;
				break;
			}
		}

		if (this.princess != null && player.getPlace() == princess.getPlace()) {
			if (player.getIsFrog()) {
				actJComboBox.addItem(kiss);
				if (!princess.getGaveConsent()) {
					actJComboBox.addItem(askConsent);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enabled) {
			try { // only works if item is a scroll
				Scroll item = (Scroll) actJComboBox.getSelectedItem();
				if (!item.getName().equals(INTSRUCTIONS.getName())) {
					// if the item is a Scroll
					if (item.getClass().equals(INTSRUCTIONS.getClass())) {	// item is of the Scroll class
						gui.displayMessage("\n>>> Read " + item);
						player.read(item);
					}
				}
			} 
			catch (Exception except) {
				Thing item = (Thing) actJComboBox.getSelectedItem();
				// if the item is asking for consent
				if (item.getName().equals(askConsent.getName())) {
					player.say("Can I kiss you?");
					princess.say("Yes!");
					princess.setGaveConsent();
				}
				// if the item is giving a kiss
				if (item.getName().equals(kiss.getName())) {
					Boolean kissed = princess.kiss(player);
					if (kissed) {
						gui.displayMessage("\n>>> Princess Tiana kisses " + player.getName() + "!");
					}
				}
			}
			gui.playTurn();
		}
	}
}
