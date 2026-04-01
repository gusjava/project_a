package a.entity.gus06.jdbc.url.parse;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230221";}

	
	
	public Object t(Object obj) throws Exception
	{
		String jdbcUrl = (String) obj;
		//jdbc:mysql://127.0.0.1:3306/mysql
		
		String[] n = jdbcUrl.split("://",2);
		if(n.length!=2) throw new Exception("Invalid JDBC url: "+jdbcUrl);
		
		String schema = n[0];
		String mainPart = n[1];
		
		String[] k = mainPart.split("/",2);
		if(k.length!=2) throw new Exception("Invalid JDBC url: "+jdbcUrl);
		
		String hostPort = k[0];
		String path = k[1];
		
		String[] l = hostPort.split(":",2);
		if(l.length!=2) throw new Exception("Invalid JDBC url: "+jdbcUrl);
		
		String host = l[0];
		int port = Integer.parseInt(l[1]);
		
		Map map = new HashMap();
		map.put("port",port);
		map.put("host",host);
		map.put("path",path);
		map.put("schema",schema);
		return map;
	}
}