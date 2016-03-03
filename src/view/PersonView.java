package view;

import java.awt.Color;
import java.util.Formatter;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import person.Person;

public class PersonView extends JPanel implements Observer {

	private Person person;
	private JLabel label;
	private JLabel name;
	private JLabel hp;
	private JLabel skill;
	private JLabel status;
	
	public PersonView(Person p) {
		person = p;
		setBorder(new LineBorder(Color.BLACK));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		label = new JLabel(p.toString().replace('(', ' ').replace(')', ' '));
		name = new JLabel(p.getName());
		hp = new JLabel("HP: " + p.getCurrentHP() +"/"+ p.getMaxHP());
		skill = new JLabel("Skill: "+ p.getSkill());
		status = new JLabel(p.getStatus().toString());
		add(name);
		add(hp);
		add(skill);
		add(status);
		add(Box.createVerticalGlue());
//		add(label);
		person.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
//				label.setText(person.toString());
				hp.setText("HP: " + person.getCurrentHP() +"/"+ person.getMaxHP());
				skill.setText("Skill: "+ person.getSkill());
				status.setText(person.getStatus().toString());
			}
		});
	}
}
