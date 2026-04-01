package a.entity.gus06.appli.gusdbmanager.data.connectors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, R, ActionListener {

	public String creationDate() {return "20150613";}

	
	private Service dataHolder;
	private Service situation;
	
	private Map connectors;

	
	public EntityImpl() throws Exception
	{
		dataHolder = Outside.service(this,"gus06.appli.gusdbmanager.data.holder");
		situation = Outside.service(this,"gus06.appli.gusdbmanager.situation.builder");
		
		connectors = new HashMap();
		
		dataHolder.addActionListener(this);
		situation.addActionListener(this);
		update();
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{update();}
	
	
	
	
	public Object g() throws Exception
	{return connectors;}
	
	
	

	public Object r(String key) throws Exception
	{
		if(!connectors.containsKey(key)) return null;
		return connectors.get(key);
	}
	
	
	
	
	
	private void update()
	{
		try
		{
			connectors.clear();
			
			String s = (String) situation.g();
			String[] n = s.split("\\|");
			if(n.length!=3) throw new Exception("Wrong data number: "+n.length);
			
			String home = n[0];
			String location = n[1];
			String wan = n[2];
			
			Map data = (Map) dataHolder.g();
			
			analyze(data,"1-"+home);
			analyze(data,"2-"+location);
			analyze(data,"3-"+wan);
			
			updated();
		}
		catch(Exception e)
		{Outside.err(this,"update()",e);}
	}



	
	
	

	private void analyze(Map data, String where) throws Exception
	{
		if(invalidWhere(where)) return;
		
		Iterator it = data.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String[] n = key.split("\\.");
			if(n.length!=3) throw new Exception("Invalid key: "+key);
			
			String where_ = n[0];
			String id = n[1];
			String info = n[2];
			
			if(where_.equals(where) && info.equals("url"))
			{
				String url = (String) data.get(key);
				String login = find(data,where,id,"login");
				String pwd = find(data,where,id,"pwd");
				String name = find(data,where,id,"name");
				
				register(id,url,login,pwd,name,where);
			}
		}
	}
	
	
	
	private boolean invalidWhere(String where)
	{return where.equals("2-false") || where.equals("2-?") || where.equals("3-false");}
	
	
	


	private String find(Map data, String p1, String p2, String p3)
	{
		if(data.containsKey(p1+"."+p2+"."+p3))
			return (String) data.get(p1+"."+p2+"."+p3);
		if(data.containsKey("%."+p2+"."+p3))
			return (String) data.get("%."+p2+"."+p3);
		if(data.containsKey("%.%."+p3))
			return (String) data.get("%.%."+p3);
		return null;
	}
	
	
	
	
	
	
	
	private void register(String id, String url, String login, String pwd, String name, String where)
	{
		if(connectors.containsKey(id)) return;
		
		Map m = new HashMap();
		m.put(CONNECTOR.KEY_URL,url);
		m.put(CONNECTOR.KEY_LOGIN,login);
		m.put(CONNECTOR.KEY_PWD,pwd);
		m.put(CONNECTOR.KEY_NAME,name);
		m.put(CONNECTOR.KEY_WHERE,where);
		
		connectors.put(id,m);
	}
	
	
	
	private void updated()
	{send(this,"updated()");}
}
