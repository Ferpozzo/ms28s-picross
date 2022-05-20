package fr.jlegall.Picross;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import java.util.List;
import java.util.Locale;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

@SuppressWarnings("serial")
public class Picross extends JFrame implements MouseListener {

	private int longueur;
	private int largeur;
	private String language;
	private List<Button> mesBoutons;
	private Grille grille;
	private List<Case> matPicrossCase;
	private ButtonHelp btnHelp;
	private static int cpt = 0;
	private List<Picross> listeTest;

	public Picross(int largeur, int longueur, String language) throws UnsupportedLookAndFeelException {
		Translator translator = new Translator();
		this.language = language;
		// Essentiellement pour Mac OS afin d'afficher les boutons correctement
		UIManager.setLookAndFeel(new MetalLookAndFeel());

		this.largeur = largeur;
		this.longueur = longueur;

		// Défini la taille des boutons de la grille et des cases des deux tableaux
		// d'indice
		int tb;
		if (largeur > 10 || longueur > 15) {
			tb = 25;
		} else {
			tb = 30;
		}

		// Creer un tableau pour stocker tous les boutons de la grille
		mesBoutons = new ArrayList<>();

		listeTest = new ArrayList<>();

		// Creer la grille suivant la largeur et la longueur donnée
		grille = new Grille(largeur, longueur);
		grille = grille.genererGrilleAleatoire(grille);

		// Recupere la matrice de la grille pour le resultat
		matPicrossCase = grille.getMatriceCase();
		grille.getMatrice();

		this.setTitle("Picross : " + this.largeur + "x" + this.longueur);
		this.setResizable(false);

		// Option du picross
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (language == "pt-br") {
		} else if (language == "fr-fr") {
			JOptionPane.setDefaultLocale(Locale.FRENCH);
		} else if (language == "en-us") {
			JOptionPane.setDefaultLocale(Locale.ENGLISH);
		}
		System.out.println(JOptionPane.getDefaultLocale());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int reponse = JOptionPane.showConfirmDialog(null, translator.getMessage(language, "exitConfirmationMessage"),
						translator.getMessage(language, "confirmMessage"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (reponse == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu(translator.getMessage(language, "menuMessage"));
		JMenuItem rulesItem = new JMenuItem(new AbstractAction(translator.getMessage(language, "rulesMessage")) {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
					try {
						if (language == "pt-br") {
							Desktop.getDesktop()
									.browse(new URI("http://gamersensato.com.br/2015/08/sobre-picross-e-pixel-art/"));
						} else if (language == "en-us") {
							Desktop.getDesktop().browse(new URI("https://www.wikihow.com/Play-Picross-DS"));
						} else if (language == "fr-fr") {
							Desktop.getDesktop()
									.browse(new URI("https://www.pix-n-cross.com/visite.php?pag=cid105&idf=15"));
						} else {
							Desktop.getDesktop().browse(new URI("https://www.wikihow.com/Play-Picross-DS"));
						}
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		JMenuItem restartItem = new JMenuItem(new AbstractAction(translator.getMessage(language, "restartMenu")) {
			@Override
			public void actionPerformed(ActionEvent e) {
				int reponse = JOptionPane.showConfirmDialog(null, translator.getMessage(language, "restartMessage"),
						translator.getMessage(language, "confirmMessage"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (reponse == JOptionPane.YES_OPTION) {
					Picross.this.dispose();
				}
			}
		});
		menu.add(rulesItem);
		menu.add(restartItem);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);

		// Creer le JPanel principal pour contenir tous les components
		JPanel mainPanel = new JPanel();

		// Creer le BoxLayout principale qui recevra tous les autres box
		BoxLayout boxMain = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(boxMain);

		JPanel pan = new JPanel();
		BoxLayout boxHaut = new BoxLayout(pan, BoxLayout.X_AXIS);
		pan.setLayout(boxHaut);
		JPanel time = new JPanel();

		time.setPreferredSize(new Dimension(((this.longueur + 1) / 2) * tb, ((this.largeur + 1) / 2) * tb));

		JPanel tabIndiceLongueur = new JPanel();
		GridLayout tabLongueur = new GridLayout(((this.largeur + 1) / 2), this.longueur);
		tabIndiceLongueur.setLayout(tabLongueur);

		for (int i = 0; i < ((this.largeur + 1) / 2); i++) {
			for (int j = 0; j < this.longueur; j++) {
				if (grille.recupererTableauLongueur()[i][j] > 0) {
					Label label = new Label(grille.recupererTableauLongueur()[i][j], tb);
					tabIndiceLongueur.add(label);
				} else {
					Label label = new Label(0, tb);
					tabIndiceLongueur.add(label);
				}
			}
		}

		time.setBackground(Color.WHITE);
		tabIndiceLongueur.setBackground(Color.YELLOW);
		pan.add(time);
		pan.add(tabIndiceLongueur);
		mainPanel.add(pan);

		JPanel pan1 = new JPanel();
		BoxLayout boxBas = new BoxLayout(pan1, BoxLayout.X_AXIS);
		pan1.setLayout(boxBas);

		JPanel tabIndiceLargeur = new JPanel();
		GridLayout tabLargeur = new GridLayout(this.largeur, ((this.longueur + 1) / 2));
		tabIndiceLargeur.setLayout(tabLargeur);

		for (int i = 0; i < this.largeur; i++) {
			for (int j = 0; j < ((this.longueur + 1) / 2); j++) {
				if (grille.recupererTableauLargeur()[i][j] > 0) {
					Label label = new Label(grille.recupererTableauLargeur()[i][j], tb);
					tabIndiceLargeur.add(label);
				} else {
					Label label = new Label(0, tb);
					tabIndiceLargeur.add(label);
				}
			}
		}

		tabIndiceLargeur.setBackground(Color.YELLOW);
		pan1.add(tabIndiceLargeur);

		JPanel matrice = new JPanel();
		matrice.setBackground(Color.GRAY);
		GridLayout grid = new GridLayout(this.largeur, this.longueur);
		matrice.setLayout(grid);
		for (int i = 0; i < this.largeur; i++) {
			for (int j = 0; j < this.longueur; j++) {
				Button btn = new Button(i, j, tb);
				mesBoutons.add(btn);
				matrice.add(btn);
			}
		}

		pan1.add(matrice);
		mainPanel.add(pan1);

		JPanel menuTest = new JPanel();

		JButton btnVerif = new JButton(translator.getMessage(language, "verifyMessage"));
		int helps = (int) ((this.largeur * this.longueur) * 0.20);
		btnHelp = new ButtonHelp(translator.getMessage(language, "helpMessage"), helps);

		btnHelp.addMouseListener(this);

		btnVerif.addMouseListener(this);

		btnHelp.addMouseListener(this.btnHelp);

		menuTest.add(btnHelp);
		menuTest.add(btnVerif);

		mainPanel.add(menuTest);

		/**
		 * Permet de mélanger la liste matPicrossCase, utiliser seulement pour l'Aide
		 */
		Collections.shuffle(matPicrossCase);
		listeTest.add(this);

		this.setContentPane(mainPanel);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	/**
	 * Méthode appelée lorsque l'on presse un bouton de la souri
	 *
	 * @param event
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getComponent() == this.btnHelp && this.btnHelp.getActif() == true) {
			try {
				this.fonctionHelp();
			} catch (java.lang.IndexOutOfBoundsException e) {
				System.err.println("Ajuda Imposs�vel");
			}

		} else if (event.getComponent() != this.btnHelp) {
			this.verifGrille();
		}
	}

	/**
	 * Méthode qui permet de griser une case parmi les cases bonnes en verifiant si
	 * elle n'est pas deja grisée
	 */
	private void fonctionHelp() {
		boolean test = false;

		while (test == false) {

			int idX = matPicrossCase.get(cpt).getX();
			int idY = matPicrossCase.get(cpt).getY();

			for (Button unBtn : mesBoutons) {
				if (unBtn.getIndiceX() == idX && unBtn.getIndiceY() == idY) {
					if (unBtn.getEtat() == 0) {
						unBtn.setEtat(1);
						test = true;
					} else {
						test = false;
					}
				}
			}
			cpt++;
		}

	}

	/**
	 * Méthode qui permet de vérifier la grille en cours avec le resultat
	 */
	private void verifGrille() {
		Translator translator = new Translator();
		int matrice[][] = new int[this.largeur][this.longueur];
		int cpt = 0;

		for (int i = 0; i < this.largeur; i++) {
			for (int j = 0; j < this.longueur; j++) {
				matrice[i][j] = this.mesBoutons.get(cpt).getEtat();
				cpt++;
			}
		}

		boolean ok = grille.compareGrille(matrice);
		final JFrame popup = new JFrame();
		JPanel pan = new JPanel();
		JLabel msg = new JLabel();
		JButton btnOk = new JButton("Ok");

		btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
		msg.setAlignmentX(Component.CENTER_ALIGNMENT);

		popup.setSize(300, 75);
		popup.setTitle("Popup");
		popup.setResizable(false);

		BoxLayout boxMain = new BoxLayout(pan, BoxLayout.Y_AXIS);

		pan.setLayout(boxMain);
		if (language == "pt-br") {
		} else if (language == "fr-fr") {
			JOptionPane.setDefaultLocale(Locale.FRENCH);
		} else if (language == "en-us") {
			JOptionPane.setDefaultLocale(Locale.ENGLISH);
		}

		if (ok) {
			msg.setText(translator.getMessage(language, "winMessage"));
			btnOk.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent event) {
					int reponse = JOptionPane.showConfirmDialog(null, translator.getMessage(language, "restartMessage"),
							translator.getMessage(language, "confirmMessage"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (reponse == JOptionPane.NO_OPTION) {
						System.exit(0);
					} else {
						popup.dispose();
						Picross.this.listeTest.get(0).dispose();
						Picross.this.listeTest.clear();
						new MenuChoix(language);
					}
				}
			});

		} else {
			msg.setText(translator.getMessage(language, "wrongVerifyMessage") + translator.getMessage(language, "youHitMessage")+cpt+"%");
			btnOk.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent event) {
					popup.dispose();
				}
			});
		}

		pan.add(msg);

		pan.add(btnOk);

		popup.add(pan);

		popup.setVisible(true);
		popup.setLocationRelativeTo(null);

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
