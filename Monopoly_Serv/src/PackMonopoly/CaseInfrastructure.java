package PackMonopoly;

import Objets.Joueur;
import Objets.Partie;

/*************************************************************************
 * Class CaseInfrastructure :
 * Case achetable autre que les terrains (Gare,service eau/electricite)
*************************************************************************/

public class CaseInfrastructure extends Case {
	private Joueur proprietaire;
	private int prix;
	private int loyer;
	private String nom;
	private String type;
	private boolean Hypothequer;
	private int PrixHypotheque;
	private int PourcentageHypotheque = 60;

	public CaseInfrastructure(String nom, String type, int prix, int loyer) {
		this.nom = nom;
		this.type = type;
		this.prix = prix;
		this.PrixHypotheque = this.prix*(this.PourcentageHypotheque/100);
		this.loyer = loyer;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getLoyer() {
		return loyer;
	}

	public void setLoyer(int loyer) {
		this.loyer = loyer;
	}

	public Joueur getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public String getType() {
		return type;
	}

	public boolean isHypothequer() {
		return Hypothequer;
	}

	public void setHypothequer(boolean hypothequer) {
		Hypothequer = hypothequer;
	}

	public int getPrixHypotheque() {
		return PrixHypotheque;
	}

	public void setPrixHypotheque(int prixHypotheque) {
		PrixHypotheque = prixHypotheque;
	}

	public int getPourcentageHypotheque() {
		return PourcentageHypotheque;
	}

	public void setPourcentageHypotheque(int pourcentageHypotheque) {
		PourcentageHypotheque = pourcentageHypotheque;
	}

	public void setType(String type) {
		this.type = type;
	}

	//action produite par la case, override sert à forcer la récriture du makeAction
	@Override
	public String makeAction(Partie partie, Joueur joueur, String RepJ){
		//si premiere demande (questionne le joueur)
		if (RepJ == ""){
			//si case libre
			if (proprietaire == null){
				//Formate le message a envoyer au joueur(client)
				//Format : 9;IDjoueur;Prix
				this.setAction("9;"+joueur.getNom()+";"+ prix);
			//si case achete et lui appartient
			}else if (proprietaire == joueur){
				this.setAction("nul");
			//si hypotheque
			}else if (Hypothequer == true){
				this.setAction("nul");
			//si case achete mais pas a lui
			}else{
				int count = 0;
				//Teste si autre case de meme type appartient au meme joueur pour augmenter le loyer
				for(int i=0; i < partie.getPlateau().size(); i++){
					if(partie.getPlateau().get(i) instanceof CaseInfrastructure){
						CaseInfrastructure t = (CaseInfrastructure) partie.getPlateau().get(i);
						if(t.getType() == type){
							if(t.getProprietaire() == proprietaire){
								count++;
							}
						}
					}
				}
				//enleve argent au joueur
				joueur.setArgent(joueur.getArgent()-(loyer*(2^(count-1))));
				//donne argent au proprietaire
				proprietaire.setArgent(proprietaire.getArgent()+(loyer*(2^(count-1))));
				//format msg à envoyer
				this.setAction("11;"+joueur.getNom()+";"+proprietaire.getNom()+";"+(loyer*(2^(count-1))));
			}
		//si reponse joueur repond oui
		}else if(RepJ.equals("true")){
			//Formate le message a envoyer au joueur(client)
			//Format : 9;IDjoueur;Prix
			this.setAction("10;"+joueur.getNom()+";"+joueur.getPosition()+";"+ prix);
			//achete et met a jour la proriete
			joueur.setArgent(joueur.getArgent()-prix);
			proprietaire = joueur;
		//si reponse jouer repond non
		}else if(RepJ.equals("false")){
			this.setAction("nul");
		}
		//Retourne le message a envoyer 
		return this.getAction();
	}
}

