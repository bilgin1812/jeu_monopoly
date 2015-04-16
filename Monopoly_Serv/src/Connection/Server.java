package Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

import Objets.Joueur;
import Objets.Partie;

/*************************************************************************
 * Class Server :
 * Contient le serveur, g�re les connections et la communication
*************************************************************************/

//** Classe principale du serveur, g�re les infos globales **
public class Server {
	private static int nbClients = 0; // nombre total de clients connect�s
	private static ServerSocket ss;
	private static Partie ServPartie;
	private static boolean NbJAtt;

	public Server(Partie maPartie) throws IOException {
		ServPartie = maPartie;
		NbJAtt = false;
		Connection();
	}

	public static void Connection() throws IOException {
		Integer port = new Integer("18000");
		
		// ouverture d'un socket serveur sur port
		ss = new ServerSocket(port.intValue());
		
		// attente en boucle de connexion (bloquant sur ss.accept)
		while (ServPartie.getNbreJoueurs() != nbClients){
			System.out.println("En attente de connection");
			Joueur clt = new Joueur(ss.accept());
			addClient(clt);
			BufferedReader in = new BufferedReader(new InputStreamReader(clt.getS().getInputStream()));
			String n = in.readLine();
			//recup le pseudo du client (exemple pour se co le client envoie son pseudo
			clt.setNom(n);
			System.out.println("Joueur " + n + " viens de se connecter");
			// un client se connecte, un nouveau thread client est lanc�		
		}
		System.out.println("Nombre de joueurs atteind");
		NbJAtt = true;
	}

	// ** Methode : envoie le message � tous les clients **
	public void sendAll(String message) throws IOException, InterruptedException {
		PrintWriter out;
		// declaration d'une variable permettant l'envoi de texte vers le client
		// parcours de la table des connect�s
		
		Thread.sleep(800); // pour rectifier bug d envoie (parfois envoie trop vite et le client n arrive pas a lire)
		
		for (int i = 0; i < ServPartie.getJoueurs().size(); i++){
			//out = (PrintWriter) ServPatie.getJoueurs().get(i).getOut();
			out = new PrintWriter(ServPartie.getJoueurs().get(i).getS().getOutputStream());
			// s�curit�, l'�l�ment ne doit pas �tre vide
			if (out != null){
				// ecriture du texte pass� en param�tre (et concat�nation d'une
				// string de fin de chaine si besoin)
				out.print(message+"\n");
				out.flush(); // envoi dans le flux de sortie
			}
		}
	}

	// ** Methode : envoie le message � 1 client **
	public void sendOne(String message, int id) throws IOException {
		PrintWriter out;
		// declaration d'une variable permettant l'envoi de texte vers le client
		//out = ServPatie.getJoueurs().get(id).getOut();
		out = new PrintWriter(ServPartie.getJoueurs().get(id).getS().getOutputStream());
		
		// extraction de l'�l�ment courant (type PrintWriter)
		// s�curit�, l'�l�ment ne doit pas �tre vide
		if (out != null){
			// ecriture du texte pass� en param�tre (et concat�nation
			// d'une string de fin de chaine si besoin)
			out.print(message);
			out.flush(); // envoi dans le flux de sortie
		}
	}

	// ** Methode : d�truit le client no i **
	public void delClient(int i) throws IOException {
		// l'�l�ment existe
		if (ServPartie.getJoueurs().get(i) != null){
			ServPartie.getJoueurs().get(i).getS().close();
			ServPartie.removeJoueur(i); // on le supprime
			System.out.println("Client deconnecte, id: " + i);
		}
		nbClients--;
	}

	// ** Methode : ajoute un nouveau client dans la liste **
	public static void addClient(Joueur clt){
		clt.setId(nbClients);
		ServPartie.addJoueur(clt);
		System.out.println("Un nouveau client, id: " + clt.getId());
		nbClients++; // un client en plus
	}

	public String LireMessage(Joueur clt) throws IOException {
		String msg = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(clt.getS().getInputStream()));
		msg = in.readLine();
		// System.out.println("msg recu : " + msg);
		return msg;
	}
	
	public boolean getNbJAtt() {
		return NbJAtt;
	}

	public void setNbJAtt(boolean nbJAtt) {
		NbJAtt = nbJAtt;
	}
	
}