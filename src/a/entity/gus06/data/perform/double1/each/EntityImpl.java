package a.entity.gus06.data.perform.double1.each;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170212";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List) return doubleEach((List) obj);
		if(obj instanceof String) return doubleEach((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private String doubleEach(String s)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			b.append(c);
			b.append(c);
		}
		return b.toString();
	}
	
	private List doubleEach(List l)
	{
		List l1 = new ArrayList();
		for(Object o:l)
		{
			l1.add(o);
			l1.add(o);
		}
		return l1;
	}
}
