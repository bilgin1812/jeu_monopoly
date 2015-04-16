package PackMonopoly;

import Objets.Joueur;
import Objets.Partie;

public class CaseGiveCarteCommunaute extends CaseGiveCarte{
	//action produite par la case, override sert à forcer la récriture du makeAction
	@Override
	public String makeAction(Partie partie, Joueur joueur, String RepJ){
		String actioncarte;
		
		//pioche carte
		partie.setCartePioche(partie.getMonPaquetCaisseCommunaute().piocher());
		
		//execute action de la carte
		actioncarte = partie.getCartePioche().makeAction(joueur, partie);
		
		return actioncarte;
	}
}
