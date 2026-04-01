package a.entity.gus06.sys.store.process.find.list2;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;


public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140907";}


	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
	}
	
	
	public Object g() throws Exception
	{return map;}
}
