package a.entity.gus06.feature.op.sum2.p;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150711";}

	
	public Object t(Object obj) throws Exception
	{return new P1((List) obj);}
	
	
	private class P1 implements P
	{
		private List pp;
		public P1(List pp){this.pp = pp;}
		
		public void p(Object obj) throws Exception
		{for(Object p:pp) ((P)p).p(obj);}
	}
}
