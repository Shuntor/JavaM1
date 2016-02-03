import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

class MyWindowListener implements WindowListener {
  GestionProtocoleClient gestion=new GestionProtocoleClient();
	@Override
  public void windowClosing(WindowEvent arg0) {
	try {
		gestion.arret();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    System.exit(0);
  }


@Override
public void windowOpened(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowClosed(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowIconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeiconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowActivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeactivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

}
