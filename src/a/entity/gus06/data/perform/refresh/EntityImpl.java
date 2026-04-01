package a.entity.gus06.data.perform.refresh;

import a.framework.*;
import javax.swing.JTable;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170412";}


	private Service performJTable;
	
	
	public EntityImpl() throws Exception
	{
		performJTable = Outside.service(this,"gus06.swing.table.handle.refresh");
	}


	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTable)
		{performJTable.p(obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
