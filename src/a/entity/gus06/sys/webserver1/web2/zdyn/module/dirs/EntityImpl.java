package a.entity.gus06.sys.webserver1.web2.zdyn.module.dirs;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140930";}


	private File rootDir;

	public EntityImpl() throws Exception
	{
		rootDir = (File) Outside.resource(this,"path#path.web2.dir.spaces");
		rootDir.mkdirs();
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String space = o[0];
		String site = o[1];
		
		Map map = new HashMap();
		
		File dir_spaces = rootDir;
		File dir_space = new File(dir_spaces,space);
		File dir_shared = new File(dir_space,"shared");
		File dir_sites = new File(dir_space,"sites");
		File dir_site = new File(dir_sites,site);
		
		File dir_config0 = new File(dir_shared,"config");
		File dir_config1 = new File(dir_site,"config");
		
		File dir_resource0 = new File(dir_shared,"resource");
		File dir_resource1 = new File(dir_site,"resource");
		
		map.put("spaces",dir_spaces);
		map.put("space",dir_space);
		map.put("shared",dir_shared);
		map.put("sites",dir_sites);
		map.put("site",dir_site);
		
		map.put("config0",dir_config0);
		map.put("config1",dir_config1);
		
		map.put("resource0",dir_resource0);
		map.put("resource1",dir_resource1);
		
		return map;
	}
}
