package Carte;

import Objets.Joueur;
import Objets.Partie;

public class CarteGagnerArgent extends Carte{
	
	private Integer montant;
		
	public Integer getMontant() {
		return montant;
	}

	public void setMontant(Integer montant) {
		this.montant = montant;
	}

	public CarteGagnerArgent(String nom, String action, Integer montant){
		this.setNom(nom);
		this.setAction(action);
		this.setMontant(montant);
	}
	
	//action produite par la carte, override sert à forcer la récriture du makeAction
	@Override
	public String makeAction(Joueur joueur, Partie partie) {
		//Argent donnee par la case depart
		int ArgentPerdu = this.getMontant();
		//Formate le message a envoyer au joueur(client)
		this.setAction("3;"+joueur.getNom()+";"+ArgentPerdu);
		//Modifie l'argent du joueur
		joueur.setArgent(joueur.getArgent()+ArgentPerdu);
		return this.getAction();
	}
}
