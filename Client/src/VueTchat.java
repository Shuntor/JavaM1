import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;

public class VueTchat extends Thread {

	private JDialog jDialogChat;
	GestionProtocoleClient gestion = new GestionProtocoleClient();
	JComboBox comboBoxUserCo;
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
			btnEnvoyer.setBounds(285, 214, 89, 36);
			panel.add(btnEnvoyer);
			
			comboBoxUserCo = new JComboBox();
			comboBoxUserCo.setBounds(285, 190, 89, 20);
			panel.add(comboBoxUserCo);
			
			JButton btnRefresh = new JButton("Refresh");
			btnRefresh.setBounds(285, 165, 89, 23);
			panel.add(btnRefresh);
			jDialogChat.setVisible(true);
			
			
		
	}
	
	public void rafraichierComboBox() throws IOException{
		String co=null;
		
		comboBoxUserCo.removeAll();
		co=gestion.recupCo();
		String tabCo[]=co.split("#");
		for (int i = 0; i < tabCo.length; i++) {
			if (i%2==0){
				comboBoxUserCo.addItem(tabCo[i]);
			}
		}
		
		
	}
}

	
