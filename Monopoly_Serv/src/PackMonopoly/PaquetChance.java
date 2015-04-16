package PackMonopoly;

import java.util.ArrayList;
import java.util.Random;

import Carte.Carte;
import Carte.CarteAvancerCase;
import Carte.CarteAvancerDepart;
import Carte.CarteGagnerArgent;
import Carte.CartePerdreArgent;

public class PaquetChance {
	
	private ArrayList<Carte> cartes;
	private Random rnd;
	
	public PaquetChance(){
		rnd = new Random();
		cartes = new ArrayList<Carte>();
		//Création manuelle des cartes chance du paquet.
		
		//2 cartes retour départ
		Carte tempCarte = new CarteAvancerDepart();
		cartes.add(tempCarte);
		cartes.add(tempCarte);
		
		//2 cartes aller en prison
		tempCarte = new CarteAvancerCase("CarteAvancerPrison", "Allez directement en prison, sans passer par"
				+ "la case d�part, vous ne toucherez pas 2000 balles dans ta face goujat!", 11);
		cartes.add(tempCarte);
		cartes.add(tempCarte);
		
		//2 cartes bonus argent
		tempCarte = new CarteGagnerArgent("CarteGagnerLoterie", "Vous gagner 10000 CHF � la loterie", 10000);
		cartes.add(tempCarte);
		tempCarte = new CarteGagnerArgent("CarteGagnerConcours", "Vous gagner 5000CHF au concours du plus bel �talon", 5000);
		cartes.add(tempCarte);
		
		//4 cartes d'amendes
		tempCarte = new CartePerdreArgent("CarteTaxeDeDebilite", "Vous n'�tes pas le plus malin sur terre. Payez"
				+ " 5000 CHF d'amende.", 5000);
		cartes.add(tempCarte);
		tempCarte = new CartePerdreArgent("CarteTaxeDePollution", "Vous �tes un vil pollueur, payer 2000 CHF", 2000);
		cartes.add(tempCarte);
		tempCarte = new CartePerdreArgent("CarteTaxeDeConti", "Vous polluez avec votre v�lo, payez 10000", 10000);
		cartes.add(tempCarte);
		tempCarte = new CartePerdreArgent("CarteTaxeDePollution", "Vous �tes un vil pollueur, payer 2000 CHF", 2000);
		cartes.add(tempCarte);
		
		//6 cartes pour se déplacer quelque part
		tempCarte = new CarteAvancerCase("CarteAvancerCase3", "Avancer faire une visite � la rue Courcelles."
				+ " Si vous passez par l'arriv�e, touchez votre salaire", 3);
		cartes.add(tempCarte);
		tempCarte = new CarteAvancerCase("CarteAvancerCase8", "Avancer faire une visite � la rue Lecourre."
				+ " Si vous passez par l'arriv�e, touchez votre salaire", 8);
		cartes.add(tempCarte);
		tempCarte = new CarteAvancerCase("CarteAvancerCase23", "Avancer faire une visite au boulevard Malesherbes."
				+ " Si vous passez par l'arriv�e, touchez votre salaire", 23);
		cartes.add(tempCarte);
		tempCarte = new CarteAvancerCase("CarteAvancerCase15", "Comme vous �tes en retard, allez prendre le train"
				+ " � la gare de Lyon", 15);
		cartes.add(tempCarte);
		tempCarte = new CarteAvancerCase("CarteAvancerCase28", "C'est la crise, ya une fuite!! Allez � la compagnie"
				+ " de distribution des eaux!!", 28);
		cartes.add(tempCarte);
		tempCarte = new CarteAvancerCase("CarteAvancerCase39", "Allez vous ruiner � la rue de la paix", 39);
		cartes.add(tempCarte);
	}
	
	public Carte piocher(){
		Integer numero = this.rnd.nextInt(16);
		return this.cartes.get(numero);
	}
}
