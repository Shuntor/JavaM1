import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Iungmann Vaurigaud Hernandez
 *
 */
public class Vue {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtPrnom;
	private JTextField jTextField_NewCompetence, textField_1, textField_MailConnexion, textField_MDPConnexion, textField_MailModif, textField_NomModif, textField_prenomModif, textField_TelModif, textField_anneeModif, textField_MailInscription, textField_NomInscription, textField_prenomInscription, textField_TelInscription, textField_anneeInscription, textField_MDPInscription, textField_MDPConfInscription;
	private JTextField textField_2;
	private JTextField textField_3;
	private JDialog jDialogConnexion, jDialogModif, jDialogInscription;
	private JButton btnDeleteComp, btnNewComp, btnAddComp, btnConnexion, btnAnnulerConnexion,  btnModifier,  btnAnnulerModifier, btnInscription, btnAnnulerInscription;
	public static TableModel modTable;
	private JTable table_1;
	private JLabel jLabelCompetences, jLabelCompetencesSelect, jLabelEmailConnexion, jLabelMDPConnexion, jLabelEmailModif, jLabelNomModif, jLabelPrenomModif, jLabelTelModif, jLabelAnneeModif, jLabelEmailInscription, jLabelNomInscription, jLabelPrenomInscription, jLabelTelInscription, jLabelAnneeInscription, jLabelMDPInscription, jLabelMDPConfInscription;
	private boolean connecte=false;
	private static GestionProtocoleClient gestion = new GestionProtocoleClient();
	JOptionPane jOption;
	static JList listeCompetences, listeCompetencesSelectionnees;
	private String listeComps[];
	private static DefaultListModel<String> DLM2;
	private ArrayList<String> listeComps2=null;
	private static ArrayList<String> listeCompsL, listeCompsSelect=null;
	/**
	 * Launch the application.
	 */
	public static void lancement() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Vue window = new Vue();
					
					window.frame.setVisible(true);
					gestion.connexion();
					chargerCompetences();
					chargerEtudiants();
					System.out.println("SYSO");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vue() {
		modTable=new TableModel();
	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Option Panes:
		jOption = new JOptionPane();
        
		
		/********************************************************************
		 * 
		 * Fen�tre Principale.
		 * 
		 ********************************************************************/
		frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 574);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		    	try {
					gestion.arret();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        System.exit(0);
		      }
		    });
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 484, 516);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblConnect = new JLabel("Connect!");
		lblConnect.setFont(new Font("High Tower Text", Font.BOLD, 20));
		lblConnect.setBounds(196, 11, 110, 20);
		panel.add(lblConnect);
		
		JLabel lblRecherche = new JLabel("Recherche");
		lblRecherche.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRecherche.setBounds(10, 31, 86, 20);
		panel.add(lblRecherche);
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setBounds(10, 62, 46, 14);
		panel.add(lblNom);
		
		textField = new JTextField();
		textField.setBounds(50, 59, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Pr\u00E9nom");
		lblNewLabel.setBounds(169, 62, 46, 14);
		panel.add(lblNewLabel);
		
		txtPrnom = new JTextField();
		txtPrnom.setBounds(227, 59, 86, 20);
		panel.add(txtPrnom);
		txtPrnom.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(354, 62, 46, 14);
		panel.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setBounds(388, 59, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		

		JLabel lblTl = new JLabel("T\u00E9l.:");
		lblTl.setBounds(10, 87, 46, 14);
		panel.add(lblTl);

		
		textField_2 = new JTextField();
		textField_2.setBounds(50, 84, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		

		JLabel lblAnneDeDiplomation = new JLabel("Ann\u00E9e de diplomation:");
		lblAnneDeDiplomation.setBounds(169, 87, 117, 14);
		panel.add(lblAnneDeDiplomation);

		
		textField_3 = new JTextField();
		textField_3.setBounds(294, 84, 106, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 146, 464, 359);
		panel.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setModel(modTable);
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton = new JButton("Rechercher!");
		btnNewButton.setBounds(169, 112, 117, 23);
		panel.add(btnNewButton);
		
		/********************************************************************
		 * 
		 * Barre de Menu 		
		 * 
		 ********************************************************************/
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnConnexion = new JMenu("Connexion");
		menuBar.add(mnConnexion);
		
		JMenuItem mntmSinscrire = new JMenuItem("S'Inscrire");
		mntmSinscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jDialogInscription.setVisible(true);
			}
		});
		mnConnexion.add(mntmSinscrire);
		
		JMenuItem mntmSeConnecter = new JMenuItem("Se Connecter");
		mntmSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jDialogConnexion.setVisible(true);
				
			}
		});
		mnConnexion.add(mntmSeConnecter);
		
		JMenuItem mntmSeDconnecter = new JMenuItem("Se D\u00E9connecter");
		mntmSeDconnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean confirm=false;
				try {
					confirm=gestion.deconnexionClient();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (confirm==true){
					connecte=false;
					if(connecte==false){
						mntmSeDconnecter.setEnabled(false);
						mntmSeConnecter.setEnabled(true);
						mntmSinscrire.setEnabled(true);
					}
					
				} 
				else{
					connecte=true;
					if(connecte==true){
						mntmSeDconnecter.setEnabled(true);
						mntmSeConnecter.setEnabled(false);
						mntmSinscrire.setEnabled(false);
					}
				}
			}
		});
		if(connecte==false){
			mntmSeDconnecter.setEnabled(false);
		}
		mnConnexion.add(mntmSeDconnecter);
		
		
		
		JMenu mnGestionDuCompte = new JMenu("Gestion du compte");
		menuBar.add(mnGestionDuCompte);
		
		JMenuItem mntmModifierMesInformations = new JMenuItem("Modifier mes informations");
		mntmModifierMesInformations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jDialogModif.setVisible(true);
			}
		});
		mnGestionDuCompte.add(mntmModifierMesInformations);
		
		
		/********************************************************************
		 * PopUp de connexion
		 * 
		 ********************************************************************/
		jDialogConnexion = new JDialog();
		jDialogConnexion.setMinimumSize(new java.awt.Dimension(400, 300));
        jDialogConnexion.setLocationRelativeTo(null);
        jDialogConnexion.setModal(true);
        jDialogConnexion.getContentPane().setLayout(null);
        jDialogConnexion.setTitle("Connexion");
        
      //Option Panes:
        
        
        jLabelEmailConnexion = new JLabel("Adrese E-mail:");
        jLabelEmailConnexion.setBounds(150, 20, 117, 23);
		jDialogConnexion.getContentPane().add(jLabelEmailConnexion);
		
		jLabelMDPConnexion = new JLabel("Mot de Passe:");
		jLabelMDPConnexion.setBounds(150, 100, 117, 23);
		jDialogConnexion.getContentPane().add(jLabelMDPConnexion);
        
		textField_MailConnexion = new JTextField();
		textField_MailConnexion.setBounds(150, 50, 86, 20);
		jDialogConnexion.getContentPane().add(textField_MailConnexion);
		textField_MailConnexion.setColumns(10);
		
		textField_MDPConnexion = new JTextField();
		textField_MDPConnexion.setBounds(150, 130, 86, 20);
		jDialogConnexion.getContentPane().add(textField_MDPConnexion);
		textField_MDPConnexion.setColumns(10);
		
        btnAnnulerConnexion = new JButton("Annuler");
		btnAnnulerConnexion.setBounds(60, 210, 117, 23);
		jDialogConnexion.getContentPane().add(btnAnnulerConnexion);
        
        btnConnexion = new JButton("Se Connecter!");
		btnConnexion.setBounds(200, 210, 117, 23);
		jDialogConnexion.getContentPane().add(btnConnexion);
		
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String eMail, mDP=null;
				boolean confirm=false;
				eMail=textField_MailConnexion.getText();
				mDP=textField_MDPConnexion.getText();
				
				try {
					confirm=gestion.connexionClient(eMail,mDP);
					System.out.println("confirm = "+confirm);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (confirm==true){
					connecte=true;
					jOption.showMessageDialog(null, "INFO: Vous �tes bien connect�", "Information", JOptionPane.INFORMATION_MESSAGE);
					if(connecte==true){
						mntmSeDconnecter.setEnabled(true);
						mntmSeConnecter.setEnabled(false);
						mntmSinscrire.setEnabled(false);
						
					}
					
				}
				else{
					connecte=false;
					jOption.showMessageDialog(null, "ERREUR: Echec de la connexion", "Erreur", JOptionPane.ERROR_MESSAGE);
					if (connecte==false){
						mntmSeDconnecter.setEnabled(false);
						mntmSeConnecter.setEnabled(true);
						mntmSinscrire.setEnabled(true);
					}
				}
					
				

				jDialogConnexion.dispose();

			}
		});
        
		btnAnnulerConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jDialogConnexion.dispose();
			}
		});
		


		/********************************************************************
		 * PopUp de gestion infos
		 * 
		 ********************************************************************/
		jDialogModif = new JDialog();
		jDialogModif.setMinimumSize(new java.awt.Dimension(500, 400));
		jDialogModif.setLocationRelativeTo(null);
		jDialogModif.setModal(true);
		jDialogModif.getContentPane().setLayout(null);
		jDialogModif.setTitle("Modifier ses informations");
        		
		jLabelNomModif = new JLabel("Nom:");
		jLabelNomModif.setBounds(60, 10, 117, 23);
		jDialogModif.getContentPane().add(jLabelNomModif);
        
		textField_NomModif = new JTextField();
		textField_NomModif.setBounds(60, 43, 86, 20);
		jDialogModif.getContentPane().add(textField_NomModif);
		textField_NomModif.setColumns(10);

		
		jLabelPrenomModif = new JLabel("Pr�nom:");
		jLabelPrenomModif.setBounds(350, 10, 117, 23);
		jDialogModif.getContentPane().add(jLabelPrenomModif);
        
		textField_prenomModif = new JTextField();
		textField_prenomModif.setBounds(350, 43, 86, 20);
		jDialogModif.getContentPane().add(textField_prenomModif);
		textField_prenomModif.setColumns(10);

        jLabelEmailModif = new JLabel("Adrese E-mail:");
        jLabelEmailModif.setBounds(60, 80, 117, 23);
		jDialogModif.getContentPane().add(jLabelEmailModif);
		
		textField_MailModif = new JTextField();
		textField_MailModif.setBounds(60, 113, 86, 20);
		jDialogModif.getContentPane().add(textField_MailModif);
		textField_MailModif.setColumns(10);
		
		jLabelTelModif = new JLabel("T�l.:");
		jLabelTelModif.setBounds(350, 80, 117, 23);
		jDialogModif.getContentPane().add(jLabelTelModif);
        
		textField_TelModif = new JTextField();
		textField_TelModif.setBounds(350, 117, 86, 20);
		jDialogModif.getContentPane().add(textField_TelModif);
		textField_TelModif.setColumns(10);
		
		jLabelAnneeModif = new JLabel("Ann�e:");
		jLabelAnneeModif.setBounds(200, 150, 117, 23);
		jDialogModif.getContentPane().add(jLabelAnneeModif);
        
		textField_anneeModif = new JTextField();
		textField_anneeModif.setBounds(200, 183, 86, 20);
		jDialogModif.getContentPane().add(textField_anneeModif);
		textField_anneeModif.setColumns(10);
		
		btnAnnulerModifier = new JButton("Annuler");
		btnAnnulerModifier.setBounds(20, 300, 200, 23);
		jDialogModif.getContentPane().add(btnAnnulerModifier);
        
        btnModifier = new JButton("Envoyer les modifications");
		btnModifier.setBounds(270, 300, 200, 23);
		jDialogModif.getContentPane().add(btnModifier);
		
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jDialogModif.dispose();
			}
		});
        
		btnAnnulerModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jDialogModif.dispose();
			}
		});
		
		/********************************************************************
		 * PopUp de inscription
		 * 
		 ********************************************************************/
		jDialogInscription = new JDialog();
		jDialogInscription.setMinimumSize(new java.awt.Dimension(500, 600));
		jDialogInscription.setLocationRelativeTo(null);
		jDialogInscription.setModal(true);
		jDialogInscription.getContentPane().setLayout(null);
		jDialogInscription.setTitle("Modifier ses informations");
        	
		jLabelNomInscription = new JLabel("Nom:");
		jLabelNomInscription.setBounds(60, 10, 117, 23);
		jDialogInscription.getContentPane().add(jLabelNomInscription);
        
		textField_NomInscription = new JTextField();
		textField_NomInscription.setBounds(60, 35, 86, 20);
		jDialogInscription.getContentPane().add(textField_NomInscription);
		textField_NomInscription.setColumns(10);
		
		jLabelPrenomInscription = new JLabel("Pr�nom:");
		jLabelPrenomInscription.setBounds(200, 10, 117, 23);
		jDialogInscription.getContentPane().add(jLabelPrenomInscription);
        
		textField_prenomInscription = new JTextField();
		textField_prenomInscription.setBounds(200, 35, 86, 20);
		jDialogInscription.getContentPane().add(textField_prenomInscription);
		textField_prenomInscription.setColumns(10);

        jLabelEmailInscription = new JLabel("Adrese E-mail:");
        jLabelEmailInscription.setBounds(60, 80, 117, 23);
        jDialogInscription.getContentPane().add(jLabelEmailInscription);
		
		textField_MailInscription = new JTextField();
		textField_MailInscription.setBounds(60, 105, 86, 20);
		jDialogInscription.getContentPane().add(textField_MailInscription);
		textField_MailInscription.setColumns(10);
		
		jLabelTelInscription = new JLabel("T�l.:");
		jLabelTelInscription.setBounds(350, 10, 117, 23);
		jDialogInscription.getContentPane().add(jLabelTelInscription);
        
		textField_TelInscription = new JTextField();
		textField_TelInscription.setBounds(350, 35, 86, 20);
		jDialogInscription.getContentPane().add(textField_TelInscription);
		textField_TelInscription.setColumns(10);
		
		jLabelAnneeInscription = new JLabel("Ann�e diplomation:");
		jLabelAnneeInscription.setBounds(60, 147, 117, 23);
		jDialogInscription.getContentPane().add(jLabelAnneeInscription);
        
		textField_anneeInscription = new JTextField();
		textField_anneeInscription.setBounds(60, 175, 86, 20);
		jDialogInscription.getContentPane().add(textField_anneeInscription);
		textField_anneeInscription.setColumns(10);
		
		jLabelMDPInscription = new JLabel("Mot de Passe:");
		jLabelMDPInscription.setBounds(350, 80, 117, 23);
		jDialogInscription.getContentPane().add(jLabelMDPInscription);
        
		textField_MDPInscription = new JTextField();
		textField_MDPInscription.setBounds(350, 105, 86, 20);
		jDialogInscription.getContentPane().add(textField_MDPInscription);
		textField_MDPInscription.setColumns(10);
		
		jLabelMDPConfInscription = new JLabel("Confirmer:");
		jLabelMDPConfInscription.setBounds(350, 147, 117, 23);
		jDialogInscription.getContentPane().add(jLabelMDPConfInscription);
        
		textField_MDPConfInscription = new JTextField();
		textField_MDPConfInscription.setBounds(350, 175, 86, 20);
		jDialogInscription.getContentPane().add(textField_MDPConfInscription);
		textField_MDPConfInscription.setColumns(10);
		
		btnNewComp=new JButton("Cr�er une comp�tence");
		btnNewComp.setBounds(275, 230, 200, 20);
		jDialogInscription.getContentPane().add(btnNewComp);
		btnNewComp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					gestion.creerCompetence(jTextField_NewCompetence.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				chargerCompetences();
				
				;
			}
		});
		
		jTextField_NewCompetence = new JTextField();
		jTextField_NewCompetence.setBounds(20, 230, 250, 20);
		jDialogInscription.getContentPane().add(jTextField_NewCompetence);
		jTextField_NewCompetence.setColumns(10);
		
		btnAnnulerInscription = new JButton("Annuler");
		btnAnnulerInscription.setBounds(20, 510, 200, 23);
		jDialogInscription.getContentPane().add(btnAnnulerInscription);
        
        btnInscription = new JButton("Envoyer les informations");
        btnInscription.setBounds(270, 510, 200, 23);
		jDialogInscription.getContentPane().add(btnInscription);
		
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean retour=false;
				try {
					retour=gestion.envoiInfo(textField_NomInscription.getText(), textField_prenomInscription.getText(), textField_MailInscription.getText(), textField_TelInscription.getText(), textField_anneeInscription.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jDialogInscription.dispose();
			}
		});
        
		btnAnnulerInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jDialogInscription.dispose();
			}
		});
		
		jLabelCompetences = new JLabel("Selectionnez vos comp�tences:");
		jLabelCompetences.setBounds(20, 295, 250, 23);
		jDialogInscription.getContentPane().add(jLabelCompetences);
		
		listeCompetences=new JList();
		listeCompetences.setBounds(30, 320, 130, 170);
		jDialogInscription.getContentPane().add(listeCompetences);
		
		
       
		
		jLabelCompetences = new JLabel("Comp�tences s�lectionn�es:");
		jLabelCompetences.setBounds(300, 295, 250, 23);
		jDialogInscription.getContentPane().add(jLabelCompetences);
		
		listeCompetencesSelectionnees=new JList();
		listeCompetencesSelectionnees.setBounds(320, 320, 130, 170);
		jDialogInscription.getContentPane().add(listeCompetencesSelectionnees);
		
		btnAddComp = new JButton("Add >>");
		btnAddComp.setBounds(188, 365, 110, 30);
		jDialogInscription.getContentPane().add(btnAddComp);
		btnAddComp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=0;
				String j=null;
				i=listeCompetences.getSelectedIndex();
				j=listeCompsL.get(i);
				listeCompsSelect.add(j);
				chargerCompetencesSelectionnees();
			}
		});
		
		btnDeleteComp = new JButton("<< Delete");
		btnDeleteComp.setBounds(188, 400, 110, 30);
		jDialogInscription.getContentPane().add(btnDeleteComp);
	}
	
	private void addWindowListener(WindowAdapter windowAdapter) {
		// TODO Auto-generated method stub
		
	}

	public void setConnecte(boolean entree){
		if (entree==true){
			this.connecte=true;
		}
		else{
			this.connecte=false;
		}
	}
	public static void chargerCompetences(){
		listeCompetences.removeAll();
			
		try{
			listeCompsL= gestion.recupererCompetences();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		DefaultListModel DLM = new DefaultListModel();
        for (int i = 0; i < listeCompsL.size(); i++)
            {
                 DLM.addElement(listeCompsL.get(i)); 
            }
        listeCompetences.setModel(DLM);
	}
	
	public static void chargerCompetencesSelectionnees(){
		listeCompetencesSelectionnees.removeAll();
			
		
		DefaultListModel DLM2 = new DefaultListModel();
        for (int i = 0; i < listeCompsSelect.size(); i++)
            {
                 DLM2.addElement(listeCompsSelect.get(i)); 
            }
        listeCompetencesSelectionnees.setModel(DLM2);
	}
	
	public static void chargerEtudiants() throws IOException{
		ArrayList<Etudiant> listeEtu = null;
		
		String requete =gestion.recupEtudiants();
		String tabRequete[]=requete.split("#");
		Etudiant etudiant = null;
		for (int i = 0; i < tabRequete.length; i++) {
			System.out.println("requete="+requete);
			
			
			if(i%2==0){
				etudiant = new Etudiant(tabRequete[i],tabRequete[i+1],null,null,null);
				modTable.addEtudiant(etudiant);
			}
			else{
				;
			}
		
		}
	}

}
