package STRI.JavaConnect;

import java.util.ArrayList;



/**
 * @author Iungmann Vaurigaud Hernandez
 *
 */
public class GestionProtocoleServeur {
	
//	public void analyser (String requete){
//	if(requete.startsWith("connexion")==true){
//		String[] parts = requete.split("|");
//		String part0 = parts[0]; 
//		String part1 = parts[1];
//		String part2 = parts[2];
//		
//		V.maMethode(part1, sommeIni);
//	}
	
	
	
	/****
	 * serialisation
	 * @param args
	 * @return
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
	public String serialisation(ArrayList<String> args){
		String chaine = "";
		for (int i = 0; i < args.size(); i++) {
			chaine+=args.get(i);
			chaine+="#";
		}
		return chaine;
	}
}

