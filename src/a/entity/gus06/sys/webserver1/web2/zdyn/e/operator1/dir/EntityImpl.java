package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator1.dir;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141003";}
	

	private int rootLength;

	public EntityImpl() throws Exception
	{
		File root = (File) Outside.resource(this,"path#path.web2.dir.spaces");
		rootLength = root.getAbsolutePath().length()+1;
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		
		Map dirs = (Map) mr.r("data dirs");
		
		if(!dirs.containsKey(info)) return "?";
		File dir = (File) dirs.get(info);
		String path = dir.getAbsolutePath();
		return path.substring(rootLength).replace(File.separator,"/");
	}
}
