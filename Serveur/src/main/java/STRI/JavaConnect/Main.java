package STRI.JavaConnect;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Debut");
		try {
    	    Class.forName( "com.mysql.jdbc.Driver" );
	    
    	} catch ( ClassNotFoundException e ) {
    	    /* Gérer les éventuelles erreurs ici. */
    	}
		
		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost:3306/coVoiturage";
		String utilisateur = "root";
		String motDePasse = "43046721";
		java.sql.Connection connexion = null;
		try {
		    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

		    /* Ici, nous placerons nos requêtes vers la BDD */
		    /* ... */
		    try {
				/* Création de l'objet gérant les requêtes */
				Statement statement = connexion.createStatement();
				/* Exécution d'une requête de lecture */
				ResultSet resultat = statement
						.executeQuery("Select * from voitures;");
				/* Récupération des données du résultat de la requête de lecture */
				while (resultat.next()) {
					int idV = resultat.getInt("idV");
					String couleur = resultat.getString("couleur");
					System.out.println("idV : " + idV);
					System.out.println("couleur : " + couleur);

					/* Traiter ici les valeurs récupérées. */
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		    
		    

		} catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
		} finally {
		    if ( connexion != null )
		        try {
		            /* Fermeture de la connexion */
		            connexion.close();
		        } catch ( SQLException ignore ) {
		            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
		        }
		}
		
		
		
		
		System.out.println("Fin");

	}

}
/* N :
 * D :
 * A : 
 * R :
 *
*/
