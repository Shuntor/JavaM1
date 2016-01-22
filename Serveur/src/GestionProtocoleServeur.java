import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;



public class GestionProtocoleServeur extends Thread {
	
	private boolean fermeture;
	private Socket connexionCourante;
	private InputStream entreeSocket;
	private OutputStream sortieSocket;
	//private Gestion gestion;
	
	String chaine;
	
	public GestionProtocoleServeur(Socket connexionCourante, InputStream entreeSocket,
			OutputStream sortieSocket/*, Gestion gestion*/) {

		//this.gestion = gestion;
		this.chaine = null;
		this.connexionCourante = connexionCourante;
		this.entreeSocket = entreeSocket;
		this.sortieSocket = sortieSocket;
		this.fermeture = false;
	}
}
