package a.entity.gus06.java.introspection.gui.selector.buildkeys;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140819";}

	private Service finder;
	private Service filterBuilder;

	public EntityImpl() throws Exception
	{
		finder = Outside.service(this,"gus06.java.searchclass.finder");
		filterBuilder = Outside.service(this,"gus06.filter.string.simple1.one");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String rule = (String) obj;
		F filter = (F) filterBuilder.t(rule);
		return finder.t(filter);
	}
}