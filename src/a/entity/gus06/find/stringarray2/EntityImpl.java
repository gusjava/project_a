package a.entity.gus06.find.stringarray2;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import javax.swing.JTable;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}


	private Service fromArray2;
	private Service fromDoubleArray2;
	private Service fromLongArray2;
	private Service fromIntArray2;
	private Service fromBooleanArray2;
	private Service fromList;
	
	public EntityImpl() throws Exception
	{
		fromArray2 = Outside.service(this,"gus06.convert.objarray2tostringarray2");
		fromDoubleArray2 = Outside.service(this,"gus06.convert.doublearray2tostringarray2");
		fromLongArray2 = Outside.service(this,"gus06.convert.longarray2tostringarray2");
		fromIntArray2 = Outside.service(this,"gus06.convert.intarray2tostringarray2");
		fromBooleanArray2 = Outside.service(this,"gus06.convert.booleanarray2tostringarray2");
		fromList = Outside.service(this,"gus06.convert.listtostringarray2");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof String[][]) return obj;
		if(obj instanceof Object[][]) return fromArray2.t(obj);
		
		if(obj instanceof double[][]) return fromDoubleArray2.t(obj);
		if(obj instanceof long[][]) return fromLongArray2.t(obj);
		if(obj instanceof int[][]) return fromIntArray2.t(obj);
		if(obj instanceof boolean[][]) return fromBooleanArray2.t(obj);
		
		if(obj instanceof JTable) return fromTableModel(((JTable) obj).getModel());
		if(obj instanceof TableModel) return fromTableModel((TableModel) obj);
		if(obj instanceof List) return fromList.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	//TODO faire une entit� convert
	//TODO prendre en compte aussi pour Object[][]
	
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
