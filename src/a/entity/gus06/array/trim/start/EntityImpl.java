package a.entity.gus06.array.trim.start;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170301";}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] input = (Object[]) obj;
		
		int start = findStart(input);
		if(start==-1) return new ArrayList();
		
		List output = new ArrayList();
		for(int i=start;i<input.length;i++) output.add(input[i]);
		
		return output;
	}
	
	
	private int findStart(Object[] array) throws Exception
	{
		int nb = array.length;
		for(int i=0;i<nb;i++)
		if(!isEmpty(array[i])) return i;
		return -1;
	}
	
	private boolean isEmpty(Object obj) throws Exception
	{
		if(obj==null) return true;
		if(obj instanceof String) return ((String)obj).trim().equals("");
		if(obj instanceof Object[]) return ((Object[])obj).length==0;
		if(obj instanceof Collection) return ((Collection)obj).isEmpty();
		if(obj instanceof Map) return ((Map)obj).isEmpty();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
