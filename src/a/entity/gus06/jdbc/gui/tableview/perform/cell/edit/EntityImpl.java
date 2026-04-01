package a.entity.gus06.jdbc.gui.tableview.perform.cell.edit;

import a.framework.*;
import javax.swing.JTable;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190502";}
	
	public static final String TITLE = "Cell value editor";


	private Service update;
	private Service input;

	public EntityImpl() throws Exception
	{
		update = Outside.service(this,"gus06.jdbc.gui.tableview.tool.cell.update");
		input = Outside.service(this,"gus06.jdbc.gui.tableview.perform.cell.edit.dialog");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		Object[] data = (Object[]) o[1];
		
		Object initValue = getSelectionValue(table);
		String initValueS = initValue!=null ? ""+initValue : "";
		
		G valueWrapper = (G) input.t(new String[]{TITLE,initValueS});
		if(valueWrapper==null) return false;
		
		String newValue = (String) valueWrapper.g();
		return update.f(new Object[]{table,data,newValue});
	}
	
	
	private Object getSelectionValue(JTable table)
	{
		int row = table.getSelectedRow();
    		int column = table.getSelectedColumn();
		return table.getValueAt(row,column);
	}
}