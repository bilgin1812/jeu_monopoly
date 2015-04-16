package interface2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author bilginh
 *
 */
public class Client {
	public String ip;
	public int port;
	public boolean joue;
	public Socket socketJeu;
	public PrintWriter out = null;
	public DataInputStream buf;

	public Client(String ip, int port) {

		this.ip = ip;
		this.port = port;
		 
	}

	public void connectServeur() {
		System.out.println("Joueur se connecte");
		BufferedReader in = null;

		try {
			socketJeu = new Socket(ip, port);
			socketJeu.setKeepAlive(true);
			System.out.println("Connexion réussie");
			//buf = new BufferedReader(new InputStreamReader(this.socketJeu.getInputStream()));
			buf = new DataInputStream(socketJeu.getInputStream());

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void envoiMessage(String message) {
		try {
			System.out.println("dans envoieMessage le message est "+ message);
			socketJeu.setKeepAlive(true);
			out = new PrintWriter(socketJeu.getOutputStream());
			out.println(message);
			out.flush();
			System.out.println("send :"+message );
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void interpretMessage(String msg)
	{
		
		/*
		 * 
		 * 
0;IDJoueur
1;IDPartie;IDJoueur
2;IDPartie;IDJoueur;
3;IDJoueur
4;IDJoueur
5;IDJ1;IDJ2;Maison;prix
6;IDJoueur;GroupeMaison;Bool

		 */
		
		String[] mouvements=msg.split(";");
		switch(mouvements[0])
		{
		case "0":
		{		
				break;
		}
		case "1":
		{
			break;
		}
		case "2":
		{
			break;
		}
		case "3":
			break;
		case "4":
		{
			break;
		}
		case "5":
		{
			break;
		}
		case "6":
		{
			break;
		}
		case "7":
		{
			break;
		}
		case "8":
			break;
		case "9":
		{
			break;
		}
		case "10":
		{
			break;
		}
		case "11":
		{
			break;
		}
		case "12":
		{
			break;
		}
		}
		
	}
	
	public String getMessage(){
		System.out.println("en attente de msg");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(this.socketJeu.getInputStream()));
			String line;
			line = reader.readLine();
			System.out.println("getMessage msg :"+ line);
			return line;
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
		return "";	
		
	}
	
	
	/*public  String getMessage()  {
	    BufferedReader reader = null;
	    
		try {
			reader = new BufferedReader(new InputStreamReader(this.socketJeu.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    StringBuilder sb = new StringBuilder();
	    String line;
	    
	    try {
	    	//on bloque tant qu'on a rien re�u;
	    	System.out.println("En attente de reception message");
			while ((line = reader.readLine()) != null)
			    sb.append(line).append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return sb.toString();
	}*/
	public void deconnexion() {

		try {

			out.close();
			// in.close();
			socketJeu.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
