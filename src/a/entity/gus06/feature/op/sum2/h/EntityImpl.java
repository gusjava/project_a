package a.entity.gus06.feature.op.sum2.h;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150711";}

	
	public Object t(Object obj) throws Exception
	{return new H1((List) obj);}
	
	
	private class H1 implements H
	{
		private List hh;
		public H1(List hh){this.hh = hh;}
		
		public double h(double v) throws Exception
		{
			double r = 0;
			for(Object h:hh) r += ((H)h).h(v);
			return r;
		}
	}
}
