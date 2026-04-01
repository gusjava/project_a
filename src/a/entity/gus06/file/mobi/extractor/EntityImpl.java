package a.entity.gus06.file.mobi.extractor;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191008";}


	private Service extractData;
	private Service buildMetaData;
	private Service buildCoverFinder;
	private Service buildThumbnailFinder;


	public EntityImpl() throws Exception
	{
		extractData = Outside.service(this,"gus06.file.mobi.extract.data");
		buildMetaData = Outside.service(this,"gus06.file.mobi.build.metadata");
		buildCoverFinder = Outside.service(this,"gus06.file.mobi.build.coverfinder");
		buildThumbnailFinder = Outside.service(this,"gus06.file.mobi.build.thumbnailfinder");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		Map data = (Map) extractData.t(file);
		Map metadata = (Map) buildMetaData.t(data);
		G coverG = (G) buildCoverFinder.t(data);
		G thumbnailG = (G) buildThumbnailFinder.t(data);
		
		Map map = new HashMap();
		map.put("data",data);
		map.put("metadata",metadata);
		map.put("coverG",coverG);
		map.put("thumbnailG",thumbnailG);
		
		return map;
	}
}
