package a.entity.gus06.swing.splitpane.cust3.divider;

import a.framework.*;
import javax.swing.JSplitPane;
import java.awt.Dimension;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191204";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JSplitPane split = (JSplitPane) o[0];
		Object value = o[1];
		
		if(value instanceof Integer) 
			split.setDividerLocation(((Integer) value).intValue());
		else if(value instanceof Double) 
			split.setDividerLocation(((Double) value).doubleValue());
		else if(value instanceof int[])
		{
			int[] n = (int[]) value;
			split.setDividerLocation(n[0]);
			split.setDividerSize(n[1]);
		}
	}
}
