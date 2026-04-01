package a.entity.gus06.app.jarfile.listing.entities.contains;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160711";}


	private Service listing;

	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.app.jarfile.listing.entities");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		List list = (List) listing.g();
		return list.contains(s);
	}
}
