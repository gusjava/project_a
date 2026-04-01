package a.entity.gus06.swing.table.cust.renderer.default1;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180505";}

	private Service build;
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.swing.table.renderer.default1");
	}
	
	public void p(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		TableCellRenderer renderer = (TableCellRenderer) build.g();
		table.setDefaultRenderer(Object.class,renderer);
	}
}
