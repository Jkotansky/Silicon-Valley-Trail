package view;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import person.Person;
import model.Adventure;
import model.City;
import model.Squad;
import model.TravelObserver;

public class TravelView extends JPanel implements  ActionListener, TravelObserver{

	private Adventure adv;
	private JComboBox<City> box;
	private JLabel travelUpdate;
	
	public TravelView(Adventure a) {
		adv = a;
		setLayout(new GridLayout(0,2));
		travelUpdate = new JLabel("");
		add(travelUpdate);
		add(new JLabel("" ));
		JLabel travelTo = new JLabel("Travel to: ");
		add(travelTo);
		box = new JComboBox<>(a.getCities());
		add(box);
		box.addActionListener(this);
		adv.addTravelObserver(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		adv.travel((City)box.getSelectedItem());
	}

	@Override
	public void travelUpdate(Adventure adventure, int distance_to_destination,
			City destination) {
		if (distance_to_destination == 0) {
			travelUpdate.setText("Arrived at " + destination.getName());
		} else {
			travelUpdate.setText("On day " + adventure.getDay() + " you are " + distance_to_destination + " miles from " + destination.getName());
		}
	}
	





}
