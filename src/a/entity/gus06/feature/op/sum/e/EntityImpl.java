package a.entity.gus06.feature.op.sum.e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150707";}

	
	public Object t(Object obj) throws Exception
	{return new E1((E[]) obj);}
	
	
	private class E1 implements E
	{
		private E[] ee;
		public E1(E[] ee){this.ee = ee;}
		
		public void e() throws Exception
		{for(E e:ee) e.e();}
	}
}
