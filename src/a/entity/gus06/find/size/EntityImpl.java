package a.entity.gus06.find.size;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220421";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return 0;
		if(obj instanceof Integer) return ((Integer) obj).intValue();
		
		if(obj instanceof List) return ((List) obj).size();
		if(obj instanceof String) return ((String) obj).length();
		if(obj instanceof StringBuffer) return ((StringBuffer) obj).length();
		if(obj instanceof StringBuilder) return ((StringBuilder) obj).length();
		if(obj instanceof Object[]) return ((Object[]) obj).length;
		
		if(obj instanceof byte[]) return ((byte[]) obj).length;
		if(obj instanceof boolean[]) return ((boolean[]) obj).length;
		if(obj instanceof char[]) return ((char[]) obj).length;
		if(obj instanceof short[]) return ((short[]) obj).length;
		if(obj instanceof boolean[]) return ((boolean[]) obj).length;
		if(obj instanceof int[]) return ((int[]) obj).length;
		if(obj instanceof long[]) return ((long[]) obj).length;
		if(obj instanceof double[]) return ((double[]) obj).length;
		if(obj instanceof float[]) return ((float[]) obj).length;
	
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}