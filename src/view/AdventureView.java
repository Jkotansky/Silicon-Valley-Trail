package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import person.Person;
import model.Adventure;
import model.Squad;

public class AdventureView extends JPanel {

	private Adventure adventure;
	private JPanel second;
	
	public AdventureView(Adventure adventure) {
		this.adventure = adventure;
		second = new JPanel();
		
		setLayout(new BorderLayout());
		
		Squad s = adventure.getSquad();
		
		StoreView store = new StoreView(adventure);
		KnapSackView ksv = new KnapSackView(s);
		TravelView tv = new TravelView(adventure);
		SquadView sv = new SquadView(s);
		FeedView fv = new FeedView(s);
		add(ksv,BorderLayout.CENTER);
		add(sv, BorderLayout.WEST);
		second.setLayout(new GridLayout(3,1));
		second.add(tv);
		second.add(store);
		second.add(fv);
		add(second, BorderLayout.SOUTH);
		
	}
}
