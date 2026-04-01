package a.entity.gus06.string.transform.app.entity.listing.en;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151124";}


	private Service getListing;
	private Service listToString;


	public EntityImpl() throws Exception
	{
		getListing = Outside.service(this,"gus06.app.jarfile.listing.entities.filter.en");
		listToString = Outside.service(this,"gus06.tostring.list");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) getListing.t(obj);
		return listToString.t(list);
	}
}
