package a.entity.gus06.sys.webserver1.web3.processor2.prepare.request.get;

import a.framework.*;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160219";}


	private Service stringToMap;

	public EntityImpl() throws Exception
	{stringToMap = Outside.service(this,"gus06.map.string.stringtomap.builder3");}

	
	public Object t(Object obj) throws Exception
	{
		String url = (String) obj;
		
		try
		{
			String[] n = url.split("\\?",2);
			if(n.length==1) return new HashMap();
		
			return stringToMap.t(n[1]);
		}
		catch(Exception e)
		{
			//String message = "Failed to parse GET params for url: "+url;
			//throw new Exception(message,e);
			
			return new HashMap();
		}
	}
}
