package a.entity.gus06.feature.op.product.h;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160802";}

	
	public Object t(Object obj) throws Exception
	{return new H1((H[]) obj);}
	
	
	private class H1 implements H
	{
		private H[] hh;
		public H1(H[] hh){this.hh = hh;}
		
		public double h(double v) throws Exception
		{
			double r = 1;
			for(H h:hh) r *= h.h(v);
			return r;
		}
	}
}
