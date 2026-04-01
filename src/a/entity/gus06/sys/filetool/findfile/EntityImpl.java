package a.entity.gus06.sys.filetool.findfile;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201115";}

	public static final String PATH_THIS = "path.this";
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		String path = (String) map.get(PATH_THIS);
		return new File(path);
	}
}