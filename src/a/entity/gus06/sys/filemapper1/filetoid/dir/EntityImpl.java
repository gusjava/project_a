package a.entity.gus06.sys.filemapper1.filetoid.dir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231127";}

	public static final String S = File.separator;
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File root = (File) o[0];
		String main = (String) o[1];
		File file = (File) o[2];
		
		return fileToId(root,main,file);
	}
	
	
	
	private String fileToId(File root, String main, File file) throws Exception
	{
		if(root==null || !root.isDirectory()) throw new Exception("Root undefined: " + root);
		if(file==null || !file.isFile()) throw new Exception("File undefined: " + file);
		
		String path = file.getAbsolutePath();
		String path0 = root.getAbsolutePath();
		
		if(!path.startsWith(path0)) return null;
		
		String path1 = path.substring(path0.length());
		if(!path1.startsWith(S)) path1 = S+path1;
		
		return pathToId(main,path1);
	}
	
	
	
	private String pathToId(String main, String path)
	{
		if(main==null) return path;
		if(!path.endsWith(S+main)) return path;
		
		int l = path.length()-main.length()-1;
		path = path.substring(0,l).replace(S,".");
		
		if(path.startsWith(".")) path = path.substring(1);
		return path;
	}
}
