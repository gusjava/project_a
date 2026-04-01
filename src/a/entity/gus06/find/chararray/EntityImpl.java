package a.entity.gus06.find.chararray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof char[]) return obj;
		if(obj instanceof String) return ((String) obj).toCharArray();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
