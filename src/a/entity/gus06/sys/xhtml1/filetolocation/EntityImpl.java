package a.entity.gus06.sys.xhtml1.filetolocation;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220908";}


	private Service findRoot;

	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.xhtml1.webroot.find");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		File root = (File) findRoot.t(file);
		if(root==null) return null;
		
		int rootLen = root.getAbsolutePath().length();
		int fileLen = file.getAbsolutePath().length();
		
		String filePath = file.getAbsolutePath();
		return filePath.substring(rootLen+1, fileLen-6).replace(File.separator,".");
	}
}