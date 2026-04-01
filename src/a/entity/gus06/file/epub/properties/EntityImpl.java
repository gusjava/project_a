package a.entity.gus06.file.epub.properties;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191103";}


	private Service extractData;
	private Service buildMetaData;

	public EntityImpl() throws Exception
	{
		extractData = Outside.service(this,"gus06.file.epub.extract.data.v2");
		buildMetaData = Outside.service(this,"gus06.file.epub.build.metadata");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		Map data = (Map) extractData.t(file);
		Map metadata = (Map) buildMetaData.t(data);
		
		return metadata;
	}
}