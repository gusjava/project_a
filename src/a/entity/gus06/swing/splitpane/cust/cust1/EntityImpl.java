package a.entity.gus06.swing.splitpane.cust.cust1;

import a.framework.*;
import javax.swing.JSplitPane;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140917";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		JSplitPane split = (JSplitPane) obj;
		split.setDividerSize(3);
		split.setDividerLocation(0.5);
	}
}