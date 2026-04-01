package a.entity.gus06.sys.xhtml1.tool.datamap.count;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221016";}

	public static final String KEY_FILE = "file";
	public static final String KEY_CHILDREN = "children";
	
	
	public Object t(Object obj) throws Exception
	{
		Map m = (Map) obj;
		if(m==null) return null;
		
		int count = 0;
		count += countNodes(m);
		return Integer.valueOf(count);
	}
	
	
	private int countNodes(Map m)
	{
		File file = (File) m.get(KEY_FILE);
		int count = 1;
		if(m.containsKey(KEY_CHILDREN))
		{
			List children = (List) m.get(KEY_CHILDREN);
			for(int i=0;i<children.size();i++)
			count += countNodes((Map) children.get(i));
		}
		return count;
	}
}