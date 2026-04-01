package a.entity.gus06.feature.wrap.c.p;

import a.framework.*;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160414";}

	
	
	public Object t(Object obj) throws Exception
	{
		Collection c = (Collection) obj;
		return new Wrap(c);
	}
	
	
	
	private class Wrap implements P
	{
		private Collection c;
		
		public Wrap(Collection c)
		{this.c = c;}
		
		public void p(Object obj) throws Exception
		{c.add(obj);}
	}
}
