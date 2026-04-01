package a.entity.gus06.swing.table.selection.column.tostring;

import a.framework.*;
import javax.swing.JTable;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190506";}

	
	
	public Object t(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		
    		int[] columns = table.getSelectedColumns();
    		if(columns==null|| columns.length==0) return "";
		
		StringBuffer sb = new StringBuffer();
		
		int rowsNb = table.getRowCount();
		int columnsNb = columns.length;
		
		for(int i=0;i<rowsNb;i++)
		{
			int row = i;
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