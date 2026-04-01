package a.entity.gus06.appli.gusclient1.project.config.entities.listing;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Iterator;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150601";}


	private Service initial1;
	private Service initial2;
	private Service entityListing;
	private Service extract;


	public EntityImpl() throws Exception
	{
		initial1 = Outside.service(this,"gus06.appli.gusclient1.project.config.entities.listing0.f1");
		initial2 = Outside.service(this,"gus06.appli.gusclient1.project.config.entities.listing0.f2");
		entityListing = Outside.service(this,"gus06.entitydev.listing1");
		extract = Outside.service(this,"gus06.entitydev.retrieve.dependencies");
	}
	
	
	public Object g() throws Exception
	{
		List listing = (List) entityListing.g();
		
		F f1 = (F) initial1.g();
		F f2 = (F) initial2.g();
		
		Set set = new HashSet();
		for(int i=0;i<listing.size();i++)
		{
			String name = (String) listing.get(i);
			if(f1.f(name) || f2.f(name))
			handle(set,listing,name);
		}
		return set;
	}
	
	
	
	
	private void handle(Set set, List listing, String name) throws Exception
	{
		if(!listing.contains(name)) return;
		if(set.contains(name)) return;
		
		set.add(name);
		
		Set dep = (Set) extract.t(name);
		Iterator it = dep.iterator();
		while(it.hasNext())
		{handle(set,listing,(String) it.next());}
	}
}
