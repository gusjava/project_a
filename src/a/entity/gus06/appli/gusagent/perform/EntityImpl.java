package a.entity.gus06.appli.gusagent.perform;

import a.framework.*;
import java.util.Map;
import java.sql.Connection;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20150626";}


	private Service isConnected;
	private Service jdbcInfo;
	private Service findHolder;
	private Service cmd;


	public EntityImpl() throws Exception
	{
		isConnected = Outside.service(this,"gus06.network.local.isconnected");
		jdbcInfo = Outside.service(this,"gus06.appli.gusagent.jdbc.infoholder");
		findHolder = Outside.service(this,"gus06.jdbc.connection.holder");
		cmd = Outside.service(this,"gus06.sys.cmd1.engine");
	}
	
	
	public void e() throws Exception
	{
		if(!isConnected.f(null)) return;
		
		G holder = (G) findHolder.t(jdbcInfo);
		Connection cx = (Connection) holder.g();
		
		cmd.p("beep");
	}
}
