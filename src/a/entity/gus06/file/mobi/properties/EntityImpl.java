package a.entity.gus06.file.mobi.properties;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191103";}


	private Service extractData;
	private Service buildMetaData;

	public EntityImpl() throws Exception
	{
		extractData = Outside.service(this,"gus06.file.mobi.extract.data");
		buildMetaData = Outside.service(this,"gus06.file.mobi.build.metadata");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		Map data = (Map) extractData.t(file);
		Map metadata = (Map) buildMetaData.t(data);
		
		return metadata;
	}
}
