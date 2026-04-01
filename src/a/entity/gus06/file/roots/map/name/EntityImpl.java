package a.entity.gus06.file.roots.map.name;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20191202";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.dir.hdd.drivername");
	}
	
	public Object g() throws Exception
	{
		File[] roots = File.listRoots();
		Map map = new HashMap();
		for(File root : roots)
		{
			String info = (String) perform.t(root);
			map.put(info,root);
		}
		return map;
	}
}
