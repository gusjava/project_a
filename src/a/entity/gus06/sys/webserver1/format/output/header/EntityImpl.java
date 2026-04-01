package a.entity.gus06.sys.webserver1.format.output.header;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140928";}

	
	private Service headerMap;
	private Service firstLine;
	
	public EntityImpl() throws Exception
	{
		headerMap = Outside.service(this,"gus06.sys.webserver1.format.output.header.map");
		firstLine = Outside.service(this,"gus06.sys.webserver1.format.output.header.first");
	}

	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String f = (String) firstLine.t(map);
		Map h = (Map) headerMap.t(map);
		
		StringBuffer b = new StringBuffer();
		b.append(f+"\r\n");
		
		Iterator it = h.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) h.get(key);
			b.append(key+": "+value+"\r\n");
		}
		
		b.append("\r\n");
		return b.toString().getBytes();
	}
}
