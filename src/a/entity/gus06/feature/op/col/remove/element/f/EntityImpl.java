package a.entity.gus06.feature.op.col.remove.element.f;

import a.framework.*;
import java.util.Collection;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150707";}


	
	public Object t(Object obj) throws Exception
	{return new P1((F) obj);}
	
	
	
	
	private class P1 implements P
	{
		private F f;
		public P1(F f) {this.f = f;}
		
		public void p(Object obj) throws Exception
		{
			Collection c = (Collection) obj;
			Iterator it = c.iterator();
			while(it.hasNext()) if(f.f(it.next())) it.remove();
		}
	}
}
