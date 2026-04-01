package a.entity.gus06.feature.op.pipe.h;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150711";}

	
	public Object t(Object obj) throws Exception
	{return new H1((H[]) obj);}
	
	
	private class H1 implements H
	{
		private H[] hh;
		public H1(H[] hh){this.hh = hh;}
		
		public double h(double v) throws Exception
		{
			double r = v;
			for(H h:hh) r = h.h(r);
			return r;
		}
	}
}
