package a.entity.gus06.feature.op.sum.h.eee;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180330";}

	
	public Object t(Object obj) throws Exception
	{return new H1((H[]) obj);}
	
	
	private class H1 implements H
	{
		private H[] hh;
		public H1(H[] hh){this.hh = hh;}
		
		public double h(double v) throws Exception
		{
			Exception ex = null;
			for(H h:hh)
			{
				try{return h.h(v);}
				catch(Exception e1){ex=e1;}
			}
			throw ex;
		}
	}
}
