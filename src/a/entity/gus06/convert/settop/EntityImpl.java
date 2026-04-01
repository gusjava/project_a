package a.entity.gus06.convert.settop;

import a.framework.*;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160711";}
	
	
	public Object t(Object obj) throws Exception
	{return new P1((Set) obj);}
	
	
	private class P1 implements P
	{
		private Set l;
		public P1(Set l){this.l = l;}
		
		public void p(Object obj) throws Exception
		{l.add(obj);}
	}
}
