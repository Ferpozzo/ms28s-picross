package fr.jlegall.Picross;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class ButtonHelp extends JButton implements MouseListener {

	private String name;
	private int value;
	private boolean actif;
	int cpt = 1;

	public ButtonHelp(String name, int val) {
		this.name = name;
		this.value = val;
		this.setText(name + " (" + val + ")");
		this.actif = true;
		this.setEnabled(true);
	}

	public void mouseClicked(MouseEvent me) {
		if (this.value > 1) {
			// System.out.println(this.value);
			this.value -= 1;
			this.setText(this.name + " (" + this.value + ")");
		} else if (this.value == 1) {
			this.value = 0;
			// System.out.println(this.value);
			this.setText(this.name + " (0)");
			this.actif = false;
			this.setEnabled(false);
		}
	}

	public boolean getActif() {
		return this.actif;
	}

	public void mousePressed(MouseEvent me) {
	}

	public void mouseReleased(MouseEvent me) {
	}

	public void mouseEntered(MouseEvent me) {
	}

	public void mouseExited(MouseEvent me) {
	}

}
