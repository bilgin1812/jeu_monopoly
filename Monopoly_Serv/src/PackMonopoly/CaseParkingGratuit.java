package PackMonopoly;

import Objets.Joueur;
import Objets.Partie;

public class CaseParkingGratuit extends Case {
	//action produite par la case, override sert à forcer la récriture du makeAction
	@Override
	public String makeAction(Partie partie, Joueur joueur, String RepJ){
		//Formate le message a envoyer au joueur(client)
		this.setAction("3;"+joueur.getNom()+";"+partie.getCagnotte());
		//Modifie l'argent du joueur
		joueur.setArgent(joueur.getArgent()+partie.getCagnotte());
		//Vide l'argent de la cagnotte
		partie.setCagnotte(0);
		//Retourne le message a envoyer 
		return this.getAction();
	}
}
