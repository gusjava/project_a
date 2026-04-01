package a.entity.gus06.sys.webserver1.format.output;

import a.framework.*;
import java.util.Map;
import java.nio.ByteBuffer;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140928";}

	public static final String KEY_CONTENT = "content";
	
	
	
	private Service buildHeader;
	
	

	public EntityImpl() throws Exception
	{
		buildHeader = Outside.service(this,"gus06.sys.webserver1.format.output.header");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		byte[] header = (byte[]) buildHeader.t(map);
		byte[] content = (byte[]) get(map,KEY_CONTENT);
		
		if(content==null) return header;
		return sum(header,content);
	}
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	
	
	private byte[] sum(byte[] b1, byte[] b2)
	{
		byte[] data = new byte[b1.length + b2.length];
		System.arraycopy(b1,0,data,0,b1.length);
		System.arraycopy(b2,0,data,b1.length,b2.length);
		return data;
	}
}
