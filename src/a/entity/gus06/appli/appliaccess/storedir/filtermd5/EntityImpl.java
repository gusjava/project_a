package a.entity.gus06.appli.appliaccess.storedir.filtermd5;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150318";}


	public static final String KEY_STOREDIR = "storedir";
	

	private Service getFile;
	private Service fileToMd5;
	
	
	public EntityImpl() throws Exception
	{
		getFile = Outside.service(this,"gus06.sys.option.getfile");
		fileToMd5 = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List md5List = (List) obj;
		
		File storeDir = (File) getFile.r(KEY_STOREDIR);
		if(storeDir==null) return null;
		
		if(!storeDir.isDirectory()) storeDir.mkdirs();
		
		List md5List1 = new ArrayList(md5List);
		if(storeDir.isDirectory()) analyzeDir(md5List1,storeDir);
		return md5List1;
	}
	
	
	
	private void analyzeDir(List md5List1, File dir) throws Exception
	{
		File[] ff = dir.listFiles();
		for(File f:ff)
		{
			if(f.isDirectory()) analyzeDir(md5List1,f);
			else if(isJarFile(f))
			{
				String md5 = (String) fileToMd5.t(f);
				if(md5List1.contains(md5))
				md5List1.remove(md5);
			}
		}
	}
	
	private boolean isJarFile(File f)
	{return f.isFile() && f.getName().toLowerCase().endsWith(".jar");}
}