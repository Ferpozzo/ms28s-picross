package fr.jlegall.Picross;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LanguageChoice extends JFrame {

	private List<JComboBox<String>> langsCombo;

	public LanguageChoice() {
		langsCombo = new ArrayList<>();

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
		JLabel label2 = new JLabel("Language:    ");

		String[] items = { "English", "Português", "Français" };
		JComboBox<String> maCombo = new JComboBox<String>(items);
		maCombo.setMaximumSize(new Dimension(100, 24));

		langsCombo.add(maCombo);
		pan2.add(label2);
		pan2.add(maCombo);

		JPanel pan3 = new JPanel();
		pan3.setPreferredSize(new Dimension(200, 100));
		BoxLayout boxBot = new BoxLayout(pan3, BoxLayout.X_AXIS);
		pan3.setLayout(boxBot);
		JButton btnValid = new JButton("Start");
		pan3.add(btnValid);

		// panMain.add(pan1);
		panMain.add(pan2);
		panMain.add(pan3);

		this.setContentPane(panMain);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		btnValid.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				String language;

				language = LanguageChoice.this.langsCombo.get(0).getSelectedItem().toString();
				if (language == "Português") {
					language = "pt-br";
				} else if (language == "English") {
					language = "en-us";
				} else if (language == "Français") {
					language = "fr-fr";
				} else {
					language = "en-us";
				}
				try {
					new MenuChoix(language);
					LanguageChoice.this.dispose();
				} catch (Exception ex) {
					Logger.getLogger(MenuChoix.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}
}
