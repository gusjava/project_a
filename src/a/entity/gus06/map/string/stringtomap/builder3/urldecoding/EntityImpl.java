package a.entity.gus06.map.string.stringtomap.builder3.urldecoding;

import a.framework.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141013";}
	
	public static final String DELIM = "&";


	private Service decoder;
	
	public EntityImpl() throws Exception
	{
		decoder = Outside.service(this,"gus06.string.transform.encoding.url.decode");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		String[] n = toArray(obj);
		
		Map map = new HashMap();
		if(n!=null) for(String s:n)
		{
			if(!s.contains("=")) throw new Exception("Invalid rule: "+obj);
			String[] nn = s.split("=",2);
			map.put(decode(nn[0]),decode(nn[1]));
		}
		return map;
	}
	
	
	private String decode(String s) throws Exception
	{return (String) decoder.t(s);}
	
	
	
	
	private String[] toArray(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj.equals("")) return null;
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return ((String) obj).split(DELIM);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
