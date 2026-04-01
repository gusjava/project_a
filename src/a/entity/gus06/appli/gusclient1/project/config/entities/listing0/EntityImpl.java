package a.entity.gus06.appli.gusclient1.project.config.entities.listing0;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150312";}


	private Service initial1;
	private Service initial2;
	private Service entityListing;


	public EntityImpl() throws Exception
	{
		initial1 = Outside.service(this,"gus06.appli.gusclient1.project.config.entities.listing0.f1");
		initial2 = Outside.service(this,"gus06.appli.gusclient1.project.config.entities.listing0.f2");
		entityListing = Outside.service(this,"gus06.entitydev.listing1");
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
			set.add(name);
		}
		return set;
	}
}
