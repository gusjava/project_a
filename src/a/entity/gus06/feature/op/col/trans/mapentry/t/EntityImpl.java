package a.entity.gus06.feature.op.col.trans.mapentry.t;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150722";}


	
	public Object t(Object obj) throws Exception
	{return new T1((T) obj);}
	
	
	
	
	private class T1 implements T
	{
		private T t;
		public T1(T t) {this.t = t;}
		
		public Object t(Object obj) throws Exception
		{
			Map m = (Map) obj;
			Map r = new HashMap();
			
			Iterator it = m.keySet().iterator();
			while(it.hasNext())
			{
				Object key = it.next();
				Object value = m.get(key);
				Object[] entry = new Object[]{key,value};
				
				Object[] entry1 = (Object[]) t.t(entry);
				if(entry1.length!=2) throw new Exception("Invalid transformed entry array size: "+entry1.length);
				
				r.put(entry1[0],entry1[1]);
			}
			return r;
		}
	}
}
