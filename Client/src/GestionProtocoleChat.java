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
		  int length=message.length();
		  byte buffer[]=message.getBytes();
		  InetAddress adresseIp = InetAddress.getByName("localhost"); 
		  DatagramPacket dataSent = new DatagramPacket(buffer,length,port); 
	      DatagramSocket socket = new DatagramSocket(); 
	  
	      socket.send(dataSent); 
////////////////////////////////////////
	      DatagramPacket dataRecieved = new DatagramPacket(new byte[length],length); 
	      socket.receive(dataRecieved); 
	      System.out.println("Data recieved : " + new String(dataRecieved.getData())); 
	      System.out.println("From : " + dataRecieved.getAddress() + ":" + dataRecieved.getPort());
		  
	}
	
	
	
}


//byte[] receiveData = new byte[1024];             
//byte[] sendData = new byte[1024]; 
//DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//chatSocket.receive(receivePacket);