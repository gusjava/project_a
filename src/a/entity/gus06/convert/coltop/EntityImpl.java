package a.entity.gus06.convert.coltop;

import a.framework.*;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160711";}


	public Object t(Object obj) throws Exception
	{return new P1((Collection) obj);}
	
	
	private class P1 implements P
	{
		private Collection c;
		public P1(Collection c) {this.c = c;}
		
		public void p(Object obj) throws Exception
		{
			c.add(obj);
		}
	}
}
