package a.entity.gus06.ling.localize.builder;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140808";}


	private Service perform;

	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.ling.localize.perform");}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder(obj);}
	
	
	
	private class Holder implements P, E, R
	{
		private Object c;
		private String ling;
	
		public Holder(Object c)
		{this.c = c;}
	
		public void p(Object obj) throws Exception
		{ling = (String) obj;}
	
		public void e() throws Exception
		{perform.v(ling,c);}
	
	
		public Object r(String key) throws Exception
		{
			if(key.equals("ling")) return ling;
			if(key.equals("obj")) return c;
			if(key.equals("keys")) return new String[]{"ling","obj"};
			
			throw new Exception("Unknown key: "+key);
		}
	}
}
