package a.entity.gus.y.roadmapsys1.engine;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, E, R {
	public String creationDate() {return "20260428";}

	private Service cxMain;

	private Connection cx;

	public EntityImpl() throws Exception
	{
		cxMain = Outside.service(this, "gus.y.roadmapdb1.cx.main");
		
		load();
	}

	public void e() throws Exception
	{
		new Thread(() -> {load();}).start();
	}
	
	private void load()
	{
		try
		{
			cx = (Connection) cxMain.g();
			
			loaded();
		}
		catch(Exception e)
		{Outside.err(this,"load()",e);}
	}

	private void loaded()
	{
		send(this, "loaded()");
	}

	public Object r(String key) throws Exception
	{
		if (key.equals("cx")) return cx;

		if (key.equals("keys")) return new String[] 
			{"cx"};
		
		throw new Exception("Unknown key: " + key);
	}
}
