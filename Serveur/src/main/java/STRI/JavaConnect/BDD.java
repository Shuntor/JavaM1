package STRI.JavaConnect;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	public static void main(String[] args){
		Etudiant etu = null;
		ArrayList<String> comp;
//		etu = Connexion("vic@gmail.com","root");
//		System.out.println(etu.getNom());
		comp=SelectionCompetences();
		for (int i = 0; i < comp.size(); i++) {
			System.out.println(comp);
		}
	}
	
	/* Inserer une competence*/
	public static void insererCompetence(String competence){
		String sql = "INSERT INTO Competences(description) values('" + competence + "');";
		requeteInsertion(sql);
	}
	/* Inserer un utilisateur */
	public static void insererUtilisateur(String nom, String prenom, String mail,int anneDiplomation, int tel, String mdp ){
		String sql = "INSERT INTO Utilisateurs values('" + nom + "','" + prenom + "','" + mail + "'," + anneDiplomation + "," + tel + "," + mdp + ");";
		requeteInsertion(sql);
	}
	/* Lier une competence à un utilisateur (insertion dans Acquerir) */
	public static void insererAcquerir(String mail, String comp ){
		String sql = "INSERT INTO Acquerir values('" + mail + "','" + comp + "');";
		requeteInsertion(sql);
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
	
	/* Selection des utilisateurs (Nom, prenom, adresse, mail et annee de diplomation)*/
	public static ArrayList<Etudiant> SelectionInfosUtiliateur( ){
		ArrayList<Etudiant> res = new ArrayList<Etudiant>();
		Etudiant etu ;
		ResultSet result;
		String sql = "SELECT nom, prenom, mail, AnneDiplomation FROM Utilisateurs;";
		result=requeteSelection(sql);
		try {
			while (result.next()) {
				etu = new Etudiant("lalaa");
				etu.setNom(result.getString(1));
				etu.setPrenom(result.getString(2));
				etu.setMail(result.getString(3));
				etu.setAnneeDip(result.getInt(4));
				//On ajoute dans le tableau
				res.add(etu);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}
	/*Recherche d'un utilisateur à partir du nom */
	public static Etudiant RechercheEtudiant(String nom ){
		ResultSet result;
		Etudiant etu = null;
		
		String sql = "SELECT nom, prenom, mail, AnneDiplomation, tel FROM Utilisateurs where nom='"+nom+"'";
//		System.out.println(sql);
		result=requeteSelection(sql);
		try {
			result.next();
			etu = new Etudiant(nom, result.getString("prenom"), result.getInt("AnneDiplomation"), result.getString("mail"), result.getInt("tel"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return etu;
	}
	/*Selection de toutes les competences */
	public static ArrayList<String> SelectionCompetences( ){
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
	/*Connexion */
	public static Etudiant Connexion(String mail, String mdp ){
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
//			try {
//				//Etape 5 : libérer ressources de la memoire
//				//cn.close();
//				//st.close();
//			} catch (SQLException e2) {
//				e2.printStackTrace();
//			}
		}
		return res;
	}
	
	
	
}
