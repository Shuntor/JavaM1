package STRI.JavaConnect;

public class Etudiant {
	
	    private String nom;
	    private String prenom;
	    private int anneeDip;
	    private String mail;
	    private int tel;
	    private String mdp;
	    
	   

	    public Etudiant(String nom, String prenom, int anneeDip, String mail, int tel, String mdp) {
	        this.nom=nom;
	        this.prenom=prenom;
	        this.anneeDip=anneeDip;
	        this.mail=mail;
	        this.tel=tel;
	        this.mdp=mdp;
	        
	    }
	    public Etudiant(String nom, String prenom, int anneeDip, String mail, int tel) {
	        this.nom=nom;
	        this.prenom=prenom;
	        this.anneeDip=anneeDip;
	        this.mail=mail;
	        this.tel=tel;
	        
	    }
	    public Etudiant(String mail) {
	    	this.nom="";
	        this.prenom="";
	        this.anneeDip=0;
	    	this.mail=mail;
	    	this.tel=0;
	    	this.mdp="root";
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




		public int getAnneeDip() {
			return anneeDip;
		}




		public void setAnneeDip(int anneeDip) {
			this.anneeDip = anneeDip;
		}




		public String getMail() {
			return mail;
		}




		public void setMail(String mail) {
			this.mail = mail;
		}




		public int getTel() {
			return tel;
		}




		public void setTel(int tel) {
			this.tel = tel;
		}

	    

	   

	   
	    
	    

}
