package a.entity.gus06.sys.expression1.apply.op._dir_s;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191013";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		if(value instanceof String) return dirS((String) value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	private File dirS(String path) throws Exception
	{
		File f = new File(path);
		if(f.isDirectory()) return f;
		
		File[] roots = File.listRoots();
		for(File root : roots)
		{
			char letter = root.getAbsolutePath().charAt(0);
			String path1 = letter + path.substring(1,path.length());
			File f1 = new File(path1);
			if(f1.isDirectory()) return f1;
		}
		
		throw new Exception("Directory not found at path: "+f);
	}
}
