import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;



public final class GestionProtocoleChat {

	GestionProtocoleClient gestion=new GestionProtocoleClient();
	final static int taille = 1024; 
	final static byte buffer[] = new byte[taille];
	
	public static void envoyerMessage(int port,String message, String mailCo) throws IOException{
		  message=mailCo+"#"+message;
		  System.out.println("message = "+ message);
		  int length=message.length();
		  byte buffer[]=message.getBytes();
		  InetAddress adresseIp = InetAddress.getByName("localhost"); 
		  DatagramPacket dataSent = new DatagramPacket(buffer,length,adresseIp,port); 
	      DatagramSocket socket = new DatagramSocket(); 
	      System.out.println("port: "+port);
	      System.out.println("adresseIp: "+adresseIp);
	      System.out.println("length: "+length);
	      socket.send(dataSent); 

		  
	}
	
	
	
}


//byte[] receiveData = new byte[1024];             
//byte[] sendData = new byte[1024]; 
//DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//chatSocket.receive(receivePacket);