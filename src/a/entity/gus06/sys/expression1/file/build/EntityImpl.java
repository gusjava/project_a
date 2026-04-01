package a.entity.gus06.sys.expression1.file.build;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170310";}


	private Service currentDir;
	private Service currentDir0;
	private Service fileProvider;


	public EntityImpl() throws Exception
	{
		currentDir = Outside.service(this,"gus06.sys.script1.access.opmap.script.dir");
		currentDir0 = Outside.service(this,"gus06.system.prop.userdir.modify");
		fileProvider = Outside.service(this,"fileprovider");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String)
			return pathToFile((String) obj).getCanonicalFile();
		if(obj instanceof Map)
			return currentDir((Map) obj);
		if(obj instanceof Object[])
			return pathToFile((Object[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private File pathToFile(Object[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path = (String) o[0];
		Map opMap = (Map) o[1];
		
		if(path.startsWith("?")) return inferFile(path);
		if(path.startsWith("<")) return buildFile(path);
		
		File f = new File(path);
		if(f.isAbsolute()) return f;
		
		File dir = currentDir(opMap);
		return new File(dir,path).getCanonicalFile();
	}
	
	private File pathToFile(String path) throws Exception
	{
		if(path.startsWith("?")) return inferFile(path);
		return buildFile(path);
	}
	
	private File inferFile(String path) throws Exception
	{
		path = path.substring(1);
		
		File f = buildFile("C"+path);
		if(f.exists()) return f;
		
		f = buildFile("D"+path);
		if(f.exists()) return f;
		
		f = buildFile("E"+path);
		if(f.exists()) return f;
		
		f = buildFile("F"+path);
		if(f.exists()) return f;
		
		f = buildFile("G"+path);
		if(f.exists()) return f;
		
		f = buildFile("H"+path);
		if(f.exists()) return f;
		
		f = buildFile("I"+path);
		if(f.exists()) return f;
		
		f = buildFile("J"+path);
		if(f.exists()) return f;
		
		f = buildFile("K"+path);
		if(f.exists()) return f;
		
		return null;
	}
	
	private File buildFile(String path) throws Exception
	{
		return (File) fileProvider.r(formatPath(path));
	}
	
	private File currentDir(Map opMap) throws Exception
	{
		File dir = (File) currentDir.t(opMap);
		if(dir!=null) return dir;
		return (File) currentDir0.g();
	}
	
	
	private String formatPath(String s)
	{
		if(s.matches("[a-zA-Z]")) return s+":/";
		if(s.matches("[a-zA-Z]:")) return s+"/";
		return s;
	}
}