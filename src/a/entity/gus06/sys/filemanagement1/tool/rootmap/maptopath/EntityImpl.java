package a.entity.gus06.sys.filemanagement1.tool.rootmap.maptopath;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250614";}
	
	public static final String KEY_PATH = "PATH";
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		if(map==null) return null;
		return (String) map.get(KEY_PATH);
	}
}