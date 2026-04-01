package a.entity.gus06.sys.script1.tool.build.file;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170311";}


	private Service currentDir;
	private Service currentDir0;


	public EntityImpl() throws Exception
	{
		currentDir = Outside.service(this,"gus06.sys.script1.access.context.script.dir");
		currentDir0 = Outside.service(this,"gus06.system.prop.userdir.modify");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path = (String) o[0];
		Map context = (Map) o[1];
		
		File f = new File(path);
		if(f.isAbsolute()) return f.getCanonicalFile();
		
		File dir = (File) currentDir.t(context);
		if(dir==null) dir = (File) currentDir0.g();
		
		return new File(dir,path).getCanonicalFile();
	}
}
