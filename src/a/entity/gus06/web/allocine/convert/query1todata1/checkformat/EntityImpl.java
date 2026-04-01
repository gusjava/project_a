package a.entity.gus06.web.allocine.convert.query1todata1.checkformat;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201021";}

	public static final String[] FIELDS = new String[]{
		"actors","blurayreleasedate","code","directors",
		"dvdreleasedate","genre","link","moviecertificate",
		"movietype","nationality","originaltitle","poster",
		"pressrating","productionyear","releasedate","runtime",
		"synopsisshort","title","trailer","universe","userrating"
	};
	
	
	public boolean f(Object obj) throws Exception
	{
		Map prop = (Map) obj;
		
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String field = (String) it.next();
			if(!isAllowed(field)) return false;
		}
		return true;
	}
	
	
	private boolean isAllowed(String field)
	{
		for(String f:FIELDS)
		if(f.equals(field)) return true;
		return false;
	}
}
