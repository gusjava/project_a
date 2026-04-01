package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator2.file.filepath;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141104";}
	
	public static final String RESOURCE = "resource";
	public static final String S = File.separator;
	


	private Service findVar;
	private String root;

	public EntityImpl() throws Exception
	{
		findVar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");
		
		File dir = (File) Outside.resource(this,"path#path.web2.dir.spaces");
		dir.mkdirs();
		root = dir.getAbsolutePath();
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		Map args = (Map) o[2];
		Map vars = (Map) o[3];
		
		File file = (File) findVar.t(new Object[]{vars,info});
		if(file==null) throw new Exception("Invalid null variable: "+info);
		
		String path = file.getAbsolutePath();
		if(!path.startsWith(root)) return null;
		path = path.substring(root.length());
		if(!path.contains(S+RESOURCE+S)) return null;
		
		if(path.startsWith(S)) path = path.substring(1);
		path = path.replace(S,"/");
		
		return path;
	}
}
