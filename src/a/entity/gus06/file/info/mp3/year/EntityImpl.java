package a.entity.gus06.file.info.mp3.year;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191226";}
	
	public static final String KEY = "year";


	private Service extractProp;

	public EntityImpl() throws Exception
	{
		extractProp = Outside.service(this,"gus06.file.mp3.extract.prop");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Map prop = (Map) extractProp.t(file);
		return prop.containsKey(KEY) ? prop.get(KEY) : null;
	}
}
