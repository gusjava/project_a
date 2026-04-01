package a.entity.gus06.feature.op.col.hasmany.element.f;

import a.framework.*;
import java.util.Collection;
import java.util.Iterator;

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
			Collection c = (Collection) obj;
			int found = 0;
			Iterator it = c.iterator();
			while(it.hasNext())
			{
				Object element = it.next();
				if(f.f(element))
				{
					found++;
					if(found>1) return true;
				}
			}
			return false;
		}
	}
}
