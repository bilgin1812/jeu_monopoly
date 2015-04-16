package PackMonopoly;

import Objets.Joueur;
import Objets.Partie;

public class CasePrison extends Case{
	//action produite par la case, override sert à forcer la récriture du makeAction
	@Override
	public String makeAction(Partie partie, Joueur joueur, String RepJ){
		//Formate le message a envoyer au joueur(client)
		//Se passe rien
		this.setAction("0;");
		return this.getAction();
	}
}
