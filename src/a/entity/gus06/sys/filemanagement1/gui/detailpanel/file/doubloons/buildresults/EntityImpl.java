package a.entity.gus06.sys.filemanagement1.gui.detailpanel.file.doubloons.buildresults;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250610";}


	private Service buildFileMap;
	private Service buildFilters;
	private Service handleSearchOne;
	
	public EntityImpl() throws Exception
	{
		buildFileMap = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.latest");
		buildFilters = Outside.service(this,"gus06.sys.filemanagement1.gui.gui1_3.search.buildfilters");
		handleSearchOne = Outside.service(this,"gus06.sys.filemanagement1.tool.search.one");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String input = (String) o[1];
		
		Map fileMap = (Map) buildFileMap.t(engine);
		F[] filters = (F[]) buildFilters.t(input);
		return handleSearchOne.t(new Object[]{fileMap,filters});
	}
}