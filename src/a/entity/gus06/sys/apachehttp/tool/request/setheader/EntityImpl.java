package a.entity.gus06.sys.apachehttp.tool.request.setheader;

import a.framework.*;
import org.apache.http.client.methods.HttpRequestBase;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191030";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		HttpRequestBase request = (HttpRequestBase) o[0];
		Map header = (Map) o[1];
		
		if(header==null) return;
		
		Iterator it = header.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) header.get(key);
			request.addHeader(key,value);
		}
	}
}
