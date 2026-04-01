package a.entity.gus06.feature.op.sum.p.eee;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180330";}

	
	public Object t(Object obj) throws Exception
	{return new P1((P[]) obj);}
	
	
	private class P1 implements P
	{
		private P[] pp;
		public P1(P[] pp){this.pp = pp;}
		
		public void p(Object obj) throws Exception
		{
			Exception ex = null;
			for(P p:pp)
			{
				try{p.p(obj);return;}
				catch(Exception e1){ex=e1;}
			}
			throw ex;
		}
	}
}
