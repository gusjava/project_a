package a.entity.gus06.sys.webserver1.web2.processor;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140929";}
	
	
	public static final int CODE_FAILED = 500;
	public static final int CODE_NOTFOUND = 404;
	public static final int CODE_OK = 200;

	public static final String KEY_CODE = "code";
	public static final String KEY_TYPE = "type";
	public static final String KEY_VERSION = "version";
	public static final String KEY_CONTENT = "content";
	
	


	private Service processor2;
	private Service toByteArray;
	
	public EntityImpl() throws Exception
	{
		processor2 = Outside.service(this,"gus06.sys.webserver1.web2.processor2");
		toByteArray = Outside.service(this,"gus06.find.bytearray");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		try
		{
			Object[] o = (Object[]) processor2.t(obj);
			if(o==null) return outputErr(CODE_NOTFOUND);
			
			String type = (String) o[0];
			byte[] data = (byte[]) toByteArray.t(o[1]);
			
			Map m = output(CODE_OK,type,data);
			if(o.length==3) m.putAll((Map) o[2]);
			return m;
		}
		catch(Exception e)
		{
			Outside.err(this,"t(Object)",e);
			return outputErr(CODE_FAILED);
		}
	}
	
	
	
	
	
	private Map outputErr(int code)
	{return output(code,"Error code: "+code);}
	
	private Map output(int code, String message)
	{return output(code,"",message.getBytes());}
	
	private Map output(int code, String type, byte[] data)
	{
		Map m = new HashMap();
		m.put(KEY_CODE,""+code);
		m.put(KEY_TYPE,type);
		m.put(KEY_VERSION,"HTTP/1.0");
		m.put(KEY_CONTENT,data);
		return m;
	}
}
