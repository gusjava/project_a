package a.entity.gus06.sys.filetool.findroot;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150324";}


	private Service pathBuilder;

	public static final String PATH_THIS = "path.this";
	public static final String PATH_ROOT = "path.root";
	public static final String PATH_PARENT = "path.parent";
	
	
	
	public EntityImpl() throws Exception
	{
		pathBuilder = Outside.service(this,"gus06.app.path.build.fromprop");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) pathBuilder.t(obj);
		return findRoot(map);
	}
	
	
	private File findRoot(Map map)
	{
		if(map.containsKey(PATH_ROOT))
			return (File) map.get(PATH_ROOT);
			
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
