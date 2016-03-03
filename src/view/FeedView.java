package view;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import person.Person;
import supplies.Food;
import model.Knapsack;
import model.Squad;

public class FeedView extends JPanel implements ActionListener, Observer {

	private Knapsack jansport;
	private Squad brick;
	private Person[] people;
	private JComboBox edibles;
	private JComboBox<Person> players;

	public FeedView(Squad s) {
		this.jansport = s.getKnapsack();
		this.brick = s;
		people = new Person[s.getNumPlayers()];
		jansport.addObserver(this);
		setLayout(new FlowLayout());
		JButton feed = new JButton("Feed");
		feed.addActionListener(this);
		add(feed);
		for (int i = 0; i < brick.getNumPlayers(); i++) {
			people[i] = s.getPlayer(i);
		}
		players = new JComboBox<Person>(people);
		add(players);
		JLabel to = new JLabel("with");
		add(to);
		refresh();
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		try{brick.feed((Food) edibles.getSelectedItem(),
				(Person) players.getSelectedItem());
		}catch(Exception e){
					
				}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		refresh();
		JFrame topframe = (JFrame) SwingUtilities.getWindowAncestor(this);
		topframe.pack();
	}

	public void refresh() {
		if (!(edibles == null)) {
			remove(edibles);
			edibles.removeAll();
		}
		edibles = new JComboBox<Food>(jansport.getEdibleSupplies());
		add(edibles);

}
}