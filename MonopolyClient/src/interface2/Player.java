package interface2;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Player {
	pion[] pion;
	pion pi;
	Player player;
    int compteur = 0;
	Panel_Jeu pan;
	Panel_status panStat;
	Panel_Utilisateur panUtil;
	String id;
	
	
	String color;
	
	public ArrayList<String> id_list = new ArrayList<String>();
	
	
	//tableau des couleurs des cases � disposition
	 //0) brun 1)bleu 2)rose 3)rouge 4) jaune 5) vert 
	ArrayList <ArrayList<String>> ColorCase= new ArrayList<ArrayList<String>>();
	ArrayList <ArrayList<Maison>> Maison= new ArrayList<ArrayList<Maison>>();
	public int doAction=13; //Par d�faut on �coute les messages du serveur
	int somme = 0;
	Client cl;
	public boolean wantToPlay= false;
	int question= 0;
	boolean played = false;
	boolean myTurn= false; //chaque joueur obtient la possibilit� de jouer
	ArrayList<String> List_id;
	

	
	
	public Player(){
		
		player = this;
		this.fillColorCase(); //initialisation couleur
		this.fillHouses(); //initialisation du nombre de maison
		//liste des joueur
		
		
	}
	
	
	
	//d�vellopement nombre al�atoire
	public void getId(String id){
		this.id = id;
		this.List_id = new ArrayList<String>();
		this.List_id.add(id);
		
	}
	
	
	public void setPanelJeu(Panel_Jeu pan){
		this.pan= pan;
	}
	

	
	public void setPanelStatus(Panel_status panStat) {
		this.panStat = panStat;
	}
	
	public void setPanelUtil(Panel_Utilisateur util){
		this.panUtil=util;
	}
	
	public void getPion(pion p){
		this.pi= p;
		System.out.println("Joueur a le pion de couleur" + this.pi.color);
		
	}
	
	public void getTabPion(pion[] p){
		this.pion= p;
		
	}
	
	//action du lancement de d� pour l'instant
	public void achete(){
		System.out.println("le client achete");
		if(cl.socketJeu.isConnected()){
			System.out.println("le client est connect�");
			cl.envoiMessage("4;0");			
			String msg = cl.getMessage();
			System.out.println("msg");
		}
		else
			System.out.println("le client n'est pas connect�");	
			
	}
	
	
	//Initialisation du tableau des couleurs � disposition
	 //0) brun 1)bleu 2)rose 3)rouge 4) jaune 5) vert 
	
	public void fillColorCase(){
		
		 for(int i=0; i<6; i++){
			 this.ColorCase.add(i, new ArrayList<String>());
			 for (int j=0; j<3; j++)
		  			this.ColorCase.get(i).add("");
		  		
		 }
		     
	}
	
	public void InsertTerrain(String msg){
		int indice =0;
		switch(msg){
			case "brun":
				indice = 0;
				break;
			case "bleu":
				indice= 1;
				break;
			case "rose":
				indice = 2;
				break;
			case "red":
				indice = 3;
				break;
			case "jaune":
				indice= 4;
				break;
			case "vert":
				indice = 5;
				break;
		}
		
		
		for (int j=0; j<3; j++){
			if (this.ColorCase.get(indice).get(j).equals("")){
				this.ColorCase.get(indice).set(j, msg);
				break;
			}
			else
				System.out.println("Vous disposez de toutes cases de couleurs" + msg);
		}
		
		
	}
	
	//impression des terrains � dispositions du joueur 
	public void gotTerrain(){
		for (int i=0; i<6; i++){
			for (int j=0; j<3; j++){
				if (!this.ColorCase.get(i).get(j).equals(""))
					System.out.print(this.ColorCase.get(i).get(j)+ " ");
			}
			System.out.println("");
		}
		
	}
	
	
	//combien de terrain de cette couleur poss�de le joueur
	public int numberOfTerrain(String msg){
		int compteur=0;
		int indice =0;
		switch(msg){
			case "brun":
				indice = 0;
				break;
			case "bleu":
				indice= 1;
				break;
			case "rose":
				indice = 2;
				break;
			case "red":
				indice = 3;
				break;
			case "jaune":
				indice= 4;
				break;
			case "vert":
				indice = 5;
				break;
		}
				
		for (int i=0; i<3; i++){
			if(this.ColorCase.get(indice).get(i).equals(msg))
				compteur++;
		}
				
		return compteur;
			
	}
	
	
	
	//Initialisation du tableau des Maison � disposition
		 //0) brun 1)bleu 2)rose 3)rouge 4) jaune 5) vert 
		
		public void fillHouses(){
			
			 for(int i=0; i<6; i++){
				 this.Maison.add(i, new ArrayList<Maison>());
				
			  		
			 }
			     
		}
		
		public void InsertHouses(Maison m, String msg){
			int indice =0;
			switch(msg){
				case "brun":
					indice = 0;
					break;
				case "bleu":
					indice= 1;
					break;
				case "rose":
					indice = 2;
					break;
				case "red":
					indice = 3;
					break;
				case "jaune":
					indice= 4;
					break;
				case "vert":
					indice = 5;
					break;
			}
			
			boolean full =true;
			for (int j=0; j<9; j++){
				if (this.Maison.get(indice).get(j)==null){
					this.Maison.get(indice).set(j, m);
					full = false;
					break;
				}
			}
				if (full ==true)
					System.out.println("Vous disposez de toute les maisons de cette couleur, vous devriez achet� un Hotel" + msg);
			}
			
			
		
		
		//impression des maisons � dispositions du joueur 
		public void gotHouse(){
			for (int i=0; i<6; i++){
				for (int j=0; j<3; j++){
					if (this.Maison.get(i).get(j) !=null)
						System.out.print("Cette maison appartient � "+ this.Maison.get(i).get(j).getOwner());
				}
				System.out.println("");
			}
			
		}
		
		
		//combien de maison de cette couleur poss�de le joueur
		public int numberOfHouse(String msg){
			int compteur=0;
			int indice =0;
			switch(msg){
				case "brun":
					indice = 0;
					break;
				case "bleu":
					indice= 1;
					break;
				case "rose":
					indice = 2;
					break;
				case "red":
					indice = 3;
					break;
				case "jaune":
					indice= 4;
					break;
				case "vert":
					indice = 5;
					break;
			}
					
			for (int i=0; i<3; i++){
				if(this.Maison.get(indice).get(i)!= null)
					compteur++;
			}
					
			return compteur;
				
		}
	
	
		
	
	
	public void lance_de(){
		
		System.out.println("Vous avez appuy� sur la touche lanc� d�");
		//this.pi.movePion(2);
		//this.pan.repaint();
		this.doAction = 3;
		   
		    
	}  

	
	public void stop_partie(){
		System.out.println("le client id=" +this.id+ "stop la partie");
		while(cl.getMessage().isEmpty()){System.out.println("Dans la boucle");}; //pour l'instant on bloque
		String message=cl.getMessage();
	}
	
	public void doSomeAction(int valeur){
		if (valeur ==1){
			this.lance_de();
			this.wantToPlay=false;
		}
		
	}
	
	
	//Gestion des messages provenant du serveur
	public void messageAction(String message){
		if (message!=null || !message.equals("nul")){
			
			System.out.println("le message est " + message);
			//impression liste_id et ip_pion
			System.out.print(" la liste des id est : " );
			for (int i=0; i<this.List_id.size(); i++){
				System.out.print(this.List_id.get(i) + " ");
			}
			System.out.println("");
			System.out.print("la liste des ip pion est : ");
			for (int i=0; i<this.pion.length; i++){
				System.out.print(this.pion[i].id + " ");
			}
			System.out.println("");
			System.out.println("");
			System.out.println("");
			
			String[] msg = message.split(";");
			
			boolean test=false;
			int num=0;
			
			//System.out.println("le message est " + msg[0] + " " + msg[1]+ "Liste Id avant insertion");
			
			
			for (int j=0; j<this.List_id.size(); j++)
				System.out.println(this.List_id.get(j));
			
		
		
		 
			try{
				for (int i=0; i<this.List_id.size(); i++){
					num=i;
					if (this.List_id.get(i).equals(msg[1])){					
						test= true;
						break;
					
					}
				}
			
			
			
			
			if (test== false){
				num= num+1;
				System.out.println("L'id est inconnu");	
				System.out.println("Avant insertion");
				System.out.print(" la liste des id est : " );
				for (int i=0; i<this.List_id.size(); i++){
					System.out.print(this.List_id.get(i) + " ");
				}
				System.out.println("");
				System.out.print("la liste des ip pion est : ");
				for (int i=0; i<this.pion.length; i++){
					System.out.print(this.pion[i].id + " ");
				}
				System.out.println("");
				this.List_id.add(msg[1]);
			 
				
				this.pion[num].id=Integer.valueOf(msg[1]);
			System.out.println("Après insertion");
			System.out.print(" la liste des id est : " );
			for (int i=0; i<this.List_id.size(); i++){
				System.out.print(this.List_id.get(i) + " ");
			}
			System.out.println("");
			System.out.print("la liste des ip pion est : ");
			for (int i=0; i<this.pion.length; i++){
				System.out.print(this.pion[i].id + " ");
			}
			System.out.println("");
			}
			
				
				
			switch (msg[0]){
				case "0":
				break;
				
				//Tour du joueur id XXXX
				case "1":
				//System.out.println("message 1 RECUUUUUUUUU");
					
					if(msg[1].equals(this.id)){
						this.panStat.info.setText("c'est a vous de Jouer");
						this.myTurn=true;
						this.doAction=13; //le player peut se mettre en mode libre		
					
					}
					else
						this.panStat.info.setText("C'est au joueur "+ msg[1] + " de jouer");
					
				break;
				
			//avance de XXX cases	
			case "2" :
				
				System.out.println("On doit bouger le pion avec l'id" + this.pion[num].id);
				this.panStat.info.setText("le joueur id " + msg[1] +" bouge de " + msg[2]);
				this.pion[num].movePion(Integer.valueOf(msg[2]));
				this.pan.repaint();
				this.doAction= 13;			
				
				break;
			//le joueur gagne de l'argent	
			case "3":
				if (msg[1].equals(this.id))
					somme= somme+Integer.valueOf(msg[2]);
				else
					this.panStat.info.setText("le joueur id " + msg[1] +" gagne " + msg[2]);
				// insertion d'un jlabel d�di�
				
				
				this.doAction=13;
				break; 
			
			//le joueur XXX gagne la partie
			case "4":
				JFrame frame= new JFrame();
				if (msg[1].equals(this.id)){
					System.out.println("Vous avez gagn� la partie, le joueur" + msg[1] );
					
					 //new  JOptionPane.showOptionDialog(frame, "Vous avez gagn�",
							//"Victory Message", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
							//null);
				}
				else
					System.out.println("Vous avez perdu la partie, le joueur" + msg[1] );				
    				this.myTurn=false;
				break;
				
				
				
			//!!! reception proposition d'achat
			case "5": 
				if (msg[2].equals(this.id)){
					
					String propAchat= "le joueur " + msg[1] +" vous propose d'acheter la maison" + msg[3]+ " pour " + msg[4]; 
					
					PopUp p = new PopUp(this.player, propAchat);
					question = p.getPopUpAnswer();
					
					PopUpAchat buy = new PopUpAchat(this.player, " De quel couleur est l'objet que vous souhaitez achetez");
					String House_Color = buy.getPopUpAnswer();
					

	    			this.doAction=13;		
				}
				
				break;
				
			//Le joueur 2 a acheter notre maison
			//!!!on r�pond ??
			case "6":
				System.out.println(" reception message 6");
				if(msg[3].equals("true"))
					System.out.println("le joueur "+ msg[1]+ " a acheter la maison");
				//cl.envoiMessage("3;blabla");
    			this.myTurn=false;
				break;
			
			// argent disponible sur le compte
			case "7":
				
				if (msg[1].equals(this.id)){
					System.out.println("vous avez "+ msg[2]+" fr" );
					somme = Integer.valueOf(msg[2]);
				}
				//cl.envoiMessage("3;blabla");
    			this.myTurn=false;
    		break;	
			
			//Ici on fait quoi concr�tement?		
			case "8":
				System.out.println("message 8");
				
				this.myTurn=false;
				break;
			
		    //proposition de vente lance une pop-up
			case "9":
				if(msg[1].equals(this.player.id)){
					System.out.println("on recoit une proposition d'achat de terrain");
					PopUp p = new PopUp(this.player,"Voulez vous achetez le terrain");
					question =p.getPopUpAnswer();
					//quelle terrain achete le joueur
					/*if (question==1 && !this.pan.position[this.pion[Integer.valueOf(this.player.id)-1].pos].color.equals("")){
						//selection du pion dans l'image
						System.out.println("Vous avez acheter le terrain de couleur" +this.pan.position[this.pion[Integer.valueOf(this.player.id)-1].pos].color );
						this.InsertTerrain(this.pan.position[this.pion[Integer.valueOf(this.player.id)-1].pos].color);
						this.gotTerrain();
						int x= this.pan.position[this.pion[Integer.valueOf(this.player.id)-1].pos].posx;
						int y= this.pan.position[this.pion[Integer.valueOf(this.player.id)-1].pos].posy;
						this.pan.terrain.add(new Terrain(x,y));
						this.pan.repaint();
						
    			}*/
    				
					if (question==1 && !this.pan.position[this.pion[num].pos].color.equals("")){
						//selection du pion dans l'image
						System.out.println("Vous avez acheter le terrain de couleur" +this.pan.position[this.pion[num].pos].color );
						this.InsertTerrain(this.pan.position[this.pion[num].pos].color);
						this.gotTerrain();
						int x= this.pan.position[this.pion[num].pos].posx;
						int y= this.pan.position[this.pion[num].pos].posy;
						this.pan.terrain.add(new Terrain(x,y));
						this.pan.repaint();
						
    			}
    			this.myTurn=false;
				}
    		break;
			case "10":
				System.out.println("le joueur " + msg[1] + " a la position " + msg[2] + " pour un prix de : "+ msg[3]);
				//cl.envoiMessage("3;blabla");
    			this.myTurn=false;
    			break;
			/*case "11":
				System.out.println("le joueur " + msg[1]+ " paye au joueur " + msg[2]+ " un prix de  " + msg[3] );
				//cl.envoiMessage("3;blabla");
    			this.myTurn=false;
    			break;
    		
    	    //tirage de carte
			case "12":
				System.out.println("vous avez tirez la carte :" + msg[3]);
				//cl.envoiMessage("3;blabla");
    			this.myTurn=false;
				break;
			case "13":
				System.out.println("message 13");
				//cl.envoiMessage("3;blabla");
    			this.myTurn=false;
    			break;
			case "14":
				System.out.println("Ouch sa douille : "+ msg[2]);
				//cl.envoiMessage("3;blabla");
    			this.myTurn=false;
				break;
				
			case "nul":
				System.out.println("MESSAGE NUL mais on continue");
				this.player.doAction= 13;
				break;*/
			default:
				System.out.println("la gestion du message "+ msg[0]+ " n'est pas encore r�alis�e ");
    			this.cl.envoiMessage("3;"+this.id);
    			this.myTurn=false;	
    			this.doAction=13;
    			break;
		
		
			}//fin du switch
			}catch(Exception E){ 
				System.out.print("sa plante ici a cause de ");
				System.out.println("Impression de toute la liste des id de la liste");
				System.out.print(" la liste des id est : " );
				for (int i=0; i<this.List_id.size(); i++){
					System.out.print(this.List_id.get(i) + " ");
				}
				System.out.println("");
				System.out.print("la liste des ip pion est : ");
				for (int i=0; i<this.pion.length; i++){
					System.out.print(this.pion[i].id + " ");
					}
				}finally{;} //si bug alors on fait rien
				
				
		 }
		
		
		}
		
	}

	

