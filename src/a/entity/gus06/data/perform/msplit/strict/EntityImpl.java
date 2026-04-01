package a.entity.gus06.data.perform.msplit.strict;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200504";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length==2) return handle(o[0],(String) o[1]);
		if(o.length==3) return handle(o[0],(String) o[1],(String) o[2]);
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	
	private Map handle(Object input, String glue1) throws Exception
	{
		if(input instanceof String[])
			return split((String[]) input,glue1);
		if(input instanceof List)
			return split((List) input,glue1);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	private Map handle(Object input, String glue1, String glue2) throws Exception
	{
		if(input instanceof String)
			return split((String) input,glue1,glue2);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	private Map split(String s, String glue1, String glue2) throws Exception
	{
		Map map = new HashMap();
		String[] nn = s.split(Pattern.quote(glue2),-1);
		for(String n:nn)
		{
			String[] kk = n.split(Pattern.quote(glue1),2);
			String key = kk[0];
			String value = kk.length==2?kk[1]:"";
			addToMap(map,key,value);
		}
		return map;
	}
	
	
	private Map split(String[] nn, String glue1) throws Exception
	{
		Map map = new HashMap();
		for(String n:nn)
		{
			String[] kk = n.split(Pattern.quote(glue1),2);
			String key = kk[0];
			String value = kk.length==2?kk[1]:"";
			addToMap(map,key,value);
		}
		return map;
	}
	
	
	private Map split(List nn, String glue1) throws Exception
	{
		Map map = new HashMap();
		for(Object n:nn)
		{
			String[] kk = n.toString().split(Pattern.quote(glue1),2);
			String key = kk[0];
			String value = kk.length==2?kk[1]:"";
			addToMap(map,key,value);
		}
		return map;
	}
	
	
	private void addToMap(Map map, String key, String value) throws Exception
	{
		if(map.containsKey(key)) 
		throw new Exception("Key found twice: ["+key+"="+map.get(key)+"] & ["+key+"="+value+"]");
		
		map.put(key,value);
	}
}
