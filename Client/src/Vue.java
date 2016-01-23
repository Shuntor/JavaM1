//import java.awt.EventQueue;
//import javax.swing.JFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Vue {

//	private JFrame frame;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Vue window = new Vue();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public Vue() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		frame = new JFrame();
//		frame.setBounds(100, 100, 450, 300);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}

	
	public String traitement() throws IOException {
		
		boolean continuer = true;
		String reponse, reponseNom, reponsePrenom, reponseMail, reponseTel, reponseAnneeDiplo, reponseMotDePasse, confidentialAnneeDip, confidentialTel, ConfidentialMail, requete = null;
		boolean quitter=false;
		
		//le flux à lire sera l'imput entrée par l'utilisateur
		BufferedReader fluxEntreeStandard = new BufferedReader(
				new InputStreamReader(System.in));
		
		System.out.println("Tapez votre choix:\n" + "1) Se connecter\n"
				+ "2) Creer un compte\n" + "3) Modifier son profil\n" + "4) Consulter l'anuaire\n"
				+ "5) Deconnexion\n" + "6) Quitter");
		
		//récupère le flux d'entré et le stock dans réponse
		reponse = fluxEntreeStandard.readLine();
		
		while (continuer) {
			switch (reponse) {
			case "1":
				
				System.out.println("Votre adresse e-mail?");
				reponseMail = fluxEntreeStandard.readLine();
				System.out.println("Votre mot de passe?");
				reponseMotDePasse = fluxEntreeStandard.readLine();
				requete= "connexion" +" "+ reponseMail +" "+ reponseMotDePasse;
				System.out.println(requete);
				continuer = false;
				return requete;
				//break;
				
				
				
			case "2":
				System.out.println("Votre nom?");
				reponseNom = fluxEntreeStandard.readLine();
				System.out.println("Votre prénom?");
				reponsePrenom = fluxEntreeStandard.readLine();
				System.out.println("Votre année Diplomante?");
				reponseAnneeDiplo = fluxEntreeStandard.readLine();
				System.out.println("Souhaitez-vous partager votre année diplomante avec les visiteurs? [o/n]");
				confidentialAnneeDip = fluxEntreeStandard.readLine();
				System.out.println("Votre numéro de Téléphone?");
				reponseTel = fluxEntreeStandard.readLine();
				System.out.println("Souhaitez-vous partager votre numéro de téléphone avec les visiteurs? [o/n]");
				confidentialTel = fluxEntreeStandard.readLine();
				System.out.println("Votre adresse e-mail? (Servira à vous connecter)");
				reponseMail = fluxEntreeStandard.readLine();
				System.out.println("Souhaitez-vous partager votre adresse e-mail avec les visiteurs?");
				ConfidentialMail = fluxEntreeStandard.readLine();
				System.out.println("Votre mot de passe?");
				reponseMotDePasse = fluxEntreeStandard.readLine();
				requete= "inscription" +" "+ reponsePrenom +" "+ reponseNom +" "+ reponseMotDePasse +" "+ reponseAnneeDiplo +" "+ confidentialAnneeDip +" "+ reponseTel +" "+ confidentialTel +" "+ reponseMail +" "+ ConfidentialMail;
				System.out.println(requete);
				//reponse=GestionProtocoleClient.inscription;
				continuer = false;
				return "OK";
			case "3":
				System.out.println("Votre nom?");
				reponseNom = fluxEntreeStandard.readLine();
				System.out.println("Votre prénom?");
				reponsePrenom = fluxEntreeStandard.readLine();
				System.out.println("Votre année Diplomante?");
				reponseAnneeDiplo = fluxEntreeStandard.readLine();
				System.out.println("Souhaitez-vous partager votre année diplomante avec les visiteurs? [o/n]");
				confidentialAnneeDip = fluxEntreeStandard.readLine();
				System.out.println("Votre numéro de Téléphone?");
				reponseTel = fluxEntreeStandard.readLine();
				System.out.println("Souhaitez-vous partager votre numéro de téléphone avec les visiteurs? [o/n]");
				confidentialTel = fluxEntreeStandard.readLine();
				System.out.println("Votre adresse e-mail? (Servira à vous connecter)");
				reponseMail = fluxEntreeStandard.readLine();
				System.out.println("Souhaitez-vous partager votre adresse e-mail avec les visiteurs?");
				ConfidentialMail = fluxEntreeStandard.readLine();
				System.out.println("Votre mot de passe?");
				reponseMotDePasse = fluxEntreeStandard.readLine();
				requete= "inscription" +" "+ reponsePrenom +" "+ reponseNom +" "+ reponseMotDePasse +" "+ reponseAnneeDiplo +" "+ confidentialAnneeDip +" "+ reponseTel +" "+ confidentialTel +" "+ reponseMail +" "+ ConfidentialMail;
				System.out.println(requete);
				//reponse=GestionProtocoleClient.inscription;
				continuer = false;
				return "OK";
			case "4":
				reponse = "CONSULTER L'ANNUAIRE";
				continuer = false;
				break;
			case "5":
				reponse = "DECONNECTION";
				continuer = false;
				break;
			case "6":
				System.out.println("BYE");
				continuer = false;
				quitter=true;
				break;
			default:
				System.out.println("Ce choix n'est pas disponible");
			}
		}
		
		if(!quitter){
			System.out.print("requête: "+reponse +" ");
			reponse = reponse + fluxEntreeStandard.readLine();
			return reponse;
		}	
		
		return null;
		
	}
	
}
