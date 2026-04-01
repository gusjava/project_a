package a.entity.gus06.swing.comp.find;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190519";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof JComponent) return obj;
		if(obj instanceof I) return ((I) obj).i();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
