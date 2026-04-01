package a.entity.gus06.math.relation.recurrence.builder;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170116";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list0 = (List) o[0];
		T t = (T) o[1];
		
		return new Wrapper(list0,t);
	}
	
	
	
	private class Wrapper implements T, G, R
	{
		private List list0;
		private T t;
		private List list;
		
		public Wrapper(List list0, T t)
		{
			this.list0 = list0;
			this.t = t;
			
			list = new ArrayList(list0);
		}
		
		public Object g() throws Exception
		{
			return list;
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("list0")) return list0;
			if(key.equals("t")) return t;
			return t(key);
		}
		
		public Object t(Object obj) throws Exception
		{
			int n = Integer.parseInt(""+obj);
			buildUntil(n+1);
			return list.get(n);
		}
		
		private void buildUntil(int size) throws Exception
		{
			while(list.size()<size)
			{
				Object newElement = t.t(list);
				list.add(newElement);
			}
		}
	}
}
