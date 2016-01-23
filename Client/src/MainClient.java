import java.io.*;
import java.net.*;

public class MainClient {

	public static void main(String args[]) {
		String requete, reponse=null;
		BufferedReader fluxEntreeStandard;
		Socket leSocket;
		PrintStream fluxSortieSocket;
		BufferedReader fluxEntreeSocket;
		boolean continuer = true;
		Vue gestionClient = new Vue();

		try {
			fluxEntreeStandard = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Bonjour");

			leSocket = new Socket("127.0.0.1", 50000); // @IP du serveur

			System.err.println("Connecté sur : " + leSocket);

			fluxSortieSocket = new PrintStream(leSocket.getOutputStream());
			fluxEntreeSocket = new BufferedReader(new InputStreamReader(leSocket.getInputStream()));

			while (continuer) {

				System.out.println("requete? ");

				requete = gestionClient.traitement();
				if (requete != null) {
					fluxSortieSocket.println(requete);

					String retour = fluxEntreeSocket.readLine();

					System.out.println("Reponse du serveur : " + retour);
				}
				System.out.println("continuer? \"oui\"/\"non\"");
				reponse = fluxEntreeStandard.readLine();
				switch (reponse) {
				case "non":
					continuer = false;
					System.out.println("fin...");
					break;
				default:
					System.out.println("continuer...");
					break;
				}
			}
			leSocket.close();
		} catch (UnknownHostException ex) {
			System.err.println("Machine inconnue : " + ex);
			ex.printStackTrace();
		} catch (IOException ex) {
			System.err.println("Erreur : " + ex);
			ex.printStackTrace();
		}
	}

}
