package a.entity.gus06.sys.base1.builder.trans;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}


	private Service empty;


	public EntityImpl() throws Exception
	{
		empty = Outside.service(this,"gus06.sys.base1.builder.trans.empty");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((G)obj);}
	
	
	
	private class Holder implements P
	{
		private G g;
		public Holder(G g) {this.g = g;}
	
		public void p(Object obj) throws Exception
		{
			synchronized(g)
			{
				if(obj.equals("empty")) empty.p(g);
				else throw new Exception("Invalid order: "+obj);
			}
		}
	}
}
