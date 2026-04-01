package a.entity.gus06.feature.op.function.max;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160624";}

	
	public Object t(Object obj) throws Exception
	{return new H1((H[]) obj);}
	
	
	private class H1 implements H
	{
		private H[] hh;
		public H1(H[] hh){this.hh = hh;}
		
		public double h(double v) throws Exception
		{
			double max = Double.MIN_VALUE;
			for(H h:hh) max = Math.max(h.h(v),max);
			return max;
		}
	}
}
