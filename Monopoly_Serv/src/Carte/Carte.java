package Carte;

import Objets.Joueur;
import Objets.Partie;

public class Carte {
	
	private String Nom;
	private String Action;
	
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getAction() {
		return Action;
	}
	public void setAction(String action) {
		Action = action;
	}
	public String makeAction(Joueur joueur, Partie partie) {
		return "Erreur action carte";
	}
}
