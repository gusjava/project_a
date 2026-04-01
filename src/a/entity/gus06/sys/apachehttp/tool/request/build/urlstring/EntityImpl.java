package a.entity.gus06.sys.apachehttp.tool.request.build.urlstring;

import a.framework.*;
import java.util.Map;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191030";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) throw new Exception("Null url found");
		if(obj instanceof String) return (String) obj;
		if(obj instanceof URL) return ((URL) obj).toString();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
