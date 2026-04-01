package a.entity.gus06.appli.gusexplorer.scripts.remote.executor;

import a.framework.*;
import java.util.Map;
import java.sql.Connection;
import java.io.PrintStream;

public class EntityImpl implements Entity, P, G, Runnable {

	public String creationDate() {return "20170421";}
	
	public static final String KEY_PATH = "path";
	public static final String KEY_ID = "id";
	
	public static final String DEFAULT_PATH = "scripts";
	public static final String DEFAULT_ID = "init";


	private Service toMap;
	private Service buildCx;
	private Service select;
	private Service main;
	
	private Thread t;
	private Map map;

	public EntityImpl() throws Exception
	{
		toMap = Outside.service(this,"gus06.data.perform.tomap");
		buildCx = Outside.service(this,"gus06.jdbc.connection.builder");
		select = Outside.service(this,"gus06.jdbc.mysql.perform.select.all.id.as.map");
		main = Outside.service(this,"gus06.sys.script1.main.main1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(t!=null && t.isAlive()) return;
		map = (Map) toMap.t(obj);
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public Object g() throws Exception
	{return map;}
	
	
	
	public void run()
	{
		try
		{
			Connection cx = (Connection) buildCx.t(map);
			
			String id = get(map,KEY_ID,DEFAULT_ID);
			String path = get(map,KEY_PATH,DEFAULT_PATH);
			
			Map row = (Map) select.t(new Object[]{cx,path,id});
			if(row==null) throw new Exception("Remote script id not found: "+id);
			
			String value = get(row,"value",null);
			if(value==null) throw new Exception("Invalid row: "+row);
			
			PrintStream out = null;
			main.p(new Object[]{value,out});
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	
	private String get(Map map, String key, String defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return (String) map.get(key);
	}
}