package a.entity.gus06.feature.op.col.apply.element.p;

import a.framework.*;
import java.util.Collection;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150707";}


	
	public Object t(Object obj) throws Exception
	{return new P1((P) obj);}
	
	
	
	
	private class P1 implements P
	{
		private P p;
		public P1(P p) {this.p = p;}
		
		public void p(Object obj) throws Exception
		{
			if(obj instanceof Object[]) toArray((Object[]) obj);
			if(obj instanceof Collection) toCollection((Collection) obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
		
		private void toArray(Object[] l) throws Exception
		{
			for(Object o:l) p.p(o);
		}
		
		private void toCollection(Collection c) throws Exception
		{
			Iterator it = c.iterator();
			while(it.hasNext()) p.p(it.next());
		}
	}
}
