package Objets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/*************************************************************************
 * Class Joueur :
 * Contient tous les informations du joueur
*************************************************************************/

public class Joueur {
	
	private int id;
	private int argent;
	private int position;
	
	private String nom;
	private boolean enPrison;
	private boolean carteSortirPrison;
	private boolean faillite;
	
	private Socket s; 			// recevra le socket liant au client
	private PrintWriter out; 	// pour gestion du flux de sortie
	private BufferedReader in; 	// pour gestion du flux d'entrée

	//initialisation joueur
	public Joueur(Socket accept) throws IOException {
		argent = 10000;
		faillite = false;
		s = accept;
		//out = new PrintWriter(s.getOutputStream());
		//in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getArgent() {
		return argent;
	}
	public void setArgent(int argent) {
		this.argent = argent;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public boolean isEnPrison() {
		return enPrison;
	}
	public void setEnPrison(boolean enPrison) {
		this.enPrison = enPrison;
	}
	public boolean isCarteSortirPrison() {
		return carteSortirPrison;
	}
	public void setCarteSortirPrison(boolean carteSortirPrison) {
		this.carteSortirPrison = carteSortirPrison;
	}
	public boolean isFaillite() {
		return faillite;
	}
	public void setFaillite(boolean faillite) {
		this.faillite = faillite;
	}
}
