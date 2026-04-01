package a.entity.gus06.feature.op.sum2.e;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150711";}

	
	public Object t(Object obj) throws Exception
	{return new E1((List) obj);}
	
	
	private class E1 implements E
	{
		private List ee;
		public E1(List ee){this.ee = ee;}
		
		public void e() throws Exception
		{for(Object e:ee) ((E)e).e();}
	}
}
