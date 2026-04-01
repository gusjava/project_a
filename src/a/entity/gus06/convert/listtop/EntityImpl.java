package a.entity.gus06.convert.listtop;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160711";}
	
	
	public Object t(Object obj) throws Exception
	{return new P1((List) obj);}
	
	
	private class P1 implements P
	{
		private List l;
		public P1(List l){this.l = l;}
		
		public void p(Object obj) throws Exception
		{l.add(obj);}
	}
}
