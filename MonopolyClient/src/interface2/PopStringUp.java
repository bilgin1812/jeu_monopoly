package interface2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PopStringUp {
    JFrame frame;
    JLabel label;
    String msg;
    Player player;
    String answer;
    public PopStringUp(Player pl, String message){
    	player= pl;
    	this.msg=message;
    	answer= (String)JOptionPane.showInputDialog(frame, msg, "PopUp", JOptionPane.PLAIN_MESSAGE, null, null, "");
    	
    	//player.cl.envoiMessage(s);
    	
    }
    
    public String getAnswer(){
    	return answer;
    }

	
}
