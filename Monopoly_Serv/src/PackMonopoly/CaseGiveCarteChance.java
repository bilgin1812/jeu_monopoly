package PackMonopoly;

import Objets.Joueur;
import Objets.Partie;

public class CaseGiveCarteChance extends CaseGiveCarte {	
	//action produite par la case, override sert à forcer la récriture du makeAction
	@Override
	public String makeAction(Partie partie, Joueur joueur, String RepJ){
		String actioncarte;
				
		//pioche carte
		partie.setCartePioche(partie.getMonPaquetChance().piocher());
		
		//execute action de la carte
		actioncarte = partie.getCartePioche().makeAction(joueur, partie);
		
		return actioncarte;
	}
}
