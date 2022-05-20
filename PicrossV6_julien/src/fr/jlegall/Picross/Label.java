/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.jlegall.Picross;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author jlegall
 */
@SuppressWarnings("serial")
public class Label extends JLabel {

	public Label(int val, int t) {
		this.setPreferredSize(new Dimension(t, t));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
		if (val == 0) {
			this.setText("");
		} else {
			this.setText(Integer.toString(val));
		}

		Border grayBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
		this.setBorder(grayBorder);
	}

}
