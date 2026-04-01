package a.entity.gus06.swing.table.selection.tostring;

import a.framework.*;
import javax.swing.JTable;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190502";}

	
	
	public Object t(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		
		int[] rows = table.getSelectedRows();
    		int[] columns = table.getSelectedColumns();

    		if(rows==null || rows.length==0) return "";
    		if(columns==null|| columns.length==0) return "";
		
		StringBuffer sb = new StringBuffer();
		
		int rowsNb = rows.length;
		int columnsNb = columns.length;
		
		for(int i=0;i<rowsNb;i++)
		{
			int row = rows[i];
			for(int j=0;j<columnsNb;j++)
			{
				int column = columns[j];
				Object value = table.getValueAt(row,column); 
				sb.append(value);
				
				if(j<columnsNb-1) sb.append("\t");
			}
			if(i<rowsNb-1) sb.append("\n");
		}
		
		return sb.toString();
	}
}
