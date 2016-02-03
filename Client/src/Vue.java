//import java.awt.EventQueue;
//import javax.swing.JFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.*;

/**
 * @author Iungmann Vaurigaud Hernandez
 *
 */
public class Vue {
	GestionProtocoleClient GestionProtocole = new GestionProtocoleClient();
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

	/*****
	 * traintement()
	 * @return 
	 * @throws IOException
	 * Thread gerant les clients
	 */
	public String traitement() throws IOException {
		
		boolean continuer = true;
		String reponse, reponseNom, reponsePrenom, reponseMail, reponseTel, reponseAnneeDiplo, reponseMotDePasse, confidentialAnneeDip, confidentialTel, ConfidentialMail, requete = null;
		boolean quitter=false;
		boolean verifMail=false;
		
		//le flux � lire sera l'imput entr�e par l'utilisateur
		BufferedReader fluxEntreeStandard = new BufferedReader(
				new InputStreamReader(System.in));
		
		System.out.println("Tapez votre choix:\n" + "1) Se connecter\n"
				+ "2) Creer un compte\n" + "3) Modifier son profil\n" + "4) Consulter l'anuaire\n"
				+ "5) Deconnexion\n" + "6) Quitter");
		
		//r�cup�re le flux d'entr� et le stock dans r�ponse
		reponse = fluxEntreeStandard.readLine();
		
		while (continuer) {
			switch (reponse) {
			case "1":
				
				do{
					System.out.println("Votre adresse e-mail?");
					reponseMail = fluxEntreeStandard.readLine();
					String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
	                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
					Pattern pattern = Pattern.compile(masque);
					Matcher controler = pattern.matcher(reponseMail);
					if (controler.matches()){
						verifMail=true;
					}
					else {
						System.out.println("L'adresse mail n'est pas valide");
						continuer = false;
						verifMail=false;
					}
				}
				while(verifMail==false);
				
				//Ok : la saisie est bonne
				System.out.println("Votre mot de passe?");
				reponseMotDePasse = fluxEntreeStandard.readLine();
				requete= GestionProtocole.serialisation("connexion", reponseMail, reponseMotDePasse);
				System.out.println(requete);
				continuer = false;
				return requete;
				//break;
				
				
			case "2":
				System.out.println("Votre nom?");
				reponseNom = fluxEntreeStandard.readLine();
				System.out.println("Votre pr�nom?");
				reponsePrenom = fluxEntreeStandard.readLine();
				System.out.println("Votre ann�e Diplomante?");
				reponseAnneeDiplo = fluxEntreeStandard.readLine();
				System.out.println("Souhaitez-vous partager votre ann�e diplomante avec les visiteurs? [o/n]");
				confidentialAnneeDip = fluxEntreeStandard.readLine();
				System.out.println("Votre num�ro de T�l�phone?");
				reponseTel = fluxEntreeStandard.readLine();
				System.out.println("Souhaitez-vous partager votre num�ro de t�l�phone avec les visiteurs? [o/n]");
				confidentialTel = fluxEntreeStandard.readLine();
				System.out.println("Votre adresse e-mail? (Servira � vous connecter)");
				reponseMail = fluxEntreeStandard.readLine();
				System.out.println("Souhaitez-vous partager votre adresse e-mail avec les visiteurs?");
				ConfidentialMail = fluxEntreeStandard.readLine();
				System.out.println("Votre mot de passe?");
				reponseMotDePasse = fluxEntreeStandard.readLine();
				requete= GestionProtocole.serialisation("inscription",reponsePrenom,reponseNom,reponseMotDePasse,reponseAnneeDiplo,confidentialAnneeDip,reponseTel,confidentialTel,reponseMail,ConfidentialMail);
				System.out.println(requete);
				//reponse=GestionProtocoleClient.inscription;
				continuer = false;
				return requete;
			case "3":
				System.out.println("Votre nom?");
				reponseNom = fluxEntreeStandard.readLine();
				System.out.println("Votre pr�nom?");
				reponsePrenom = fluxEntreeStandard.readLine();
				System.out.println("Votre ann�e Diplomante?");
				reponseAnneeDiplo = fluxEntreeStandard.readLine();
				System.out.println("Souhaitez-vous partager votre ann�e diplomante avec les visiteurs? [o/n]");
				confidentialAnneeDip = fluxEntreeStandard.readLine();
				System.out.println("Votre num�ro de T�l�phone?");
				reponseTel = fluxEntreeStandard.readLine();
				System.out.println("Souhaitez-vous partager votre num�ro de t�l�phone avec les visiteurs? [o/n]");
				confidentialTel = fluxEntreeStandard.readLine();
				System.out.println("Votre adresse e-mail? (Servira � vous connecter)");
				reponseMail = fluxEntreeStandard.readLine();
				System.out.println("Souhaitez-vous partager votre adresse e-mail avec les visiteurs?");
				ConfidentialMail = fluxEntreeStandard.readLine();
				System.out.println("Votre mot de passe?");
				reponseMotDePasse = fluxEntreeStandard.readLine();
				requete= GestionProtocole.serialisation("modification",reponsePrenom,reponseNom,reponseMotDePasse,reponseAnneeDiplo,confidentialAnneeDip,reponseTel,confidentialTel,reponseMail,ConfidentialMail);
				System.out.println(requete);
				//reponse=GestionProtocoleClient.inscription;
				continuer = false;
				return requete;
			case "4":
				requete="consulter";
				continuer = false;
				return requete;
			case "5":
				requete="deconnexion#simple";
				continuer = false;
				return requete;
			case "6":
				System.out.println("BYE");
				requete="deconnexion#quitter";
				continuer = false;
				quitter=true;
				return requete;
			default:
				System.out.println("Ce choix n'est pas disponible");
			}
		}
		
		if(!quitter){
			System.out.print("requ�te: "+reponse +" ");
			reponse = reponse + fluxEntreeStandard.readLine();
			return reponse;
		}	
		
		else{
			return null;
		}
		
	}
	
}
