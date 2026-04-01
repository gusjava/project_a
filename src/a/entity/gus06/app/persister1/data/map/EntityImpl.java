package a.entity.gus06.app.persister1.data.map;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, V, R {

	public String creationDate() {return "20160511";}


	private Service persister;


	public EntityImpl() throws Exception
	{persister = Outside.service(this,"gus06.app.persister1");}
	
	
	
	public Object r(String key) throws Exception
	{
		Object data = persister.r(key);
		if(data==null) return null;
		if(data instanceof Map) return data;
		return null;
	}
	
	
	public void v(String key, Object obj) throws Exception
	{persister.v(key,(Map) obj);}
}
