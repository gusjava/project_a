package a.entity.gus06.feature.wrap.fc.p;

import a.framework.*;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		Collection c = (Collection) o[1];
		
		return new Wrap(f,c);
	}
	
	
	
	private class Wrap implements P
	{
		private F f;
		private Collection c;
		
		public Wrap(F f, Collection c)
		{
			this.f = f;
			this.c = c;
		}
		
		public void p(Object obj) throws Exception
		{
			if(f.f(obj)) c.add(obj);
		}
	}
	
}
