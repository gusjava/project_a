package a.entity.gus06.sys.webserver1.web2.zdyn.e.dir.locator;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141001";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String name = (String) o[1];
		
		File dir_shared = (File) mr.r("data dirs shared");
		File dir_site = (File) mr.r("data dirs site");
		
		File dir0 = new File(dir_shared,name);
		File dir1 = new File(dir_site,name);
		
		dir0.mkdirs();
		dir1.mkdirs();
		
		return new File[]{dir0,dir1};
	}
}
