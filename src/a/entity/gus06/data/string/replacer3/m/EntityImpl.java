package a.entity.gus06.data.string.replacer3.m;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161029";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String line = (String) o[0];
		String s1 = (String) o[1];
		T t = (T) o[2];
		
		Pattern p = Pattern.compile(s1);
		Matcher m = p.matcher(line);
		
		List list = new ArrayList();
		while(m.find())
		{
			String s = m.group();
			
			Map map = new HashMap();
			list.add(map);
			
			map.put("g",buildGroups(m));
			map.put("s",s);
			map.put("start",Integer.valueOf(m.start()));
			map.put("end",Integer.valueOf(m.end()));
			map.put("length",Integer.valueOf(s.length()));
		}
		
		int total = list.size();
		for(int i=0;i<total;i++)
		{
			boolean first = i==0;
			boolean last = i==total-1;
			
			Map map = (Map) list.get(i);
			
			map.put("first",Boolean.valueOf(first));
			map.put("last",Boolean.valueOf(last));
			map.put("index",Integer.valueOf(i));
			map.put("total",Integer.valueOf(total));
			
			map.put("r",list);
			if(!first) map.put("p",list.get(i-1));
			if(!last) map.put("n",list.get(i+1));
		}
		
		StringBuffer b = new StringBuffer();
		int offset = 0;
		
		for(int i=0;i<total;i++)
		{
			Map map = (Map) list.get(i);
			String g1 = toString(t.t(map));
			int start = ((Integer) map.get("start")).intValue();
			int end = ((Integer) map.get("end")).intValue();
			
			b.append(line.substring(offset,start));
			b.append(g1);
			
			offset = end;
		}
		
		b.append(line.substring(offset,line.length()));
		return b.toString();
	}
	
	
	private List buildGroups(Matcher m)
	{
		List list = new ArrayList();
		for(int i=0;i<=m.groupCount();i++)
		{
			Map map = new HashMap();
			map.put("s",m.group(i));
			map.put("start",Integer.valueOf(m.start(i)));
			map.put("end",Integer.valueOf(m.end(i)));
			
			list.add(map);
		}
		return list;
	}
	
	private String toString(Object obj) throws Exception
	{
		if(obj==null) return "null";
		if(obj instanceof String) return ""+obj;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof Boolean) return ""+obj;
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}