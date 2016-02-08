import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel {
	 
		

	/**
	 * @author Iungmann Vaurigaud Hernandez
	 *
	 */
	
		private List<Etudiant> listeEtu = new ArrayList<>();

	    public TableModel() {
	        listeEtu = new ArrayList<>();
	    }

	    @Override
	    public int getColumnCount() {
	        return 3;

	    }

	    /*public void setValueAt(Object value, int row, int col) {
	     data[row][col] = value;
	     fireTableCellUpdated(row, col);
	     }*/
	    @Override
	    public String getColumnName(int a) {
	        if (a == 0) {
	            return "Nom";
	        }
	        if (a == 1) {
	            return "Prenom";
	        }
	        if (a == 2){
	        	return "Adresse e-mail";
	        }
	        else
	        
	        return null;
	        
	    }

	    @Override
	    public int getRowCount() {
	        if (listeEtu == null) {
	            return 3;
	        }
	        return listeEtu.size();
	    }

	    @Override
	    public Object getValueAt(int row, int column) {        
	        switch(column){
	            case 0:
	                return listeEtu.get(row).getNom();
	            case 1:
	                return listeEtu.get(row).getPrenom();
	            case 2:
	            	return listeEtu.get(row).getMail();
	            }
	                
	           /* return lcl.get(row).getAdresse();
	            return lcl.get(row).getTaille();*/
	        return null;
	            
	    }
	        
	    

	    public void addEtudiant(Etudiant etu) {
	        listeEtu.add(etu);
	        fireTableStructureChanged();
	    }
	    
	    public void supprEtudiant(int row) {
	        listeEtu.remove(row);
	        fireTableStructureChanged();
	    }
	    
	    
}
