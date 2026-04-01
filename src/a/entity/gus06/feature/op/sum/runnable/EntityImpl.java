package a.entity.gus06.feature.op.sum.runnable;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150707";}

	
	public Object t(Object obj) throws Exception
	{return new Runnable1((Runnable[]) obj);}
	
	
	private class Runnable1 implements Runnable
	{
		private Runnable[] rr;
		public Runnable1(Runnable[] rr){this.rr = rr;}
		
		public void run()
		{for(Runnable r:rr) r.run();}
	}
}
