package a.entity.gus06.sys.filemanagement1.gui.gui1_3.search.buildresults;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201124";}


	private Service buildFileMap;
	private Service buildFilters;
	private Service buildFilterAdvanced;
	private Service handleSearchOne;
	private Service handleSearchByRow;
	
	public EntityImpl() throws Exception
	{
		buildFileMap = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.latest");
		buildFilters = Outside.service(this,"gus06.sys.filemanagement1.gui.gui1_3.search.buildfilters");
		buildFilterAdvanced = Outside.service(this,"gus06.sys.expression1.builder1a.f");
		handleSearchOne = Outside.service(this,"gus06.sys.filemanagement1.tool.search.one");
		handleSearchByRow = Outside.service(this,"gus06.sys.filemanagement1.tool.search.byrow");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String input = (String) o[1];
		
		if(input.startsWith(">"))
			return handleAdvanced(engine, input.substring(1));
		return handleSimple(engine, input);
	}
	
	
	private Object handleSimple(Object engine, String input) throws Exception
	{
		Map fileMap = (Map) buildFileMap.t(engine);
		F[] filters = (F[]) buildFilters.t(input);
		return handleSearchOne.t(new Object[]{fileMap,filters});
	}
	
	private Object handleAdvanced(Object engine, String input) throws Exception
	{
		Map fileMap = (Map) buildFileMap.t(engine);
		F filter = (F) buildFilterAdvanced.t(":"+input);
		return handleSearchByRow.t(new Object[]{fileMap,filter});
	}
}