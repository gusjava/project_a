package a.entity.gus06.app.statemap;

import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140705";}


	private Map map;
	
	public EntityImpl() throws Exception
	{
	}
	
	
	public Object g() throws Exception
	{
		if(map==null) init();
		return map;
	}
	
	
	
	private void init() throws Exception
	{
		map = new HashMap();
		
		map.put("threadnumber",""+Thread.activeCount());
		map.put("jvm_freemem",""+Runtime.getRuntime().freeMemory());
		map.put("jvm_totalmem",""+Runtime.getRuntime().totalMemory());
		map.put("jvm_maxmem",""+Runtime.getRuntime().maxMemory());
		map.put("procnumber",""+Runtime.getRuntime().availableProcessors());
	}
}
