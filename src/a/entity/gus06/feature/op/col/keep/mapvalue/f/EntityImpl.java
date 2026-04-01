package a.entity.gus06.feature.op.col.keep.mapvalue.f;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150721";}


	
	public Object t(Object obj) throws Exception
	{return new P1((F) obj);}
	
	
	
	
	private class P1 implements P
	{
		private F f;
		public P1(F f) {this.f = f;}
		
		public void p(Object obj) throws Exception
		{
			Map m = (Map) obj;
			Iterator it = m.keySet().iterator();
			while(it.hasNext())
			{
				Object key = it.next();
				Object value = m.get(key);
				
				if(!f.f(value)) it.remove();
			}
		}
	}
}
