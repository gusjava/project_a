package a.entity.gus06.data.filter.isempty;

import a.framework.*;
import java.util.Collection;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20141021";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return true;
		
		if(obj instanceof Map) return ((Map) obj).size()==0;
		if(obj instanceof Collection) return ((Collection) obj).size()==0;
		if(obj instanceof String) return ((String) obj).length()==0;
		if(obj instanceof StringBuffer) return ((StringBuffer) obj).length()==0;
		if(obj instanceof StringBuilder) return ((StringBuilder) obj).length()==0;
		if(obj instanceof File) return empty((File) obj);
		
		if(obj instanceof Object[]) return ((Object[]) obj).length==0;
		if(obj instanceof boolean[]) return ((boolean[]) obj).length==0;
		if(obj instanceof byte[]) return ((byte[]) obj).length==0;
		if(obj instanceof char[]) return ((char[]) obj).length==0;
		if(obj instanceof int[]) return ((int[]) obj).length==0;
		if(obj instanceof long[]) return ((long[]) obj).length==0;
		if(obj instanceof double[]) return ((double[]) obj).length==0;
		if(obj instanceof float[]) return ((float[]) obj).length==0;
		
		return false;
	}
	
	
	
	
	private boolean empty(File file)
	{
		if(file.isFile()) return file.length()==0;
		File[] ff = file.listFiles();
		return ff==null?true:ff.length==0;
	}
}