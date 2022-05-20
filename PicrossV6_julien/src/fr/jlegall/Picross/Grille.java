package fr.jlegall.Picross;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grille {

	private int largeur;
	private int longueur;

	private Case grille[][];
	private int tabIndexLargeur[][];
	private int tabIndexLongueur[][];

	public Grille(int largeur, int longueur) {
		this.largeur = largeur;
		this.longueur = longueur;

		this.grille = new Case[largeur][longueur];
		this.tabIndexLargeur = new int[largeur][(longueur + 1) / 2];
		this.tabIndexLongueur = new int[(largeur + 1) / 2][longueur];

		for (int i = 0; i < largeur; i++) {
			for (int j = 0; j < longueur; j++) {
				this.grille[i][j] = new Case(i, j);
			}
		}

		for (int i = 0; i < largeur; i++) {
			for (int j = 0; j < ((longueur + 1) / 2); j++) {
				this.tabIndexLargeur[i][j] = 0;
			}
		}

		for (int i = 0; i < ((largeur + 1) / 2); i++) {
			for (int j = 0; j < longueur; j++) {
				this.tabIndexLongueur[i][j] = 0;
			}
		}
	}

	// Méthode qui permet de voir la grille ainsi que les tableaux d'indice
	// Utilisé qu'en version console
	public void voirGrille() {
		System.out.println("## GRILLE ##");
		for (int i = 0; i < this.largeur; i++) {
			for (int j = 0; j < this.longueur; j++) {
				// System.out.print("(" + this.grille[i][j].getX() + "," +
				// this.grille[i][j].getY() + ")");
				System.out.print(this.grille[i][j].getEtat());
			}
			System.out.println();
		}

		// System.out.println("## INDEX LARGEUR ##");
		for (int i = 0; i < this.largeur; i++) {
			for (int j = 0; j < (this.longueur + 1) / 2; j++) {
				System.out.print(this.tabIndexLargeur[i][j]);
			}
			System.out.println();
		}

		// System.out.println("## INDEX LONGUEUR ##");
		for (int i = 0; i < (this.largeur + 1) / 2; i++) {
			for (int j = 0; j < this.longueur; j++) {
				System.out.print(this.tabIndexLongueur[i][j]);
			}
			System.out.println();
		}
	}

	// Méthode qui permet la génération de grille aléatoire
	public Grille genererGrilleAleatoire(Grille g) {
		Random rand = new Random();
		int num;

		// Création de la grille aléatoire
		for (int i = 0; i < g.largeur; i++) {
			for (int j = 0; j < g.longueur; j++) {
				num = rand.nextInt(2);
				g.grille[i][j].setEtat(num);
			}
		}
		int idLargeur = (g.largeur - 1) / 2;
		int idLongueur = (g.longueur - 1) / 2;

		// Création du tableau d'index largeur de la matrice
		for (int i = 0; i < g.largeur; i++) {
			for (int j = 0; j < g.longueur; j++) {
				if (g.grille[i][j].getEtat() == 1) {
					if (j > 0 && g.grille[i][j - 1].getEtat() == 0) {
						for (int l = 1; l < idLongueur + 1; l++) {
							g.tabIndexLargeur[i][l - 1] = g.tabIndexLargeur[i][l];
							g.tabIndexLargeur[i][l] = 0;
						}
					}
					g.tabIndexLargeur[i][idLongueur] += 1;
				}
			}
		}

		// Création du tableau d'index longueur de la matrice
		for (int i = 0; i < g.longueur; i++) {
			for (int j = 0; j < g.largeur; j++) {
				if (g.grille[j][i].getEtat() == 1) {
					if (j > 0 && g.grille[j - 1][i].getEtat() == 0) {
						for (int l = 1; l < idLargeur + 1; l++) {
							g.tabIndexLongueur[l - 1][i] = g.tabIndexLongueur[l][i];
							g.tabIndexLongueur[l][i] = 0;
						}
					}
					g.tabIndexLongueur[idLargeur][i] += 1;
				}
			}
		}

		return g;
	}

	// Méthode qui permet de renvoyer la liste des cases avec l'état 1
	public List<Case> getMatriceCase() {
		ArrayList<Case> matrice = new ArrayList<>();
		for (int i = 0; i < this.largeur; i++) {
			for (int j = 0; j < this.longueur; j++) {
				if (this.grille[i][j].getEtat() == 1) {
					matrice.add(this.grille[i][j]);
				}
			}
		}

		return matrice;
	}

	// Méthode qui permet uniquement la récuperation de la matrice
	public int[][] getMatrice() {
		int matrice[][] = new int[this.largeur][this.longueur];

		for (int i = 0; i < this.largeur; i++) {
			for (int j = 0; j < this.longueur; j++) {
				matrice[i][j] = this.grille[i][j].getEtat();
			}
		}

		return matrice;
	}

	// Méthode qui permet la récupération du tableau d'indice Longueur
	public int[][] recupererTableauLongueur() {
		return this.tabIndexLongueur;
	}

	// Méthode qui permet la récuperation du tableau d'indice Largeur
	public int[][] recupererTableauLargeur() {
		return this.tabIndexLargeur;
	}

	// Méthode qui permet de comparer deux grille,
	// celle du picross et celle du résultat
	public boolean compareGrille(int[][] mat) {
		boolean result;
		int cpt = 0;

		for (int i = 0; i < this.largeur; i++) {
			for (int j = 0; j < this.longueur; j++) {
				if (mat[i][j] != this.getMatrice()[i][j]) {
					cpt++;
				}
			}
		}

		if (cpt == 0) {
			result = true;
		} else {
			result = false;
		}

		return result;
	}

}
