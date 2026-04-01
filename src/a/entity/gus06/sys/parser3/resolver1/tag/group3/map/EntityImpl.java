package a.entity.gus06.sys.parser3.resolver1.tag.group3.map;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}


	private Service cutMethod1;
	private Service cutMethod2;
	private Service buildMap;

	public EntityImpl() throws Exception
	{
		cutMethod1 = Outside.service(this,"gus06.sys.parser3.cut.symbol.a1");
		cutMethod2 = Outside.service(this,"gus06.sys.parser3.cut.symbol.b1");
		buildMap = Outside.service(this,"gus06.map.factory.silentmap");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List l = (List) o[0];
		T t = (T) o[1];
		
		Map result = (Map) buildMap.g();
		
		List cut = (List) cutMethod1.t(new Object[]{l,","});
		if(cut==null)
		{
			addToMap(result,l,t);
		}
		else
		{
			int number = cut.size();
			for(int i=0;i<number;i++)
			{
				List x = (List) cut.get(i);
				addToMap(result,x,t);
			}
		}
		return result;
	}
	
	
	
	private void addToMap(Map map, List l, T t) throws Exception
	{
		List cut = (List) cutMethod2.t(new Object[]{l,":"});
		if(cut==null) throw new Exception("Invalid element inside map");
		
		Object key = t.t(cut.get(0));
		Object value = t.t(cut.get(1));
		put(map,key,value);
	}
	
	
	
	private void put(Map map, Object key, Object value)
	{
		if(key!=null && value!=null) map.put(key,value);
	}
}
