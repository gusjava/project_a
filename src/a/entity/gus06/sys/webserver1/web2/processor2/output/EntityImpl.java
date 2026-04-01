package a.entity.gus06.sys.webserver1.web2.processor2.output;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140930";}
	
	public static final String KEY_OUTPUT = "output";
	

	private Service handleFile;
	private Service handleHtml;

	public EntityImpl() throws Exception
	{
		handleFile = Outside.service(this,"gus06.sys.webserver1.web2.processor2.output.file");
		handleHtml = Outside.service(this,"gus06.sys.webserver1.web2.processor2.output.html");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map main = (Map) obj;
		Object output = get(main,KEY_OUTPUT);
		if(output==null) return null;
		
		if(output instanceof File)
			return handleFile.t(main);
		return handleHtml.t(main);
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
