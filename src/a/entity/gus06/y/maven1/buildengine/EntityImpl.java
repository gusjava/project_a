package a.entity.gus06.y.maven1.buildengine;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251217";}
	
	public static final String KEY_URL = "url";
	public static final String KEY_LOGIN = "login";
	public static final String KEY_PWD = "pwd";
	public static final String KEY_STOREDIR = "path.storedir";


	private Service crawler;
	private Service retrieve;
	private Service browse;

	public EntityImpl() throws Exception
	{
		crawler = Outside.service(this,"gus06.y.maven1.html.crawler");
		retrieve = Outside.service(this,"gus06.y.maven1.html.retrieve");
		browse = Outside.service(this,"gus06.y.maven1.html.browse");
	}
	
	public Object t(Object obj) throws Exception
	{return new Engine((Map) obj);}
	
	
	private class Engine extends S1 implements R, V, E, Runnable
	{
		private Map map;
		private String url;
		private String login;
		private String pwd;
		private File storeDir;
		
		private Thread t;
		
		public Engine(Map map)
		{
			this.map = map;
			url = (String) map.get(KEY_URL);
			login = (String) map.get(KEY_LOGIN);
			pwd = (String) map.get(KEY_PWD);
			storeDir = buildStoreDir();
		}
		
		public void e() throws Exception
		{
			if(t!=null && t.isAlive()) return;
			
			loading();
			t = new Thread(this,"THREAD_"+getClass().getName());
			t.start();
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("browse")) {browse((String) obj);return;}
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("url")) return url;
			if(key.equals("login")) return login;
			if(key.equals("pwd")) return pwd;
			if(key.equals("storeDir")) return storeDir;
			
			if(key.startsWith("crawl:")) return crawl(key.substring(6));
			if(key.startsWith("retrieve:")) return retrieve(key.substring(9));
			
			if(key.equals("keys")) return new String[]{
				"url","login","pwd","storeDir"};
			throw new Exception("Unknown key: "+key);
		}
		
		public void run()
		{
			try
			{
				
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"run()",e);}
			loaded();
		}
		
		private File buildStoreDir()
		{
			if(!map.containsKey(KEY_STOREDIR)) return null;
			String path = (String) map.get(KEY_STOREDIR);
			if(path.equals("")) return null;
			File dir = new File(path);
			if(!dir.exists()) dir.mkdirs();
			if(!dir.isDirectory()) return null;
			return dir;
		}
		
		private void browse(String path) throws Exception
		{browse.p(path);}
		
		private List crawl(String path) throws Exception
		{return (List) crawler.t(new Object[]{url,path});}
		
		private Map retrieve(String path) throws Exception
		{return (Map) retrieve.t(new Object[]{url,path});}
		
		private void loading()
		{send(this,"loading()");}
		
		private void loaded()
		{send(this,"loaded()");}
		
		private void updated()
		{send(this,"updated()");}
	}
}
