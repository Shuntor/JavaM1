import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;


public class Traitement extends Thread {

	private boolean fermeture;
	private Socket connexionCourante;
	private InputStream entreeSocket;
	private OutputStream sortieSocket;
	private String requete;
	
//	private BDD BDD;

	String chaine;

	public Traitement(Socket connexionCourante, InputStream entreeSocket,
			OutputStream sortieSocket) {

//		this.gestion = gestion;
		this.chaine = null;
		this.connexionCourante = connexionCourante;
		this.entreeSocket = entreeSocket;
		this.sortieSocket = sortieSocket;
		this.fermeture = false;
		this.requete = null;
		
	}

	public void run() {

		String motClef=null;
		while (!fermeture) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entreeSocket));
			
			try {
				requete=reader.readLine();
				System.out.println("requete recue = "+requete);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String tabRequete[]=requete.split(" ");
			motClef=tabRequete[0];
			System.out.println("motClef="+tabRequete[0]);
			/*****/
			switch (motClef){
			case "inscription":
				//BDD.insert(tabRequete[1],tabRequete[2],tabRequete[3],tabRequete[4],tabRequete[5],tabRequete[6], tabRequete[7],tabRequete[8],tabRequete[9] );
				System.out.println("J'ai reçu une "+ motClef);
				break;
			case "connexion":
				System.out.println("J'ai reçu une "+ motClef);
				chaine="OK";
				break;
			case "consulter":
				 
				break;
			case "modifier":
				 
				break;
			case "recherche":
				 
				break;
			case "déconnexion":
				 
				break;
			default:
				System.out.println("ERREUR (défaut)");
			}
			
			
			
			/****/
			System.out.println("serveur envoie la chaine= " + chaine);
			
			if (chaine != null) {
				PrintStream pStream = new PrintStream(sortieSocket);
				pStream.println(chaine);
			} else
				fermeture = true;
		}

		try {
			System.out.println("fermeture connexion " + connexionCourante);
			connexionCourante.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}