package a.entity.gus06.app.entity.listing;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140828";}

	private Service listingDev;
	private Service listingInside;


	public EntityImpl() throws Exception
	{
		listingDev = Outside.service(this,"gus06.entitydev.listing1");
		listingInside = Outside.service(this,"gus06.app.jarfile.listing.entities");
	}
	
	
	public Object g() throws Exception
	{
		List list1 = (List) listingDev.g();
		List list2 = (List) listingInside.g();
		
		List all = new ArrayList();
		addUnique(all,list1);
		addUnique(all,list2);
		
		Collections.sort(all);
		return all;
	}
	
	
	
	
	private void addUnique(List all, List l)
	{for(Object o:l) if(!all.contains(o)) all.add(o);}
}
