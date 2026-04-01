package a.entity.gus06.file.epub.extractor;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191009";}

	private Service extractData;
	private Service buildMetaData;
	private Service buildCoverFinder;

	public EntityImpl() throws Exception
	{
		extractData = Outside.service(this,"gus06.file.epub.extract.data.v2");
		buildMetaData = Outside.service(this,"gus06.file.epub.build.metadata");
		buildCoverFinder = Outside.service(this,"gus06.file.epub.build.coverfinder");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		Map data = (Map) extractData.t(file);
		Map metadata = (Map) buildMetaData.t(data);
		G coverG = (G) buildCoverFinder.t(data);
		
		Map map = new HashMap();
		map.put("data",data);
		map.put("metadata",metadata);
		map.put("coverG",coverG);
		
		return map;
	}
}