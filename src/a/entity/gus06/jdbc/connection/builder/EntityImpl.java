package a.entity.gus06.jdbc.connection.builder;

import a.framework.*;
import java.util.Map;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20170207";}

	private Service getCx;
	private Service initWatcher;
	private Service getSbddName;
	
	private Map cx_watcher;

	public EntityImpl() throws Exception
	{
		getCx = Outside.service(this,"gus06.jdbc.connection.builder.get");
		initWatcher = Outside.service(this,"gus06.mysql.socketfactory.watcher.init");
		getSbddName = Outside.service(this,"gus06.jdbc.connection.sbddname");
		
		cx_watcher = new HashMap();
	}
	
	public Object t(Object obj) throws Exception
	{
		Object cx = getCx.t(obj);
		String sbddName = (String) getSbddName.t(cx);
		
		if(sbddName.equals("mysql") || sbddName.equals("mariadb"))
		{
			Object watcher = initWatcher.g();
			cx_watcher.put(cx,watcher);
		}
		return cx;
	}
	
	public Object g() throws Exception
	{return cx_watcher;}
}
