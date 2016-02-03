package STRI.JavaConnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;


/**
 * @author Iungmann Vaurigaud Hernandez
 *
 */
public class Traitement extends Thread {

	private boolean fermeture;
	private Socket connexionCourante;
	private InputStream entreeSocket;
	private OutputStream sortieSocket;
	
	
//	private BDD BDD;

	String chaine;

	/** Traitement
	 * @param connexionCourante
	 * @param entreeSocket
	 * @param sortieSocket
	 */
	public Traitement(Socket connexionCourante, InputStream entreeSocket,
			OutputStream sortieSocket) {

//		this.gestion = gestion;
		this.chaine = null;
		this.connexionCourante = connexionCourante;
		this.entreeSocket = entreeSocket;
		this.sortieSocket = sortieSocket;
		this.fermeture = false;
		
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {

		int anneeDipl;
		Boolean connecte=false;
		String motClef, requete=null;
		BDD base=new BDD();
		GestionProtocoleServeur gestionProtocoleServeur=new GestionProtocoleServeur();
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
			String tabRequete[]=requete.split("#");
			motClef=tabRequete[0];
			System.out.println("motClef="+tabRequete[0]);
			/*****/
			switch (motClef){
			case "inscription":
				
				


				base.insererUtilisateur(tabRequete[1], tabRequete[2], tabRequete[3], tabRequete[4], anneeDipl =Integer.parseInt(tabRequete[5]), tabRequete[6]);
				System.out.println("J'ai recu une "+ motClef);

				break;
			case "connexion":
				System.out.println("J'ai recu une "+ motClef);
				chaine="OK";
				connecte=true;
				break;
			case "consulter":
				if (connecte==true){

					chaine ="connecte";
				}
				else {
					chaine="non connecte";

				}
				break;
			case "modifier":
				 
				break;
			case "recherche":
				 
				break;
				
			case "recupCompetence":
				ArrayList<String> comp = new ArrayList<String>();
				comp = BDD.SelectionCompetences(); //On recupere toutes les competences dans la BDD
				chaine = gestionProtocoleServeur.serialisation(comp); //On serialise
					
			break;
			case "deconnexion":
				System.out.println("1: "+tabRequete[0] + "et 2: "+tabRequete[1]);
				connecte=false; 
				if (tabRequete[1]=="quitter"){
					fermeture=true;
					chaine =null;
				}
				else{
					chaine="Deconnexion confirmee";
				}
				break;
			
			default:

				

				System.out.println("ERREUR (defaut)");

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