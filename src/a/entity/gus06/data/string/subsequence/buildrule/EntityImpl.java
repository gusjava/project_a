package a.entity.gus06.data.string.subsequence.buildrule;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180515";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) return obj;
		if(obj instanceof Integer) return ""+obj;
		if(obj instanceof int[]) return intArrayToRule((int[]) obj);
		if(obj instanceof List) return listToRule((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String intArrayToRule(int[] array) throws Exception
	{
		if(array.length==1) return ""+array[0];
		if(array.length==2) return array[0]+".."+array[1];
		
		throw new Exception("Invalid array length: "+array.length);
	}
	
	private String listToRule(List list) throws Exception
	{
		if(list.size()==1) return ""+list.get(0);
		if(list.size()==2) return list.get(0)+".."+list.get(1);
		
		throw new Exception("Invalid list length: "+list.size());
	}
}
