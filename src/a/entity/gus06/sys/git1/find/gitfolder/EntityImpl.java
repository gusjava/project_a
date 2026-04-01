package a.entity.gus06.sys.git1.find.gitfolder;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T, F {

	public String creationDate() {return "20200115";}
	
	public static final String NAME = ".git";

	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null) return null;
		
		if(isGitFolder(file)) return file;
		
		File f = file;
		while(f!=null)
		{
			if(isGitFolder(f)) return f;
			
			File f1 = new File(f,NAME);
			if(f1.isDirectory()) return f1;
			
			f = f.getParentFile();
		}
		return null;
	}
	
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	
	
	private boolean isGitFolder(File f)
	{return f.isDirectory() && f.getName().equals(NAME);}
}