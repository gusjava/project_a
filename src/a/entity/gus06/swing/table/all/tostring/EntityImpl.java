package a.entity.gus06.swing.table.all.tostring;

import a.framework.*;
import javax.swing.JTable;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191003";}

	
	
	public Object t(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		
		int rowCount = table.getRowCount();
		int columnCount = table.getColumnCount();

    		if(rowCount==0) return "";
    		if(columnCount==0) return "";
		
		StringBuffer sb = new StringBuffer();
		
		for(int i=0;i<rowCount;i++)
		{
			for(int j=0;j<columnCount;j++)
			{
				Object value = table.getValueAt(i,j); 
				sb.append(value);
				if(j<columnCount-1) sb.append("\t");
			}
			if(i<rowCount-1) sb.append("\n");
		}
		
		return sb.toString();
	}
}
