package Carte;

import Objets.Joueur;
import Objets.Partie;

public class CarteAvancerDepart extends Carte{
	
	public CarteAvancerDepart(){
		this.setNom("CarteAvancerDepart");
		this.setAction("Avancez jusqu'à la case \"Départ\"");
	}
	
	//action produite par la carte, override sert à forcer la récriture du makeAction
	@Override
	public String makeAction(Joueur joueur, Partie partie) {
		
		//format : 2;IDJoueur;distance
		this.setAction("2;"+joueur.getNom()+";"+(partie.getPlateau().size()-joueur.getPosition()));
		
		//Deplacer le joueur
		joueur.setPosition(0);
		
		return this.getAction();
	}
}
