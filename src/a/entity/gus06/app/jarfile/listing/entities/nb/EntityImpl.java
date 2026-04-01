package a.entity.gus06.app.jarfile.listing.entities.nb;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140829";}


	private Service listing;


	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.app.jarfile.listing.entities");
	}
	
	
	public Object g() throws Exception
	{
		List list = (List) listing.g();
		return ""+list.size();
	}
}
