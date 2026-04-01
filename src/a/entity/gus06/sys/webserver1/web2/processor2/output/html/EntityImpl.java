package a.entity.gus06.sys.webserver1.web2.processor2.output.html;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141002";}
	
	public static final String KEY_OUTPUT = "output";
	public static final String KEY_OUTPUT_ENCODING = "output_encoding";
	public static final String KEY_OUTPUT_TRANSFORM = "output_transform";
	public static final String KEY_OUTPUT_HEADER = "output_header";
	

	
	public Object t(Object obj) throws Exception
	{
		Map main = (Map) obj;
		
		String html = main.get(KEY_OUTPUT).toString();
		String enc = (String) get(main,KEY_OUTPUT_ENCODING);
		T trans = (T) get(main,KEY_OUTPUT_TRANSFORM);
		Map h = (Map) get(main,KEY_OUTPUT_HEADER);
		
		if(trans!=null) html = (String) trans.t(html);
		byte[] data = getBytes(html,enc);

		if(h!=null && !h.isEmpty())
			return new Object[]{"html",data,h};
		return new Object[]{"html",data};
	}
	
	
	
	private byte[] getBytes(String html, String enc) throws Exception
	{
		if(enc!=null) return html.getBytes(enc);
		return html.getBytes();
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
