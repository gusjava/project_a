package a.entity.gus06.swing.comp.setenabled;

import a.framework.*;
import java.awt.Component;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250517";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object comp = o[0];
		Boolean enabled = (Boolean) o[1];
		
		if(comp instanceof Component)	{((Component) comp).setEnabled(enabled);return;}
			
		throw new Exception("Invalid data type: "+comp.getClass().getName());
	}
}