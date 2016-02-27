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
import java.awt.Point;

public class VueTchat /*extends Thread*/ {

	private JDialog jDialogChat;
	GestionProtocoleClient gestion = new GestionProtocoleClient();
	JComboBox comboBoxUserCo;
	public static JTextArea textArea;
	private JTextField textField;
	private ArrayList<Utilisateur> listeUserCo=new ArrayList<>();
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
			
			
			JPanel panel = new JPanel();
			panel.setLocation(new Point(300, 0));
			panel.setBackground(new Color(128, 128, 128));
			panel.setBounds(0, 0, 465, 261);
			jDialogChat.getContentPane().add(panel);
			panel.setLayout(null);
			
			textArea = new JTextArea();
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
				System.out.println("dest = "+dest);
				System.out.println("listeUserCo.get(0).getMail()= "+listeUserCo.get(0).getMail());
				
				int p=rechercherPort(dest, listeUserCo);
				System.out.println("p= "+p);
				try {
					GestionProtocoleChat.envoyerMessage(p, message, Vue.getMailCo());
					System.out.println("Message bien envoyé!");
					String areaTemp=textArea.getText();
					textArea.setText(areaTemp+System.getProperty("line.separator")+"("+Vue.getMailCo()+")"+message);
					textArea_1.setText("");
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
			
			textField = new JTextField();
			textField.setBounds(379, 190, 76, 20);
			panel.add(textField);
			textField.setColumns(10);
			jDialogChat.setVisible(true);
			
			
		
	}
	
	public void rafraichireComboBox() throws IOException{
		String co=null;
		
		
		comboBoxUserCo.removeAllItems();
		co=gestion.recupCo();
		String tabCo[]=co.split("#");
		for (int i = 0; i < tabCo.length-1; i++) {
			if (i%2==0){
				System.out.println("i= "+i);
				System.out.println("tabCo(i)+(i+1) = "+tabCo[i]+" - "+tabCo[i+1]);
				comboBoxUserCo.addItem(tabCo[i]);
				Utilisateur user=new Utilisateur(tabCo[i], Integer.parseInt(tabCo[i+1]));
				System.out.println("user.getMail() = "+user.getMail());
				System.out.println("user.getPort() = "+user.getPort());
				listeUserCo.add(user);
			}
			
		}
		
		
	}
	
	public int rechercherPort(String mail, ArrayList<Utilisateur> liste){
		int port;
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i).getMail().startsWith(mail)){
				System.out.println("son port est : " +liste.get(i).getPort());
				return liste.get(i).getPort();	
			}
		}
		return 0;
		
	}
}

	
