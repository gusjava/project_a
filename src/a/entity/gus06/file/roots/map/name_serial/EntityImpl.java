package a.entity.gus06.file.roots.map.name_serial;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20191126";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.dir.hdd.name_serial");
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
