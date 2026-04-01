package a.entity.gus06.data.buildholder.bool;

import a.framework.*;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20161012";}


	
	public Object t(Object obj) throws Exception
	{return new Holder((Boolean)obj);}

	
	
	public Object g() throws Exception
	{return new Holder(Boolean.TRUE);}
	
	
	
	private class Holder extends S1 implements T, P, G
	{
		private Boolean data;
		
		public Holder(Boolean data)
		{this.data = data;}
		
		public Object g() throws Exception
		{return data;}
		
		public void p(Object obj) throws Exception
		{
			data = (Boolean) obj;
			modified();
		}
		
		public Object t(Object obj) throws Exception
		{
			Boolean data1 = (Boolean) obj;
			int change = getChange(data1);
			data = data1;
			modified();
			return Integer.valueOf(change);
		}
		
		private int getChange(Boolean data1)
		{
			boolean b = data.booleanValue();
			boolean b1 = data1.booleanValue();
			return b1 ? (b ? 1:-1) : (!b ? 2:-2);
		}
		
		private void modified()
		{send(this,"modified()");}
	}
}
