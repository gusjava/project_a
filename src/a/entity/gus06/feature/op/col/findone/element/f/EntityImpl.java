package a.entity.gus06.feature.op.col.findone.element.f;

import a.framework.*;
import java.util.Collection;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150721";}


	
	public Object t(Object obj) throws Exception
	{return new T1((F) obj);}
	
	
	
	
	private class T1 implements T
	{
		private F f;
		public T1(F f) {this.f = f;}
		
		public Object t(Object obj) throws Exception
		{
			Collection c = (Collection) obj;
			Iterator it = c.iterator();
			while(it.hasNext())
			{
				Object element = it.next();
				if(f.f(element)) return element;
			}
			return null;
		}
	}
}
