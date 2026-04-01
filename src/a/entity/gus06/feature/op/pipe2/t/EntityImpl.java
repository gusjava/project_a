package a.entity.gus06.feature.op.pipe2.t;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150711";}

	
	public Object t(Object obj) throws Exception
	{return new T1((List) obj);}
	
	
	private class T1 implements T
	{
		private List tt;
		public T1(List tt){this.tt = tt;}
		
		public Object t(Object obj) throws Exception
		{
			Object r = obj;
			for(Object t:tt) r = ((T)t).t(r);
			return r;
		}
	}
}
