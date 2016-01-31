package STRI.JavaConnect;

public class Etudiant {
	
	    private String nom;
	    private String prenom;
	    private String anneeDip;
	    private String mail;
	    private String tel;
	    
	    
	   

	    public Etudiant(String nom, String prenom, String anneeDip, String mail, String tel) {
	        this.nom=nom;
	        this.prenom=prenom;
	        this.anneeDip=anneeDip;
	        this.mail=mail;
	        this.tel=tel;
	        
	    }




		public String getNom() {
			return nom;
		}




		public void setNom(String nom) {
			this.nom = nom;
		}




		public String getPrenom() {
			return prenom;
		}




		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}




		public String getAnneeDip() {
			return anneeDip;
		}




		public void setAnneeDip(String anneeDip) {
			this.anneeDip = anneeDip;
		}




		public String getMail() {
			return mail;
		}




		public void setMail(String mail) {
			this.mail = mail;
		}




		public String getTel() {
			return tel;
		}




		public void setTel(String tel) {
			this.tel = tel;
		}

	    

	   

	   
	    
	    

}
