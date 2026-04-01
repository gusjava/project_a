package a.entity.gus06.find.chararray2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof char[][]) return obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
