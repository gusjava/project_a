package a.entity.gus06.list.string.stringtolist.builder2;

import a.framework.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141007";}
	
	public static final String DELIM = ";";
	
	
	public Object t(Object obj) throws Exception
	{
		String[] n = toArray(obj);
		if(n==null) return new ArrayList();
		return new ArrayList(Arrays.asList(n));
	}
	
	
	private String[] toArray(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj.equals("")) return null;
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return ((String) obj).split(DELIM);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
