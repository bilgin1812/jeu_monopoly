package Carte;

import Objets.Joueur;
import Objets.Partie;

public class CarteAvancerDepart extends Carte{
	
	public CarteAvancerDepart(){
		this.setNom("CarteAvancerDepart");
		this.setAction("Avancez jusqu'� la case \"D�part\"");
	}
	
	//action produite par la carte, override sert � forcer la r�criture du makeAction
	@Override
	public String makeAction(Joueur joueur, Partie partie) {
		
		//format : 2;IDJoueur;distance
		this.setAction("2;"+joueur.getNom()+";"+(partie.getPlateau().size()-joueur.getPosition()));
		
		//Deplacer le joueur
		joueur.setPosition(0);
		
		return this.getAction();
	}
}
