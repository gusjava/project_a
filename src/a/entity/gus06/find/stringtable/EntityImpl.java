package a.entity.gus06.find.stringtable;

import a.framework.*;
import javax.swing.table.TableModel;
import javax.swing.JTable;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150702";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof String[][]) return obj;
		if(obj instanceof JTable) return fromTableModel(((JTable) obj).getModel());
		if(obj instanceof TableModel) return fromTableModel((TableModel) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String[][] fromTableModel(TableModel model)
	{
		int x = model.getRowCount();
        	int y = model.getColumnCount();
        	String[][] tab = new String[x][y];

        	for(int i=0;i<x;i++) for(int j=0;j<y;j++)
		tab[i][j] = (String) model.getValueAt(i,j);           
		
		return tab;
	}
}
