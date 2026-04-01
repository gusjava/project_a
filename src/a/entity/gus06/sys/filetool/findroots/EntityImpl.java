package a.entity.gus06.sys.filetool.findroots;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200310";}

	public static final String PATH_THIS = "path.this";
	public static final String PATH_ROOT = "path.root";
	public static final String PATH_PARENT = "path.parent";



	private Service pathBuilder;
	
	public EntityImpl() throws Exception
	{
		pathBuilder = Outside.service(this,"gus06.app.path.build.fromprop");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) pathBuilder.t(obj);
		return findRoot(map);
	}
	
	
	private Object findRoot(Map map)
	{
		if(map.containsKey(PATH_ROOT))
			return (File) map.get(PATH_ROOT);
		
		int index = 1;
		List roots = new ArrayList();
		while(map.containsKey(PATH_ROOT+index))
		{
			roots.add((File) map.get(PATH_ROOT+index));
			index++;
		}
		if(!roots.isEmpty()) return roots;
			
		File r = findRootFromThis(map);
		if(r!=null) return r;
		
		return (File) map.get(PATH_PARENT);
	}
	
	
	
	private File findRootFromThis(Map map)
	{
		File f = (File) map.get(PATH_THIS);
		String name = f.getName().split("\\.")[0];
		File p = f.getParentFile();
		File dir = new File(p,name);
		
		if(!dir.isDirectory()) return null;
		return dir;
	}
}
