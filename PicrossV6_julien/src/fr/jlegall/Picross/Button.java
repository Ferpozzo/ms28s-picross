package fr.jlegall.Picross;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Button extends JButton implements MouseListener {

	private int etat;
	private int indiceX;
	private int indiceY;
	public Button(int x, int y, int t) {

		this.indiceX = x;
		this.indiceY = y;
		this.etat = 0;
		this.setPreferredSize(new Dimension(t, t));
		this.setBackground(Color.WHITE);
		this.addMouseListener(this);

		Border grayBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
		this.setBorder(grayBorder);

	}

	public void mouseClicked(MouseEvent event) {

		if (event.getButton() == 1) {
			if (this.etat == 0) {
				this.etat = 1;
				this.setBackground(Color.GREEN);
			} else {
				this.etat = 0;
				this.setBackground(Color.WHITE);
			}
		} else if (event.getButton() == 3) {
			if (this.etat == 0 || this.etat == 1) {
				this.etat = 0;
				this.setBackground(Color.LIGHT_GRAY);
			}
		}

	}

	public int getEtat() {
		return etat;
	}

	public int getIndiceX() {
		return indiceX;
	}

	public int getIndiceY() {
		return indiceY;
	}

	public void setEtat(int e) {
		if (e == 1) {
			this.etat = e;
			this.setBackground(Color.GREEN);
		} else {
			this.etat = e;
			this.setBackground(Color.WHITE);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
