package PackMonopoly;

import java.util.Vector;

import Objets.Joueur;
import Objets.Partie;

/*************************************************************************
 * Class CaseTerrain :
 * Case Terrain (achetable)
*************************************************************************/

public class CaseTerrain extends Case {
	private Joueur proprietaire;
	private int prix;
	private String nom;
	private String couleur;
	private int nbBatiments;
	private Vector<Integer> loyers = new Vector<Integer>();
	private int PrixMaison;
	private boolean Hypothequer;
	private int PrixHypotheque;
	private float PourcentageHypotheque = 60;
	private int typeAction = 1;

	public CaseTerrain(String nom, String couleur,int loyer, int PrixMaison, int prixTerrain) {
		this.nom = nom;
		this.couleur = couleur;
		this.PrixMaison = PrixMaison;
		this.prix = prixTerrain;
		this.loyers.add(loyer);
		this.PrixHypotheque = (int)(this.prix*(this.PourcentageHypotheque/100));
		for(int i = 1; i < 6; i ++){
			this.loyers.add(loyer * (i+2));
		}
	}
	
	public int getPrixHypotheque() {
		return PrixHypotheque;
	}

	public void setPrixHypotheque(int prixHypotheque) {
		PrixHypotheque = prixHypotheque;
	}

	public float getPourcentageHypotheque() {
		return PourcentageHypotheque;
	}

	public void setPourcentageHypotheque(int pourcentageHypotheque) {
		PourcentageHypotheque = pourcentageHypotheque;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public Joueur getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}

	//action produite par la case, override sert à forcer la récriture du makeAction
	@Override
	public String makeAction(Partie partie,Joueur joueur, String RepJ){
		//si premiere demande (questionne le joueur)
		if (RepJ == ""){
			//si terrain libre
			if (proprietaire == null){
				//Formate le message a envoyer au joueur(client)
				//Format : 9;IDjoueur;Prix
				this.setAction("9;"+joueur.getNom()+";"+ prix);
				System.out.println("Proposition achat terrain \"" + nom + "\" à " + joueur.getNom());
			//si terrain achete et lui appartient
			}else if (proprietaire == joueur){
				this.setAction("nul");
				System.out.println(joueur.getNom() + " passe sur son propre terrain, ne paye rien");
			//si terrain hypotheque
			}else if (Hypothequer == true){
				this.setAction("nul");
				System.out.println(joueur.getNom() + " passe sur son un terrain hypothequé, ne paye rien");
			//si terrain achete mais pas a lui
			}else{
				//enleve argent au joueur
				//si batiment sur le terrain
				joueur.setArgent(joueur.getArgent()-(loyers.get(nbBatiments)));
				//donne argent au proprietaire
				proprietaire.setArgent(proprietaire.getArgent()+(loyers.get(nbBatiments)));
				//Formate le message a envoyer au joueur(client)
				//Format : 11;IDjoueur;IDProprietaire;Loyer
				this.setAction("11;"+joueur.getNom()+";"+proprietaire.getNom()+";"+(loyers.get(nbBatiments)));
				System.out.println(joueur.getNom() + " passe sur un terrain déjà acheté, paye " + loyers.get(nbBatiments));
				System.out.println("Sold "+ joueur.getNom() + " : " + joueur.getArgent() + ".-");
			}
		//si reponse joueur repond oui
		}else if(RepJ.equals("true")){
			//Formate le message a envoyer au joueur(client)
			//Format : 9;IDjoueur;Prix
			this.setAction("10;"+joueur.getNom()+";"+joueur.getPosition()+";"+ prix);
			//achete et met a jour la proriete
			joueur.setArgent(joueur.getArgent()-prix);
			proprietaire = joueur;
			System.out.println(joueur.getNom() + " achete terrain \"" + nom + "\"");
			System.out.println("Sold "+ joueur.getNom() + " : " + joueur.getArgent() + ".-");
		//si reponse jouer repond non
		}else if(RepJ.equals("false")){
			this.setAction("nul");
			System.out.println(joueur.getNom() + " refuse proposition achat terrain \"" + nom + "\"");
		}
		//Retourne le message a envoyer 
		return this.getAction();
	}
	
	//fonction d'ajout de batiment
	public void AjtBatiment(){
		nbBatiments++;
	}
	
	//fonction de vente de batiment
	public void SuppBatiment(){
		nbBatiments--;
	}
	
	public int getNbBatiments() {
		return nbBatiments;
	}

	public void setNbBatiments(int nbBatiments) {
		this.nbBatiments = nbBatiments;
	}
	
	public int getPrixMaison() {
		return PrixMaison;
	}

	public void setPrixMaison(int prixMaison) {
		PrixMaison = prixMaison;
	}
	
	public Vector<Integer> getLoyers() {
		return loyers;
	}

	public void setLoyers(Vector<Integer> loyers) {
		this.loyers = loyers;
	}

	public boolean isHypothequer() {
		return Hypothequer;
	}

	public void setHypothequer(boolean hypothequer) {
		Hypothequer = hypothequer;
	}
	
	public int getTypeAction() {
		return typeAction;
	}

	public void setTypeAction(int typeAction) {
		this.typeAction = typeAction;
	}

}
