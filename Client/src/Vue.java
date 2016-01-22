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
		String reponse = null;
		boolean quitter=false;
		
		//le flux à lire sera l'imput entrée par l'utilisateur
		BufferedReader fluxEntreeStandard = new BufferedReader(
				new InputStreamReader(System.in));
		
		System.out.println("Tapez votre choix:\n" + "1) Se connecter\n"
				+ "2) Creer un compte\n" + "3) Modifier son profil\n" + "4) Consulter l'anuaire\n"
				+ "5) Deconnexion\n" + "6) Quitter");
		
		//récupère le flux d'entré et le stock dans reponse
		reponse = fluxEntreeStandard.readLine();
		
		while (continuer) {
			switch (reponse) {
			case "1":
				reponse = "SE CONNECTER";
				
				continuer = false;
				break;
			case "2":
				reponse = "CREER UN COMPTE";
				continuer = false;
				break;
			case "3":
				reponse = "MODIFIER SON PROFIL";
				continuer = false;
				break;
			case "4":
				reponse = "CONSULTER L'ANNAUAIRE";
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
