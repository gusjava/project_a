package a.entity.gus06.sys.webserver1.web2.site.pseudo.action.register;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.io.PrintStream;
import java.security.PublicKey;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141008";}

	public static final String AVAILABLE = "available";
	public static final String USED = "used";
	public static final String BLOCKED = "blocked";
	public static final String DONE = "done";
	public static final String FAILED = "failed";



	private Service findProp;
	private Service jdbcConnection;
	private Service createTable;
	private Service checkAvailable;
	private Service insertNewPseudo;
	private Service buildKey;
	
	private PrintStream out;


	public EntityImpl() throws Exception
	{
		findProp = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.find.prop1");
		jdbcConnection = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.jdbc.connection");
		createTable = Outside.service(this,"gus06.sys.webserver1.web2.site.pseudo.jdbc.create.table");
		checkAvailable = Outside.service(this,"gus06.sys.webserver1.web2.site.pseudo.jdbc.check.available");
		insertNewPseudo = Outside.service(this,"gus06.sys.webserver1.web2.site.pseudo.jdbc.insert.newpseudo");
		buildKey = Outside.service(this,"gus06.sys.crypto.pseudo.build.publickey1");
		
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void p(Object obj) throws Exception
	{
		R mr = (R) obj;
		
		boolean isBlocked = checkBlocked(mr);
		if(isBlocked) {print(mr,BLOCKED);return;}
		
		Map params_get = (Map) mr.r("params_get");
		Map params_post = (Map) mr.r("params_post");
		String ip_addr = (String) mr.r("input address");
		
		String pseudo = (String) params_get.get("pseudo");
		
		Connection cx = (Connection) jdbcConnection.t(mr);
		createTable.p(cx);
		
		boolean available = checkAvailable(cx,pseudo);
		if(!available) {print(mr,USED);return;}
		
		if(params_post.isEmpty())
		{print(mr,AVAILABLE);return;}
		
		boolean done = registerPseudo(cx,pseudo,ip_addr,params_post);
		if(done) print(mr,DONE);
		else print(mr,FAILED);
	}
	
	
	
	private boolean checkBlocked(R mr) throws Exception
	{
		return false;
	}
	
	
	
	private boolean checkAvailable(Connection cx, String pseudo) throws Exception
	{return checkAvailable.f(new Object[]{cx,pseudo});}
	
	
	
	
	
	
	private boolean registerPseudo(Connection cx, String pseudo, String ip, Map map)
	{
		try
		{
			String owner = get(map,"owner");
			if(!owner.equals(pseudo)) throw new Exception("Owner and pseudo are differents: "+owner+"/"+pseudo);
			
			PublicKey key = (PublicKey) buildKey.t(map);
			if(key==null) throw new Exception("Inconsistent data for owner: "+owner);
			
			map.put("ip",ip);
			
			insertNewPseudo.p(new Object[]{cx,map});
			out.println("New pseudo registered: "+pseudo);
			return true;
		}
		catch(Exception e)
		{
			Outside.err(this,"registerPseudo(Connection,String,String,Map)",e);
			return false;
		}
	}
	
	
	
	
	private void print(R mr, String res) throws Exception
	{
		P h = (P) mr.r("data h");
		h.p(res);
	}
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
}
