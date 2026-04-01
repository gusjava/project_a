package a.entity.gus06.java.srccode.inferpackage;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251219";}

	private Service inferRoot;

	public EntityImpl() throws Exception
	{
		inferRoot = Outside.service(this,"gus06.java.srcdir.infer");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		File packageDir = file.getParentFile();
		
		File rootDir = (File) inferRoot.t(packageDir);
		if(rootDir==null) return null;
		
		String rootPath = rootDir.getAbsolutePath();
		String packagePath = packageDir.getAbsolutePath();
		
		if(!packagePath.startsWith(rootPath)) return null;
		if(rootPath.equals(packagePath)) return null;
		
		return packagePath.substring(rootPath.length()+1)
		.replace('\\','.').replace('/','.');
	}
}
