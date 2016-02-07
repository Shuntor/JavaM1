/**
 * 
 */

/**
 * @author quent
 *
 */
public class VerifSaisie {
	
	public boolean verifMail(String email){
		if( email!=null && email.trim().length()>0 )
			return email.matches("^[a-zA-Z0-9\\.\\-\\_]+@([a-zA-Z0-9\\-\\_\\.]+\\.)+([a-zA-Z]{2,4})$");
		
		return false;
	}	
	
	public boolean verifTel (String tel){
		if( tel!=null && tel.trim().length()>0 )
			return tel.matches("^63\\d{8}$");
		
		return false;
	}
	
	public boolean verifAnnee (String annee){
		if( annee!=null && annee.trim().length()>0 )
			return annee.matches("^(19|20)\\d{2}$");
		
		return false;
	}
	
}

