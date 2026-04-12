package a.entity.gus06.convert.classtoentityname;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160507";}

	
	public Object t(Object obj) throws Exception
	{
		String name = toName(obj);
		if(!name.endsWith(".EntityImpl")) return null;
		if(!name.startsWith("a.entity.")) return null;
		
		return name.substring(9,name.length()-11);
	}
	
	
	private String toName(Object obj) throws Exception
	{
		if(obj instanceof Class) return ((Class) obj).getName();
		if(obj instanceof String) return (String) obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}