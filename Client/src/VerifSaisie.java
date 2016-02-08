/**
 * 
 */

/**
 * @author quent
 *
 */
public class VerifSaisie {
	
	/** Verifi si l'adresse mail correspond au format
	 * @param email
	 * @return
	 */
	public boolean verifMail(String email){
		if( email!=null && email.trim().length()>0 )
			return email.matches("^[a-zA-Z0-9\\.\\-\\_]+@([a-zA-Z0-9\\-\\_\\.]+\\.)+([a-zA-Z]{2,4})$");
		
		return false;
	}	
	
	/** Verifie si le numero de telephone correspond au format
	 * @param tel
	 * @return
	 */
	public boolean verifTel (String tel){
		if( tel!=null && tel.trim().length()>0 )
			return tel.matches("^0\\d{9}$");
		
		return false;
	}
	
	/** Verifie si l'annee respecte le format
	 * @param annee
	 * @return
	 */
	public boolean verifAnnee (String annee){
		if( annee!=null && annee.trim().length()>0 )
			return annee.matches("^(19|20)\\d{2}$");
		
		return false;
	}
	
	/** Verifie si le mot de passe est bien confirmé
	 * @param mdp
	 * @param confMdp
	 * @return
	 */
	public boolean verifConfMDP (String mdp, String confMdp){
		if( mdp.equals(confMdp))
			return true;
		else{
			return false;
		}
	}
	
	
}

