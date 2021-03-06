package STRI.JavaConnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.DatagramSocket;
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
	private Hashtable ht;	
	
//	private BDD BDD;

	String chaine;

	/** Traitement
	 * @param connexionCourante
	 * @param entreeSocket
	 * @param sortieSocket
	 */
	public Traitement(Socket connexionCourante, InputStream entreeSocket,
			OutputStream sortieSocket, Hashtable ht) {

//		this.gestion = gestion;
		
		this.connexionCourante = connexionCourante;
		this.entreeSocket = entreeSocket;
		this.sortieSocket = sortieSocket;
		this.ht=ht;
		
		
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
			case "ajouterCompUtilisateur":
				base.insererCompUtilisateur(tabRequete[1], tabRequete[2]);
				System.out.println("J'ai recu une "+ motClef);
                chaine="ok";
				break;
			case "supprCompUtilisateur":
				base.supprimerCompUtilisateur(tabRequete[1], tabRequete[2]);
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
			case "messages": //Recupere tous les messages et les envoie au client avec le format : Source1#Message1#Source2#Message2#...etc
				ArrayList<String> messages = new ArrayList<String>();
				messages=BDD.recupererMessages(tabRequete[1]);
				chaine=gestionProtocoleServeur.serialisation(messages);
				BDD.SupprimerTousMessages(tabRequete[1]);
				break;
			case "ajoutMessage":
				
				break;
				
			case "modifInfoUtilisateur":
				base.updateUtilisateur(tabRequete[1], tabRequete[2], tabRequete[3], tabRequete[4], tabRequete[5], tabRequete[6], tabRequete[7], tabRequete[8] );
				chaine="OK";
				
				
				break;
				
			case "supprimer":
				base.supprimerAcquerir(tabRequete[1]);
				base.supprimerUtilisateur(tabRequete[1]);
				chaine="ok";
				break;
			
			case "supprMails":
				base.SupprimerTousMessages(tabRequete[1]);
				chaine="OK";
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
			
			case "mail":
				
				base.ajouterMessage(tabRequete[1], tabRequete[2], mailCo);
				chaine ="OK";	
				
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
				
				ht.remove(mailCo);
				mailCo=null;
				chaine="OK";
				
				break;
			case "arret":
				fermeture=true;
				if (mailCo!=null){
					ht.remove(mailCo);
				}
				mailCo=null;
				chaine="OK";
				break;
			case "coordonnees":
				String tabCoordonnees[] = requete.split("#");
				System.out.println("tabCoordonnees="+tabCoordonnees[1]+ "/////"+tabCoordonnees[0]);
				ht.put(mailCo, tabCoordonnees[1]);
				System.out.println("tab pour "+mailCo+":"+ht.get(mailCo));
				chaine="OK";
				break;
			case "address":
                String tabAddress[] = requete.split("#");
                System.out.println("tabCoordonnees="+tabAddress[1]+ "/////"+tabAddress[0]);
                chaine=(String) ht.get(tabAddress[1]);
                System.out.println("syso "+ht.get(mailCo));
                break;
			case "avoir":
				ArrayList<String> listeCo=new ArrayList<String>();
                for (int o = 0; o < ht.size(); o++) {
					Enumeration e=ht.keys();
					while(e.hasMoreElements()){
						String clef=(String)e.nextElement();
						listeCo.add(clef);
						listeCo.add((String) ht.get(clef));
					}
				}
                for (int j = 0; j < listeCo.size(); j++) {
					if (j==0){
						chaine=listeCo.get(j)+"#";
						
					}
					else{
						chaine=requete+listeCo.get(j)+"#";
						
					}
				}
                
                break;
			case "LIKER":
				base.ajouterLike(tabRequete[1], mailCo ,tabRequete[3]);
				chaine="OK";
				break;
			case "UNLIKER":
				System.out.println("mailCo= "+mailCo);
				chaine=base.SupprimerLike(tabRequete[1], tabRequete[2] ,tabRequete[3]);
				
				
				break;
			case "LIKEURS":
				ArrayList<String> recommandeurs = new ArrayList<String>();
				recommandeurs=base.selectLikers(tabRequete[1]);
				chaine=gestionProtocoleServeur.serialisation(recommandeurs);
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