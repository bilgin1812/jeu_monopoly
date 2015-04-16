package Objets;

import java.util.ArrayList;

import Carte.Carte;
import Connection.Server;
import PackMonopoly.*;

/*************************************************************************
 * Class Partie :
 * Contient tous les informations de la partie
*************************************************************************/

public class Partie {
	private Joueur joueurCurr;
	private int IDjouerCurr;
	private ArrayList<Joueur> Joueurs = new ArrayList<Joueur>();
	private ArrayList<Case> Plateau= new ArrayList<Case>();
	private String etat;
	private int NbreJoueurs;
	private int cagnotte;
	private Carte CartePioche;
	private PaquetChance MonPaquetChance;
	private PaquetCaisseCommunaute MonPaquetCaisseCommunaute;
	private Server Serveur; // pour y avoir accès dans le code de thullen (les cartes)

	//constructeur de la partie initialise tous les objets (plateau,batiment,etc...)
	public Partie() {
		
		//Initialisation Plateau
		CaseDepart case1 = new CaseDepart(2000);
		CaseTerrain case2 = new CaseTerrain("Case 2","Brun",400, 400, 400);
		CaseGiveCarteCommunaute case3 = new CaseGiveCarteCommunaute();
		CaseTerrain case4 = new CaseTerrain("Case 4","Brun",500, 400, 600);
		CaseTaxe case5 = new CaseTaxe(200);
		CaseInfrastructure case6 = new CaseInfrastructure("Case 6 Gare", "Gare", 1500, 500);
		CaseTerrain case7 = new CaseTerrain("Case 7","Bleu",700, 500, 900);
		CaseGiveCarteChance case8 = new CaseGiveCarteChance();
		CaseTerrain case9 = new CaseTerrain("Case 9","Bleu",700, 500, 1100);
		CaseTerrain case10 = new CaseTerrain("Case 10","Bleu",800, 600, 1200);
		CasePrison case11 = new CasePrison();
		CaseTerrain case12 = new CaseTerrain("Case 12","Violet",1000, 700, 1400);
		CaseInfrastructure case13 = new CaseInfrastructure("Case 13 Infr","Infr", 2000, 500);
		CaseTerrain case14 = new CaseTerrain("Case 14","Violet",1100, 700, 1600);
		CaseTerrain case15 = new CaseTerrain("Case 15","Violet",1100, 700, 1700);
		CaseInfrastructure case16 = new CaseInfrastructure("Case 16 Gare", "Gare", 1500, 500);
		CaseTerrain case17 = new CaseTerrain("Case 17","Orange",1200, 900, 1900);
		CaseGiveCarteCommunaute case18 = new CaseGiveCarteCommunaute();
		CaseTerrain case19 = new CaseTerrain("Case 19","Orange",1300, 1000, 2100);
		CaseTerrain case20 = new CaseTerrain("Case 20","Orange",1300, 1000, 2200);
		CaseParkingGratuit case21 = new CaseParkingGratuit();
		CaseTerrain case22 = new CaseTerrain("Case 22","Rouge",1500, 1200, 2400);
		CaseGiveCarteChance case23 = new CaseGiveCarteChance();
		CaseTerrain case24 = new CaseTerrain("Case 24","Rouge",1600, 1200, 2600);
		CaseTerrain case25 = new CaseTerrain("Case 25","Rouge",1600, 1200, 2700);
		CaseInfrastructure case26 = new CaseInfrastructure("Case 26 Gare", "Gare", 1500, 500);
		CaseTerrain case27 = new CaseTerrain("Case 27","Jaune",1900, 1500, 2900);
		CaseTerrain case28 = new CaseTerrain("Case 28","Jaune",1900, 1500, 3000);
		CaseInfrastructure case29 = new CaseInfrastructure("Case 29 Infr", "Infr", 2000, 500);
		CaseTerrain case30 = new CaseTerrain("Case 30","Jaune",2000, 1700, 3200);
		CaseGoToPrison case31 = new CaseGoToPrison();
		CaseTerrain case32 = new CaseTerrain("Case 32","Vert",2500, 2000, 3400);
		CaseTerrain case33 = new CaseTerrain("Case 33","Vert",2500, 2000, 3500);
		CaseGiveCarteCommunaute case34 = new CaseGiveCarteCommunaute();
		CaseTerrain case35 = new CaseTerrain("Case 35","Vert",2700, 2200, 3700);
		CaseInfrastructure case36 = new CaseInfrastructure("Case 36 Gare", "Gare", 1500, 500);
		CaseGiveCarteChance case37 = new CaseGiveCarteChance();
		CaseTerrain case38 = new CaseTerrain("Case 38","Blanche",3000, 2500, 4000);
		CaseTaxe case39 = new CaseTaxe(500);
		CaseTerrain case40 = new CaseTerrain("Case 40","Blanche",3500, 2500, 4500);
		
		Plateau.add(case1);
		Plateau.add(case2);
		Plateau.add(case3);
		Plateau.add(case4);
		Plateau.add(case5);
		Plateau.add(case6);
		Plateau.add(case7);
		Plateau.add(case8);
		Plateau.add(case9);
		Plateau.add(case10);
		Plateau.add(case11);
		Plateau.add(case12);
		Plateau.add(case13);
		Plateau.add(case14);
		Plateau.add(case15);
		Plateau.add(case16);
		Plateau.add(case17);
		Plateau.add(case18);
		Plateau.add(case19);
		Plateau.add(case20);
		Plateau.add(case21);
		Plateau.add(case22);
		Plateau.add(case23);
		Plateau.add(case24);
		Plateau.add(case25);
		Plateau.add(case26);
		Plateau.add(case27);
		Plateau.add(case28);
		Plateau.add(case29);
		Plateau.add(case30);
		Plateau.add(case31);		
		Plateau.add(case32);
		Plateau.add(case33);
		Plateau.add(case34);
		Plateau.add(case35);
		Plateau.add(case36);
		Plateau.add(case37);
		Plateau.add(case38);
		Plateau.add(case39);
		Plateau.add(case40);
			
		//tas de carte
		MonPaquetChance = new PaquetChance();
		MonPaquetCaisseCommunaute = new PaquetCaisseCommunaute();
	}
	
	public Carte getCartePioche() {
		return CartePioche;
	}

	public void setCartePioche(Carte cartePioche) {
		CartePioche = cartePioche;
	}

	public PaquetChance getMonPaquetChance() {
		return MonPaquetChance;
	}

	public void setMonPaquetChance(PaquetChance monPaquetChance) {
		MonPaquetChance = monPaquetChance;
	}

	public PaquetCaisseCommunaute getMonPaquetCaisseCommunaute() {
		return MonPaquetCaisseCommunaute;
	}

	public void setMonPaquetCaisseCommunaute(
			PaquetCaisseCommunaute monPaquetPaquetCaisseCommunaute) {
		MonPaquetCaisseCommunaute = monPaquetPaquetCaisseCommunaute;
	}

	public int getNbreJoueurs() {
		return NbreJoueurs;
	}

	public void setNbreJoueurs(int nbreJoueurs) {
		NbreJoueurs = nbreJoueurs;
	}

	
	public int getIDjouerCurr() {
		return IDjouerCurr;
	}

	public void setIDjouerCurr(int iDjouerCurr) {
		IDjouerCurr = iDjouerCurr;
	}
	
	public Joueur getJoueurCurr() {
		return joueurCurr;
	}

	public void setJoueurCurr(Joueur joueurCurr) {
		this.joueurCurr = joueurCurr;
	}

	public ArrayList<Joueur> getJoueurs() {
		return Joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		Joueurs = joueurs;
	}
	
	public void addJoueur(Joueur joueurs) {
		Joueurs.add(joueurs);
	}
	
	public void removeJoueur(int idjoueurs) {
		Joueurs.remove(idjoueurs);
	}

	public ArrayList<Case> getPlateau() {
		return Plateau;
	}

	public void setPlateau(ArrayList<Case> plateau) {
		Plateau = plateau;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	public int getCagnotte() {
		return cagnotte;
	}

	public void setCagnotte(int cagnotte) {
		this.cagnotte = cagnotte;
	}
	
	public Server getServeur() {
		return Serveur;
	}

	public void setServeur(Server serveur) {
		Serveur = serveur;
	}
	
}
