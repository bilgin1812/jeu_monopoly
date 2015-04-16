package GestionServ;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Menu extends JFrame{
	private static final long serialVersionUID = 1L;
	private static boolean checkClique = false;
	
	//declaration composant graphique
	private JPanel pan = new JPanel();
	private JPanel boutonPane = new JPanel();
	private JButton boutonCree = new JButton("Crée une Partie");
	private JButton boutonSup = new JButton("Supprimer Partie(s)");
	private JLabel titre = new JLabel("Gestionnaire Serveur");
	private JLabel titrejoueurs = new JLabel("Joueur(s) connecté(s)");
	
	
	// ********************************************************************
	// tableau joueurs (model)
	// ******************************************************************//
	private String[]  entetesTJ = {"Joueur connecte"};
	private Object[][] donneesTJ = {
			{"Cysboy"},
			{"BZHHydde"},
			{"IamBow"},
			{"FunMan"}
	};
	private DefaultTableModel modelTJ = new  DefaultTableModel(donneesTJ,entetesTJ);
	
	// ********************************************************************
	// JTable
	// mise en forme du tableau de joueurs
	// ******************************************************************//
	private JTable tableJoueur = new JTable(modelTJ) {
		private static final long serialVersionUID = 1L;
		
		//met les cellules en read only si pas colonne des check box
		public boolean isCellEditable(int row, int columm){
			return false;
		}
	};
	
	// ********************************************************************
	// tableau partie (model)
	// ******************************************************************//
	private String[] entetes = {"", "Nom", "Nb joueurs", "Etat"};
	private Object[][] donnees = {
            {false, "Gniaaa", "2/5", "En attente"},
            {false, "Mannheim", "5/5", "En Cours"},
            {false, "qwertz", "2/5", "En attente"},
            {false, "Moew", "4/5", "En attente"},
            {false, "Wouf", "5/5", "En Cours"},
            {false, "Salut", "5/5", "En Cours"}
    };
	private DefaultTableModel model = new  DefaultTableModel(donnees,entetes);
	
	//model cellule
	private DefaultTableCellRenderer MoncellRenderer = new DefaultTableCellRenderer();
	
	// ********************************************************************
	// JTable
	// mise en forme du tableau
	// ******************************************************************//
	private JTable tableau = new JTable(model) {
		private static final long serialVersionUID = 1L;
		
		//met les cellules en read only si pas colonne des check box
		public boolean isCellEditable(int row, int columm){
			if (columm != 0)
				return false;
			else
				return true;
		}
		
        public Class<?> getColumnClass(int column) {
        	//alignement centrer
        	MoncellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        	//applique a la colonne nb joueurs
        	getColumn("Nb joueurs").setCellRenderer(MoncellRenderer);
            switch (column) {
                case 0:
                    return Boolean.class;
                case 1:
                    return String.class;
                case 2:
                    return String.class;           
                case 3:
                    return String.class;
            }
			return null;
        }
    };
	
    // ********************************************************************
 	// CreePartie
 	// action produite par le bouton "boutonCree"
 	// ******************************************************************//
    class CreePartie implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Gniaaa");
		}
    }
    
    // ********************************************************************
  	// SupPartie
  	// action produite par le bouton "boutonSup"
  	// ******************************************************************//
    class SupPartie implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		System.out.println("Gniaaa");
    	}
    }
     
    // ********************************************************************
   	// SelectPartie
   	// action produite quand clique sur ligne du tableau
   	// ******************************************************************//
    class SelectPartie implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			int idligne;
			
			//test pour qu il fasse action une seul fois par clique 
			//(avant fasait au clique et au relachement)
			if(checkClique == false){
				//**************rempli tabjoueur
				//affiche tablejoueurs
				titrejoueurs.setVisible(true);
				tableJoueur.setVisible(true);
				//id ligne
				idligne = Character.getNumericValue(arg0.getSource().toString().charAt(arg0.getSource().toString().length()-2));
				System.out.println(idligne);
				checkClique = true;
			}
			else{
				checkClique = false;
			}	
		}
    }
	
    // ********************************************************************
 	// Menu
 	// Cree la fenetre
 	// ******************************************************************//
	public Menu(){
		this.setTitle("Monopoly");
		this.setSize(300, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//bloque la multi selection de ligne
		tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//bloque selection ligne tablejoueur
		tableJoueur.setRowSelectionAllowed(false);
		
		//ajout listener
		boutonCree.addActionListener(new CreePartie());
		boutonSup.addActionListener(new SupPartie());
		tableau.getSelectionModel().addListSelectionListener(new SelectPartie());
		
		//btn
		boutonPane.add(boutonCree);
		boutonPane.add(boutonSup);
		
		//fenetre
		pan.add(titre, "Center");
		pan.add(tableau.getTableHeader(), BorderLayout.NORTH);
		tableau.getTableHeader().setEnabled(false);;
		pan.add(tableau, BorderLayout.CENTER);
		pan.add(boutonPane);
		pan.add(titrejoueurs, "Center");
		tableJoueur.getColumnModel().getColumn(0).setPreferredWidth(200);
		pan.add(tableJoueur, BorderLayout.CENTER);
		
		//cache tablejoueurs
		titrejoueurs.setVisible(false);
		tableJoueur.setVisible(false);
		
		this.setContentPane(pan);
		this.setVisible(true);
	} 
}
