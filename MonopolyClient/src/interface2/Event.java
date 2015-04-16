package interface2;

import java.io.IOException;

public class Event extends Thread {
	Player player;
	Client cl;
	public Event(Player pl){
		this.player =pl;
		//activation du client
		cl = new Client("192.168.1.1",18000 );
		cl.connectServeur();
		if (cl.socketJeu.isConnected()==true){
			System.out.println("Le joueur est connect�");
			cl.envoiMessage(this.player.id);
		}
		
	}
	
	public void run(){
		while (this.cl.socketJeu.isConnected()){
			    
			  
				
				//player souhaite lanc� un d�
				if(this.player.doAction==3){
					System.out.println("Etat 3");
					if (this.player.myTurn){
						System.out.println("On est dans l'�tat 3");
						this.player.doAction=13;
						this.player.played=true;
						this.cl.envoiMessage("4;"+this.player.id);					
					
					}
					else
						System.out.println("Ce n'est pas votre tour");
						this.player.doAction=13;
				}
				
				//le client souhaite acheter
				if(this.player.doAction==4){
					System.out.println("Etat 4");
					if (this.player.myTurn){
						String question="Que souhaitez vous achetez une maison(1) ? Un Hotel(2)";
						PopStringUp pop = new PopStringUp(this.player, question);
						String rep = pop.getAnswer();
						
						//S'agit il d'une maison ou un Hotel
						if (rep.equals("1")){
							
							PopUpAchat buy = new PopUpAchat(this.player, " De quel couleur est la maison que vous souhaitez achetez");
							String House_Color = buy.getPopUpAnswer();
							
							//a t'il suffisamment de terrain d cette couleur
							if(this.player.numberOfTerrain(House_Color)==3){
								System.out.println("Ok vous pouvez acheter une maison sur les terrains de cette couleur");
								this.player.somme = this.player.somme-200;
								this.player.InsertHouses(new Maison(10, 10, House_Color), House_Color);
								this.player.gotHouse();
							}
							
							else
								System.out.println("Vous n'avez pas suffisamment de terrain pour achetez une maison de cette couleur");
							}
						
						//l'utilisateur veut acheter
							else if (rep.equals("2")){
								PopUpAchat buy = new PopUpAchat(this.player, " De quel couleur est l'hotel que vous souhaitez achetez");
								String House_Color = buy.getPopUpAnswer();
								if (this.player.numberOfHouse("House_Color")==3)
									System.out.println("Ok vous pouver achetez un hotel");
								
								
														
							}
						
						
						//traitement avant envoie
					}
					
					this.player.doAction=13;
				}
				
				//
				
				//player envoie fin du tour
				if(this.player.doAction==5){
					if (this.player.played){
						System.out.println("je passe mon tour");
						this.cl.envoiMessage("3;"+this.player.id);
						this.player.played=false;
					    this.player.doAction= 13;
					}
					else{
						System.out.println("Fin du tour pas possible; vous n'avez pas jou�");
						this.player.doAction=3;
					}
						
				}
				
				//on peut rajouter d'autre cas ici, en g�n�ral il sont li� � l'action
				//sur un bouton, il faut aussi v�rifi� que c'est sont tour.
				
					
					
				
				//laisse l'utilisateur libre mais il �coute tout de m�me ce qui se passe
				if (this.player.doAction==13){
					String msg="";
					try {
						if(this.cl.buf.available()>0){
							 msg = this.cl.buf.readLine();
							if (msg.contains("nul")){
								System.out.println("Event a d�tect� un message nul");
								continue;
							}
							else{
								this.player.messageAction(msg);
								if (this.player.question==1){
									this.player.question=0;
									cl.envoiMessage("true");					
								
								}
							
								else if(this.player.question==2){
									this.player.question=0;
									cl.envoiMessage("false");
								
								}
							}
					}
								
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				}
				}
					

			
		 
	}
	
	
}

		
	


