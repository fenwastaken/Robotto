import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Line extends JPanel{

	JLabel lab = new JLabel();
	JButton bt = new JButton();
	
	public Line(String labText, String btText){

		this.setPreferredSize(new Dimension(300, 40));

		lab.setText(labText);
		lab.setPreferredSize(new Dimension(80, 30));
		
		bt.setText(btText);
		
		this.add(lab);
		this.add(bt);
		
	}

	public Line(Component comp, String btText){

		this.setPreferredSize(new Dimension(300, 40));

		
		bt.setText(btText);
		
		this.add(comp);
		this.add(bt);
		
	}
	
	public JLabel getLab() {
		return lab;
	}

	public void setLab(JLabel lab) {
		this.lab = lab;
	}

	public JButton getBt() {
		return bt;
	}

	public void setBt(JButton bt) {
		this.bt = bt;
	}
	
	

}
