import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
 
public class Ecoute extends Thread {
           
            private boolean fermeture=false;
            static int port = 8532;
            static int taille = 1024;
            static byte buffer[] = new byte[taille];
            static DatagramSocket socket=null;
           
            public Ecoute( int port, int taille, byte buffer[], DatagramSocket chatSocket) {
 
 
                       
                        this.port = port;
                        this.taille = taille;
                        this.buffer = buffer;
                        this.socket=chatSocket;
                       
                       
            }
           
            public void run(){
                        System.out.println("Client en écoute!! sur le port: "+socket.getLocalPort());
                        while (true) {
                        	DatagramPacket data = new DatagramPacket(new byte[1024],1024); 
                                   try {
                                               socket.receive(data);
                                   } catch (IOException e) {
                                               // TODO Auto-generated catch block
                                               e.printStackTrace();
                                   }
                                   
                                   String message=new String (data.getData());
                                   System.out.println("messsage reçu du client: "+message);
                                   String tabMessage[]= message.split("#");
                                   System.out.println("tabMessage[0] = "+tabMessage[0]);
                                   System.out.println("tabMessage[1] = "+tabMessage[1]);
                                   String area=VueTchat.textArea.getText();
                                   VueTchat.textArea.removeAll();
                                   VueTchat.textArea.setText(area+System.getProperty("line.separator")+"("+tabMessage[0]+")"+tabMessage[1]);
                                   
                                   
                                   
                                  
                                  
                                  
                        }
            }
}