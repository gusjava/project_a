package a.entity.gus06.swing.comp.cust.border.empty10;

import a.framework.*;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170513";}


	public EntityImpl() throws Exception
	{
	}
	
	public void p(Object obj) throws Exception
	{
		JComponent comp = (JComponent) obj;
		comp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	}
}
