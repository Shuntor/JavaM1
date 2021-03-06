import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Iungmann Vaurigaud Hernandez
 *
 */
public class Vue {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtPrnom;
	private JTextField jTextField_NewCompetenceModif, jTextField_NewCompetence, textField_1, textField_MailConnexion, textField_MDPConnexion, textField_MailModif, textField_NomModif, textField_prenomModif, textField_TelModif, textField_anneeModif, textField_MailInscription, textField_NomInscription, textField_prenomInscription, textField_TelInscription, textField_anneeInscription, textField_MDPInscription, textField_MDPConfInscription;
	private JTextField textField_2;
	private JTextField textField_3;
	private JDialog jDialogInfoEtudiant, jDialogConnexion, jDialogModif, jDialogInscription, jDialogLikers;
	private JButton btnDeleteCompModif, btnAddCompModif, btnNewCompModif, btnRafraichir, btnDeleteComp, btnNewComp, btnAddComp, btnConnexion, btnAnnulerConnexion,  btnModifier,  btnAnnulerModifier, btnInscription, btnAnnulerInscription;
	public static TableModel modTable;
	public static TableModelComp modTableComp;
	private JLabel jLabelNbLikes;
	private JTable table_1;
	private JLabel jLabelCompetencesSelectModif, jLabelCompetencesModif, jLabelEmailInfo, jLabelNomInfo, jLabelPrenomInfo, jLabelTelInfo, jLabelAnneeInfo, jLabelCompetences, jLabelCompetencesSelect, jLabelEmailConnexion, jLabelMDPConnexion, jLabelEmailModif, jLabelNomModif, jLabelPrenomModif, jLabelTelModif, jLabelAnneeModif, jLabelEmailInscription, jLabelNomInscription, jLabelPrenomInscription, jLabelTelInscription, jLabelAnneeInscription, jLabelMDPInscription, jLabelMDPConfInscription;
	public boolean connecte=false;
	private boolean verifMail, verifTel, verifAnnee=false;
	private static GestionProtocoleClient gestion = new GestionProtocoleClient();
	static JOptionPane jOption;
	static JList listeCompetencesModif, listeCompetencesSelectionneesModif, listeCompetences, listeCompetencesSelectionnees;
	private static String mailCo;
	private String listeComps[];
	private static DefaultListModel<String> DLM2;
	private static ArrayList<String> listeComps2=null;
	private static ArrayList<String> listeCompsL=null;
	private static ArrayList<String> listeCompsLModif=null;
	private static String retourCompModif;
	private static ArrayList<String> listeCompsSelectModif, listeCompsSelect=new ArrayList<String>();
	static ArrayList<Etudiant> listeEtu;
	VerifSaisie controle=new VerifSaisie();
	private ArrayList<String> jListeCompSelectModif;
	GestionProtocoleChat gestionChat = new GestionProtocoleChat();
	private JDialog jDialogLaisserMessage, jDialogConsulterMail;
	private String utilisateurSelectionne;
	private JButton btnConsultMail;
	private JTextArea textAreaMails, textArealikers;
	private String mailsrecus[];
	private JButton btnLike, btnUnlike, btnQuiLike;
	private String reqLikers = null;
	private String testUnlike=null;
	private JButton btn_modifInfo;
	private String infoModif;
	JRadioButton jRadioShowCompetencesModif, jRadioShowAnneeDipModif, jRadioShowTelModif;
	private int nbLikes;
	
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
					VueTchat chatWindow =new VueTchat();
					chatWindow.run();
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
		 * Fenetre Principale.
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
		
		btnQuiLike=new JButton("likers");
		btnQuiLike.setBounds(26, 12, 110, 19);
		panel.add(btnQuiLike);
		btnQuiLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					reqLikers=gestion.quiLike(mailCo);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String tabReqLikers[]=reqLikers.split("#");
				for (nbLikes = 0; nbLikes < tabReqLikers.length; nbLikes++) {
					if (reqLikers==null){
						
					}
					else{
						if(nbLikes%2==0){
							if (nbLikes==0){
								textArealikers.setText("("+tabReqLikers[nbLikes]+") - " + tabReqLikers[nbLikes+1]);
							}
							else{
								String mem=textArealikers.getText();
								textArealikers.setText(mem+System.getProperty("line.separator")+"("+tabReqLikers[nbLikes]+") - " + tabReqLikers[nbLikes+1]);
							}
						}
					}
				}
				nbLikes=nbLikes+1;
				System.out.println("tabReqLikers.length= "+tabReqLikers.length);
				System.out.println("nbLikes= "+nbLikes);
				jDialogLikers.setVisible(true);
			}
		});
		
		btnConsultMail = new JButton("Mail");
		btnConsultMail.setBounds(326, 109, 117, 23);
		panel.add(btnConsultMail);
		btnConsultMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(connecte==false){
					jOption.showMessageDialog(null, "ERREUR: Il faut être connecte...", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else{
					try {
						mailsrecus=gestion.recupererMail(mailCo);
						
						
							for (int m = 0; m < mailsrecus.length; m++) {
								if (m%2==0){
									if(m==0){
										textAreaMails.setText("("+mailsrecus[m]+") "+mailsrecus[m+1]);
									}
									else{
									String temp = textAreaMails.getText();
									textAreaMails.setText(temp+System.getProperty("line.separator")+"("+mailsrecus[m]+") "+mailsrecus[m+1]);
									}
								}
						
							}
							jDialogConsulterMail.setVisible(true);
						
					} catch (ArrayIndexOutOfBoundsException e1) {
						// TODO Auto-generated catch block
						textAreaMails.setText("Vous n'avez pas recu de mails");
						jDialogConsulterMail.setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnRafraichir = new JButton("Rafraichir");
		btnRafraichir.setBounds(326, 12, 117, 23);
		panel.add(btnRafraichir);
		btnRafraichir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int j=table_1.getRowCount();
				try {
					for (int i = 0; i < j; i++) {
						System.out.println("nombre lignes:"+table_1.getRowCount());
						modTable.supprEtudiant(j-1-i);
					}
					chargerEtudiants();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JLabel lblConnect = new JLabel("Connect!");
		lblConnect.setFont(new Font("High Tower Text", Font.BOLD, 20));
		lblConnect.setBounds(196, 11, 110, 20);
		panel.add(lblConnect);
		
		JLabel lblRecherche = new JLabel("Recherche");
		lblRecherche.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRecherche.setBounds(10, 31, 86, 20);
		panel.add(lblRecherche);
		lblRecherche.setEnabled(false);

		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setBounds(10, 62, 46, 14);
		panel.add(lblNom);
		lblNom.setEnabled(false);
		
		textField = new JTextField();
		textField.setBounds(50, 59, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		textField.setEnabled(false);
		
		
		JLabel lblNewLabel = new JLabel("Prenom");
		lblNewLabel.setBounds(169, 62, 46, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setEnabled(false);
		
		txtPrnom = new JTextField();
		txtPrnom.setBounds(227, 59, 86, 20);
		panel.add(txtPrnom);
		txtPrnom.setColumns(10);
		txtPrnom.setEnabled(false);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(354, 62, 46, 14);
		panel.add(lblEmail);
		lblEmail.setEnabled(false);
		
		textField_1 = new JTextField();
		textField_1.setBounds(388, 59, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEnabled(false);
		

		JLabel lblTl = new JLabel("Tel.:");
		lblTl.setBounds(10, 87, 46, 14);
		panel.add(lblTl);
		lblTl.setEnabled(false);

		
		textField_2 = new JTextField();
		textField_2.setBounds(50, 84, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setEnabled(false);
		

		JLabel lblAnneDeDiplomation = new JLabel("Annee de diplomation:");
		lblAnneDeDiplomation.setBounds(169, 87, 117, 14);
		panel.add(lblAnneDeDiplomation);
		lblAnneDeDiplomation.setEnabled(false);

		
		textField_3 = new JTextField();
		textField_3.setBounds(294, 84, 106, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 146, 464, 359);
		panel.add(scrollPane);
		
		
		table_1 = new JTable();
		table_1.setModel(modTable);
		scrollPane.setViewportView(table_1);
		
		table_1.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	String adresseMail;
		        int row = table_1.rowAtPoint(evt.getPoint());
		        //int col = table_1.columnAtPoint(evt.getPoint());
		        adresseMail=(String) table_1.getValueAt(row, 2);
		        utilisateurSelectionne=adresseMail;
		        infoEtudiant(adresseMail);
		        jDialogInfoEtudiant.setVisible(true);
		        
		        
		    }
		});
		
		JButton btnNewButton = new JButton("Rechercher!");
		btnNewButton.setBounds(169, 112, 117, 23);
		panel.add(btnNewButton);
		btnNewButton.setEnabled(false);
		
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
		
		JMenu mnGestionDuCompte = new JMenu("Gestion du compte");
		menuBar.add(mnGestionDuCompte);
		
		
		JMenuItem mntmModifierMesInformations = new JMenuItem("Modifier mes informations");
		mnGestionDuCompte.add(mntmModifierMesInformations);
		mntmModifierMesInformations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					infoModif=gestion.infoEtudiant(mailCo);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String tabInfoModif[]=infoModif.split("#");
				textField_NomModif.setText(tabInfoModif[0]);
				textField_prenomModif.setText(tabInfoModif[1]);
				textField_MailModif.setText(tabInfoModif[2]);
				textField_TelModif.setText(tabInfoModif[3]);
				textField_anneeModif.setText(tabInfoModif[4]);
				if(tabInfoModif[5].startsWith("1")){
					jRadioShowTelModif.setSelected(true);
				}
				if(tabInfoModif[6].startsWith("1")){
					jRadioShowAnneeDipModif.setSelected(true);
				}
				if(tabInfoModif[7].startsWith("1")){
					jRadioShowCompetencesModif.setSelected(true);
				}
				chargerCompetencesModif();
				chargerCompetencesSelectionneesModif();
				jDialogModif.setVisible(true);
				
			}
		});
		mntmModifierMesInformations.setEnabled(false);
		
		JMenuItem mntmSupprimerCompte = new JMenuItem("Supprimer mon compte");
		mnGestionDuCompte.add(mntmSupprimerCompte);
		mntmSupprimerCompte.setEnabled(false);
		//mntmSupprimerCompte.setEnabled(false);
		
		JMenuItem mntmSeDconnecter = new JMenuItem("Se Deconnecter");
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
						mntmSupprimerCompte.setEnabled(false);
						mntmModifierMesInformations.setEnabled(false);
						
					}
					
				} 
				else{
					connecte=true;
					if(connecte==true){
						mntmSeDconnecter.setEnabled(true);
						mntmSeConnecter.setEnabled(false);
						mntmSinscrire.setEnabled(false);
						mntmSupprimerCompte.setEnabled(true);
						mntmModifierMesInformations.setEnabled(true);

					}
				}
			}
		});
		if(connecte==false){
			mntmSeDconnecter.setEnabled(false);
		}
		mnConnexion.add(mntmSeDconnecter);
		
		
		
		mntmSupprimerCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					gestion.supprimerCompte(getMailCo());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connecte=false;
				mntmSeDconnecter.setEnabled(false);
				mntmSeConnecter.setEnabled(true);
				mntmSinscrire.setEnabled(true);
				mntmSupprimerCompte.setEnabled(false);
				mntmModifierMesInformations.setEnabled(false);
			}
		});
		
		
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
					setMailCo(eMail);
					jOption.showMessageDialog(null, "INFO: Vous etes bien connecte", "Information", JOptionPane.INFORMATION_MESSAGE);
					if(connecte==true){
						mntmSeDconnecter.setEnabled(true);
						mntmSeConnecter.setEnabled(false);
						mntmSinscrire.setEnabled(false);
						mntmSupprimerCompte.setEnabled(true);
						mntmModifierMesInformations.setEnabled(true);
						try {
							gestion.runService();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				else{
					connecte=false;
					jOption.showMessageDialog(null, "ERREUR: Echec de la connexion", "Erreur", JOptionPane.ERROR_MESSAGE);
					if (connecte==false){
						mntmSeDconnecter.setEnabled(false);
						mntmSeConnecter.setEnabled(true);
						mntmSinscrire.setEnabled(true);
						mntmSupprimerCompte.setEnabled(false);
						mntmModifierMesInformations.setEnabled(false);
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
		jDialogModif.setMinimumSize(new java.awt.Dimension(500, 600));
		jDialogModif.setLocationRelativeTo(null);
		jDialogModif.setModal(true);
		jDialogModif.getContentPane().setLayout(null);
		jDialogModif.setTitle("Modifier ses informations");
        		
		jLabelNomModif = new JLabel("Nom:");
		jLabelNomModif.setBounds(60, 10, 117, 23);
		jDialogModif.getContentPane().add(jLabelNomModif);
        
		textField_NomModif = new JTextField();
		textField_NomModif.setBounds(60, 35, 86, 20);
		jDialogModif.getContentPane().add(textField_NomModif);
		textField_NomModif.setColumns(10);

		
		jLabelPrenomModif = new JLabel("Prenom:");
		jLabelPrenomModif.setBounds(200, 10, 117, 23);
		jDialogModif.getContentPane().add(jLabelPrenomModif);
        
		textField_prenomModif = new JTextField();
		textField_prenomModif.setBounds(200, 35, 86, 20);
		jDialogModif.getContentPane().add(textField_prenomModif);
		textField_prenomModif.setColumns(10);

        jLabelEmailModif = new JLabel("Adrese E-mail:");
        jLabelEmailModif.setBounds(60, 80, 117, 23);
		jDialogModif.getContentPane().add(jLabelEmailModif);
		
		textField_MailModif = new JTextField();
		textField_MailModif.setBounds(60, 105, 86, 20);
		jDialogModif.getContentPane().add(textField_MailModif);
		textField_MailModif.setColumns(10);
		textField_MailModif.setEditable(false);
		
		jLabelTelModif = new JLabel("Tel.:");
		jLabelTelModif.setBounds(350, 10, 117, 23);
		jDialogModif.getContentPane().add(jLabelTelModif);
        
		textField_TelModif = new JTextField();
		textField_TelModif.setBounds(350, 35, 86, 20);
		jDialogModif.getContentPane().add(textField_TelModif);
		textField_TelModif.setColumns(10);
		
		btn_modifInfo = new JButton("Modifier mes informations");
		btn_modifInfo.setBounds(270, 207, 200, 23);
		jDialogModif.getContentPane().add(btn_modifInfo);
		btn_modifInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sh_tel, sh_annee, sh_comp;
				sh_tel="0";
				sh_annee="0";
				sh_comp="0";
				
				if (jRadioShowTelModif.isSelected()){
					sh_tel="1";
				}
				if (jRadioShowAnneeDipModif.isSelected()){
					sh_annee="1";
				}
				if (jRadioShowCompetencesModif.isSelected()){
					sh_comp="1";
				}
				
					
				try {
					gestion.modifInfo(mailCo, textField_NomModif.getText(), textField_prenomModif.getText(), textField_TelModif.getText(), textField_anneeModif.getText(), sh_tel, sh_annee, sh_comp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		jLabelAnneeModif = new JLabel("Annee:");
		jLabelAnneeModif.setBounds(60, 147, 117, 23);
		jDialogModif.getContentPane().add(jLabelAnneeModif);
        
		textField_anneeModif = new JTextField();
		textField_anneeModif.setBounds(60, 175, 86, 20);
		jDialogModif.getContentPane().add(textField_anneeModif);
		textField_anneeModif.setColumns(10);
		
		btnAnnulerModifier = new JButton("Annuler");
		btnAnnulerModifier.setBounds(20, 510, 200, 23);
		jDialogModif.getContentPane().add(btnAnnulerModifier);
        
        btnModifier = new JButton("Envoyer les modifications");
		btnModifier.setBounds(270, 510, 200, 23);
		jDialogModif.getContentPane().add(btnModifier);
		
		btnNewCompModif=new JButton("Creer une competence");
		btnNewCompModif.setBounds(275, 260, 200, 20);
		jDialogModif.getContentPane().add(btnNewCompModif);
		btnNewCompModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					gestion.creerCompetence(jTextField_NewCompetenceModif.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				chargerCompetences();
				
				;
			}
		});
		
		jTextField_NewCompetenceModif = new JTextField();
		jTextField_NewCompetenceModif.setBounds(20, 260, 250, 20);
		jDialogModif.getContentPane().add(jTextField_NewCompetenceModif);
		jTextField_NewCompetenceModif.setColumns(10);
		
		jLabelCompetencesModif = new JLabel("Selectionnez vos competences:");
		jLabelCompetencesModif.setBounds(20, 295, 250, 23);
		jDialogModif.getContentPane().add(jLabelCompetencesModif);
		
		listeCompetencesModif=new JList();
		listeCompetencesModif.setBounds(30, 320, 130, 170);
		jDialogModif.getContentPane().add(listeCompetencesModif);
		
		jLabelCompetencesSelectModif = new JLabel("Competences selectionnees:");
		jLabelCompetencesSelectModif.setBounds(300, 295, 250, 23);
		jDialogModif.getContentPane().add(jLabelCompetencesSelectModif);
		
		listeCompetencesSelectionneesModif=new JList();
		listeCompetencesSelectionneesModif.setBounds(320, 320, 130, 170);
		jDialogModif.getContentPane().add(listeCompetencesSelectionneesModif);
		
		btnAddCompModif = new JButton("Add >>");
		btnAddCompModif.setBounds(188, 365, 110, 30);
		jDialogModif.getContentPane().add(btnAddCompModif);
		btnAddCompModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String com;
				com=(String) listeCompetencesModif.getSelectedValue();
				System.out.println("j= "+com+"/");
				try {
					gestion.ajouterCompUtilisateur(mailCo, com);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				chargerCompetencesSelectionneesModif();
			}
		});
		
		btnDeleteCompModif = new JButton("<< Delete");
		btnDeleteCompModif.setBounds(188, 400, 110, 30);
		jDialogModif.getContentPane().add(btnDeleteCompModif);
		btnDeleteCompModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String com;
				com=(String) listeCompetencesSelectionneesModif.getSelectedValue();
				System.out.println("j= "+com+"/");
				try {
					gestion.supprCompUtilisateur(mailCo, com);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				chargerCompetencesSelectionneesModif();
			}
		});
//		
//		btnModifier.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				jDialogModif.dispose();
//			}
//		});
//        
//		btnAnnulerModifier.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				jDialogModif.dispose();
//			}
//		});
//		
		jRadioShowTelModif = new JRadioButton("Vue Visiteurs du Telephone");
		jRadioShowTelModif.setBounds(220,80,200,23);
		jDialogModif.getContentPane().add(jRadioShowTelModif);
		
		jRadioShowAnneeDipModif  = new JRadioButton("Vue Visiteurs de l'année dipl.");
		jRadioShowAnneeDipModif.setBounds(220,113,200,23);
		jDialogModif.getContentPane().add(jRadioShowAnneeDipModif);
		
		jRadioShowCompetencesModif = new JRadioButton("Vue Visiteurs des comp.");
		jRadioShowCompetencesModif.setBounds(220,146,200,23);
		jDialogModif.getContentPane().add(jRadioShowCompetencesModif);
//		
//		btnModifier.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				if(controle.verifMail(textField_MailModif.getText())){
//					if(controle.verifTel(textField_TelModif.getText())){
//						if(controle.verifAnnee(textField_anneeModif.getText())){	
//								if((textField_NomModif.getText().length()!=0) && (textField_prenomModif.getText().length()!=0) && (textField_MailModif.getText().length()!=0) && (textField_TelModif.getText().length()!=0) && (textField_anneeModif.getText().length()!=0)){	
//									try {
//										gestion.modif(textField_NomModif.getText(), textField_prenomModif.getText(), textField_MailModif.getText(), textField_TelModif.getText(), textField_anneeModif.getText(), listeCompsSelectModif, jRadioShowTelModif.isSelected(), jRadioShowAnneeDipModif.isSelected(), jRadioShowCompetencesModif.isSelected());
//									} catch (IOException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//									jDialogModif.dispose();
//								}
//								else{
//									jOption.showMessageDialog(null, "ERREUR: Tous les champs doivent etre saisis...  ", "Erreur", JOptionPane.ERROR_MESSAGE);
//								}							
//						}
//						else{
//							jOption.showMessageDialog(null, "ERREUR: Annee diplomation incoherente... ", "Erreur", JOptionPane.ERROR_MESSAGE);
//						}
//					}
//					else{
//						jOption.showMessageDialog(null, "ERREUR: Numero de telephone incoherent... ", "Erreur", JOptionPane.ERROR_MESSAGE);
//					}
//				}
//				else{
//					jOption.showMessageDialog(null, "ERREUR: Adresse mail incoherente... ", "Erreur", JOptionPane.ERROR_MESSAGE);
//				}
//			}
//		});
//		
//		mntmModifierMesInformations.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int i=0;
//				String trame = null, trameC = null;
//				chargerCompetences2();
//				try {
//					trame=gestion.infoEtudiant(mailCo);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				String tabTrame[]= trame.split("#");
//				Etudiant etudiant = null;
//				System.out.println("trame="+trame);
//				etudiant = new Etudiant(tabTrame[i],tabTrame[i+1],tabTrame[i+2],tabTrame[i+3],tabTrame[i+4]);	
//				textField_NomModif.setText(etudiant.getNom());
//				textField_prenomModif.setText(etudiant.getPrenom());
//				textField_MailModif.setText(etudiant.getMail());
//				textField_TelModif.setText(etudiant.getTel());
//				textField_anneeModif.setText(etudiant.getAnneeDip());
//				try {
//					trameC=gestion.competencesUtilisateur(mailCo);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				String tabRequete2[]=trameC.split("#");
//				listeCompetencesSelectionnees.removeAll();
//				DefaultListModel DLM3 = new DefaultListModel();
//		        for (int j = 0; j < tabRequete2.length; j++) {
//		        	 DLM3.addElement(tabRequete2[j]); 
//		        	 jListeCompSelectModif.add(tabRequete2[j]);
//				}  
//		        listeCompetencesSelectionneesModif.setModel(DLM3);
//		        
//		        if (tabTrame[5].startsWith("1")){
//		        	jRadioShowTelModif.setSelected(true);
//		        }
//		        if (tabTrame[6].startsWith("1")){
//		        	jRadioShowAnneeDipModif.setSelected(true);
//		        }
//		        if (tabTrame[7].startsWith("1")){
//		        	jRadioShowCompetencesModif.setSelected(true);
//		        }
//		        
//				jDialogModif.setVisible(true);
//			}
//			
//			
//			
//		});
		
		
		/********************************************************************
		 * PopUp de inscription
		 * 
		 ********************************************************************/
		jDialogInscription = new JDialog();
		jDialogInscription.setMinimumSize(new java.awt.Dimension(500, 600));
		jDialogInscription.setLocationRelativeTo(null);
		jDialogInscription.setModal(true);
		jDialogInscription.getContentPane().setLayout(null);
		jDialogInscription.setTitle("S'inscrire");
        	
		jLabelNomInscription = new JLabel("Nom:");
		jLabelNomInscription.setBounds(60, 10, 117, 23);
		jDialogInscription.getContentPane().add(jLabelNomInscription);
        
		textField_NomInscription = new JTextField();
		textField_NomInscription.setBounds(60, 35, 86, 20);
		jDialogInscription.getContentPane().add(textField_NomInscription);
		textField_NomInscription.setColumns(10);
		
		jLabelPrenomInscription = new JLabel("Prenom:");
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
		
		jLabelTelInscription = new JLabel("Tel.:");
		jLabelTelInscription.setBounds(350, 10, 117, 23);
		jDialogInscription.getContentPane().add(jLabelTelInscription);
        
		textField_TelInscription = new JTextField();
		textField_TelInscription.setBounds(350, 35, 86, 20);
		jDialogInscription.getContentPane().add(textField_TelInscription);
		textField_TelInscription.setColumns(10);
		
		JRadioButton jRadioShowTelInscription = new JRadioButton("Vue Visiteurs");
		jRadioShowTelInscription.setBounds(350,55,130,23);
		jDialogInscription.getContentPane().add(jRadioShowTelInscription);
		
		jLabelAnneeInscription = new JLabel("Annee diplomation:");
		jLabelAnneeInscription.setBounds(60, 147, 117, 23);
		jDialogInscription.getContentPane().add(jLabelAnneeInscription);
        
		textField_anneeInscription = new JTextField();
		textField_anneeInscription.setBounds(60, 175, 86, 20);
		jDialogInscription.getContentPane().add(textField_anneeInscription);
		textField_anneeInscription.setColumns(10);
		
		JRadioButton jRadioShowAnneeDipInscription  = new JRadioButton("Vue Visiteurs");
		jRadioShowAnneeDipInscription.setBounds(60,197,130,23);
		jDialogInscription.getContentPane().add(jRadioShowAnneeDipInscription);
		
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
		
		btnNewComp=new JButton("Creer une competence");
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
		
		
        
		btnAnnulerInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jDialogInscription.dispose();
			}
		});
		
		jLabelCompetences = new JLabel("Selectionnez vos competences:");
		jLabelCompetences.setBounds(20, 295, 250, 23);
		jDialogInscription.getContentPane().add(jLabelCompetences);
		
		listeCompetences=new JList();
		listeCompetences.setBounds(30, 320, 130, 170);
		jDialogInscription.getContentPane().add(listeCompetences);
		
		
       
		
		jLabelCompetences = new JLabel("Competences selectionnees:");
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
				String com;
				com=(String) listeCompetences.getSelectedValue();
				System.out.println("j= "+com+"/");
				listeCompsSelect.add(com);
				chargerCompetencesSelectionnees();
			}
		});
		
		btnDeleteComp = new JButton("<< Delete");
		btnDeleteComp.setBounds(188, 400, 110, 30);
		jDialogInscription.getContentPane().add(btnDeleteComp);
		btnDeleteComp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String com;
				com=(String) listeCompetencesSelectionnees.getSelectedValue();
				System.out.println("j= "+com+"/");				
				listeCompsSelect.remove(com);
				chargerCompetencesSelectionnees();
			}
		});
		
		JRadioButton jRadioShowCompetencesInscription = new JRadioButton("Vue Visiteurs");
		jRadioShowCompetencesInscription.setBounds(180,435,130,23);
		jDialogInscription.getContentPane().add(jRadioShowCompetencesInscription);
		
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(controle.verifMail(textField_MailInscription.getText())){
					if(controle.verifTel(textField_TelInscription.getText())){
						if(controle.verifAnnee(textField_anneeInscription.getText())){
							if(controle.verifConfMDP(textField_MDPInscription.getText(), textField_MDPConfInscription.getText())){		
								if((textField_NomInscription.getText().length()!=0) && (textField_prenomInscription.getText().length()!=0) && (textField_MailInscription.getText().length()!=0) && (textField_TelInscription.getText().length()!=0) && (textField_anneeInscription.getText().length()!=0)){	
									try {
											gestion.envoiInfo(textField_NomInscription.getText(), textField_prenomInscription.getText(), textField_MailInscription.getText(), textField_TelInscription.getText(), textField_anneeInscription.getText(), textField_MDPInscription.getText(), listeCompsSelect, jRadioShowTelInscription.isSelected(), jRadioShowAnneeDipInscription.isSelected(), jRadioShowCompetencesInscription.isSelected());
										} catch (IOException e) {
												// TODO Auto-generated catch block
											e.printStackTrace();
										}
										jDialogInscription.dispose();
								}
								else{
									jOption.showMessageDialog(null, "ERREUR: Tous les champs doivent etre saisis...  ", "Erreur", JOptionPane.ERROR_MESSAGE);
								}
							}
							else{
								jOption.showMessageDialog(null, "ERREUR: Les mots de passe sont differents... ", "Erreur", JOptionPane.ERROR_MESSAGE);
							}
						}
						else{
							jOption.showMessageDialog(null, "ERREUR: Annee diplomation incoherente... ", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					}
					else{
						jOption.showMessageDialog(null, "ERREUR: Numero de telephone incoherent... ", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					jOption.showMessageDialog(null, "ERREUR: Adresse mail incoherente... ", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		/********************************************************************
		 * PopUp de Laisser un message
		 * 
		 ********************************************************************/
		
		jDialogLaisserMessage = new JDialog();
		jDialogLaisserMessage.setMinimumSize(new java.awt.Dimension(500, 600));
		jDialogLaisserMessage.setLocationRelativeTo(null);
		jDialogLaisserMessage.setModal(true);
		jDialogLaisserMessage.getContentPane().setLayout(null);
		jDialogLaisserMessage.setTitle("Laisser un message");
		
		JPanel panel2 = new JPanel();
		panel2.setLocation(new Point(300, 0));
		panel2.setBackground(new Color(128, 128, 128));
		panel2.setBounds(0, 0, 465, 261);
		jDialogLaisserMessage.getContentPane().add(panel2);
		panel2.setLayout(null);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBorder(new LineBorder(new Color(30, 144, 255)));
		textArea_1.setBounds(10, 11, 364, 143);
		panel2.add(textArea_1);
		
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gestion.envoiMail(textArea_1.getText(), utilisateurSelectionne);
					jDialogLaisserMessage.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEnvoyer.setBounds(285, 214, 89, 36);
		panel2.add(btnEnvoyer);
		
		
		/********************************************************************
		 * PopUp de Consulter Mail
		 * 
		 ********************************************************************/
		jDialogConsulterMail = new JDialog();
		jDialogConsulterMail.setMinimumSize(new java.awt.Dimension(500, 600));
		jDialogConsulterMail.setLocationRelativeTo(null);
		jDialogConsulterMail.setModal(true);
		jDialogConsulterMail.getContentPane().setLayout(null);
		jDialogConsulterMail.setTitle("Mes Mails");
		
		JPanel panel3 = new JPanel();
		panel3.setLocation(new Point(300, 0));
		panel3.setBackground(new Color(128, 128, 128));
		panel3.setBounds(0, 0, 500, 600);
		jDialogConsulterMail.getContentPane().add(panel3);
		panel3.setLayout(null);
		
		textAreaMails = new JTextArea();
		textAreaMails.setBorder(new LineBorder(new Color(30, 144, 255)));
		textAreaMails.setBounds(10, 11, 364, 143);
		textAreaMails.setEditable(false);
		panel3.add(textAreaMails);
		
		JButton btnSupprMail = new JButton("Tout supprimer");
		btnSupprMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gestion.supprTousMail(mailCo);
					textAreaMails.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSupprMail.setBounds(285, 214, 89, 36);
		panel3.add(btnSupprMail);
		
		/**********************************************************
		 * PopUp de Likers
		 * *******************************************************/
		jDialogLikers = new JDialog();
		jDialogLikers.setMinimumSize(new java.awt.Dimension(500, 600));
		jDialogLikers.setLocationRelativeTo(null);
		jDialogLikers.setModal(true);
		jDialogLikers.getContentPane().setLayout(null);
		jDialogLikers.setTitle("Mes Mails");
		
		JPanel panel4 = new JPanel();
		panel4.setLocation(new Point(300, 0));
		panel4.setBounds(0, 0, 500, 600);
		jDialogLikers.getContentPane().add(panel4);
		panel4.setLayout(null);
		
		textArealikers = new JTextArea();
		textArealikers.setBorder(new LineBorder(new Color(30, 144, 255)));
		textArealikers.setBounds(10, 11, 364, 143);
		textArealikers.setEditable(false);
		panel4.add(textArealikers);
		
//		jLabelNbLikes = new JLabel("Nombre de likes: "+ nbLikes );
//		jLabelNbLikes.setBounds(350, 10, 200, 23);
//		panel4.add(jLabelNbLikes);
	}
	
	
	
	/**Affiche les informations de l'utilisateur choisi dans la JTable en fonction de si on est visiteur ou utilisateur
	 * @param mail
	 */
	public void infoEtudiant(String mail){
		String requete = null; 
		String comps = null;
		int i =0;
		try {
			requete = gestion.infoEtudiant(mail);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tabRequete[]=requete.split("#");
		Etudiant etudiant = null;
		System.out.println("requete="+requete);
		etudiant = new Etudiant(tabRequete[i],tabRequete[i+1],tabRequete[i+2],tabRequete[i+3],tabRequete[i+4]);	
		
		
		jDialogInfoEtudiant = new JDialog();
		jDialogInfoEtudiant.setMinimumSize(new java.awt.Dimension(500, 400));
		jDialogInfoEtudiant.setLocationRelativeTo(null);
		jDialogInfoEtudiant.setModal(false);
		jDialogInfoEtudiant.getContentPane().setLayout(null);
		jDialogInfoEtudiant.setTitle("Information sur " + etudiant.getNom());
		
		jLabelNomInfo = new JLabel("Nom: " + etudiant.getNom());
		jLabelNomInfo.setBounds(60, 10, 200, 23);
		jDialogInfoEtudiant.getContentPane().add(jLabelNomInfo);
        
		jLabelPrenomInfo = new JLabel("Prenom: " + etudiant.getPrenom());
		jLabelPrenomInfo.setBounds(350, 10, 200, 23);
		jDialogInfoEtudiant.getContentPane().add(jLabelPrenomInfo);
		
		JButton btnChat = new JButton("Laisser un message");
		btnChat.setBounds(350, 45, 110, 30);
		jDialogInfoEtudiant.getContentPane().add(btnChat);
		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jDialogLaisserMessage.setVisible(true);
				
			}
		});
        
		jLabelEmailInfo = new JLabel("Adrese E-mail: " + etudiant.getMail());
		jLabelEmailInfo.setBounds(60, 45, 200, 23);
        jDialogInfoEtudiant.getContentPane().add(jLabelEmailInfo);
		
        System.out.println("tabRquete[5]="+tabRequete[5]);
        System.out.println("tabRquete[6]="+tabRequete[6]);
        System.out.println("tabRquete[7]="+tabRequete[7]);
        
		jLabelTelInfo = new JLabel("Tel.: " + etudiant.getTel());
		jLabelTelInfo.setBounds(60, 80, 200, 23);
		if(connecte==true){
			jDialogInfoEtudiant.getContentPane().add(jLabelTelInfo);
		}
		else{
			if(tabRequete[5].startsWith("1")){
				jDialogInfoEtudiant.getContentPane().add(jLabelTelInfo);
			}
		}
		jLabelAnneeInfo = new JLabel("Annee diplomation: " + etudiant.getAnneeDip());
		jLabelAnneeInfo.setBounds(60, 115, 200, 23);
		//jDialogInfoEtudiant.getContentPane().add(jLabelAnneeInfo);
		if(connecte==true){
			jDialogInfoEtudiant.getContentPane().add(jLabelAnneeInfo);
		}
		else{
			if(tabRequete[6].startsWith("1")){
				jDialogInfoEtudiant.getContentPane().add(jLabelAnneeInfo);
			}
		}
		
		JLabel jLabelcompetencesInfo =new JLabel("Compétences:");
		jLabelcompetencesInfo.setBounds(200, 125, 200, 23);
		if(connecte==true){
			jDialogInfoEtudiant.getContentPane().add(jLabelcompetencesInfo);
		}
		else{
			if(tabRequete[7].startsWith("1")){
				jDialogInfoEtudiant.getContentPane().add(jLabelcompetencesInfo);
			}
		}
		
		try {
			comps = gestion.competencesUtilisateur(mail);
			System.out.println("comps="+comps);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tabRequete2[]=comps.split("#");
		
//		for (int o = 0; o < tabRequete2.length; o++) {
//			System.out.println("requete="+requete);
//			Competence comp = new Competence(tabRequete2[o]);
//			modTableComp.addCompetence(comp);
//			
//		}
		
//		JScrollPane scrollPane2 = new JScrollPane();
//		scrollPane2.setBounds(10, 146, 464, 359);
//		jDialogInfoEtudiant.getContentPane().add(scrollPane2);
//		
//		JTable tableComp= new JTable();
//		tableComp.setModel(modTableComp);
//		scrollPane2.setViewportView(tableComp);
//		
//		tableComp.addMouseListener(new java.awt.event.MouseAdapter() {
//		    @Override
//		    public void mouseClicked(java.awt.event.MouseEvent evt) {
//		    	String competence;
//		        int row = table_1.rowAtPoint(evt.getPoint());
//		        //int col = table_1.columnAtPoint(evt.getPoint());
//		        competence=(String) table_1.getValueAt(row, 1);
//		        
//		        
//		        
//		        
//		    }
//		});
		
		JList listeCompetencesInfo =new JList();
		listeCompetencesInfo.setBounds(200, 150, 130, 170);
		listeCompetencesInfo.removeAll();
		DefaultListModel DLM3 = new DefaultListModel();
        for (int j = 0; j < tabRequete2.length; j++) {
        	 DLM3.addElement(tabRequete2[j]); 
		}
                
           
        listeCompetencesInfo.setModel(DLM3);
		
        if(connecte==true){
			jDialogInfoEtudiant.getContentPane().add(listeCompetencesInfo);
		}
		else{
			if(tabRequete[7].startsWith("1")){
				jDialogInfoEtudiant.getContentPane().add(listeCompetencesInfo);
			}
		}
        
        btnLike=new JButton("Liker");
        btnLike.setBounds(400, 150, 110, 30);
		jDialogInfoEtudiant.getContentPane().add(btnLike);
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					gestion.likerComp(listeCompetencesInfo.getSelectedValue().toString(), mailCo, utilisateurSelectionne);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnUnlike=new JButton("Unliker");
        btnUnlike.setBounds(400, 190, 110, 30);
		jDialogInfoEtudiant.getContentPane().add(btnUnlike);
		btnUnlike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					testUnlike=gestion.unlikerComp(listeCompetencesInfo.getSelectedValue().toString(), mailCo, utilisateurSelectionne);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (testUnlike.startsWith("NOK")){
					jOption.showMessageDialog(null, "ERREUR:Vous n'avez pas like cette compétence de cette personne... ", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		
      
		
		
		 
	}
	
	
	
	/**Modifie le paramètre connecte pour savoir si l'utilisateur est connecté
	 * @param entree
	 */
	public void setConnecte(boolean entree){
		if (entree==true){
			this.connecte=true;
		}
		else{
			this.connecte=false;
		}
	}
	
	/**Recharge la JList des competences generales a l'INSCRIPTION
	 * 
	 */
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
	
	public static void chargerCompetencesModif(){
		listeCompetencesModif.removeAll();
			
		try{
			listeCompsLModif= gestion.recupererCompetences();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		DefaultListModel DLMModif = new DefaultListModel();
        for (int i = 0; i < listeCompsLModif.size(); i++)
            {
                 DLMModif.addElement(listeCompsLModif.get(i)); 
            }
        listeCompetencesModif.setModel(DLMModif);
	}
	
	public static void chargerCompetencesSelectionneesModif(){
		listeCompetencesSelectionneesModif.removeAll();
			
		try{
			retourCompModif= gestion.competencesUtilisateur(mailCo);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();	
		}
		String tabRetourCompModif[]=retourCompModif.split("#");
		DefaultListModel DLMModif = new DefaultListModel();
        for (int i = 0; i < tabRetourCompModif.length; i++)
            {
                 DLMModif.addElement(tabRetourCompModif[i]); 
            }
        listeCompetencesSelectionneesModif.setModel(DLMModif);
	}
	
//	public static void refreshCompetencesSelectionneesModif(){
//		listeCompetencesSelectionneesModif.removeAll();
//
//		DefaultListModel DLM2 = new DefaultListModel();
//        for (int i = 0; i < listeCompsSelectModif.size(); i++)
//            {
//                 DLM2.addElement(listeCompsSelectModif.get(i)); 
//            }
//        listeCompetencesSelectionneesModif.setModel(DLM2);
//	}
	
	/**Recharge la JList des competences que l'utilisateur selectionne à l'INSCRIPTION
	 * 
	 */
	public static void chargerCompetencesSelectionnees(){
		listeCompetencesSelectionnees.removeAll();
			
		
		DefaultListModel DLM2 = new DefaultListModel();
        for (int i = 0; i < listeCompsSelect.size(); i++)
            {
                 DLM2.addElement(listeCompsSelect.get(i)); 
            }
        listeCompetencesSelectionnees.setModel(DLM2);
	}
	
	
	
	/**récupère tous les étudiants pour les afficher
	 * @throws IOException
	 */
	public static void chargerEtudiants() throws IOException{
		
		
		String requete =gestion.recupEtudiants();
		String tabRequete[]=requete.split("#");
		Etudiant etudiant = null;
		for (int i = 0; i < tabRequete.length; i++) {
			System.out.println("requete="+requete);
			
			
			if(i%5==0){
				etudiant = new Etudiant(tabRequete[i],tabRequete[i+1],tabRequete[i+2],tabRequete[i+3],tabRequete[i+4]);
				modTable.addEtudiant(etudiant);
				//listeEtu.add(etudiant);
			}
		}
	}
	
//	public static void chargerCompTable() throws IOException{
//		
//		
//		ArrayList<String> requete =gestion.recupererCompetences();
//		
//		Etudiant etudiant = null;
//		for (int i = 0; i < requete.size(); i++) {
//			System.out.println("requete="+requete);
//			
//			
//			if(i%3==0){
//				Competence comp = new Competence(requete.get(i), requete.get(i+1), requete.get(i+2));
//				modTableComp.addCompetence(comp);
//				//listeEtu.add(etudiant);
//			}
//		}
//	}

	public static String getMailCo() {
		return mailCo;
	}

	public void setMailCo(String mailCo) {
		this.mailCo = mailCo;
	}
	
//	public static void chargerCompetences2(){
//		listeCompetencesModif.removeAll();
//			
//		try{
//			ArrayList<String> listeCompsModif= gestion.recupererCompetences();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			
//		}
//		DefaultListModel DLM = new DefaultListModel();
//        for (int i = 0; i < listeCompsL.size(); i++)
//            {
//                 DLM.addElement(listeCompsL.get(i)); 
//            }
//        listeCompetencesModif.setModel(DLM);
//	}
//	
//	public static void chargerCompetencesSelectionnees2(){
//		listeCompetencesSelectionneesModif.removeAll();
//			
//		
//		DefaultListModel DLM2 = new DefaultListModel();
//        for (int i = 0; i < listeCompsSelectModif.size(); i++)
//            {
//                 DLM2.addElement(listeCompsSelectModif.get(i)); 
//            }
//        listeCompetencesSelectionneesModif.setModel(DLM2);
//	}
	
	
	
}
