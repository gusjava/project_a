package a.entity.gus06.sys.expression1.apply.op._valuelist_for;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161020";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.map.build.sortedvalues.forkeys");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Map) return new T1((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Map map;
		public T1(Map map) {this.map = map;}
		
		public Object t(Object obj) throws Exception
		{
			List keys = toList(obj);
			return perform.t(new Object[]{map,keys});
		}
	}
	
	
	
	
	private List toList(Object obj) throws Exception
	{
		if(obj instanceof List) return (List) obj;
		if(obj instanceof String[]) return toList((String[]) obj);
		if(obj instanceof String) return toList((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private List toList(String[] oo)
	{
		List l = new ArrayList();
		for(String o:oo) l.add(o);
		return l;
	}
	
	private List toList(String s)
	{
		return toList(s.split(";"));
	}
}
