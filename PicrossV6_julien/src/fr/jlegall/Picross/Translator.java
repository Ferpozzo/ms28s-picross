package fr.jlegall.Picross;

import java.util.HashMap;
import java.util.Map;

public class Translator {

	private Map<String, Map<String, String>> languages = new HashMap<>();

	public Translator() {
		this.setLanguages();
	}

	private void setLanguages() {
		Map<String, String> pt_br = new HashMap<>();
		pt_br.put("startMessage", "Começar");
		pt_br.put("gridMessage", "Grid");
		pt_br.put("menuMessage", "Menu");
		pt_br.put("rulesMessage", "Regras");
		pt_br.put("helpMessage", "Ajuda");
		pt_br.put("verifyMessage", "Verificar");
		pt_br.put("confirmMessage", "Confirmar");
		pt_br.put("wrongVerifyMessage", "Errado !! Tente novamente.");
		pt_br.put("winMessage", "Parabéns!! Você ganhou!");
		pt_br.put("exitConfirmationMessage", "Tem certeza que deseja sair?");
		pt_br.put("restartMenu", "Recomeçar");
		pt_br.put("restartMessage", "Deseja recomeçar?");
		pt_br.put("youHitMessage", "Você acertou: ");

		this.languages.put("pt-br", pt_br);

		Map<String, String> en_us = new HashMap<>();
		en_us.put("startMessage", "Start");
		en_us.put("gridMessage", "Grid");
		en_us.put("menuMessage", "Menu");
		en_us.put("rulesMessage", "Rules");
		en_us.put("helpMessage", "Help");
		en_us.put("verifyMessage", "Verify");
		en_us.put("confirmMessage", "Confirm");
		en_us.put("wrongVerifyMessage", "Wrong !! Try again.");
		en_us.put("winMessage", "Congratulations!! You win!");
		en_us.put("exitConfirmationMessage", "Are you sure you want to quit?");
		en_us.put("restartMenu", "Restart");
		en_us.put("restartMessage", "Do you want to start over?");
		en_us.put("youHitMessage", "You hit: ");

		this.languages.put("en-us", en_us);

		Map<String, String> fr_fr = new HashMap<>();
		fr_fr.put("startMessage", "Commencer");
		fr_fr.put("gridMessage", "Grille");
		fr_fr.put("menuMessage", "Menu");
		fr_fr.put("rulesMessage", "Des règles");
		fr_fr.put("helpMessage", "Aider");
		fr_fr.put("verifyMessage", "Vérifier");
		fr_fr.put("confirmMessage", "Confirmer");
		fr_fr.put("wrongVerifyMessage", "Tort!! Essayer à nouveau.");
		fr_fr.put("winMessage", "Toutes nos félicitations!! Tu as gagné.");
		fr_fr.put("exitConfirmationMessage", "Êtes-vous sûr de vouloir quitter?");
		fr_fr.put("restartMenu", "Recommencer");
		fr_fr.put("restartMessage", "Voulez-vous recommencer?");
		fr_fr.put("youHitMessage", "Vous avez raison: ");

		this.languages.put("fr-fr", fr_fr);
	}

	public String getMessage(String language, String key) {
		return this.languages.get(language).get(key);
	}
}