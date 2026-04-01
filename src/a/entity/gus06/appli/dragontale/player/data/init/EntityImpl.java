package a.entity.gus06.appli.dragontale.player.data.init;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20200516";}


	private Map map;
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		map.put("health","5");
		map.put("health_max","5");
		map.put("health_min","0");
		map.put("facingright","true");
	}

	
	public Object g() throws Exception
	{return map;}
}
