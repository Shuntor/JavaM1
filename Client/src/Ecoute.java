import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Ecoute extends Thread {
	
	private boolean fermeture=false;
	private Socket connexionCourante;
	private InputStream entreeSocket;
	private OutputStream sortieSocket;
	
	public Ecoute(Socket connexionCourante, InputStream entreeSocket,
			OutputStream sortieSocket) {


		
		this.connexionCourante = connexionCourante;
		this.entreeSocket = entreeSocket;
		this.sortieSocket = sortieSocket;
		
		
		
	}
	
	public void run(){
		System.out.println("Client en écoute!!");
		while (true) {
		
			try {
				connexionCourante = GestionProtocoleClient.leServeur.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
			System.err.println("Nouvelle connexion : "+ connexionCourante);
		
			try {
				entreeSocket = connexionCourante.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				sortieSocket = connexionCourante.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}
}
