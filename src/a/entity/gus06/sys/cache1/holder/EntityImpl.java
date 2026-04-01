package a.entity.gus06.sys.cache1.holder;

import a.framework.*;

public class EntityImpl implements Entity, R, T {

	public String creationDate() {return "20180307";}


	private Service manager;

	public EntityImpl() throws Exception
	{manager = Outside.service(this,"gus06.sys.cache1");}
	
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((String) obj);}
	
	
	public Object r(String key) throws Exception
	{return new Holder(key);}
	
	
	
	private class Holder implements P, G, T
	{
		private String key;
		public Holder(String key) {this.key = key;}
		
		public void p(Object obj) throws Exception
		{manager.v(key,obj);}
		
		public Object g() throws Exception
		{return manager.r(key);}
		
		public Object t(Object obj) throws Exception
		{
			Object oldValue = manager.r(key);
			manager.v(key,obj);
			return oldValue;
		}
	}
}
