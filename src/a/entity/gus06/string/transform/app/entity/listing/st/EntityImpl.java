package a.entity.gus06.string.transform.app.entity.listing.st;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151024";}


	private Service getListing;
	private Service listToString;


	public EntityImpl() throws Exception
	{
		getListing = Outside.service(this,"gus06.app.jarfile.listing.entities.filter.st");
		listToString = Outside.service(this,"gus06.tostring.list");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) getListing.t(obj);
		return listToString.t(list);
	}
}
