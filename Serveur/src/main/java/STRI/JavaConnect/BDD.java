package STRI.JavaConnect;

import java.util.ArrayList;
import java.util.List;

public class BDD {
	/* La liste qui contiendra tous les résultats de nos essais */
    private List<String> messages = new ArrayList<String>();

    public List<String> executerTests( String requete ) {
    	/* Chargement du driver JDBC pour MySQL */
    	try {
    	    Class.forName( "com.mysql.jdbc.Driver" );
	    
    	} catch ( ClassNotFoundException e ) {
    	    /* Gérer les éventuelles erreurs ici. */
    	}
    	
    	
    	

        return messages;
    } 
}
