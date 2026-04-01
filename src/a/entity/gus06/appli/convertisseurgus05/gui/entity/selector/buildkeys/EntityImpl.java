package a.entity.gus06.appli.convertisseurgus05.gui.entity.selector.buildkeys;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150525";}

	private Service listing;
	private Service listFilter;
	
	private List list;

	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.appli.convertisseurgus05.data.gus05.listing");
		listFilter = Outside.service(this,"gus06.list.filter.rule.one");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String rule = (String) obj;
		initList();
		return listFilter.t(new Object[]{list,rule});
	}
	
	
	private void initList() throws Exception
	{if(list==null) list = (List) listing.g();}
}
