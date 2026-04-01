package a.entity.gus06.feature.op.col.apply.element.fp;

import a.framework.*;
import java.util.Collection;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150707";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		P p = (P) o[1];
		
		return new P1(f,p);
	}
	
	
	
	
	private class P1 implements P
	{
		private F f;
		private P p;
		
		public P1(F f, P p)
		{
			this.f = f;
			this.p = p;
		}
		
		public void p(Object obj) throws Exception
		{
			if(obj instanceof Object[]) toArray((Object[]) obj);
			if(obj instanceof Collection) toCollection((Collection) obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
		
		private void toArray(Object[] l) throws Exception
		{
			for(Object o:l) if(f.f(o)) p.p(o);
		}
		
		private void toCollection(Collection c) throws Exception
		{
			Iterator it = c.iterator();
			while(it.hasNext())
			{
				Object o = it.next();
				if(f.f(o)) p.p(o);
			}
		}
	}
}
