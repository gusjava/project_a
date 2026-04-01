package a.entity.gus06.sys.webserver1.processor;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140929";}
	
	
	public static final String KEY = "app.webserver.processor";
	
	
	private Service processor1;
	private Service processor2;
	private Service processor3;
	
	private Map prop;
	
	
	public EntityImpl() throws Exception
	{
		processor1 = Outside.service(this,"gus06.sys.webserver1.web1.processor");
		processor2 = Outside.service(this,"gus06.sys.webserver1.web2.processor");
		processor3 = Outside.service(this,"gus06.sys.webserver1.web3.processor");
		
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{return s().t(obj);}
	
	
	
	
	
	private Service s() throws Exception
	{
		String id = get(KEY);
		if(id==null) return processor1;
		
		if(id.equals("1")) return processor1;
		if(id.equals("2")) return processor2;
		if(id.equals("3")) return processor3;
		
		throw new Exception("Invalid processor id: "+id);
	}
	
	
	
	private String get(String key)
	{
		if(!prop.containsKey(key)) return null;
		return (String) prop.get(key);
	}
}
