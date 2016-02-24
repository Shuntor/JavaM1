import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.activation.MailcapCommandMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VueTchat extends Thread {

	private JDialog jDialogChat;
	GestionProtocoleClient gestion = new GestionProtocoleClient();
	JComboBox comboBoxUserCo;
	private static JTextArea textArea;
	
	public static JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public void run() {
			jDialogChat = new JDialog();
			jDialogChat.setMinimumSize(new java.awt.Dimension(400, 300));
			jDialogChat.setLocationRelativeTo(null);
			jDialogChat.setModal(false);
			jDialogChat.setTitle("Chat avec : ");
			jDialogChat.getContentPane().setLayout(null);
			ArrayList<Utilisateur> listeUserCo=new ArrayList<>();
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(128, 128, 128));
			panel.setBounds(0, 0, 384, 261);
			jDialogChat.getContentPane().add(panel);
			panel.setLayout(null);
			
			JTextArea textArea = new JTextArea();
			textArea.setDisabledTextColor(new Color(109, 109, 109));
			textArea.setEditable(false);
			textArea.setBounds(10, 11, 364, 143);
			panel.add(textArea);
			
			JTextArea textArea_1 = new JTextArea();
			textArea_1.setBorder(new LineBorder(new Color(30, 144, 255)));
			textArea_1.setBounds(10, 165, 265, 85);
			panel.add(textArea_1);
			
			JButton btnEnvoyer = new JButton("Envoyer");
			btnEnvoyer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				String message=textArea_1.getText();
				String dest=(String) comboBoxUserCo.getSelectedItem();
				int p=rechercherPort(dest, listeUserCo);
				try {
					GestionProtocoleChat.envoyerMessage(p, message, Vue.getMailCo());
					System.out.println("Message bien envoyé!");
					textArea.setText("("+Vue.getMailCo()+")"+message);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.err.println("error Socket UDP");
					textArea.setText("Impossible de contacter cette personne, rafraichissez!");
				}
				}
			});
			btnEnvoyer.setBounds(285, 214, 89, 36);
			panel.add(btnEnvoyer);
			
			comboBoxUserCo = new JComboBox();
			comboBoxUserCo.setBounds(285, 190, 89, 20);
			panel.add(comboBoxUserCo);
			
			JButton btnRefresh = new JButton("Refresh");
			btnRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						rafraichireComboBox();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnRefresh.setBounds(285, 165, 89, 23);
			panel.add(btnRefresh);
			jDialogChat.setVisible(true);
			
			
		
	}
	
	public ArrayList<Utilisateur> rafraichireComboBox() throws IOException{
		String co=null;
		ArrayList<Utilisateur> listeUserCo = new ArrayList<Utilisateur>();
		
		comboBoxUserCo.removeAll();
		co=gestion.recupCo();
		String tabCo[]=co.split("#");
		for (int i = 0; i < tabCo.length; i++) {
			if (i%2==0){
				comboBoxUserCo.addItem(tabCo[i]);
				Utilisateur user=new Utilisateur(tabCo[i], Integer.getInteger(tabCo[i+1]));
				listeUserCo.add(user);
			}
			
		}
		return listeUserCo;
		
	}
	
	public int rechercherPort(String mail, ArrayList<Utilisateur> liste){
		int port;
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).getMail().startsWith(mail)){
				return liste.get(i).getPort();	
			}
		}
		return 0;
		
	}
}

	
