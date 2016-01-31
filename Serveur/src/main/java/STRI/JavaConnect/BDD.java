package STRI.JavaConnect;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class BDD {
//	/* La liste qui contiendra tous les résultats de nos essais */
//    private List<String> messages = new ArrayList<String>();
//
//    public List<String> executerTests( String requete ) {
//    	/* Chargement du driver JDBC pour MySQL */
//    	try {
//    	    Class.forName( "com.mysql.jdbc.Driver" );
//	    
//    	} catch ( ClassNotFoundException e ) {
//    	    /* Gérer les éventuelles erreurs ici. */
//    	}
//    	
//    	
//    	
//
//        return messages;
//    } 
//	public static void main(String[] args){
//		insererCompetence2("Cuisine", "kent@yahoo.fr");
//	}
	
	/* Inserer une competence*/
	public static void insererCompetence(String competence){
		String sql = "INSERT INTO Competences(description) values('" + competence + "');";
		requeteInsertion(sql);
	}
	/* Inserer un utilisateur */
	public static void insererUtilisateur(String nom, String prenom, String mail,int anneDiplomation ){
		String sql = "INSERT INTO Utilisateurs values('" + nom + "','" + prenom + "','" + mail + "'," + anneDiplomation + ");";
		requeteInsertion(sql);
	}
	/* Lier une competence à un utilisateur (insertion dans Acquerir) */
	public static void insererAcquerir(String mail, int idComp ){
		String sql = "INSERT INTO Acquerir values('" + mail + "','" + idComp + "');";
		requeteInsertion(sql);
	}
	/* Ajouter une competence puis la lier à l'utilisateur qui l'a saisi  */
	public static void insererCompetence2(String competence, String mail ){
		String sql = "INSERT INTO Competences(description) values('" + competence + "');";
		String sql2 = "INSERT INTO Acquerir values('" + mail + "',LAST_INSERT_ID());";
		//Recupere le dernier id de type auto_increment genere
//		requeteInsertion(sql, sql2);
		requeteInsertion(sql);
		requeteInsertion(sql2);
	}
	
	
	/*Requête generale d'insertion*/
	private static void requeteInsertion(String requete){
		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost:3306/CONNECT";
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
			//Etape 4 : execution requête
			st.executeUpdate(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				//Etape 5 : libérer ressources de la memoire
				cn.close();
				st.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
//	public static void requeteInsertion(String requete, String requete2){
//		this(requete);
//		this(requete2);
//	}
	
	/* Selection d'un utilisateur (Nom, prenom, adresse, mail et annee de diplomation)*/
	public static ArrayList<Etudiant> SelectionInfosUtiliateur( ){
		String sql = "SELECT nom, prenom, mail, AnneDiplomation FROM Utilisateurs;";
		requeteInsertion(sql);
	}
	/*Recherche d'un utilisateur à partir du nom */
	
	/*Selection de toute les competences */
	
	/* Requete generale de selection */
	private static ResultSet requeteSelection(String requete){
		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost:3306/CONNECT";
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
			//Etape 4 : execution requête
			res=st.executeQuery(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				//Etape 5 : libérer ressources de la memoire
				cn.close();
				st.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}
	
	
	
}
