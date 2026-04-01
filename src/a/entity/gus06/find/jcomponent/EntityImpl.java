package a.entity.gus06.find.jcomponent;

import a.framework.*;
import java.awt.Color;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141028";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof JComponent) return obj;
		if(obj instanceof I) return ((I) obj).i();
		if(obj instanceof G) return t(((G) obj).g());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
