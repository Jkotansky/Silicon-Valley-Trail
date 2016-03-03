package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Squad;

public class SquadView extends JPanel implements Observer {

	private ArrayList<PersonView> viewers;

	public SquadView(Squad s) {
		viewers = new ArrayList<PersonView>();
		for (int i = 0; i < s.getNumPlayers(); i++) {
			viewers.add(new PersonView(s.getPlayer(i)));
		}
		setLayout(new GridLayout(5, 1));
		for (PersonView i : viewers) {
			add(i);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (PersonView i : viewers) {
					i.update(arg0, arg1);
				}
			}
		});

	}

}
