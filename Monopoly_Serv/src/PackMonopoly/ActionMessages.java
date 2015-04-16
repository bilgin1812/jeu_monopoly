package PackMonopoly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Connection.Server;
import Objets.Joueur;
import Objets.Partie;

public class ActionMessages {

	private Server serv;
	private Partie p;
	Random rnd = new Random();
	
	// ********************************************************************
	// ActionMessages
	// Constructeur
	// ******************************************************************//
	public ActionMessages(Server s, Partie p) {
		this.serv = s;
		this.p = p;
	}

	// ********************************************************************
	// ParseMessage
	// Split le message recu du client
	// ******************************************************************//
	public String[] ParseMessage(String m) {
		return m.split(";");
	}
	
	// ********************************************************************
	// action
	// Effectue les actions en fonction des message recu du client
	// ******************************************************************//
	public boolean action(String m) throws IOException, InterruptedException {
		boolean finTour = false;
		Joueur joueurCourant = p.getJoueurCurr();
		//int idjoueurCur = p.getIDjouerCurr();

		String[] message = ParseMessage(m);

		switch (message[0]) {
		case "0":
			// PARAM : IDJOUEUR
			// demande des parties existantes
			// Pas dans cette artie du code (ici controlleur partie et sera dans controlleur de jeu)
			break;
		case "1":
			// PARAM : IDPARTIE, IDJOUEUR
			// Créer partie
			// Pas dans cette artie du code (ici controlleur partie et sera dans controlleur de jeu)
			break;
		case "2":
			// PARAM : IDPARTIR, IDJOUEUR
			// joindre partie
			// Pas dans cette artie du code (ici controlleur partie et sera dans controlleur de jeu)
			break;
		case "3":
			// PARAM : IDJOUEUR
			// fin du tour joueur
			System.out.println("Joueur " + message[1] + " passe son tour");
			finTour = true;
			break;
		case "4":
			// PARAM : IDJOUEUR
			// lancement du dé
			System.out.println(message[1] + " Lance le de");
			int count = 0;
			String[] de = ParseMessage(lancerDe(count));
			int distance = Integer.parseInt(de[0]);
			int nbdouble = Integer.parseInt(de[1]);
			
			// Test si triple double (fonction renvois -1) go prison
			if (distance != -1) {
				// Test si joueur en prison
				if(joueurCourant.isEnPrison() == true){
					// si pas fait de double aux des
					if (nbdouble <= 1) {
						System.out.println("Joueur " + message[1] + "est en prison, pas de double, ne bouge pas");
					// si fait aumoin un double aux des
					}else{
						System.out.println("Joueur " + message[1] + "sort de prison grâce à un double");
						joueurCourant.setEnPrison(false);
					}
				}
				// si pas ou plus en prison
				if(joueurCourant.isEnPrison() == false){
					
					System.out.println("distance a effectuer par " + message[1] + ": " + distance);

					// Deplace le joueur
					joueurCourant.setPosition((joueurCourant.getPosition() + distance));
					
					// envoie de la position au joueur
					// format : 2;IDJoueur;distance
					serv.sendAll("2;" + message[1] + ";" + distance);
					
					// Si position plus grand que le plateau = un tour a ete fait,
					// 2000.- , envoie notif' + moduler le bazar pour que ca joue
					if (joueurCourant.getPosition() > p.getPlateau().size()) {
						joueurCourant.setPosition(joueurCourant.getPosition()
								% p.getPlateau().size());
						// Effectue action case depart
						serv.sendAll(p.getPlateau().get(0).makeAction(p,joueurCourant, ""));
					}
				}
			} else {
				int PosPrison = FindPrison();
				
				// test si joueur deja en prison
				if(joueurCourant.isEnPrison() == true){
					System.out.println("Triple double " + message[1] + " reste en prison");
					
					// envoie msg joueur en prison
					// format : 13;IDJoueur
					serv.sendAll("13;" + message[1] + ";" + PosPrison);
				}else{
					// test si joueur a une carte sortie de prison
					if (joueurCourant.isCarteSortirPrison() == true){
						System.out.println("Triple double, mais " + message[1] + " a une carte \"sortie de prison\" il échappe à la prison");
						
						//Formate le message a envoyer au joueur(client)
						serv.sendAll("15;"+joueurCourant.getNom());
					}else{
						System.out.println("J" + message[1] + " va en prison");
						
						// Deplace le joueur en prison
						joueurCourant.setPosition(PosPrison);
						
						// envoie msg joueur en prison
						// format : 13;IDJoueur
						serv.sendAll("13;" + message[1] + ";" + PosPrison);
					}
				}
			}
			
			SendNotif(joueurCourant);
			
			break;
		case "5":
			// PARAM : IDJOUEUR1, IDJOUEUR2, TERRAIN, PRIX
			// Proposition vente terrain entre 2 joueurs
			
			System.out.println(message[1] + " vend terrain " + message[3] + " à " + message[2] + " pour " + message[4] + ".-");
			
			int PosTerrain = Integer.parseInt(message[3]);
			
			//test si terrain hypothequer
			if (p.getPlateau().get(PosTerrain) instanceof CaseTerrain) {
				CaseTerrain t = (CaseTerrain) p.getPlateau().get(PosTerrain);
				if (t.isHypothequer() == true) {
					break;
				}
			}else{
				CaseInfrastructure t = (CaseInfrastructure) p.getPlateau().get(PosTerrain);
				if (t.isHypothequer() == true) {
					break;
				}
			}
			
			Joueur j2 = FindJoueur(Integer.parseInt(message[2]));

			// Si J2 a pas assez d'argent
			if (j2.getArgent() < Integer.parseInt(message[4])) {
				serv.sendAll("6;" + message[2] + ";" + message[1] + ";false");
				break;
			}

			// tester maison terrain + si terrain ou infrastructure
			if (p.getPlateau().get(PosTerrain) instanceof CaseTerrain) {
				CaseTerrain t = (CaseTerrain) p.getPlateau().get(PosTerrain);
				if (t.getNbBatiments() != 0)
					break;
			}else if(!(p.getPlateau().get(PosTerrain) instanceof CaseInfrastructure)){
				break;
			}

			// envoyer message à tous
			serv.sendAll("5;" + message[1] + ";" + message[2] + ";"
					+ message[3] + ";" + message[4]);

			// lire message joueur 2
			String rep = serv.LireMessage(j2);
			if (rep.equals("true")) {
				// Vente acceptée
				serv.sendAll("6;" + message[2] + ";" + message[1] + ";true");
				CaseTerrain t = (CaseTerrain) p.getPlateau().get(PosTerrain);
				t.setProprietaire(j2);
				joueurCourant.setArgent(joueurCourant.getArgent()
						+ Integer.parseInt(message[4]));
				j2.setArgent(j2.getArgent() - Integer.parseInt(message[4]));

			} else {
				// Vente refusée
				// 6;IDJ2;IDJ1;BOOLEAN
				serv.sendAll("6;" + message[2] + ";" + message[1] + ";false");
			}
			
			System.out.println("Sold "+ joueurCourant.getNom() + " : " + joueurCourant.getArgent() + ".-");
			System.out.println("Sold "+ j2.getNom() + " : " + j2.getArgent() + ".-");

			break;
		case "6":
			// PARAM : IDJOUEUR, GROUPECOULEURMAISON, BOOLEAN (true = acheter, false = vente)
			// Achat / Vente de maison sur son terrain
				
			System.out.println(message[1] + " fait action sur maison couleur " + message[2] + ", achat(true)/vente(false) : " + message[3]);
			
			// check si tout les terrains et qu'ils sont pas hypotheques
			if (CheckFullColor(message[2], joueurCourant)) {
				if (message[3].equals("true")) {
					// achat maison				
					int prixMaisons = 0;
					ArrayList<CaseTerrain> maisons = GetTerrainsCouleur(message[2]);
					// test si maisons max
					if (maisons.get(0).getNbBatiments() < 5) { 
						// calcul du prix
						for (int i = 0; i < maisons.size(); i++) {
							prixMaisons += maisons.get(i).getPrixMaison();
						}
						// test si assez fric
						if (joueurCourant.getArgent() > prixMaisons) {
							joueurCourant.setArgent(joueurCourant.getArgent()
									- prixMaisons);
							AddMaisonsCouleur(message[2]);
							serv.sendAll("16;" + message[1] + ";true");
						} else
							serv.sendAll("16;" + message[1] + ";false");
					} else {
						serv.sendAll("16;" + message[1] + ";false");
					}
				} else {
					// Vente maisons
					int prixMaisons = 0;
					ArrayList<CaseTerrain> maisons = GetTerrainsCouleur(message[2]);
					if (maisons.get(0).getNbBatiments() > 0) {
						// test si maisons max
						for (int i = 0; i < maisons.size(); i++) {
							prixMaisons += maisons.get(i).getPrixMaison();
						}
						// test si assez fric
						joueurCourant.setArgent(joueurCourant.getArgent() - prixMaisons);
						SuppMaisonsCouleur(message[2]);
						serv.sendAll("16;" + message[1] + ";true");

					} else
						serv.sendAll("16;" + message[1] + ";false");
				}
			} else {
				serv.sendAll("16;" + message[1] + ";false");
			}
			
			System.out.println("Sold "+ joueurCourant.getNom() + " : " + joueurCourant.getArgent() + ".-");

			break;
			
		case "7":
			// PARAM : IDJOUEUR, TERRAIN, BOOLEAN
			// Hypotheque un terrain
			
			System.out.println(message[1] + " fait action hypotheque sur terrain " + message[2] + ", hypo(true)/rachatHypo(false) : " + message[3]);
			
			Object t;
			
			// check type case
			int Position = Integer.parseInt(message[2]);
			if (p.getPlateau().get(Position) instanceof CaseTerrain){
				t = (CaseTerrain) p.getPlateau().get(Position);
			}else{
				t = (CaseInfrastructure) p.getPlateau().get(Position);
			}
			
			// Test si veux l'hypothequer ou deshypothequer
			if (message[3].equals("true")) {
				// Hypotheque
				// check type case
				if (t instanceof CaseTerrain){
					// Tester si pas de maison
					if (((CaseTerrain) t).getNbBatiments() == 0){
						int prixHypo = ((CaseTerrain) t).getPrixHypotheque();
						System.out.println(prixHypo);
						// Hypotheque terrain
						((CaseTerrain) t).setHypothequer(true);
						// Donne argent au joueur
						joueurCourant.setArgent(joueurCourant.getArgent() + prixHypo);
						serv.sendAll("16;" + message[1] + ";true");	
					}else
						serv.sendAll("16;" + message[1] + ";false");
				}else{
					int prixHypo = ((CaseInfrastructure) t).getPrixHypotheque();
					// Hypotheque terrain
					((CaseInfrastructure) t).setHypothequer(true);
					// Donne argent au joueur
					joueurCourant.setArgent(joueurCourant.getArgent() + prixHypo);
					serv.sendAll("16;" + message[1] + ";true");
				}
			}else{
				// Deshypotheque
				// check type case
				if (t instanceof CaseTerrain){
					// Test si joueur assez argent
					int prixHypo = ((CaseTerrain) t).getPrixHypotheque();
					if (joueurCourant.getArgent() > prixHypo) {
						// enleve argent au joueur
						joueurCourant.setArgent(joueurCourant.getArgent() - prixHypo);
						// deshypotheque le terrain
						((CaseTerrain) t).setHypothequer(false);
						serv.sendAll("16;" + message[1] + ";true");
					} else
						serv.sendAll("16;" + message[1] + ";false");
				}else{
					// Test si joueur assez argent
					int prixHypo = ((CaseInfrastructure) t).getPrixHypotheque();
					if (joueurCourant.getArgent() > prixHypo) {
						// enleve argent au joueur
						joueurCourant.setArgent(joueurCourant.getArgent() - prixHypo);
						// deshypotheque le terrain
						((CaseInfrastructure) t).setHypothequer(false);
						serv.sendAll("16;" + message[1] + ";true");
					} else
						serv.sendAll("16;" + message[1] + ";false");
				}
			}
			
			System.out.println("Sold "+ joueurCourant.getNom() + " : " + joueurCourant.getArgent() + ".-");
			
			break;
			
		default:
			// faux message
			break;
		}

		return finTour;
	}

	// ********************************************************************
	// FindJoueur
	// Trouve le joueur en fonction de son ID
	// ******************************************************************//
	public Joueur FindJoueur(int id) {
		for (int i = 0; i < p.getJoueurs().size(); i++) {
			if (p.getJoueurs().get(i).getId() == id)
				return p.getJoueurs().get(i);
		}
		return null;
	}

	// ********************************************************************
	// AddMaisonsCouleur
	// Ajoute une maisons en fonction de la couleur
	// ******************************************************************//
	public void AddMaisonsCouleur(String couleur) {
		for (int i = 0; i < p.getJoueurs().size(); i++) {
			if (p.getPlateau().get(i) instanceof CaseTerrain) {
				CaseTerrain t = (CaseTerrain) p.getPlateau().get(i);
				if (t.getCouleur().equals(couleur)) {
					t.AjtBatiment();
				}
			}
		}
	}

	// ********************************************************************
	// SuppMaisonsCouleur
	// Supprime une maisons en fonction de la couleur
	// ******************************************************************//
	public void SuppMaisonsCouleur(String couleur) {
		for (int i = 0; i < p.getJoueurs().size(); i++) {
			if (p.getPlateau().get(i) instanceof CaseTerrain) {
				CaseTerrain t = (CaseTerrain) p.getPlateau().get(i);
				if (t.getCouleur().equals(couleur)) {
					t.SuppBatiment();
				}
			}
		}
	}

	// ********************************************************************
	// GetTerrainsCouleur
	// Renvoie tout les terrain d'une certaine couleur
	// ******************************************************************//
	public ArrayList<CaseTerrain> GetTerrainsCouleur(String couleur) {
		ArrayList<CaseTerrain> m = new ArrayList<CaseTerrain>();
		for (int i = 0; i < p.getJoueurs().size(); i++) {
			if (p.getPlateau().get(i) instanceof CaseTerrain) {
				CaseTerrain t = (CaseTerrain) p.getPlateau().get(i);
				if (t.getCouleur().equals(couleur)) {
					m.add(t);
				}
			}
		}
		return m;
	}

	// ********************************************************************
	// CheckFullColor
	// Test si le joueur a tout les terrains et qu'ils ne sont pas hypothequer
	// ******************************************************************//
	public boolean CheckFullColor(String couleur, Joueur j) {
		boolean check = true;
		for (int i = 0; i < p.getPlateau().size(); i++) {
			if (p.getPlateau().get(i) instanceof CaseTerrain) {
				CaseTerrain t = (CaseTerrain) p.getPlateau().get(i);
				if (t.getCouleur().equals(couleur)) {
					if ((t.getProprietaire() != j) && (t.isHypothequer() == false)) {
						check = false;
					}
				}
			}
		}

		return check;
	}

	// ********************************************************************
	// SendNotif
	// Envoie les notifications de la case aux joueurs
	// ******************************************************************//
	public void SendNotif(Joueur joueurCourant) throws IOException, InterruptedException {
		String repAction;
		
		// Si Action de la case demande envoie notif (un message attent pas de retour)
		if (p.getPlateau().get(joueurCourant.getPosition()).getTypeAction() == 0) {
			serv.sendAll(p.getPlateau().get(joueurCourant.getPosition()).makeAction(p,joueurCourant, ""));
		}		
		// Si Action de la case demande envoie confirmation (envoie un message et attent une rep)
		else if (p.getPlateau().get(joueurCourant.getPosition()).getTypeAction() == 1){
			repAction = p.getPlateau().get(joueurCourant.getPosition()).makeAction(p,joueurCourant, "");
			
			serv.sendAll(repAction);
			
			// relance l'action si besoin 
			// (ne le fait pas pour une action qui renvoie nul et l'action de payer le loyer a un autre joueur
			if ((!(repAction.equals("nul"))) || (!(repAction.contains("11;")))) {

				// Lit reponse du joueur (client), renvoie msg positif ou
				// negatif
				String Reponsejoueur = serv.LireMessage(joueurCourant);

				// relance action de la case, avec la rep du joueur (client)
				String MsgAEnvoyer = p.getPlateau()
						.get(joueurCourant.getPosition())
						.makeAction(p,joueurCourant, Reponsejoueur);

				// si repondu negativement a la demande, donc pas de donnee a
				// envoyer au joueurs (clients)
				if (!(MsgAEnvoyer.equals("nul"))) {
					serv.sendAll(MsgAEnvoyer);
				}
			}
		}
		// Si case de type give carte
		else if (p.getPlateau().get(joueurCourant.getPosition()).getTypeAction() == 2) {
			
			repAction = p.getPlateau().get(joueurCourant.getPosition()).makeAction(p,joueurCourant, "");
			
			serv.sendAll(repAction);
			
			//relance action de la case si c etait pas un carte qui donne ou prend de l argent 
			if (!(repAction.contains("3;"))) {
				SendNotif(joueurCourant);
			}	
		}

	}

	// ********************************************************************
	// lancerDe
	// effectue le lancer de deux des et gère les doubles (retire un fois)
	// ******************************************************************//
	public String lancerDe(int count) {
		count++;
		int D1 = rnd.nextInt(5) + 1;
		int D2 = rnd.nextInt(5) + 1;
		int distance = 0;

		// Test si double
		if ((D1 == D2) && (count <= 2)) {
			System.out.println("Double");
			String[] de = ParseMessage(lancerDe(count));
			distance = Integer.parseInt(de[0]);
		} else if ((D1 == D2) && (count == 3)) {
			System.out.println("Triple double, direction prison");
			return "-1;"+(count+1);
		} else {
			return ""+ (D1 + D2 + distance) + ";" + count;
		}
		return "" + (D1 + D2 + distance) + ";" + count;
		//return "7;1";
	}

	// ********************************************************************
	// FindPrison
	// Trouve la position de la case prison
	// ******************************************************************//
	public int FindPrison() {

		for (int i = 0; i < p.getPlateau().size(); i++) {
			if (p.getPlateau().get(i) instanceof CasePrison) {
				return i;
			}
		}
		return -1;
	}
}
