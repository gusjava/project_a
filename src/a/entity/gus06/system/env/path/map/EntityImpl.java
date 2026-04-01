package a.entity.gus06.system.env.path.map;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.io.FileFilter;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170312";}
	
	public static final FileFilter FILTER = new FileFilter() {
		public boolean accept(File f)
		{
			if(!f.isFile()) return false;
			String name = f.getName().toLowerCase();
			return name.endsWith(".exe") || name.endsWith(".cmd") || name.endsWith(".bat");
		}
	};

	private Service getFiles;
	private Service getName;

	public EntityImpl() throws Exception
	{
		getFiles = Outside.service(this,"gus06.system.env.path.files");
		getName = Outside.service(this,"gus06.file.getname0");
	}
	
	public Object g() throws Exception
	{
		File[] ff = (File[]) getFiles.g();
		Map map = new HashMap();
		
		for(File f:ff)
		{
			File[] files = f.listFiles(FILTER);
			if(files!=null) for(File file:files)
			{
				String name = (String) getName.t(file);
				map.put(name,file);
			}
		}
		
		return map;
	}
}
