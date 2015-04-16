package PackMonopoly;

import Objets.Joueur;
import Objets.Partie;

/*************************************************************************
 * Class CaseDepart :
 * Fonction de la case depart
*************************************************************************/

public class CaseDepart extends Case {
	//Argent donnee par la case depart
	private int ArgentDonner;
	
	public CaseDepart(int ArgentDonner){
		this.ArgentDonner = ArgentDonner;
	}
	
	public int getArgentDonner() {
		return ArgentDonner;
	}

	public void setArgentDonner(int argentDonner) {
		ArgentDonner = argentDonner;
	}

	//action produite par la case, override sert à forcer la récriture du makeAction
	@Override
	public String makeAction(Partie partie, Joueur joueur, String RepJ){
		//Formate le message a envoyer au joueur(client)
		this.setAction("3;"+joueur.getNom()+";"+ArgentDonner);
		//Modifie l'argent du joueur
		joueur.setArgent(joueur.getArgent()+ArgentDonner);
		//Retourne le message a envoyer 
		return this.getAction();
	}
}
