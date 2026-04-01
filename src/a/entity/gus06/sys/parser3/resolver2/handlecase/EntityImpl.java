package a.entity.gus06.sys.parser3.resolver2.handlecase;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160813";}
	
	public static final String VALUE = "value";

	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		List list = (List) o[1];
		String rule = (String) o[2];
		
		
		String[] words = rule.split(" ");
		Map map = new HashMap();
	
		String key = null;
		List buff = null;
	
		for(String word:words)
		{
			if(b(word))
			{
				key = bb(word);
				buff = new ArrayList();
			}
			else
			{
				if(buff==null)
				{
					Map tag = nextTag(list);
					if(tag==null) throw new Exception("Failed to resolve rule: "+rule);
					if(!value(tag).equals(word)) throw new Exception("Failed to apply rule: "+rule);
				}
				else
				{
					Map tag = nextTag(list);
					while(tag!=null && !value(tag).equals(word))
					{
						buff.add(tag);
						tag = nextTag(list);
					}
			
					if(tag==null) throw new Exception("Failed to apply rule: "+rule);
					if(buff.isEmpty()) throw new Exception("Failed to apply rule: "+rule);
			
					map.put(key,t.t(buff));
				}
			}
		}
	
		if(buff==null) throw new Exception();
	
		Map tag = nextTag(list);
		while(tag!=null)
		{
			buff.add(tag);
			tag = nextTag(list);
		}
	
		if(!buff.isEmpty()) map.put(key,t.t(buff));
		return map;
	}
	
	
	
	
	
	private boolean b(String s)
	{return s.startsWith("[") && s.endsWith("]");}
	
	private String bb(String s)
	{return s.substring(1,s.length()-1);}
	
	
	private Map nextTag(List l)
	{
		if(l.isEmpty()) return null;
		return (Map) l.remove(0);
	}
	
	private Object value(Map m)
	{return m.get(VALUE);}
}
