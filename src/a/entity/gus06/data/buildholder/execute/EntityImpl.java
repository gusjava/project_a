package a.entity.gus06.data.buildholder.execute;

import a.framework.*;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20170805";}


	
	public Object t(Object obj) throws Exception
	{return new Holder((E) obj);}

	
	
	public Object g() throws Exception
	{return new Holder(null);}
	
	
	
	private class Holder extends S1 implements E, P, G
	{
		private E execute;
		
		public Holder(E execute)
		{this.execute = execute;}
		
		public Object g() throws Exception
		{return execute;}
		
		public void e() throws Exception
		{if(execute!=null) execute.e();}
		
		public void p(Object obj) throws Exception
		{
			execute = (E) obj;
			modified();
		}
		
		private void modified()
		{send(this,"modified()");}
	}
}
