package a.entity.gus06.feature.op.function2.min;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160624";}

	
	public Object t(Object obj) throws Exception
	{return new H1((List) obj);}
	
	
	private class H1 implements H
	{
		private List hh;
		public H1(List hh){this.hh = hh;}
		
		public double h(double v) throws Exception
		{
			double min = Double.MAX_VALUE;
			for(Object h:hh) min = Math.min(((H)h).h(v),min);
			return min;
		}
	}
}
