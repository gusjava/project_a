package a.entity.gus06.sys.filebackup1.once.yearly;

import a.framework.*;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20190724";}


	private Service manager;
	private Service canBackup;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.sys.filebackup1.manager");
		canBackup = Outside.service(this,"gus06.app.persister1.once.yearly");
	}
	
	
	public Object r(String key) throws Exception
	{
		Object holder = manager.r(key);
		return new Holder(key,holder);
	}
	
	
	
	private class Holder implements P, G, F, R, V
	{
		private String k;
		private Object holder;
		
		public Holder(String k, Object holder)
		{
			this.k = k;
			this.holder = holder;
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(canBackup.f(k)) {((P) holder).p(obj);return true;}
			return false;
		}
		
		public void p(Object obj) throws Exception
		{if(canBackup.f(k)) ((P) holder).p(obj);}
		
		public Object g() throws Exception
		{return ((G) holder).g();}
		
		public Object r(String key) throws Exception
		{return ((R) holder).r(key);}
		
		public void v(String key, Object obj) throws Exception
		{((V) holder).v(key,obj);}
	}
}
