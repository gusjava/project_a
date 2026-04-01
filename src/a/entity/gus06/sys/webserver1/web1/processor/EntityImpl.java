package a.entity.gus06.sys.webserver1.web1.processor;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140928";}
	
	
	public static final int CODE_FAILED = 500;
	public static final int CODE_NOTFOUND = 404;
	public static final int CODE_OK = 200;

	public static final String KEY_CODE = "code";
	public static final String KEY_TYPE = "type";
	public static final String KEY_VERSION = "version";
	public static final String KEY_CONTENT = "content";
	
	


	private Service findFile;
	private Service dynEngine;
	private Service getExtension;
	private Service readFile;
	private Service toByteArray;
	
	
	
	public EntityImpl() throws Exception
	{
		findFile = Outside.service(this,"gus06.sys.webserver1.web1.processor.findfile");
		dynEngine = Outside.service(this,"gus06.sys.webserver1.web1.dynengine");
		getExtension = Outside.service(this,"gus06.file.getextension");
		readFile = Outside.service(this,"gus06.file.read.raw");
		toByteArray = Outside.service(this,"gus06.find.bytearray");
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		try
		{
			Map input = (Map) obj;
			File file = (File) findFile.t(input);
			if(file==null) return outputErr(CODE_NOTFOUND);
			
			String type = type(file);
			byte[] data = content(file,type,input);
			return output(CODE_OK,type,data);
		}
		catch(Exception e)
		{
			Outside.err(this,"t(Object)",e);
			return outputErr(CODE_FAILED);
		}
	}
	
	
	
	private String type(File file) throws Exception
	{return (String) getExtension.t(file);}
	
	
	
	private byte[] content(File file, String type, Map input) throws Exception
	{
		T t = (T) dynEngine.t(type);
		if(t==null) return (byte[]) readFile.t(file);
		
		Object result = t.t(new Object[]{file,input});
		return (byte[]) toByteArray.t(result);
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
