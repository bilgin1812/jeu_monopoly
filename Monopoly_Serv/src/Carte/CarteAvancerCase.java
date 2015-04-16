package Carte;

import java.io.IOException;

import Objets.Joueur;
import Objets.Partie;

public class CarteAvancerCase extends Carte{
	
	//Carte chance faisant bouger le joueur -> need une case ou le dÃ©placer!!
	private Integer idCase;
	
	public CarteAvancerCase(String nom, String action, Integer idCase){
		this.setNom(nom);
		this.setAction(action);
		this.setIdCase(idCase);
	}
	
	public Integer getIdCase() {
		return idCase;
	}

	public void setIdCase(Integer idCase) {
		this.idCase = idCase;
	}
	
	//action produite par la carte, override sert à forcer la récriture du makeAction
	@Override
	public String makeAction(Joueur joueur, Partie partie) {
		
		//Deplacer le joueur
		if(joueur.getPosition() < this.idCase){
			int distance = this.idCase - joueur.getPosition();

			//format : 2;IDJoueur;distance
			this.setAction("2;"+joueur.getNom()+";"+distance);
			joueur.setPosition(joueur.getPosition()+distance);
		//Dans ce cas, le joueur devra faire un tour du plateau -> Case départ à gérer.
		}else{
			int distance = (partie.getPlateau().size() - joueur.getPosition()) + this.idCase;
			
			//envoie de la position du joueur au serveur
			//format : 2;IDJoueur;distance
			this.setAction("2;"+joueur.getNom()+";"+distance);
			joueur.setPosition(joueur.getPosition()+distance);
			
			//action case depart
			try {
				partie.getServeur().sendAll(partie.getPlateau().get(0).makeAction(partie, joueur, ""));
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return this.getAction();
	}
}