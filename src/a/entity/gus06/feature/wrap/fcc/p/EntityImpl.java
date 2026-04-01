package a.entity.gus06.feature.wrap.fcc.p;

import a.framework.*;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160414";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		Collection c1 = (Collection) o[1];
		Collection c2 = (Collection) o[2];
		
		return new Wrap(f,c1,c2);
	}
	
	
	
	private class Wrap implements P
	{
		private F f;
		private Collection c1;
		private Collection c2;
		
		public Wrap(F f, Collection c1, Collection c2)
		{
			this.f = f;
			this.c1 = c1;
			this.c2 = c2;
		}
		
		public void p(Object obj) throws Exception
		{
			Collection c = f.f(obj) ? c1 : c2;
			if(c!=null) c.add(obj);
		}
	}
	
}
