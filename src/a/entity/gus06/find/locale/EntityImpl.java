package a.entity.gus06.find.locale;

import a.framework.*;
import java.util.Locale;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160421";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Locale) return obj;
		if(obj instanceof String) return new Locale((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
