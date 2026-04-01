package a.entity.gus06.app.main.rb;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140925";}


	private Map main;
	
	public EntityImpl() throws Exception
	{main = (Map) Outside.resource(this,"main");}
	
	
	public Object g() throws Exception
	{
		Map m = new HashMap();
		Iterator it = main.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String[] n = key.split("\\.rb\\.");
			if(n.length==2) m.put(n[1],main.get(key));
		}
		return m;
	}
}
