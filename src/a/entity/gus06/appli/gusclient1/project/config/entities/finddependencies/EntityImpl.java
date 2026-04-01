package a.entity.gus06.appli.gusclient1.project.config.entities.finddependencies;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150312";}


	private Service initialExtraction;
	private Service entityListing;
	private Service extract;


	public EntityImpl() throws Exception
	{
		initialExtraction = Outside.service(this,"gus06.appli.gusclient1.project.config.entities.listing0");
		entityListing = Outside.service(this,"gus06.entitydev.listing1");
		extract = Outside.service(this,"gus06.entitydev.retrieve.dependencies");
	}
	
	
	public Object g() throws Exception
	{
		List listing = (List) entityListing.g();
		Set initial = (Set) initialExtraction.g();
		
		Map map = new HashMap();
		
		Iterator it = initial.iterator();
		while(it.hasNext())
		{
			String name0 = (String) it.next();
			if(listing.contains(name0))
			{
				addToMap(map,"*",name0);
				addDependencies(map,listing,name0);
			}
		}
		return map;
	}
	
	
	private void addToMap(Map map, String key, String value)
	{
		if(!map.containsKey(key)) map.put(key,new HashSet());
		Set s = (Set) map.get(key);
		s.add(value);
	}
	
	
	private void addDependencies(Map map, List listing, String name) throws Exception
	{
		if(map.containsKey(name)) return;
		
		Set dep = (Set) extract.t(name);
		Iterator it = dep.iterator();
		while(it.hasNext())
		{
			String name0 = (String) it.next();
			if(listing.contains(name0))
			{
				addToMap(map,name,name0);
				addDependencies(map,listing,name0);
			}
		}
	}
}
