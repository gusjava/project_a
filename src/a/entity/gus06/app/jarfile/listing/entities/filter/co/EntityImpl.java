package a.entity.gus06.app.jarfile.listing.entities.filter.co;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151024";}


	private Service listFilter;
	private Service listing;
	private Service filterBuilder;


	public EntityImpl() throws Exception
	{
		listFilter = Outside.service(this,"gus06.list.findall");
		listing = Outside.service(this,"gus06.app.jarfile.listing.entities");
		filterBuilder = Outside.service(this,"gus06.filter.string.build.contains");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		List list = (List) listing.g();
		
		F filter = (F) filterBuilder.t(s);
		return listFilter.t(new Object[]{list,filter});
	}
}
