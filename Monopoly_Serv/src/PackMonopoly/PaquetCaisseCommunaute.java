package PackMonopoly;

import java.util.ArrayList;
import java.util.Random;

import Carte.Carte;
import Carte.CarteAvancerCase;
import Carte.CarteGagnerArgent;
import Carte.CartePerdreArgent;

public class PaquetCaisseCommunaute {
	
	private ArrayList<Carte> cartes;
	private Random rnd;
	
	public PaquetCaisseCommunaute(){
		rnd = new Random();
		cartes = new ArrayList<Carte>();
		Carte tempCarte = new Carte();
		//CrÃ©ation manuelle des cartes chance du paquet.
		
		//8 cartes gagner de la thuuune
		tempCarte = new CarteGagnerArgent("CarteGagnerLoterie", "Vous gagner 10000 CHF à la loterie", 10000);
		cartes.add(tempCarte);
		cartes.add(tempCarte);
		tempCarte = new CarteGagnerArgent("CarteGagnerConcours", "Vous gagner 5000CHF au concours du plus bel étalon", 5000);
		cartes.add(tempCarte);	
		cartes.add(tempCarte);	
		tempCarte = new CarteGagnerArgent("CarteLaVieilleACame", "Votre grand-mère a camlsé, vous héritez de 8000 CHF", 8000);
		cartes.add(tempCarte);
		cartes.add(tempCarte);	
		tempCarte = new CarteGagnerArgent("CarteCadeauDeDieu", "Dieu vous aime, recevez 20000", 20000);
		cartes.add(tempCarte);
		cartes.add(tempCarte);	
		
		//4 cartes se faire entuber
		tempCarte = new CartePerdreArgent("CarteDeVoyeur", "Vous avez espionné la voisine sous la douche. Payez"
				+ " 5000 CHF d'amende.", 5000);
		cartes.add(tempCarte);
		tempCarte = new CartePerdreArgent("CarteTaxeDePollution", "Vous êtes un vil pollueur, payer 2000 CHF", 2000);
		cartes.add(tempCarte);
		tempCarte = new CartePerdreArgent("CarteTaxeInutile", "L'état vous rackette, payez 10000 CHF", 10000);
		cartes.add(tempCarte);
		tempCarte = new CartePerdreArgent("CarteTaxeDEscroc", "Vous êtes un vil escroc, payer 2000 CHF", 2000);
		cartes.add(tempCarte);
		
		//4 cartes aller quelque part
		
		tempCarte = new CarteAvancerCase("CarteAvancerCase18", "Allez au boulevard St-Michel. Si vous passez par go"
				+ " recevez 2000 CHF", 18);
		cartes.add(tempCarte);
		tempCarte = new CarteAvancerCase("CarteAvancerCase32", "Allez à l'avenue Foch", 32);
		cartes.add(tempCarte);
		tempCarte = new CarteAvancerCase("CarteAvancerCase25", "Allez à la gare du Nord", 25);
		cartes.add(tempCarte);
		tempCarte = new CarteAvancerCase("CarteAvancerCase20", "Parc gratuit, youpi les amis", 20);
		cartes.add(tempCarte);
	}
	
	public Carte piocher(){
		Integer numero = this.rnd.nextInt(16);
		return this.cartes.get(numero);
	}
}