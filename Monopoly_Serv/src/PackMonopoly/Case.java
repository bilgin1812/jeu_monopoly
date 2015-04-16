package PackMonopoly;

import Objets.*;

/*************************************************************************
 * Class Case :
 * Corps de base des case du plateau
*************************************************************************/

public class Case {
	private String nom;
	//0 = notification ;1 = demandeConfirmation (attend une reponse)
	private int typeAction = 0;
	private String Action;
	
	public String getAction() {
		return Action;
	}
	public void setAction(String action) {
		Action = action;
	}
	public String makeAction(Partie partie, Joueur joueur, String RepJ) {
		return "Erreur action case";
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getTypeAction() {
		return typeAction;
	}
	public void setTypeAction(int typeAction) {
		this.typeAction = typeAction;
	}
}
