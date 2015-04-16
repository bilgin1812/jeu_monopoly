package PackMonopoly;

import Objets.*;

public class CaseTaxe extends Case {
	private int taxe;

	public CaseTaxe(int taxe){
		this.taxe = taxe;
	}
	
	public int getTaxe() {
		return taxe;
	}

	public void setTaxe(int taxe) {
		this.taxe = taxe;
	}
	
	//action produite par la case, override sert à forcer la récriture du makeAction
	@Override
	public String makeAction(Partie partie, Joueur joueur, String RepJ){
		//Formate le message a envoyer au joueur(client)
		this.setAction("14;"+joueur.getNom()+";"+taxe);
		//Modifie l'argent du joueur
		joueur.setArgent(joueur.getArgent()-taxe);
		//Ajoute l'argent à la cagnotte
		partie.setCagnotte(partie.getCagnotte()+taxe);
		//Retourne le message a envoyer 
		return this.getAction();
	}

}
