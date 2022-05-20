package fr.jlegall.Picross;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class MenuChoix extends JFrame {

	private List<JComboBox<String>> mesCombos;

	public MenuChoix(String language) {
		Translator translator = new Translator();
		mesCombos = new ArrayList<>();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200, 200);
		this.setResizable(false);

		JPanel panMain = new JPanel();

		BoxLayout boxMain = new BoxLayout(panMain, BoxLayout.Y_AXIS);
		panMain.setLayout(boxMain);

		JPanel pan2 = new JPanel();
		pan2.setPreferredSize(new Dimension(200, 100));
		BoxLayout boxMiddle = new BoxLayout(pan2, BoxLayout.X_AXIS);
		pan2.setLayout(boxMiddle);
		JLabel label2 = new JLabel(translator.getMessage(language, "gridMessage") + " :    ");

		String[] items = { "5x5", "10x10", "15x15", "20x20", "25x25" };
		JComboBox<String> maCombo = new JComboBox<String>(items);
		maCombo.setMaximumSize(new Dimension(100, 24));

		mesCombos.add(maCombo);
		pan2.add(label2);
		pan2.add(maCombo);

		JPanel pan3 = new JPanel();
		pan3.setPreferredSize(new Dimension(200, 100));
		BoxLayout boxBot = new BoxLayout(pan3, BoxLayout.X_AXIS);
		pan3.setLayout(boxBot);
		JButton btnValid = new JButton(translator.getMessage(language, "startMessage"));
		pan3.add(btnValid);

		// panMain.add(pan1);
		panMain.add(pan2);
		panMain.add(pan3);

		this.setContentPane(panMain);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		btnValid.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				int tailleB = 5;
				int id;

				MenuChoix.this.mesCombos.get(0).getSelectedItem().toString();
				id = MenuChoix.this.mesCombos.get(0).getSelectedIndex() + 1;

				try {
					new Picross(id * tailleB, id * tailleB, language);
					MenuChoix.this.dispose();
				} catch (UnsupportedLookAndFeelException ex) {
					Logger.getLogger(MenuChoix.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}
}
