import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Iungmann Vaurigaud Hernandez
 *
 */
public class GestionProtocoleClient {

	
//	public void analyser (String requete){
//		if(requete.startsWith("connexion")==true){
//			String[] parts = requete.split("|");
//			String part0 = parts[0]; 
//			String part1 = parts[1];
//			String part2 = parts[2];
//			
//			V.maMethode(part1, sommeIni);
//		}
		
	Socket leSocket;
	PrintStream fluxSortieSocket;
	BufferedReader fluxEntreeSocket;
		
		/****
		 * serialisation
		 * @param args
		 * @return chaine
		 * Mise en forme des trames
		 */
		public String serialisation(String... args){
			String chaine = "";
			for (String arg:args){
				chaine+=arg;
				chaine+="#";
			}
			return chaine;
		}
		
		/**deserialisation
		 * @param requete
		 * @return
		 */
		public String[] deserialisation(String requete){
			String tabRequete[]=requete.split("#");
			return tabRequete;
		}
		
		
		
		/**Connexion socket
		 * @throws UnknownHostException
		 * @throws IOException
		 */
		public void connexion() throws UnknownHostException, IOException{
			leSocket = new Socket("127.0.0.1", 50000); // @IP du serveur
			
			fluxSortieSocket = new PrintStream(leSocket.getOutputStream());
			fluxEntreeSocket = new BufferedReader(new InputStreamReader(leSocket.getInputStream()));
			System.out.println("Connecté!");
		}
		
		/**Envoi general des trames
		 * @param requete
		 * @return retour
		 * @throws IOException
		 */
		private String envoiTrame(String requete) throws IOException{
			System.out.println(requete);
			fluxSortieSocket.println(requete);
			
			String retour = fluxEntreeSocket.readLine();
			return retour;
		}
		
		
		
		/**Connexion d'un client
		 * @param mail
		 * @param mDP
		 * @return boolean
		 * @throws IOException
		 */
		public boolean connexionClient(String mail, String mDP) throws IOException{
			boolean ok=false;
			String requete, retour=null;
			
			requete=serialisation("connexion",mail,mDP);
			retour=envoiTrame(requete);
			System.out.println("retour = "+retour);
			if (retour.startsWith("OK")){
				return true;
			}
			else{
				return false;
			}
			
		}
		
		
		
		/**Deconnexion d'un client
		 * @return boolean
		 * @throws IOException
		 */
		public boolean deconnexionClient() throws IOException{
			String requete, retour=null;
			
			requete=serialisation("deconnexion#simple");
			retour = envoiTrame(requete);
			System.out.println("retour = "+retour);
			if (retour.startsWith("OK")){
				return true;
			}
			else{
				return false;
			}
			
		}
		
		/**Envoi la requete d'arret du client afin que le thread du serveur s'arrete ainsi que la connexion Socket
		 * @throws IOException
		 */
		public void arret() throws IOException{
			String requete, retour=null;
			
			requete=serialisation("arret","arret");
			retour = envoiTrame(requete);
			System.out.println("retour = "+retour);
		}
		
		/** Envoi d'une trame pour demander les informations sur un client en fonction de son adresse Mail
		 * @param email
		 * @return retour
		 * @throws IOException
		 */
		public String infoEtudiant (String email) throws IOException{
			String requete, retour=null;
			requete=serialisation("infoEtudiant",email);
			retour = envoiTrame(requete);
			System.out.println("retour = "+retour);
			return retour;
					
		}
		
		
		/**Recupere les competences d'un utilisateur en fonction de son adresse Mail
		 * @param email
		 * @return retour
		 * @throws IOException
		 */
		public String competencesUtilisateur (String email) throws IOException{
			String requete, retour=null;
			requete=serialisation("compsUtilisateur",email);
			retour = envoiTrame(requete);
			System.out.println("retour = "+retour);
			return retour;
					
		}
		
		/** envoi les infos sur l'étudiant pour inscription
		 * @param nom
		 * @param prenom
		 * @param mail
		 * @param tel
		 * @param anneeDipl
		 * @param mdp
		 * @param listeComp
		 * @param showTel
		 * @param showAnneeDipl
		 * @param showCompetences
		 * @throws IOException
		 */
		public void envoiInfo(String nom, String prenom, String mail, String tel, String anneeDipl, String mdp, ArrayList<String>listeComp, boolean showTel, boolean showAnneeDipl, boolean showCompetences) throws IOException{
			String requete, retour, showAnneeDipl2, showTel2, showCompetences2=null;
			if (showAnneeDipl==true){
				showAnneeDipl2="1";
			}else{
				showAnneeDipl2="0";
			}
			if (showTel==true){
				showTel2="1";
			}else{
				showTel2="0";
			}
			if (showCompetences==true){
				showCompetences2="1";
			}else{
				showCompetences2="0";
			}
			requete=serialisation("inscription", nom, prenom, mail, anneeDipl, tel, mdp, showTel2, showAnneeDipl2, showCompetences2);
			retour=envoiTrame(requete);
			
			System.out.println("retour = "+retour);
			requete="assignerCompetences#"+retour+"#";
			for (int i = 0; i < listeComp.size(); i++) {
				requete = requete+listeComp.get(i)+"#";
			}
			retour = envoiTrame(requete);
			
		}
		
		
		/**Requete pour que le serveur envoi les competences generales
		 * @return liste
		 * @throws IOException
		 */
		public ArrayList<String> recupererCompetences() throws IOException{
			ArrayList<String> liste = null ;
			int x=0;
			String requete="recupCompetence";
			String retour=envoiTrame(requete);
			System.out.println("retour = "+retour);
			String listeRequete[] = retour.split("#");
			liste = new ArrayList<String>(Arrays.asList(listeRequete));
			return liste;
		}
		
		/**requete pour que le serveur assigne des competences à un utilisateur
		 * @param competences
		 * @return 
		 * @throws IOException
		 */
		public boolean envoiCompetences(ArrayList<String> competences) throws IOException{
			String retour, requete = null;
			requete="assignerCompetences#";
			for (int i = 0; i < competences.size(); i++) {
				if (i==0){
					requete=competences.size()+"#";
				}
				requete = requete+competences.get(i);
				requete= requete+"#";
			}
			retour = envoiTrame(requete);
			
			return true;
		}
		
		
		/**Creer une nouvelle competence dans la base de donnees
		 * @param c
		 * @throws IOException
		 */
		public void creerCompetence(String c) throws IOException{
			String retour, requete = null;
			requete="ajoutCompetence#"+c;
			retour = envoiTrame(requete);
		}
		
		/**requete pour récupérer tous les etudiants inscrits
		 * @return retour
		 * @throws IOException
		 */
		public String recupEtudiants() throws IOException{
			String retour, requete = null;
			requete="recupEtudiant#etu";
			retour=envoiTrame(requete);
			return retour;
			
		}
		
//		public void modif(String nom, String prenom, String mail, String tel, String anneeDipl, ArrayList<String>listeComp, boolean showTel, boolean showAnneeDipl, boolean showCompetences) throws IOException{
//			String requete, retour, showAnneeDipl2, showTel2, showCompetences2=null;
//			if (showAnneeDipl==true){
//				showAnneeDipl2="1";
//			}else{
//				showAnneeDipl2="0";
//			}
//			if (showTel==true){
//				showTel2="1";
//			}else{
//				showTel2="0";
//			}
//			if (showCompetences==true){
//				showCompetences2="1";
//			}else{
//				showCompetences2="0";
//			}
//			requete=serialisation("modifier", nom, prenom, mail, anneeDipl, tel, showTel2, showAnneeDipl2, showCompetences2);
//			retour=envoiTrame(requete);
//			
//			System.out.println("retour = "+retour);
//			requete="assignerCompetences#"+retour+"#";
//			for (int i = 0; i < listeComp.size(); i++) {
//				requete = requete+listeComp.get(i)+"#";
//			}
//			retour = envoiTrame(requete);
//			
//		}
		
		/**Supprime le compte d'un utilisateur
		 * @param mail
		 * @throws IOException
		 */
		public void supprimerCompte(String mail) throws IOException{
			String retour, requete = null;
			requete="supprimer#"+mail;
			retour=envoiTrame(requete);
			
		}
		
		public void envoiCoordonnees(){
			
		}
		
	
	}
	
	


