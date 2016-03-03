package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import model.Adventure;
import model.InTransitException;
import model.InsufficientFundsException;
import model.ItemNotForSaleException;
import model.Squad;
import model.Store;

public class StoreView extends JPanel implements Observer {

	private Squad squad;
	private Adventure adv;
	private JLabel balance;
	
	public StoreView(Adventure a) {
		this.squad = a.getSquad();
		adv = a;
		setLayout(new BorderLayout());
		balance = new JLabel("Balance: $" + squad.getBalance(), SwingConstants.CENTER);
		add(balance,BorderLayout.CENTER);
		add(new VendorView(adv), BorderLayout.SOUTH);
		squad.getKnapsack().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {	
				balance.setText("Balance: $" + squad.getBalance());	
				repaint();
	}


	class VendorView extends JPanel implements ActionListener {
		
		private Store store;
		private JButton purchase;
		private JComboBox<String> items;
		private TextField amount;
		private String[] stock;
		
		public VendorView(Adventure a){
			try {
				store = a.getCurrentCity().getStore();
				stock = store.getItemNames();
				setLayout(new GridLayout(0,5));
				JLabel buy = new JLabel("Buy");
				add(buy);
				amount = new TextField("", 5);
				add(amount);
				JLabel of = new JLabel("of");
				add(of);
				String[] names = new String[stock.length];
				int j = 0;
				for(String i: stock){
					try {
						names[j] = i +"  ($"+ store.getPrice(i)+")";
						j++;
					} catch (ItemNotForSaleException e) {
					}
				}
				items = new JComboBox<String>(names);
				add(items);
				purchase = new JButton("Place Order");
				purchase.addActionListener(this);
				add(purchase);
			} catch (InTransitException e) {
			}
		}


		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try {
				squad.purchaseSupply(stock[items.getSelectedIndex()], Integer.parseInt(amount.getText()), store);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
			} catch (ItemNotForSaleException e) {
				// TODO Auto-generated catch block
			} catch (InsufficientFundsException e) {
				// TODO Auto-generated catch block
			}
		}
		
		
		
	}
}

