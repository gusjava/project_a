package a.entity.gus06.sys.filebackup1.once.changed;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20190724";}


	private Service manager;
	private Service checkDoubloon;



	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.sys.filebackup1.manager");
		checkDoubloon = Outside.service(this,"gus06.file.doubloon.check");
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
			File file = (File) obj;
			File last = (File) r("last");
			if(last!=null && checkDoubloon.f(new File[]{file,last})) return false;
			
			((P) holder).p(file);
			return true;
		}
		
		public void p(Object obj) throws Exception
		{f(obj);}
		
		public Object g() throws Exception
		{return ((G) holder).g();}
		
		public Object r(String key) throws Exception
		{return ((R) holder).r(key);}
		
		public void v(String key, Object obj) throws Exception
		{((V) holder).v(key,obj);}
	}
}
