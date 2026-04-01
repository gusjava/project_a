package a.entity.gus06.feature.op.col.hasnone.mapvalue.f;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}


	
	public Object t(Object obj) throws Exception
	{return new F1((F) obj);}
	
	
	
	
	private class F1 implements F
	{
		private F f;
		public F1(F f) {this.f = f;}
		
		public boolean f(Object obj) throws Exception
		{
			Map m = (Map) obj;
			Iterator it = m.keySet().iterator();
			while(it.hasNext())
			{
				Object key = it.next();
				Object value = m.get(key);
				
				if(f.f(value)) return false;
			}
			return true;
		}
	}
}
