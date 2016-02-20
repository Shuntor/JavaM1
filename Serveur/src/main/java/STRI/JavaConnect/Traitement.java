package STRI.JavaConnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Enumeration;


/**
 * @author Iungmann Vaurigaud Hernandez
 *
 */
public class Traitement extends Thread {

	private boolean fermeture=false;
	private Socket connexionCourante;
	private InputStream entreeSocket;
	private OutputStream sortieSocket;
	private String mailCo = null;
	
	Hashtable ht = new Hashtable();
	
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
		
		this.connexionCourante = connexionCourante;
		this.entreeSocket = entreeSocket;
		this.sortieSocket = sortieSocket;
		
		
		
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
			
			chaine=null;
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
				base.insererUtilisateur(tabRequete[1], tabRequete[2], tabRequete[3], tabRequete[4], tabRequete[5], tabRequete[6], tabRequete[7], tabRequete[8], tabRequete[9]);
				System.out.println("J'ai recu une "+ motClef);
				chaine=tabRequete[3];
				break;
			case "assignerCompetences":
				String mail=tabRequete[1];
				for (int i = 2; i < tabRequete.length; i++) {
					base.insererAcquerir(mail, tabRequete[i]);
				}
				
				System.out.println("J'ai recu une "+ motClef);
                chaine="ok";
				break;
			case "connexion":
				Etudiant etu=null;
				System.out.println("J'ai recu une "+ motClef);
				chaine=base.ConnexionClient(tabRequete[1], tabRequete[2]);
				if (chaine.startsWith("OK")){
					mailCo=tabRequete[1];
					
				}
				connecte=true;
				break;
			
			case "supprimer":
				base.supprimerAcquerir(tabRequete[1]);
				base.supprimerUtilisateur(tabRequete[1]);
				chaine="ok";
				break;
			
			
			case "modifier":
				base.modifier(tabRequete[1], tabRequete[2], tabRequete[3], tabRequete[4], tabRequete[5], tabRequete[6], tabRequete[7], tabRequete[8], tabRequete[9]);
				break;
			
			case "compsUtilisateur":
				try {
					chaine=base.selectDescriptionParMail(tabRequete[1]);
					System.out.println("chaine description:"+chaine);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
				
			case "infoEtudiant":
				
				try {
					chaine=base.selectEtudiant(tabRequete[1]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				break;
				
			case "recherche":
				 
				break;
			
			case "recupEtudiant":
				String chaine2;
				ArrayList<Etudiant> listeEtu;
				listeEtu=base.SelectionInfosUtiliateur();
				for (int i = 0; i < listeEtu.size(); i++) {
					etu=listeEtu.get(i);
					if(i==0){
						chaine=etu.getNom() + "#";
						chaine=chaine+etu.getPrenom() + "#";
						chaine=chaine+etu.getMail() + "#";
						chaine=chaine+etu.getTel() + "#";
						chaine=chaine+etu.getAnneeDip() + "#";
					}
					else{
				
						chaine=chaine+etu.getNom() + "#";
						chaine=chaine+etu.getPrenom() + "#";
						chaine=chaine+etu.getMail() + "#";
						chaine=chaine+etu.getTel() + "#";
						chaine=chaine+etu.getAnneeDip() + "#";
						
					}
				}
				System.out.println("chaine="+chaine);
				break;
				
			case "ajoutCompetence":
				base.insererCompetence(tabRequete[1]); 
				chaine="OK";
				break;
				
			case "recupCompetence":
				ArrayList<String> comp = new ArrayList<String>();
				comp = BDD.SelectionCompetences(); //On recupere toutes les competences dans la BDD
				chaine = gestionProtocoleServeur.serialisation(comp); //On serialise
				System.out.println("chaine = "+chaine);
				break;
			case "deconnexion":
				System.out.println("1: "+tabRequete[0] + "et 2: "+tabRequete[1]);
				connecte=false; 
				chaine="OK";
				
				break;
			case "arret":
				fermeture=true;
				chaine="OK";
				break;
//			case "coordonnees":
//				String tabCoordonnees[] = requete.split("#/");
//				ht.put(mailCo, tabCoordonnees[1]);
//				break;
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