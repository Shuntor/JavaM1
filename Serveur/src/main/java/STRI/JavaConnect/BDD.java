package STRI.JavaConnect;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public static void sauverEnBase(String competence){
		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost:3306/CONNECT";
		String login = "root";
		String passwd = "43046721";
		java.sql.Connection cn = null;
		Statement st = null;
		
		try {
			//Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			//Etape 2 : Recuperation de la connexion
			cn = DriverManager.getConnection(url, login, passwd);
			//Etape 3 : Creation d'un statement
			st = (Statement) cn.createStatement();
			String sql = "INSERT INTO Competences values('" + competence + "');";
			//Etape 4 : execution requête
			st.executeUpdate(sql);
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
}
