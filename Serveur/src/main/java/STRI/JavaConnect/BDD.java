package STRI.JavaConnect;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @author Iungmann Vaurigaud Hernandez
 *
 */
/**
 * @author quent
 *
 */
public class BDD {
	/**
	 * @param args
	 */
	public static void main(String[] args){
//		Etudiant etu = null;
//		ArrayList<String> comp;
//		etu = Connexion("vic@gmail.com","root");
//		System.out.println(etu.getNom());
//		comp=SelectionCompetences();
//		for (int i = 0; i < comp.size(); i++) {
//			System.out.println(comp);
//		}
//		comp=SelectionCompetencesUtilisateur("vic@gmail.com");
//		System.out.println(comp);
//		ajouterLike("Maths","vic@gmail.com","kent@yahoo.fr");
     
		
	}
	
	public synchronized static void updateUtilisateur(String email, String nom, String prenom, String tel, String annee, String sh_tel, String sh_annee, String sh_comp){
		String sql = "UPDATE utilisateurs SET nom='"+nom+"', prenom='"+prenom+"', tel='"+tel+"', showTel='"+sh_tel+"', showAnneDiplomation='"+sh_annee+"', showCompetences='"+sh_comp+"' WHERE mail='"+email+"';";
		requeteInsertion(sql);
	}
	
	public synchronized static void insererCompUtilisateur(String mail, String competence){
		String sql="INSERT INTO acquerir (mail,description) VALUES ('"+mail+"', '"+competence+"');" ;
		requeteInsertion(sql);
	}
	
	public synchronized static void supprimerCompUtilisateur(String mail, String competence){
		String sql="DELETE FROM Acquerir WHERE (mail='"+mail+"', description='"+competence+"');" ;
		requeteInsertion(sql);
	}
	
	/** Inserer une competence
	 * @param competence
	 */
	public synchronized static void insererCompetence(String competence){
		String sql = "INSERT INTO Competences(description) values('" + competence + "');";
		requeteInsertion(sql);
	}

	
	/**Inserer un utilisateur
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @param tel
	 * @param anneeDipl
	 * @param mdp
	 * @param showTel
	 * @param showAnneeDipl
	 * @param showComp
	 */
	public synchronized static void insererUtilisateur(String nom, String prenom, String mail,String tel, String anneeDipl, String mdp, String showTel, String showAnneeDipl, String showComp ){
		String sql = "INSERT INTO utilisateurs (nom, prenom, mail, AnneDiplomation, tel, mdp, showTel, showAnneDiplomation, showCompetences) VALUES ('"+nom+"', '"+prenom+"', '"+mail+"', '"+tel+"', '"+anneeDipl+"', '"+mdp+"', '"+showTel+"', '"+showAnneeDipl+"', '"+showComp+"');";
		System.out.println("requete inscription ="+sql);
		requeteInsertion(sql);
	}

	/** Lier une competence a un utilisateur (insertion dans Acquerir)
	 * @param mail
	 * @param comp
	 * 
	 */
	public synchronized static void insererAcquerir(String mail, String comp ){

		String sql="INSERT INTO acquerir (mail,description) VALUES ('"+mail+"', '"+comp+"');" ;
        requeteInsertion(sql);
	}

	
	/**Supprimer les compétences acquises par un utilisateur.
	 * @param mail
	 */
	public synchronized static void supprimerAcquerir(String mail ){
            String sql = "DELETE FROM acquerir WHERE mail='"+mail+"';" ;
            requeteInsertion(sql);
	}
	
	
	
	/**Supprime un utilisateur de la table Utlisateurs
	 * @param mail
	 */
	public synchronized static void supprimerUtilisateur(String mail ){
        String sql = "DELETE FROM Utilisateurs WHERE mail='"+mail+"';" ;
        requeteInsertion(sql);

	}
	
	
	/**
	 * @param mail
	 * @return requete
	 * @throws SQLException
	 */
	public synchronized String selectDescriptionParMail(String mail) throws SQLException{
		String requete;
		ResultSet resultat = requeteSelection("SELECT description FROM Acquerir WHERE mail='"+mail+"';" );
		resultat.next();
		requete = resultat.getString("description") + "#";
        while ( resultat.next() ) {
        	requete = requete + resultat.getString("description") + "#";
        }
        return requete;
	}
	
	
	/** Requete generale d'insertion
	 * @param requete
	 * 
	 */
	private synchronized static void requeteInsertion(String requete){
		/* Connexion a la base de donnees */
		String url = "jdbc:mysql://localhost:3306/connect";
		String login = "root";
		String passwd = "root";
		java.sql.Connection cn = null;
		Statement st = null;
		
		try {
			//Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			//Etape 2 : Recuperation de la connexion
			cn = DriverManager.getConnection(url, login, passwd);
			//Etape 3 : Creation d'un statement
			st = (Statement) cn.createStatement();
			//Etape 4 : execution requete
			st.executeUpdate(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				//Etape 5 : liberer ressources de la memoire
				cn.close();
				st.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * @param mail
	 * @return requete
	 * @throws SQLException
	 */
	public synchronized String selectEtudiant(String mail) throws SQLException{
		String requete = null;
        ResultSet resultat = requeteSelection( "SELECT nom, prenom, mail, tel, AnneDiplomation, showTel, showAnneDiplomation, showCompetences  FROM Utilisateurs WHERE mail='"+mail+"';" );
        
        while ( resultat.next() ) {
        	requete = resultat.getString("nom") + "#" + resultat.getString("prenom") + "#" + resultat.getString("mail") + "#" + resultat.getString("tel") + "#" + resultat.getString("AnneDiplomation")+ "#" + resultat.getString("showTel")+ "#" + resultat.getString("showAnneDiplomation")+ "#" + resultat.getString("showCompetences");
        }
        return requete;       
	}
	/** Selection des utilisateurs (Nom, prenom, adresse, mail et annee de diplomation)
	 * @return
	 */
	public synchronized static ArrayList<Etudiant> SelectionInfosUtiliateur( ){
		ArrayList<Etudiant> res = new ArrayList<Etudiant>();
		Etudiant etu ;
		ResultSet result;
		String sql = "SELECT nom, prenom, mail, tel, AnneDiplomation FROM Utilisateurs;";
		result=requeteSelection(sql);
		try {
			while (result.next()) {
				etu = new Etudiant("lalaa");
				etu.setNom(result.getString(1));
				etu.setPrenom(result.getString(2));
				etu.setMail(result.getString(3));
				etu.setTel(result.getInt(4));
				etu.setAnneeDip(result.getInt(5));
				//On ajoute dans le tableau
				res.add(etu);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}
	/** Recherche d'un utilisateur à partir du nom
	 * @param nom
	 * @return
	 */
	public synchronized static Etudiant RechercheEtudiant(String nom ){
		ResultSet result;
		Etudiant etu = null;
		
		String sql = "SELECT nom, prenom, mail, AnneDiplomation, tel FROM Utilisateurs where nom='"+nom+"'";
//		System.out.println(sql);
		result=requeteSelection(sql);
		try {
			result.next();
			etu = new Etudiant(nom, result.getString("prenom"), result.getInt("AnneDiplomation"), result.getString("mail"), result.getInt("tel"));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return etu;
	}
	

	/** Selection et suppression des messages d'un utilisateur lors de sa connexion
	 * @param mail
	 * @return
	 */
	public synchronized static ArrayList<String> SelectionMessages(String mail){
		ResultSet result;
		ArrayList<String> messages = new ArrayList<String>();
		String sql = "SELECT source, message FROM Messages WHERE dest='"+mail+"';";
		result=requeteSelection(sql);
		try {
			while (result.next()) {
				messages.add(result.getString(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return messages;
	}
	
	/** Selection de toutes les compétences
	 * @return
	 */
	public synchronized static ArrayList<String> SelectionCompetences( ){
		ResultSet result;
		ArrayList<String> competences = new ArrayList<String>();
		String sql = "SELECT * FROM Competences;";
		result=requeteSelection(sql);
		try {
			while (result.next()) {
				competences.add(result.getString(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return competences;
	}

	
	
	/**Selectionner les competences acquéries par un utilisateur selon son adresse Mail
	 * @param mail
	 * @return
	 */
	public synchronized static ArrayList<String> SelectionCompetencesUtilisateur( String mail ){
		ResultSet result;
		ArrayList<String> competences = new ArrayList<String>();
		String sql = "SELECT description FROM Acquerir where mail= '"+mail+"';";
		result=requeteSelection(sql);
		try {
			while (result.next()) {
				competences.add(result.getString(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return competences;
	}
	/** Connexion
	 * @param mail
	 * @param mdp
	 * @return etu
	 */
	public synchronized static Etudiant Connexion(String mail, String mdp ){
		ResultSet result;
		Etudiant etu = null;
		
		String sql = "SELECT nom, prenom, mail, AnneDiplomation, tel FROM Utilisateurs where mail='"+mail+"' AND mdp='"+mdp+"';";
//		System.out.println(sql);
		result=requeteSelection(sql);
		try {
			result.next();
			etu = new Etudiant(result.getString("nom"), result.getString("prenom"), result.getInt("AnneDiplomation"), result.getString("mail"), result.getInt("tel"), mdp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return etu;
	}
	
	/**Requête générale de selection
	 * @param requete
	 * @return
	 */
	private synchronized static ResultSet requeteSelection(String requete){
		/* Connexion a la base de donnees */
		String url = "jdbc:mysql://localhost:3306/connect";
		String login = "root";
		String passwd = "root";
		java.sql.Connection cn = null;
		Statement st = null;
		ResultSet res = null;
		
		try {
			//Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			//Etape 2 : Recuperation de la connexion
			cn = DriverManager.getConnection(url, login, passwd);
			//Etape 3 : Creation d'un statement
			st = (Statement) cn.createStatement();
			//Etape 4 : execution requete
			res=st.executeQuery(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
//			try {
//				//Etape 5 : liberer ressources de la memoire
//				//cn.close();
//				//st.close();
//			} catch (SQLException e2) {
//				e2.printStackTrace();
//			}
		}
		return res;
	}
	
	/**Connexion d'un etudiant
	 * @param mail
	 * @param mdp
	 * @return
	 */
	public synchronized static String ConnexionClient(String mail, String mdp ){
		ResultSet result;
		Etudiant etu = null;
		
		
		String sql = "SELECT nom, prenom, mail, AnneDiplomation, tel FROM Utilisateurs where mail='"+mail+"' AND mdp='"+mdp+"';";
//		System.out.println(sql);
		result=requeteSelection(sql);
		try {
			result.next();
			etu = new Etudiant(result.getString("nom"), result.getString("prenom"), result.getInt("AnneDiplomation"), result.getString("mail"), result.getInt("tel"), mdp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "NOK";
		}
		if (etu.getNom()!=null){
			return "OK";
		}else{
			return "NOK";
		}
	}
	
	public static void modifier(String nom, String prenom, String mail,String tel, String anneeDipl, String mdp, String showTel, String showAnneeDipl, String showComp){
		//String comp;
		supprimerAcquerir(mail);
		supprimerUtilisateur(mail);
		insererUtilisateur(nom,prenom,mail,tel, anneeDipl, mdp, showTel, showAnneeDipl, showComp );
		
	}
	/*Les messages*/
	public synchronized static void ajouterMessage(String message, String destinataire, String source){
		String sql ="INSERT INTO Messages values('"+destinataire+"','"+source+"','"+message+"');";
		requeteInsertion(sql);
	}
	
	public synchronized static void SupprimerTousMessages(String destinataire){
		String sql ="DELETE FROM Messages where dest='"+destinataire+"';";
		requeteInsertion(sql);
	}
	
	public synchronized static void SupprimerMessage(String message, String destinataire, String source){
		String sql ="DELETE FROM Messages where dest='"+destinataire+"' AND source='"+source+"' AND Message='"+message+"';";

		requeteInsertion(sql);
	}
	public synchronized static ArrayList<String> recupererMessages(String dest){
		ResultSet result;
		ArrayList<String> messages = new ArrayList<String>();
		
		String sql = "Select source, message FROM Messages WHERE dest='"+dest+"';";
		result=requeteSelection(sql);
		try {
			while (result.next()) {
				messages.add(result.getString(1));
				messages.add(result.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messages;
	}
	
	/* LES LIKES */
	
	public synchronized static void ajouterLike(String comp, String mailDeCeluiQuiRecommande, String MailDuRecommande){
		String sql = "INSERT INTO Recommander values('"+comp+"','"+mailDeCeluiQuiRecommande+"','"+ MailDuRecommande+"');";
		requeteInsertion(sql);
	}
	public synchronized static String SupprimerLike(String comp, String mailDeCeluiQuiRecommande, String MailDuRecommande){
		String sql;		
		ResultSet result;
		ArrayList<String> recommandeurs = new ArrayList<String>();
		
		
		sql = "Select utilisateurQuiRecommande, comp FROM Recommander WHERE comp='"+comp+"' AND utilisateurQuiRecommande='"+ mailDeCeluiQuiRecommande+"' AND utilisateurRecommande='"+MailDuRecommande+"';";
		System.out.println("requete= "+sql);
		result=requeteSelection(sql);
		try {
			if (!result.next()){
				return "NOK";
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		try {
//			while (result.next()) {
//				recommandeurs.add(result.getString(1));
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			return "NOK";
//		}
		sql = "DELETE FROM Recommander WHERE comp='"+comp+"' AND utilisateurQuiRecommande='"+ mailDeCeluiQuiRecommande+"' AND utilisateurRecommande='"+MailDuRecommande+"';";
		System.out.println("requete= "+sql);
		try {
			requeteInsertion(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "NOK";
		}
		return "OK";
	}
	public synchronized static ArrayList<String> selectLikers(String recommande){
		ResultSet result;
		ArrayList<String> recommandeurs = new ArrayList<String>();
		
		String sql = "Select utilisateurQuiRecommande, comp FROM Recommander WHERE utilisateurRecommande='"+recommande+"';";
		System.out.println("requete= "+sql);
		result=requeteSelection(sql);
		try {
			while (result.next()) {
				recommandeurs.add(result.getString(1));
				recommandeurs.add(result.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recommandeurs;
	}
	
}
