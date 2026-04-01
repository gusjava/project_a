package a.entity.gus06.data.filter.ismany;

import a.framework.*;
import java.util.Collection;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20200112";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		
		if(obj instanceof Map) return ((Map) obj).size()>1;
		if(obj instanceof Collection) return ((Collection) obj).size()>1;
		if(obj instanceof String) return ((String) obj).length()>1;
		if(obj instanceof StringBuffer) return ((StringBuffer) obj).length()>1;
		if(obj instanceof StringBuilder) return ((StringBuilder) obj).length()>1;
		if(obj instanceof File) return empty((File) obj);
		
		if(obj instanceof Object[]) return ((Object[]) obj).length>1;
		if(obj instanceof boolean[]) return ((boolean[]) obj).length>1;
		if(obj instanceof byte[]) return ((byte[]) obj).length>1;
		if(obj instanceof char[]) return ((char[]) obj).length>1;
		if(obj instanceof int[]) return ((int[]) obj).length>1;
		if(obj instanceof long[]) return ((long[]) obj).length>1;
		if(obj instanceof double[]) return ((double[]) obj).length>1;
		if(obj instanceof float[]) return ((float[]) obj).length>1;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private boolean empty(File file)
	{
		if(file.isFile()) return false;
		File[] ff = file.listFiles();
		return ff==null?false:ff.length>1;
	}
}
