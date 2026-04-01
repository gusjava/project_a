package a.entity.gus06.appli.gusdbmanager.connection.builder;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150613";}


	private Service connectors;
	private PrintStream out;
	

	
	public EntityImpl() throws Exception
	{
		connectors = Outside.service(this,"gus06.appli.gusdbmanager.data.connectors");
		out = (PrintStream) Outside.resource(this,"sysout");
	}



	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		Map map = toMap(obj);
		
		String url = get(map,CONNECTOR.KEY_URL);
		String login = get(map,CONNECTOR.KEY_LOGIN);
		String pwd = get(map,CONNECTOR.KEY_PWD);
		
		if(url==null) throw new Exception(ERRORS.ERR_UNKNOWN_URL);
		if(login==null) throw new Exception(ERRORS.ERR_UNKNOWN_LOGIN);
		if(pwd==null) throw new Exception(ERRORS.ERR_UNKNOWN_PWD);
		
		try
		{
			out.println("Attempt to connect to: url="+url+" login="+login);
			Connection cx = DriverManager.getConnection(url,login,pwd);
			out.println("Connection created: url="+url+" login="+login);
			
			return cx;
		}
		catch(SQLException e)
		{
			String m = e.getMessage().toLowerCase();
			if(m.startsWith("communications link failure")) throw new Exception(ERRORS.ERR_SERVER_NOTAVAILABLE,e);
			if(m.startsWith("access denied")) throw new Exception(ERRORS.ERR_SERVER_UNAUTHORIZED,e);
			throw e;
		}
	}
	
	
	
	
	
	private Map toMap(Object obj) throws Exception
	{
		if(obj instanceof Map) return (Map) obj;
		if(obj instanceof String) return (Map) connectors.r((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}
