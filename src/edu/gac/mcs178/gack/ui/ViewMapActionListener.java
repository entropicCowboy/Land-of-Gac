package edu.gac.mcs178.gack.ui;

import edu.gac.mcs178.gack.domain.Person;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewMapActionListener implements ActionListener {
	
	private GraphicalUserInterface gui;
	private Person player;
	
	public ViewMapActionListener(GraphicalUserInterface gui, Person player) {
		super();
		this.gui = gui;
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// added image of map to display when player looks around
		JFrame frame = new JFrame("Image Display");
		ImageIcon originalIcon = new ImageIcon("src/edu/gac/mcs178/gack/ui/Land of Gac Map.png");
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(447, 519, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(label, BorderLayout.CENTER);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
