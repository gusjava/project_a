package a.entity.gus06.app.entity.checkname;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20140828";}

	private Service findListing;

	public EntityImpl() throws Exception
	{
		findListing = Outside.service(this,"gus06.app.entity.listing");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String name = (String) obj;
		List listing = (List) findListing.g();
		return listing.contains(name);
	}
}
