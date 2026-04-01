package a.entity.gus06.data.viewer.class1.introspector.listrenderer.finddisplay;

import a.framework.*;
import java.lang.reflect.*;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140820";}
	

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return " ";
		if(obj instanceof Method)
		{
			Method m = (Method) obj;
			return "UTIL_method"+mod(m.getModifiers())+"#"+m.getName();
		}
		if(obj instanceof Field)
		{
			Field f = (Field) obj;
			return "UTIL_field"+mod(f.getModifiers())+"#"+f.getName();
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String mod(int mod)
	{
		if(Modifier.isPublic(mod)) return "Public";
		if(Modifier.isProtected(mod)) return "Protected";
		if(Modifier.isPrivate(mod)) return "Private";
		return "";
	}
}
