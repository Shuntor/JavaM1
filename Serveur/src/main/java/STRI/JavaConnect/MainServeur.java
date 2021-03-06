package STRI.JavaConnect;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Hashtable;



/**
 * @author Iungmann Vaurigaud Hernandez
 *
 */
public class MainServeur {
	
	/* Port par defaut */
	public final static int portEcho = 50000;
	public static Hashtable ht = new Hashtable();
	
	//socketEcoute:
	public static int port = 9000;
	public static int taille = 1024;
	public static byte buffer[] = new byte[taille];
	public static DatagramSocket sSocket=null;
	
	public static void main(String[] args) throws SocketException {
		ServerSocket leServeur=null;
		Socket connexionCourante;
		InputStream entreeSocket;
		OutputStream sortieSocket;
		
		
		
        sSocket = new DatagramSocket(port);
		
		EcouteServeur socketEcoute=new EcouteServeur (port, taille, buffer, sSocket);
		socketEcoute.start();
		
		try {
			leServeur = new ServerSocket(portEcho);
		} catch (IOException ex) {
			// fin de connexion
			System.err.println("Impossible de creer un socket serveur sur ce port : "+ ex);
			try {
				// on demande un port anonyme
				leServeur = new ServerSocket(0);
			} catch (IOException ex2) {
				// fin de connexion
				System.err.println("Impossible de creer un socket serveur : "+ ex);
			}
		}
		
		if (leServeur != null) {
			try {
				System.err.println("En attente de connexion sur le port : "+ leServeur.getLocalPort());
				while (true) {
					
					connexionCourante = leServeur.accept();					
					System.err.println("Nouvelle connexion : "+ connexionCourante);
					
					entreeSocket = connexionCourante.getInputStream();
					sortieSocket = connexionCourante.getOutputStream();
					//Switch case
					Traitement traitement1 = new Traitement(connexionCourante,entreeSocket,sortieSocket, ht);					
					traitement1.start();					
				}
			} catch (Exception ex) {
				// erreur de connexion
				System.err.println("Une erreur est survenue : " + ex);
				ex.printStackTrace();
				
			}
		}
	}

}
