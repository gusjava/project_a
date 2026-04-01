package a.entity.gus06.appli.gusclient1.sender;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20141017";}

	public static final String PROPKEY = "url.send.pseudo";
	
	private Service sendPost;
	private Service signer;
	private Service findPseudo;
	
	private Map prop;
	

	public EntityImpl() throws Exception
	{
		sendPost = Outside.service(this,"*gus06.web.httprequest.post.send");
		signer = Outside.service(this,"gus06.sys.crypto.pseudo.sign");
		findPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
		
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = toMap(obj);
		map.put("pseudo",pseudo());
		
		String url = get(PROPKEY);
		if(url==null) throw new Exception("Property not found: "+PROPKEY);
		
		String res = send(url,map);
		if(res.startsWith("redirect:"))
		{
			url = res.substring(9);
			res = send(url,map);
		}
		
		if(res.startsWith("error:")) throw new Exception("Command failed: "+res.substring(6));
		
		if(res.startsWith("sign:"))
		{
			String code = res.substring(5);
			String sign = (String) signer.t(code);
			if(sign==null) throw new Exception("Sign could not be generated");
			
			map.put("sign",sign);
			res = send(url,map);
		}
		return res;
	}
	
	
	
	
	private Map toMap(Object obj) throws Exception
	{
		if(obj instanceof Map) return (Map) obj;
		if(obj instanceof String) return toMap((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Map toMap(String cmd)
	{
		Map map = new HashMap();
		map.put("cmd",cmd);
		return map;
	}
	
	
	private String pseudo() throws Exception
	{return (String) findPseudo.g();}
	
	
	private String send(String url, Map input) throws Exception
	{
		Map m = new HashMap();
		m.put("body",input);
		m.put("url",url);
		
		return (String) sendPost.t(m);
	}
	
	
	private String get(String key)
	{
		if(!prop.containsKey(key)) return null;
		return (String) prop.get(key);
	}
}