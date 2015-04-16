package PackMonopoly;

import Objets.Joueur;
import Objets.Partie;

public class CaseGoToPrison extends Case {
	//Trouve la position de la prison
	public int FindPrison(Partie maPartie){		
		for (int i=0; i < maPartie.getPlateau().size(); i++){
			if(maPartie.getPlateau().get(i) instanceof CasePrison){
				return i;
			}
		}	
		return -1;			
	}
	
	//action produite par la case, override sert à forcer la récriture du makeAction
	@Override
	public String makeAction(Partie partie, Joueur joueur, String RepJ){
		if(joueur.isCarteSortirPrison() == false){
			//Cherche position prison
			int PosPrison = FindPrison(partie);
			//Formate le message a envoyer au joueur(client)
			this.setAction("13;"+joueur.getNom()+";"+PosPrison);
			//Deplace le joueur en prison
			joueur.setPosition(PosPrison);
			//le marque comme en prison
			joueur.setEnPrison(true);
			//Retourne le message a envoyer 
			return this.getAction();
		}else{
			//Cherche position prison
			int PosPrison = FindPrison(partie);
			//Deplace le joueur en prison
			joueur.setPosition(PosPrison);
			//Formate le message a envoyer au joueur(client)
			this.setAction("15;"+joueur.getNom());
			//Perd ca carte
			joueur.setCarteSortirPrison(false);
			return this.getAction();
		}		
	}
}
