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
public class BDD {
	
//    private List<String> messages = new ArrayList<String>();
//
//    public List<String> executerTests( String requete ) {
//    	/* Chargement du driver JDBC pour MySQL */
//    	try {
//    	    Class.forName( "com.mysql.jdbc.Driver" );
//	    
//    	} catch ( ClassNotFoundException e ) {
//    	    /* Gerer les eventuelles erreurs ici. */
//    	}
//    	
//    	
//    	
//
//        return messages;
//    } 
	/**
	 * @param args
	 */
/*	public static void main(String[] args){
		Etudiant etu = null;
		ArrayList<String> comp;
//		etu = Connexion("vic@gmail.com","root");
//		System.out.println(etu.getNom());
//		comp=SelectionCompetences();
//		for (int i = 0; i < comp.size(); i++) {
//			System.out.println(comp);
//		}
		comp=SelectionCompetencesUtilisateur("vic@gmail.com");
		System.out.println(comp);
	}*/
	

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
//		String url = InformationsConnexion.urlBD();
//        String utilisateur = InformationsConnexion.utilisateurBD();
//        String motDePasse = InformationsConnexion.mdpBD();
        String url = "jdbc:mysql://localhost:3306/connect";
		String login = "root";
		String passwd = "root";
        
		
		
        try (java.sql.Connection connexion = DriverManager.getConnection(url, login, passwd)){
            //INSERER UN lOCAL
            java.sql.Statement insertUtilisateur = connexion.createStatement();
            int id;
            
            
            //String requeteInsertLocal=("\"INSERT INTO Locaux (idL, nom, adresse) VALUES (1, '".concat(nom).concat("', '").concat(adresse).concat("');\"") );
            int statut = insertUtilisateur.executeUpdate("INSERT INTO utilisateurs (nom, prenom, mail, AnneDiplomation, tel, mdp, showTel, showAnneDiplomation, showCompetences) VALUES ('"+nom+"', '"+prenom+"', '"+mail+"', '"+tel+"', '"+anneeDipl+"', '"+mdp+"', '"+showTel+"', '"+showAnneeDipl+"', '"+showComp+"');" );
            
            
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
//		String sql = "INSERT INTO Utilisateurs values('" + nom + "','" + prenom + "','" + mail + "'," + tel + "," + anneeDipl + "," + mdp + ");";
//		requeteInsertion(sql);
	}

	/** Lier une competence a un utilisateur (insertion dans Acquerir)
	 * @param mail
	 * @param comp
	 * 
	 */
	public synchronized static void insererAcquerir(String mail, String comp ){
		String url = "jdbc:mysql://localhost:3306/connect";
		String login = "root";
		String passwd = "root";
        
        try (java.sql.Connection connexion = DriverManager.getConnection(url, login, passwd)){
            
            java.sql.Statement insertAcquerir = connexion.createStatement();
            int statut = insertAcquerir.executeUpdate("INSERT INTO acquerir (mail,description) VALUES ('"+mail+"', '"+comp+"');" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public synchronized static void supprimerAcquerir(String mail ){
		String url = "jdbc:mysql://localhost:3306/connect";
		String login = "root";
		String passwd = "root";
        
        try (java.sql.Connection connexion = DriverManager.getConnection(url, login, passwd)){
            //INSERER UN lOCAL
            java.sql.Statement insertAcquerir = connexion.createStatement();

            //String requeteInsertLocal=("\"INSERT INTO Locaux (idL, nom, adresse) VALUES (1, '".concat(nom).concat("', '").concat(adresse).concat("');\"") );
            int statut = insertAcquerir.executeUpdate("DELETE FROM acquerir WHERE mail='"+mail+"';" );

        } catch (SQLException e) {
            e.printStackTrace();
        }

	}
	
	public synchronized static void supprimerUtilisateur(String mail ){
		String url = "jdbc:mysql://localhost:3306/connect";
		String login = "root";
		String passwd = "root";
        
        try (java.sql.Connection connexion = DriverManager.getConnection(url, login, passwd)){
            //INSERER UN lOCAL
            java.sql.Statement insertAcquerir = connexion.createStatement();

            //String requeteInsertLocal=("\"INSERT INTO Locaux (idL, nom, adresse) VALUES (1, '".concat(nom).concat("', '").concat(adresse).concat("');\"") );
            int statut = insertAcquerir.executeUpdate("DELETE FROM Utilisateurs WHERE mail='"+mail+"';" );

        } catch (SQLException e) {
            e.printStackTrace();
        }

	}
	
	/**
	 * @param mail
	 * @return requete
	 * @throws SQLException
	 */
	public synchronized String selectEtudiant(String mail) throws SQLException{
		String url = "jdbc:mysql://localhost:3306/connect";
		String login = "root";
		String passwd = "root";
		String requete = null;
		
		try (java.sql.Connection connexion = DriverManager.getConnection(url, login, passwd)){
	            //INSERER UN lOCAL
				java.sql.Statement selectEtu = connexion.createStatement();
	           
	            ResultSet resultat2 = selectEtu.executeQuery( "SELECT nom, prenom, mail, tel, AnneDiplomation, showTel, showAnneDiplomation, showCompetences  FROM Utilisateurs WHERE mail='"+mail+"';" );
	            
	            while ( resultat2.next() ) {
	            	requete = resultat2.getString("nom") + "#" + resultat2.getString("prenom") + "#" + resultat2.getString("mail") + "#" + resultat2.getString("tel") + "#" + resultat2.getString("AnneDiplomation")+ "#" + resultat2.getString("showTel")+ "#" + resultat2.getString("showAnneDiplomation")+ "#" + resultat2.getString("showCompetences");
	            }
	            return requete;
		}        
	}
	
	
	/**
	 * @param mail
	 * @return requete
	 * @throws SQLException
	 */
	public synchronized String selectDescriptionParMail(String mail) throws SQLException{
		String url = "jdbc:mysql://localhost:3306/connect";
		String login = "root";
		String passwd = "root";
		String requete = null;
		int i=0;
		
		try (java.sql.Connection connexion = DriverManager.getConnection(url, login, passwd)){
	            //INSERER UN lOCAL
				java.sql.Statement selectEtu = connexion.createStatement();
	           
	            ResultSet resultat2 = selectEtu.executeQuery( "SELECT description  FROM Acquerir WHERE mail='"+mail+"';" );
	            
	            while ( resultat2.next() ) {
	            	if(i==0){
	            		requete = resultat2.getString("description") + "#";
	            	}
	            	else{
	            		requete = requete + resultat2.getString("description") + "#";
	            	}
	            	i++;
	            }
	            return requete;
		}        
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
		String comp;
		supprimerAcquerir(mail);
		supprimerUtilisateur(mail);
		insererUtilisateur(nom,prenom,mail,tel, anneeDipl, mdp, showTel, showAnneeDipl, showComp );
		
		
	}
	
}
