package STRI.JavaConnect;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
 
public class EcouteServeur extends Thread {
           
            
            static int port;
            static int taille;
            static byte buffer[];
            static DatagramSocket socket;
           

			public EcouteServeur(int port, int taille, byte[] buffer, DatagramSocket sSocket) {
				// TODO Auto-generated constructor stub
				this.port = port;
                this.taille = taille;
                this.buffer = buffer;
                this.socket=sSocket;
			}

			
           
			public void run(){
				String chaine2=null;
				System.out.println("Server2 en écoute!!");
				while (true) {
					DatagramPacket data = new DatagramPacket(buffer, buffer.length);
					try {
						socket.receive(data);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String message=new String (data.getData());
					String tabMessage[]= message.split("#");

					ArrayList<String> listeCo=new ArrayList<String>();
					
						Enumeration e=MainServeur.ht.keys();
						while(e.hasMoreElements()){
							String clef=(String)e.nextElement();
							listeCo.add(clef);
							listeCo.add((String) MainServeur.ht.get(clef));
						}
					
					System.out.println("listeCo(1)= "+listeCo.get(1));
					for (int t = 0; t < listeCo.size(); t++) {
						if (t==0){
							chaine2=listeCo.get(t)+"#";	
						}
						else{
							chaine2=chaine2+listeCo.get(t)+"#";

						}
					}//fin for
					InetAddress serveurIp = null;
					try {
						serveurIp = InetAddress.getByName("localhost");
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					System.out.println("chaine2="+chaine2);
					int length2 = chaine2.length(); 
					System.out.println(length2);
					byte buffer2[] = chaine2.getBytes(); 
					int port2=data.getPort();
					DatagramPacket dataSent = new DatagramPacket(buffer2,length2,serveurIp,port2); 
					DatagramSocket socket = null;
					try {
						socket = new DatagramSocket();
					} catch (SocketException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					} 

					try {
						socket.send(dataSent);
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}








				}
			}
}