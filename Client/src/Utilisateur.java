
public class Utilisateur {
	private String mail;
    private int port;
    private String adresse="localhost";
    
    public Utilisateur(String mail, int port) {
        this.mail=mail;
        this.port=port;
        
    }

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
    
    
}
