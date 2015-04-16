package PackMonopoly;

import java.io.IOException;

import Connection.Server;
//import GestionServ.Menu;
import Objets.Joueur;
import Objets.Partie;

/*************************************************************************
 * Class Main : Controlleur de jeu gère tous ce qui ce passe de côte serveur
 *************************************************************************/

public class Main {
	private ActionMessages Actions;

	private Joueur joueurCourant;
	private int idjoueurCurTable;
	//private int idjoueurCur;
	private Partie maPartie;
	private String nomjoueur;

	// ********************************************************************
	// main
	// ******************************************************************//
	public static void main(String[] args) throws IOException, InterruptedException {
		Main m = new Main();
		m.LancerPartie();
		//new Menu();
	}

	// ********************************************************************
	// CheckFin
	// Test si plus qu'un joueur pas en faillite
	// ******************************************************************//
	public String CheckFin() {
		int count = 0;
		String id = "";

		for (int i = 0; i < maPartie.getJoueurs().size(); i++) {
			if (maPartie.getJoueurs().get(i).isFaillite() == false) {
				count++;
				id = maPartie.getJoueurs().get(i).getNom();
			}
		}

		if (count == 1)
			return id;
		else
			return "";
	}

	// ********************************************************************
	// LancerPartie
	// effectue le processus d'un tour pour chaque joueur
	// ******************************************************************//
	public void LancerPartie() throws IOException, InterruptedException {
		maPartie = new Partie();
		maPartie.setNbreJoueurs(2);
		String IdGagnant;
		int argentDepartJoueur = 10000;
		
		//new Menu();
	
		// Lance le serveur
		Server Serv = new Server(maPartie);

		// attend nombre joueur voulu
		while (Serv.getNbJAtt() == false) {
		}
		
		// Pour avoir accès à l'objet serveur depuis le code de thullen (les cartes)
		// Lors de l'appel de l action de la carte dans les cases giveCarte besoin de l'objet serveur
		maPartie.setServeur(Serv);
		
		// initialise argent de départ des joueurs
		for(int i = 0; i < maPartie.getJoueurs().size(); i++){
			maPartie.getJoueurs().get(i).setArgent(argentDepartJoueur);			
		}
		
		// initialise le joueur courant
		maPartie.setJoueurCurr(maPartie.getJoueurs().get(0));

		Actions = new ActionMessages(Serv, maPartie);

		System.out.println("Lance la partie");

		while (true) {
			joueurCourant = maPartie.getJoueurCurr();
			nomjoueur = maPartie.getJoueurCurr().getNom();
			//idjoueurCur = maPartie.getIDjouerCurr(); // id joueur, id qui ne change pas (pas dynamique comme la position dans la liste
			idjoueurCurTable = maPartie.getJoueurs().indexOf(joueurCourant); // emplacement dans le tableau de joueur
			
			System.out.println("Au tour de " + joueurCourant.getNom());
			
			// message type confirmation(attend reponse):
			// Annonce au joueur que c'est son tour et demande lancer de
			Serv.sendAll("1;" + nomjoueur);

			// Essay de communique avec le client (try/catch pour intercepter
			// les fin de session inopiné du client)
			try {
				// Sleep pour corriger erreur lors de la deco des joueurs (quand
				// plus aucun joueur, le serveur lisait trop vite (fontion
				// liremessage) et recevait de msg nul et continuait a tourner)
				Thread.sleep(100);
				// Attend reponse joueur
				// Lecture car le joueur pourrai vouloir gerer ces terrains
				// avant de lancer le de (achat maison etc)
				// achat :


				boolean finTour = false;
				while (finTour == false) {
					String message = Serv.LireMessage(joueurCourant);
					//System.out.println(message);
					finTour = Actions.action(message);
				}

				// Test si joueur en faillite
				if (joueurCourant.getArgent() <= 0) {
					joueurCourant.setFaillite(true);
				}

				// Passe le tour au joueur suivant
				maPartie.setJoueurCurr(maPartie.getJoueurs().get(((idjoueurCurTable + 1) % maPartie.getJoueurs().size())));
				
				// Test fin de partie, test plus que un joueur pas en faillite
				IdGagnant = CheckFin();
				if (!(IdGagnant.equals(""))) {
					Serv.sendAll("4;" + IdGagnant);
					System.out.println(IdGagnant + " a gagner !");
					//break;
				}

			} catch (IOException e) {
				// Passe le tour et test si reste des joueurs
				Serv.delClient(idjoueurCurTable);
				if (maPartie.getJoueurs().size() != 0) {
					// Passe le tour au joueur suivant seulement si dernier
					// joueur de la list (car sinon le joueur suivant prend ca
					// place)
					if (idjoueurCurTable == maPartie.getJoueurs().size())
						maPartie.setJoueurCurr(maPartie.getJoueurs().get(0));
					else
						maPartie.setJoueurCurr(maPartie.getJoueurs().get(
								(idjoueurCurTable)));
				} else {
					System.out.println("Plus de joueurs, fin de partie");
					break;
				}
			}
		}
	}
}
