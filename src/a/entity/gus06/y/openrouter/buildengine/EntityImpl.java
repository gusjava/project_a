package a.entity.gus06.y.openrouter.buildengine;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.util.Properties;
import java.io.FileOutputStream;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251201";}
	
	public static final String KEY_MODEL = "model";
	public static final String KEY_APIKEY = "apikey";
	public static final String KEY_STOREDIR = "storedir";
	public static final String KEY_TIMEOUT = "timeout";

	private Service queryModels;
	private Service queryCredits;
	private Service mapKey;
	
	public EntityImpl() throws Exception
	{
		queryModels = Outside.service(this,"gus06.web.openrouter.api.models.query");
		queryCredits = Outside.service(this,"gus06.web.openrouter.api.credits.query");
		mapKey = Outside.service(this,"gus06.list.mapkey");
	}
	
	public Object t(Object obj) throws Exception
	{return new Engine((Map) obj);}
	
	
	private class Engine extends S1 implements R, V, E, Runnable
	{
		private Map map;
		private String model;
		private String apikey;
		private File storeDir;
		private Integer timeout;
		
		private Thread t;
		private List modelList;
		private Map credits;
		private Map dataById;
		private Map data;
		
		public Engine(Map map)
		{
			this.map = map;
			model = (String) map.get(KEY_MODEL);
			apikey = (String) map.get(KEY_APIKEY);
			storeDir = buildStoreDir();
			timeout = buildTimeout();
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
			if(key.equals("select")) {select((String) obj);return;}
			if(key.equals("loadCredits")) {loadCredits();return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("model")) return model;
			if(key.equals("apikey")) return apikey;
			if(key.equals("modelList")) return modelList;
			if(key.equals("dataById")) return dataById;
			if(key.equals("data")) return data;
			if(key.equals("credits")) return credits;
			if(key.equals("storeDir")) return storeDir;
			if(key.equals("timeout")) return timeout;
			
			if(key.equals("keys")) return new String[]{
				"model","apikey","dataById","data","credits","storeDir","timeout"};
			throw new Exception("Unknown key: "+key);
		}
		
		public void run()
		{
			try
			{
				loadModels();
				loadCredits();
				updateData();
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"run()",e);}
		}
		
		private void select(String model) throws Exception
		{
			this.model = model;			
			map.put(KEY_MODEL, model);
			updateData();
		}
		
		
		private void loadModels() throws Exception
		{
			Map m = new HashMap();
			m.put(KEY_APIKEY, apikey);
			modelList = (List) queryModels.t(m);
			dataById = (Map) mapKey.t(new Object[]{modelList, (T) k->((Map)k).get("id")});
			modelsLoaded();
		}
		
		private void loadCredits() throws Exception
		{
			Map m = new HashMap();
			m.put(KEY_APIKEY, apikey);
			credits = (Map) queryCredits.t(m);
			creditsLoaded();
		}
		
		
		private void updateData() throws Exception
		{
			if(dataById==null) throw new Exception("dataById not initialized yet");
			data = model!=null && dataById.containsKey(model) ? (Map) dataById.get(model) : null;
			updated();
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
		
		private Integer buildTimeout()
		{
			if(!map.containsKey(KEY_TIMEOUT)) return null;
			String val = (String) map.get(KEY_TIMEOUT);
			if(val.equals("")) return null;
			return Integer.valueOf(val);
		}
		
		
		private void loading()
		{send(this,"loading()");}
		
		private void modelsLoaded()
		{send(this,"modelsLoaded()");}
		
		private void creditsLoaded()
		{send(this,"creditsLoaded()");}
		
		private void updated()
		{send(this,"updated()");}
	}
}
