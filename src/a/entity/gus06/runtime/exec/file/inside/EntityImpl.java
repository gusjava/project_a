package a.entity.gus06.runtime.exec.file.inside;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180220";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		File root = (File) o[1];
		
		if(root==null) return Runtime.getRuntime().exec(p(file));
		if(!root.isDirectory()) throw new Exception("Invalid root dir: "+root);
		
		return Runtime.getRuntime().exec(p(file),null,root);
	}
	
	
	private String p(File f)
	{return "\""+f.getAbsolutePath()+"\"";}
}
