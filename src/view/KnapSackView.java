package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import supplies.Food;
import supplies.Supplies;
import model.Knapsack;
import model.Squad;

public class KnapSackView extends JPanel implements Observer {

	private Knapsack jansport;
	private ArrayList<Supplies> supplyArr;
	//	private ArrayList<SuppliesView> supplyView;
	private JPanel panel;

	public KnapSackView(Squad s) {
		jansport = s.getKnapsack();
		//		supplyView = new ArrayList<SuppliesView>();
		supplyArr = new ArrayList<Supplies>();
		setPreferredSize(new Dimension(200,400));
		setLayout(new BorderLayout());
		add(new JLabel("Knapsack:"), BorderLayout.NORTH);

		panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel,BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		add(panel);

		updateLabels();
		jansport.addObserver(this);
	}

	private void updateLabels() {
		//box layout when done use horizontal/vertical glue

		Supplies[] packedItems = jansport.getSupplies();
//		for (Supplies s : packedItems) // DEBUG
//			System.out.println(s);
		for (Supplies i : packedItems) {
			panel.add(new SuppliesView(i));			
		}
		panel.add(Box.createVerticalGlue());
		panel.revalidate();
		panel.repaint();

	}

	@Override
	public void update(Observable o, Object arg) {
//		System.out.println("Updating Knapsack View");  // DEBUG
		panel.removeAll();
		updateLabels();
	}

	// works like squad and person view 



	class SuppliesView extends JLabel {

		public SuppliesView(Supplies s){
			super(s.toString());
			setAlignmentX(LEFT_ALIGNMENT);
		}

	}

}
