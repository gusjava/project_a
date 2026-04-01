package a.entity.gus06.sys.filemanagement1.gui.gui1_3.search.buildfilters.forname;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201124";}


	private Service buildFilterAOT;
	private Service buildFilterAOTn;
	private Service buildFilterCo;
	private Service buildFilterCon;
	
	public EntityImpl() throws Exception
	{
		buildFilterAOT = Outside.service(this,"gus06.filter.string.build.allofthem");
		buildFilterAOTn = Outside.service(this,"gus06.filter.string.build.allofthem_n");
		buildFilterCo = Outside.service(this,"gus06.filter.string.build.contains");
		buildFilterCon = Outside.service(this,"gus06.filter.string.build.contains_n");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		
		boolean strict = false;
		if(input.startsWith("!"))
		{
			strict = true;
			input = input.substring(1);
		}
		
		boolean fullTerm = false;
		if(input.startsWith("'"))
		{
			fullTerm = true;
			input = input.substring(1);
		}
		
		if(fullTerm)
		{
			if(strict) return buildFilterCo.t(input);
			return buildFilterCon.t(input);
		}
		if(strict) return buildFilterAOT.t(input);
		return buildFilterAOTn.t(input);
	}
}