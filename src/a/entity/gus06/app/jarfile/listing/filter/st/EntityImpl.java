package a.entity.gus06.app.jarfile.listing.filter.st;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180129";}


	private Service listFilter;
	private Service listing;
	private Service filterBuilder;


	public EntityImpl() throws Exception
	{
		listFilter = Outside.service(this,"gus06.list.findall");
		listing = Outside.service(this,"gus06.app.jarfile.listing");
		filterBuilder = Outside.service(this,"gus06.filter.string.build.startswith");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		List list = (List) listing.g();
		
		F filter = (F) filterBuilder.t(s);
		return listFilter.t(new Object[]{list,filter});
	}
}
