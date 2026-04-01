package a.entity.gus06.sys.xhtml1.resolve.file;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220908";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File root = (File) o[0];
		File file = (File) o[1];
		String src = (String) o[2];
		
		return resolveFile(root, file, src);
	}
	
	private File resolveFile(File root, File file, String src)
	{
		if(src.startsWith("/")) return resolveAbsolutePath(root,src);
		return resolveRelativePath(file,src);
	}
	
	private File resolveAbsolutePath(File root, String src)
	{
		String relPath = src.substring(1).replace("/",File.separator);
		return new File(root,relPath);
	}
	
	private File resolveRelativePath(File file, String src)
	{
		File dir = file.getParentFile();
		while(src.startsWith("../"))
		{
			dir = dir.getParentFile();
			src = src.substring(3);
		}
		if(src.startsWith("./"))
		{
			src = src.substring(2);
		}
		String relPath = src.replace("/",File.separator);
		return new File(dir,relPath);
	}
}