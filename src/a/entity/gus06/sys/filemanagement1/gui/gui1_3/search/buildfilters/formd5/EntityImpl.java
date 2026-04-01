package a.entity.gus06.sys.filemanagement1.gui.gui1_3.search.buildfilters.formd5;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201124";}


	private Service buildFilterEq;
	
	public EntityImpl() throws Exception
	{
		buildFilterEq = Outside.service(this,"gus06.filter.string.build.equals");
	}
	
	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		return isMd5(input) ? buildFilterEq.t(input) : null;
	}
	
	private boolean isMd5(String input)
	{return input.matches("[0-9A-F]{32}");}
}