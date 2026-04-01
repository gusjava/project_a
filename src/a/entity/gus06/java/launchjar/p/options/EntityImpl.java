package a.entity.gus06.java.launchjar.p.options;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190522";}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		String[] options = toStringArray(o[1]);
		
		if(options!=null)
		for(String option:options)
		{
			if(!option.startsWith("-")) list.add("-"+option);
			else list.add(option);
		}
	}
	
	
	private String[] toStringArray(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return ((String) obj).split(" ");
		if(obj instanceof List) return toStringArray1((List) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	public String[] toStringArray1(List l) throws Exception
	{
		int number = l.size();
		String[] yy = new String[number];
		for(int i=0;i<number;i++) yy[i] = ""+l.get(i);
		return yy;
	}
}
