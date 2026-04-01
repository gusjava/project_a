package a.entity.gus06.sys.filemanagement1.tool.treemap.children.filetype;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200418";}

	public static final String KEY_TYPE = "type";
	public static final String TYPE_FILE = "file";

	
	
	public Object t(Object obj) throws Exception
	{
		List children = new ArrayList((List) obj);
		Iterator it = children.iterator();
		while(it.hasNext())
		{
			Map child = (Map) it.next();
			String type = (String) child.get(KEY_TYPE);
			if(!type.equals(TYPE_FILE)) it.remove();
		}
		return children;
	}
}
