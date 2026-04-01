package a.entity.gus06.feature.wrap.e.p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180118";}

	
	
	public Object t(Object obj) throws Exception
	{
		E e = (E) obj;
		return new Wrap(e);
	}
	
	
	private class Wrap implements P
	{
		private E e;
		
		public Wrap(E e)
		{this.e = e;}
		
		public void p(Object obj) throws Exception
		{e.e();}
	}
}
