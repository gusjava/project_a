package a.entity.gus06.string.transform.url.sendpost;

import a.framework.*;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150314";}


	private Service send;

	public EntityImpl() throws Exception
	{send = Outside.service(this,"*gus06.web.httprequest.post.send");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("\n",-1);
		if(n.length<2) throw new Exception("Invalid input: "+s);
		
		URL url = new URL(n[0].trim());
		
		Map map = new HashMap();
		for(int i=1;i<n.length;i++)
		{
			String[] k = n[i].split(":",2);
			map.put(k[0],k[1]);
		}
		return send(url,map);
	}
	
	
	private String send(URL url, Map input)
	{
		Map m = new HashMap();
		m.put("body",input);
		m.put("url",url);
		
		try{return (String) send.t(m);}
		catch(Exception e)
		{return e.toString();}
	}
}