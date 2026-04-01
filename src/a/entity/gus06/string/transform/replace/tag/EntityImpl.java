package a.entity.gus06.string.transform.replace.tag;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140826";}


	private Map prop;
	private Map path;
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
		path = (Map) Outside.resource(this,"path");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) prop.get(key);
			text = text.replace("{prop:"+key+"}",value);
		}
		
		it = path.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = path.get(key).toString();
			text = text.replace("{path:"+key+"}",value);
		}
		return text;
	}
}
