package a.entity.gus06.swing.textcomp.cust.action.ctrl_h.tool.perform.chooser.names;

import a.framework.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160504";}

	public static final String ENTITYSTART = "gus.string.transform.";
	
	
	private Service entityListing;
	private Service entityunique;
	
	private Map map;
	

	public EntityImpl() throws Exception
	{
		entityListing = Outside.service(this,"gus06.app.jarfile.listing.entities.filter.st");
		entityunique = Outside.service(this,"entityunique");
	}
	
	
	public synchronized Object g() throws Exception
	{
		if(map==null) initMap();
		return new HashMap(map);
	}
	
	
	private void initMap() throws Exception
	{
		List list = (List) entityListing.t(ENTITYSTART);
		
		map = new HashMap();
		for(int i=0;i<list.size();i++)
		{
			String name = (String) list.get(i);
			String key = formatName(name);
			Object entity = getEntity(name);
			
			map.put(key,entity);
		}
	}
	
	
	private String formatName(String name)
	{return name.substring(ENTITYSTART.length()).replace(".","_");}
	
	private Object getEntity(String name) throws Exception
	{return entityunique.t(name);}
}
