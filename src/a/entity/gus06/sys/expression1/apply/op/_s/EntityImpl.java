package a.entity.gus06.sys.expression1.apply.op._s;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.awt.Rectangle;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170203";}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

	private Service handleRectangle;
	
	public EntityImpl() throws Exception
	{
		handleRectangle = Outside.service(this,"gus06.tostring.rectangle");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return "null";
		
		if(obj instanceof Object[]) return handleArray((Object[]) obj);
		if(obj instanceof List) return handleList((List) obj);
		if(obj instanceof Set) return handleSet((Set) obj);
		if(obj instanceof Map) return handleMap((Map) obj);
		
		if(obj instanceof Rectangle) return handleRectangle.t(obj);
		if(obj instanceof Number) return handleNumber((Number) obj);
		if(obj instanceof Date) return handleDate((Date) obj);
		
		return obj.toString();
	}
	
	
	private List handleArray(Object[] array)
	{
		List l = new ArrayList();
		for(int i=0;i<array.length;i++)
		l.add(handleElement(array[i]));
		return l;
	}
	
	private List handleList(List list)
	{
		List l = new ArrayList();
		for(int i=0;i<list.size();i++)
		l.add(handleElement(list.get(i)));
		return l;
	}
	
	private Set handleSet(Set set)
	{
		Set s = new HashSet();
		Iterator it = set.iterator();
		while(it.hasNext())
		s.add(handleElement(it.next()));
		return s;
	}
	
	private Map handleMap(Map map)
	{
		Map m = new HashMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map.get(key);
			m.put(handleElement(key),handleElement(value));
		}
		return m;
	}
	
	private String handleElement(Object obj)
	{
		if(obj==null) return "null";
		return obj.toString();
	}
	
	private String handleNumber(Number n)
	{
		String s = n.toString();
		if(!s.contains(".")) return s;
		
		while(s.endsWith("0")) s = s.substring(0, s.length()-1);
		if(s.endsWith(".")) s = s.substring(0, s.length()-1);
		
		return s;
	}
	
	private String handleDate(Date date) throws Exception
	{
		String s = sdf.format(date);
		if(s.endsWith(" 00:00:00")) return s.substring(0,10);
		return s;
	}
}