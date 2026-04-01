package a.entity.gus06.thread.transfer.build.holder;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20161025";}


	
	public Object g() throws Exception
	{return new Holder();}
	
	
	
	private class Holder implements P, G
	{
		private Object data;
		
		public synchronized Object g() throws Exception
		{
			Object data1 = data;
			data = null;
			return data1;
		}
		
		public synchronized void p(Object obj) throws Exception
		{
			data = obj;
		}
	}
}