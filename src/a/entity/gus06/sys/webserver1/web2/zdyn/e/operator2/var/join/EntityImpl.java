package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator2.var.join;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141025";}


	private Service findVar;
	
	public EntityImpl() throws Exception
	{findVar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		Map args = (Map) o[2];
		Map vars = (Map) o[3];
		
		return get(vars,info);
	}
	
	
	
	private String get(Map vars, String info) throws Exception
	{
		String[] n = info.split(":",2);
		String key = n[0];
		String delim = n.length==2?n[1]:" ";
		
		Object result = findVar.t(new Object[]{vars,key});
		if(result==null) return "null";
		return join(result,delim);
	}
	
	
	
	
	private String join(Object obj, String delim)
	{
		if(obj instanceof Object[]) return join((Object[]) obj,delim);
		if(obj instanceof List) return join((List) obj,delim);
		if(obj instanceof Set) return join((Set) obj,delim);
		if(obj instanceof Map) return join((Map) obj,delim);
		
		return "?";
	}
	
	private String join(Object[] array, String delim)
	{
		StringBuffer b = new StringBuffer();
		int number = array.length;
		for(int i=0;i<number;i++)
		{
			b.append(array[i]);
			if(i<number-1) b.append(delim);
		}
		return b.toString();
	}
	
	private String join(List list, String delim)
	{
		StringBuffer b = new StringBuffer();
		int number = list.size();
		for(int i=0;i<number;i++)
		{
			b.append(list.get(i));
			if(i<number-1) b.append(delim);
		}
		return b.toString();
	}
	
	private String join(Set set, String delim)
	{
		StringBuffer b = new StringBuffer();
		int number = set.size();
		ArrayList list = new ArrayList(set);
		Collections.sort(list);
		for(int i=0;i<number;i++)
		{
			b.append(list.get(i));
			if(i<number-1) b.append(delim);
		}
		return b.toString();
	}
	
	private String join(Map map, String delim)
	{
		StringBuffer b = new StringBuffer();
		int number = map.size();
		
		ArrayList list = new ArrayList(map.keySet());
		Collections.sort(list);
		for(int i=0;i<number;i++)
		{
			b.append(list.get(i));
			if(i<number-1) b.append(delim);
		}
		return b.toString();
	}
}
