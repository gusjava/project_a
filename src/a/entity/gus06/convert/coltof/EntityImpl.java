package a.entity.gus06.convert.coltof;

import a.framework.*;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151124";}


	public Object t(Object obj) throws Exception
	{return new F1((Collection) obj);}
	
	
	private class F1 implements F
	{
		private Collection c;
		public F1(Collection c) {this.c = c;}
		
		public boolean f(Object obj) throws Exception
		{return c.contains(obj);}
	}
}