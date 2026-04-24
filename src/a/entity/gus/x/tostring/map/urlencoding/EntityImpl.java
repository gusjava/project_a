package a.entity.gus.x.tostring.map.urlencoding;

import java.util.Map;
import a.framework.*;
import java.util.Iterator;
import java.net.URLEncoder;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20170130";}

	public static final String CHARSET = "UTF-8";

	public Object t(Object obj) throws Exception
	{return mapToString((Map) obj);}
	
	private String mapToString(Map map) throws Exception
	{
		StringBuffer b = new StringBuffer();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = toString(map.get(key));
			append(b,key,value);
		}
		return b.toString();
	}
	
	private void append(StringBuffer b, String key, String value) throws Exception
	{
		try
		{
			String bloc = format(key)+"="+format(value);
			if(b.length()>0) b.append("&");
			b.append(bloc);
		}
		catch(Exception e)
		{
			String message = "Building input failed for key="+key+" and value="+value;
			throw new Exception(message,e);
		}
	}
	
	private String format(String data) throws Exception
	{return URLEncoder.encode(data,CHARSET);}
	
	private String toString(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof Boolean) return ""+obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
