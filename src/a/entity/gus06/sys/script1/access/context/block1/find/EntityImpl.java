package a.entity.gus06.sys.script1.access.context.block1.find;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151103";}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		String name = (String) o[1];
		
		Map execution = (Map) get1(context,"execution");
		Map currentTag = (Map) get1(execution,"current");
		Map stack = (Map) get1(currentTag,"stack");
		
		while(stack!=null)
		{
			Map block1 = (Map) get1(stack,"block1");
			Object element = get0(block1,name);
			if(element!=null) return element;
			
			stack = (Map) get0(stack,"parent");
		}
		return null;
	}
	
	
	
	
	private Object get0(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}
