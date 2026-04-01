package a.entity.gus06.sys.webserver1.web2.zdyn.module.error;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140930";}
	
	
	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		
		String message = (String) data.get("err_message");
		String space = (String) data.get("space");
		String site = (String) data.get("site");
		
		StringBuffer b = new StringBuffer();
		b.append("<ul>");
		b.append("<li><b>Current space:</b> "+space+"</li>");
		b.append("<li><b>Current site:</b> "+site+"</li>");
		b.append("<li><b>Error:</b> "+message+"</li>");
		b.append("</ul>");
		
		return b.toString();
	}
}
