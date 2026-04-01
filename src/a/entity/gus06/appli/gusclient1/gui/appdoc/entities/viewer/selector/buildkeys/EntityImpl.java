package a.entity.gus06.appli.gusclient1.gui.appdoc.entities.viewer.selector.buildkeys;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140828";}

	private Service listing;
	private Service listFilter;

	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.app.jarfile.listing.entities");
		listFilter = Outside.service(this,"gus06.list.filter.rule.one");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String rule = (String) obj;
		List list = (List) listing.g();
		return listFilter.t(new Object[]{list,rule});
	}
}
