package a.entity.gus06.file.properties.filetostring;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150620";}


	private Service readFile;
	private Service mapToString;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
		mapToString = Outside.service(this,"gus06.tostring.map1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) readFile.t(obj);
		return mapToString.t(map);
	}
}
