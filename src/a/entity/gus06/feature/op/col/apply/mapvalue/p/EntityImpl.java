package a.entity.gus06.feature.op.col.apply.mapvalue.p;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150707";}


	
	public Object t(Object obj) throws Exception
	{return new P1((P) obj);}
	
	
	
	
	private class P1 implements P
	{
		private P p;
		public P1(P p) {this.p = p;}
		
		public void p(Object obj) throws Exception
		{
			Map m = (Map) obj;
			Iterator it = m.keySet().iterator();
			while(it.hasNext()) p.p(m.get(it.next()));
		}
	}
}
